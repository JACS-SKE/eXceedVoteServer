package com.jacs.server;

import java.util.*;
import java.net.*;

public class Server
{
    ServerSocket myServer;
    Client myClient;

    public Server()
	{
		try
		{
			ShowStatus("Starting Server.");
			myServer = new ServerSocket(9999);
			while(true)
			{
				Socket mySocket = myServer.accept();
				myClient = new Client(this,mySocket);
				ShowStatus("["+myClient.CLIENT_IP+"] Client Connected.");
				myClient.start();
			}
		}
		catch(Exception e)
		{
			KillServer("ERROR->Server() : "+e.toString());
		}
		finally
		{
			KillServer("ERROR->Server Error.");
		}
    }
    void ShowStatus(String str)
    {
        try
        {
            str = "->"+str;
            System.out.println(str);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    void KillServer(String str)
    {
        try
        {
            myServer.close();
            ShowStatus("Server Stoped.");
            ShowStatus(str);
        }
        catch(Exception e)
        {
            ShowStatus("Server Error.");
        }
    }
}