package jacs.server;

import jacs.database.exceed.controller.DatabasesController;

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
	//private DBController dbcontroller
	private DatabasesController controller_db;
    public Client(Server myServer,Socket mySocket) throws IOException
    {
    	//new db
    		// create database controller
    		controller_db = new DatabasesController();
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
			sendData("Connected to Server");
        }
        catch(Exception e)
        {
            killClient("ERROR->Client() : "+e.toString());
        }
    }
    
    public void sendData(String str)
    {
    	try{
			out.writeObject(str);
			out.flush();
		}
		catch(IOException ioException){
			killClient("ERROR->SendData()");
		}
    }
    
    public void run()
    {
        try{
        	while(true){
				try{
					String message = (String)in.readObject();
					System.out.println("Message from ["+CLIENT_IP+"] ->" + message);
					//sendData(db.receiveMsg(message));
					sendData(controller_db.recieveMsg(message));
				}
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
				}
			}
        }
        catch(SocketException e)
        {
            myServer.showStatus("["+CLIENT_IP+"] Connection Reset.");
        }
        catch(Exception e)
        {
            killClient("ERROR->Client run() : "+e.toString());
        }
        catch(OutOfMemoryError ofm){
        	killClient("Client Disconnected.");
        }
        finally
        {
            killClient("Client Disconnected.");
        }
    }
    
    public void killClient(String str)
    {
        myServer.showStatus("["+CLIENT_IP+"] "+str);
        try
        {
            mySocket.close();
            in.close();
            out.close();
            myThread=null;
        }
        catch(Exception e)
        {
            myServer.showStatus("Client Error."+e);
        }
    }
    
}
