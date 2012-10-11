package com.jacs.server;

import java.io.*;
import java.net.*;

public class Client extends Thread
{
    Server myServer;
    Socket mySocket;
    Thread myThread;

    String CLIENT_IP;

    private ObjectOutputStream out;
	private ObjectInputStream in;

    public Client(Server myServer,Socket mySocket) throws IOException
    {
        this.myServer = myServer;
        this.mySocket = mySocket;
        this.CLIENT_IP = mySocket.getInetAddress().getHostAddress();
        mySocket.setKeepAlive(true);
        mySocket.setTcpNoDelay(true);
        try
        {
        	out = new ObjectOutputStream(mySocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(mySocket.getInputStream());
			SendData("Connected to Server");
        }
        catch(Exception e)
        {
            KillClient("ERROR->Client() : "+e.toString());
        }
    }
    
    public void SendData(String str)
    {
    	try{
			out.writeObject(str);
			out.flush();
		}
		catch(IOException ioException){
			KillClient("ERROR->SendData()");
		}
    }
    
    public void run()
    {
        try{
        	while(true){
				try{
					String message = (String)in.readObject();
					System.out.println("Message from ["+CLIENT_IP+"] ->" + message);
				}
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
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
        catch(OutOfMemoryError ofm){
        	KillClient("Client Disconnected.");
        }
        finally
        {
            KillClient("Client Disconnected.");
        }
    }
    
    public void KillClient(String str)
    {
        myServer.ShowStatus("["+CLIENT_IP+"] "+str);
        try
        {
            mySocket.close();
            in.close();
            out.close();
            myThread=null;
        }
        catch(Exception e)
        {
            myServer.ShowStatus("Client Error."+e);
        }
    }
}
