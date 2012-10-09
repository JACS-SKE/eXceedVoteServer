package com.jacs.server;

import java.io.*;
import java.net.*;

public class Client extends Thread
{
    Server myServer;
    Socket mySocket;
    Thread myThread;

    String CLIENT_IP;

    protected BufferedReader inMsg;
    protected PrintWriter outMsg;

    public Client(Server myServer,Socket mySocket) throws IOException
    {
        this.myServer = myServer;
        this.mySocket = mySocket;
        this.CLIENT_IP = mySocket.getInetAddress().getHostAddress();
        mySocket.setKeepAlive(true);
        mySocket.setTcpNoDelay(true);
        try
        {
            inMsg = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            outMsg = new PrintWriter(mySocket.getOutputStream(),true);
        }
        catch(Exception e)
        {
            KillClient("ERROR->Client() : "+e.toString());
        }
    }
    void SendData(String str)
    {
        outMsg.print(str+'\n');
        if(outMsg.checkError())
            KillClient("ERROR->SendData()");
    }
    public void run()
    {
        try{
            synchronized(this)
            {
                char charBuffer[] = new char[1];
                while(inMsg.read(charBuffer,0,1)!=-1)
                {
                    StringBuffer stringBuffer = new StringBuffer(1024);
                    while((byte)charBuffer[0]!=10)
                    {
                        stringBuffer.append(charBuffer[0]);
                        inMsg.read(charBuffer,0,1);
                    }
                    myServer.ShowStatus("Received From ["+CLIENT_IP+"]: "+stringBuffer.toString());
                    SendData("Server Received :"+stringBuffer.toString());
                }
            }
        }
        catch(SocketException e)
        {
            myServer.ShowStatus("["+CLIENT_IP+"] Connection Reset.");
        }
        catch(Exception e)
        {
            KillClient("ERROR->Client run() : "+e.toString());
        }
        finally
        {
            KillClient("Client Disconnected.");
        }
    }
    void KillClient(String str)
    {
        myServer.ShowStatus("["+CLIENT_IP+"] "+str);
        try
        {
            mySocket.close();
            inMsg.close();
            outMsg.close();
            myThread=null;
        }
        catch(Exception e)
        {
            myServer.ShowStatus("Client Error."+e);
        }
    }
}
