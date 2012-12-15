package jacs.gui;

import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.dao.Project_eXceedDAO;
import jacs.database.exceed.model.Project_eXceed;
import jacs.gui.CriteriaManage.ButtonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class Result extends JPanel{
	public static Project_eXceedDAO p_dao = DaoFactory.getInstance().getProject_eXceedDAO();
   JButton refresh = new JButton("Refresh");
   DefaultTableModel model = new DefaultTableModel();
   JTable table ;
   JScrollPane scrollPane;
	
	public Result() {
      
	      
		table = new JTable(model);
		table.setEnabled(false);
		scrollPane = new JScrollPane(table);
		
      SpringLayout layout = new SpringLayout();
      model.addColumn("Projects");
      model.addColumn("Ballot");
      model.addColumn("Score");
		java.util.List<Project_eXceed> list = p_dao.findAllProjects();
		Iterator<Project_eXceed> it = list.iterator();
		while(it.hasNext())	{
			Project_eXceed project = it.next();
			model.addRow(new Object[]{project.getProject_Name(),project.getBallots(),project.getScore()}); 
			
		}
      
      this.setLayout(layout);
      
      this.add(refresh);
      this.add(scrollPane);
      ActionListener listener = new ButtonListener();
      refresh.addActionListener(listener);	
      
      
      layout.putConstraint(SpringLayout.WEST, refresh,
              100,
              SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, refresh,
              90	,
              SpringLayout.NORTH, this);
      /************************************/
      layout.putConstraint(SpringLayout.WEST, scrollPane,
              300,
              SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, scrollPane,
              30	,
              SpringLayout.NORTH, this);
      

	}
	class ButtonListener implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("Refresh")){
				int row = model.getRowCount();
				for(int i=0;i<row;row--){
						model.removeRow(i);	
				}
				java.util.List<Project_eXceed> list = p_dao.findAllProjects();
				Iterator<Project_eXceed> it = list.iterator();
				while(it.hasNext())	{
					Project_eXceed project = it.next();
					model.addRow(new Object[]{project.getProject_Name(),project.getBallots(),project.getScore()}); 
					
				}
			}
			
		}
	}
}
