package invoicecreating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EnterHours {
	JTextField date,hours;
	JFrame thisFrame;
	String rowIndexPointer="";
	String thisUserRole="";
	String thisUserType="";
	String thisUserId="";
	public EnterHours(final JFrame myFrame,final String userRole,final String userType,final String userID){
		new UserOptions(myFrame,userRole,userType,userID);		
		thisUserRole=userRole;
		thisUserType=userType;
		thisUserId=userID;
		
		int length=150;
		myFrame.setTitle(String.format("%1$"+length+ "s", "Enter Hours Data"));
		
		JLabel titleLabel = new JLabel("ENTER HOURS");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLabel.setBounds(250, 100, 350, 40);
		myFrame.add(titleLabel);
		
		ArrayList listOfProjects=new ArrayList();
		int totalColumns=5;
		String enterhoursColumnNames[] = {"Project Number", "Project Name", "Developer Name", "Date", "Worked Hours"};
		String outdata[][]=new String[0][totalColumns];
		if(listOfProjects!=null && listOfProjects.size()!=0){			
			Iterator iterator=listOfProjects.iterator();
			int arrayIndex=0;
			while(iterator.hasNext()){
				String[] enterhoursTokens=(String[])iterator.next();
				outdata[arrayIndex][0]=enterhoursTokens[0];
				outdata[arrayIndex][1]=enterhoursTokens[1];
				outdata[arrayIndex][2]=enterhoursTokens[2];
				outdata[arrayIndex][3]=enterhoursTokens[3];
				outdata[arrayIndex][4]=enterhoursTokens[4];
				arrayIndex=arrayIndex+1;
			}			
		}
		
		final JTable assignprojectTable = new JTable(outdata, enterhoursColumnNames);
	    ListSelectionModel rowSelection = assignprojectTable.getSelectionModel();
	    rowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    rowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		rowIndexPointer=""+assignprojectTable.getSelectedRow();
	    	}
	    });	    
		JScrollPane scrollPane = new JScrollPane(assignprojectTable);
		scrollPane.setBounds(600, 200, 475, 250);
		myFrame.add(scrollPane, BorderLayout.CENTER);
		
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		saveButton.setBounds(50, 600, 200, 30);		
		myFrame.add(saveButton);
		
		JButton updateButton = new JButton("Update");
		updateButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		updateButton.setBounds(260, 600, 200, 30);
		myFrame.add(updateButton);
		
		titleLabel = new JLabel("Date");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		titleLabel.setBounds(100, 150, 100, 25);
		myFrame.add(titleLabel);
		
		date = new JTextField();
		date.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		date.setBounds(250, 148, 250, 25);
		myFrame.add(date);
		
		titleLabel = new JLabel("Hours");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		titleLabel.setBounds(100, 180, 100, 25);
		myFrame.add(titleLabel);
		
		hours = new JTextField();
		hours.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		hours.setBounds(250, 178, 250, 25);
		myFrame.add(hours);  

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		thisFrame=myFrame;
		myFrame.getContentPane().repaint();
    }
}
