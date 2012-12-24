package jacs;

import java.util.Scanner;

<<<<<<< HEAD
import org.apache.derby.iapi.db.Database;

=======
>>>>>>> 675cd1c08d2179cbfc4a01242f1933b255bd168e
import jacs.database.exceed.controller.DatabasesController;
import jacs.gui.Gui;
import jacs.server.Server;

/**
 * This class Main for Running Server for eXceedVote.
 * @author GoDParTicle
<<<<<<< HEAD
 * @author nookskill
=======
 * @author Nookskill
>>>>>>> guitest
 */

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
<<<<<<< HEAD

=======
		//DatabasesController data = new DatabasesController();
		//data.init();
>>>>>>> 675cd1c08d2179cbfc4a01242f1933b255bd168e
		Gui gui = new Gui();
		gui.run();
		
		
	}
}
