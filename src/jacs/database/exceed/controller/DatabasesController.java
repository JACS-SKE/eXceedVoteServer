package jacs.database.exceed.controller;


public class DatabasesController {

	
	public DatabasesController() {
		super();
	}
	
	public String recieveMsg(String msg)	{
		//pattern of msg : REGIS/LOGIN,username,password
		String[] temp_msg = msg.split(",");
		if (temp_msg.length == 3) {
			String type = temp_msg[0];
			String username = temp_msg[1];
			String password = temp_msg[2];
			if (type.toUpperCase().equals("REGIS")) {
				
			} else if (type.toUpperCase().equals("LOGIN")) {
			
			}
		}else {
			msg = "error";
		}
		return msg;
	}
	public String registration(String username, String password)	{
		return "";
	}
	public String authentication(String usernam,String password)	{
		return "";
	}
}
