package jacs.gui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import jacs.server.Server;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 * This class MainGUI for eXceedVoteServer
 * @author Nookskill
 *
 */
public class Gui extends JFrame{
	public static Scanner sc = new Scanner(System.in);

	public Gui(){
		super("Admin Panel");
		init(); 	
	}
	private void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000,700));
		this.pack();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel1 = new UserManage();
		tabbedPane.addTab("User", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JComponent panel2 = new ProjectManage();
        tabbedPane.addTab("Project",panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = new CriteriaManage();
        tabbedPane.addTab("Criteria",panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = new Result();
        tabbedPane.addTab("Result",panel4);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	public void run() {

		this.setVisible(true);
		new Server();
	}
	


}
