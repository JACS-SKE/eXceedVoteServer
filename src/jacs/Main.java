package jacs;

import java.util.Scanner;

import jacs.server.Server;

/**
 * This class Main for Running Server for eXceedVote.
 * @author GoDParTicle
 *
 */

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		new Server();
	}
}
