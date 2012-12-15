package jacs.gui;

import jacs.database.exceed.dao.CriteriaDAO;
import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.model.Criteria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class CriteriaManage extends JPanel{
	public static CriteriaDAO cri_dao = DaoFactory.getInstance().getCriteriaDAO();
	 JLabel name = new JLabel("Add Criteria "); 
    JTextField projectName = new JTextField(15);
    JButton add = new JButton("Add");
    JButton delete = new JButton("Delete");
    JLabel message = new JLabel();
    DefaultTableModel model = new DefaultTableModel();
    JTable table ;
    JScrollPane scrollPane;
	
	public CriteriaManage() {
       
	      
		table = new JTable(model);
		table.setEnabled(false);
		scrollPane = new JScrollPane(table);
       SpringLayout layout = new SpringLayout();
       model.addColumn("Criteria"); 
		List<Criteria> list = cri_dao.findAllCriteria();
		Iterator<Criteria> it = list.iterator();
		while(it.hasNext())	{
			Criteria criteria = it.next();
			//table.setValueAt(user.getUsername(), i, 0);
			model.addRow(new Object[]{criteria.getName()}); 
			
		}
       
       this.setLayout(layout);
       
       this.add(name);
       this.add(projectName);
       this.add(add);
       this.add(delete);
       this.add(message);
       this.add(scrollPane);
       ActionListener listener = new ButtonListener();
       add.addActionListener(listener);
       delete.addActionListener(listener);		
       
       layout.putConstraint(SpringLayout.WEST, name,
               100,
               SpringLayout.WEST, this);
       layout.putConstraint(SpringLayout.WEST, projectName,
               5,
               SpringLayout.EAST, name);
       layout.putConstraint(SpringLayout.NORTH, name,
               30,
               SpringLayout.NORTH, this);
       layout.putConstraint(SpringLayout.NORTH, projectName,
               30,
               SpringLayout.NORTH, this);

       /************************/
       layout.putConstraint(SpringLayout.WEST, add,
               100,
               SpringLayout.WEST, this);
       layout.putConstraint(SpringLayout.WEST, delete,
               5,
               SpringLayout.EAST, name);
       layout.putConstraint(SpringLayout.NORTH, add,
               90	,
               SpringLayout.NORTH, this);
       layout.putConstraint(SpringLayout.NORTH, delete,
               90,
               SpringLayout.NORTH, this);        
       /**********************/
       layout.putConstraint(SpringLayout.WEST, message,
               100,
               SpringLayout.WEST, this);
       layout.putConstraint(SpringLayout.NORTH, message,
               120	,
               SpringLayout.NORTH, this);
       /**********************/
       layout.putConstraint(SpringLayout.WEST, scrollPane,
               400,
               SpringLayout.WEST, this);
       layout.putConstraint(SpringLayout.NORTH, scrollPane,
               30	,
               SpringLayout.NORTH, this);
       

	}
class ButtonListener implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("Add")){
				String userkey = projectName.getText().toString();
			
				String mess = cri_dao.saveCriteria(userkey);
				message.setText(mess);
				projectName.setText("");
				
				if(mess.contains("ADD_CRITERIA_SUCCESSED"))
					model.addRow(new Object[]{userkey}); 
				
			}
			else if(event.getActionCommand().equals("Delete")){
				String userkey = projectName.getText().toString();
				
				String mess = cri_dao.deleteCriteria(userkey);
				message.setText(mess);
				projectName.setText("");
				int length = model.getRowCount();
				for(int i=0;i<length;i++){
					if(model.getValueAt(i, 0).toString().equals(userkey)){
						model.removeRow(i);
						break;
					}
				}
			}
			
		}
		
	}
}
