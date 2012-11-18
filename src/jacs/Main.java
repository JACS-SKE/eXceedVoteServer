package jacs;

import java.util.List;
import java.util.Scanner;

import jacs.database.exceed.dao.BallotDAO;
import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.dao.UserDAO;
import jacs.database.exceed.model.Ballot;
import jacs.database.exceed.model.User;
import jacs.server.Server;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static BallotDAO bdao;
	public static UserDAO dao;
	public static void main(String[] args) {
		new Server();
		//testUserDao();
		//testBallotDao();
	}
}
