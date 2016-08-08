package invoicecreating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Invoicedata{
	JFrame thisFrame;
	String rowIndexPointer="";
	String thisUserRole="";
	String thisUserType="";
	String thisUserId="";
	public Invoicedata(){
		
	}
	public void generateInvoice(final JFrame myFrame,final String userRole,final String userType,final String userID){
		new UserOptions(myFrame,userRole,userType,userID);		
		new UserOptions(myFrame,userRole,userType,userID);		
		thisUserRole=userRole;
		thisUserType=userType;
		thisUserId=userID;
		
		int length=150;
		myFrame.setTitle(String.format("%1$"+length+ "s", "Generate Invoice"));
		
		JLabel titleLabel = new JLabel("GENERATE INVOCIE");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLabel.setBounds(250, 100, 350, 40);
		myFrame.add(titleLabel);
		
		JButton openButton = new JButton("Open");
		openButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		openButton.setBounds(260, 250, 200, 30);
		myFrame.add(openButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		exitButton.setBounds(500, 250, 200, 30);
		myFrame.add(exitButton);
		
		thisFrame=myFrame;
		myFrame.getContentPane().repaint();
			
	}
	
	public void displayHours(final JFrame myFrame,final String userRole,final String userType,final String userID){
		new UserOptions(myFrame,userRole,userType,userID);		
		new UserOptions(myFrame,userRole,userType,userID);		
		thisUserRole=userRole;
		thisUserType=userType;
		thisUserId=userID;
		
		int length=150;
		myFrame.setTitle(String.format("%1$"+length+ "s", "Display Hours Data"));
		
		JLabel titleLabel = new JLabel("DISPLAY HOURS");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLabel.setBounds(250, 100, 350, 40);
		myFrame.add(titleLabel);
		
		ArrayList listOfHours=new ArrayList();
		int columnsCount=7;
		String hourColumnNames[] = {"Project Number", "Project Name", "Employee", "Start Date", "End Date", "Hours","Approval"};
		String hoursColumnDataValues[][]=new String[0][columnsCount];
		if(listOfHours!=null && listOfHours.size()!=0){			
			Iterator iterator=listOfHours.iterator();
			int arrayIndex=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				hoursColumnDataValues[arrayIndex][0]=tokens[0];
				hoursColumnDataValues[arrayIndex][1]=tokens[1];
				hoursColumnDataValues[arrayIndex][2]=tokens[2];
				hoursColumnDataValues[arrayIndex][3]=tokens[3];
				hoursColumnDataValues[arrayIndex][4]=tokens[4];
				hoursColumnDataValues[arrayIndex][5]=tokens[5];
				hoursColumnDataValues[arrayIndex][6]=tokens[6];
				arrayIndex=arrayIndex+1;
			}			
		}
		
		final JTable facultyTable = new JTable(hoursColumnDataValues, hourColumnNames);
	    ListSelectionModel tableRowSelection = facultyTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		rowIndexPointer=""+facultyTable.getSelectedRow();
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(facultyTable);
		tableScrollPane.setBounds(100, 200, 475, 250);
		myFrame.add(tableScrollPane, BorderLayout.CENTER);
		
		thisFrame=myFrame;
		myFrame.getContentPane().repaint();
	}
}
