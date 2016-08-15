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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Reportsdata{
	JFrame thisFrame;
	String rowIndexPointer="";
	String thisUserRole="";
	String thisUserType="";
	String thisUserId="";
	public Reportsdata(){
		
	}
	public void budgetReport(final JFrame myFrame,final String userRole,final String userType,final String userID){
		new UserOptions(myFrame,userRole,userType,userID);		
		thisUserRole=userRole;
		thisUserType=userType;
		thisUserId=userID;
		
		int length=150;
		myFrame.setTitle(String.format("%1$"+length+ "s", "Budget Report"));
		
		JLabel titleLabel = new JLabel("BUDGET REPORT");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLabel.setBounds(250, 100, 350, 40);
		myFrame.add(titleLabel);
		
		ArrayList budgetList=new ArrayList();
		int totalColumns=5;
		String budgetColumnNames[] = {"Project Number", "Budget", "Spent%", "Left%", "Time Left Days"};
		String output[][]=new String[0][totalColumns];
		if(budgetList!=null && budgetList.size()!=0){			
			Iterator iterator=budgetList.iterator();
			int arrayIndex=0;
			while(iterator.hasNext()){
				String[] budgettokens=(String[])iterator.next();
				output[arrayIndex][0]=budgettokens[0];
				output[arrayIndex][1]=budgettokens[1];
				output[arrayIndex][2]=budgettokens[2];
				output[arrayIndex][3]=budgettokens[3];
				output[arrayIndex][4]=budgettokens[4];
				arrayIndex=arrayIndex+1;
			}			
		}
		
		final JTable budgetTable = new JTable(output, budgetColumnNames);
	    ListSelectionModel rowSelection = budgetTable.getSelectionModel();
	    rowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    rowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		rowIndexPointer=""+budgetTable.getSelectedRow();
	    	}
	    });	    
		JScrollPane scrollPane = new JScrollPane(budgetTable);
		scrollPane.setBounds(100, 250, 475, 250);
		myFrame.add(scrollPane, BorderLayout.CENTER);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		exitButton.setBounds(250, 600, 200, 30);
		myFrame.add(exitButton);	
		
		JLabel titleLabel1 = new JLabel("Type");
		titleLabel1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		titleLabel1.setBounds(100, 150, 100, 25);
		myFrame.add(titleLabel1);
		
		String projectNumberLabels[] = {"All","All"};
	    final JComboBox projectNumberComboBox = new JComboBox(projectNumberLabels);	    
	    projectNumberComboBox.setMaximumRowCount(7);
	    projectNumberComboBox.setFont(new Font("Times New Roman", Font.ITALIC, 16));
	    projectNumberComboBox.setSelectedIndex(0);
	    projectNumberComboBox.setBounds(250, 148, 250, 25);
	    myFrame.add(projectNumberComboBox);
		
	    JButton generateButton = new JButton("Generate Report");
	    generateButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
	    generateButton.setBounds(150, 200, 150, 25);
		myFrame.add(generateButton);
	    
		thisFrame=myFrame;
		myFrame.getContentPane().repaint();
	}
	
	public void hoursReport(final JFrame myFrame,final String userRole,final String userType,final String userID){
		new UserOptions(myFrame,userRole,userType,userID);		
		new UserOptions(myFrame,userRole,userType,userID);		
		thisUserRole=userRole;
		thisUserType=userType;
		thisUserId=userID;
		
		int length=150;
		myFrame.setTitle(String.format("%1$"+length+ "s", "Hours Report"));
		
		JLabel titleLabel = new JLabel("HOURS REPORT");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLabel.setBounds(250, 100, 350, 40);
		myFrame.add(titleLabel);
		
		ArrayList listOfEmployeeHours=new ArrayList();
		int totalColumns=6;
		String hourColumnNames[] = {"Project Number", "Project Name", "Developer Name", "Worked Hours", "Bill Rate", "Amount"};
		String output[][]=new String[0][totalColumns];
		if(listOfEmployeeHours!=null && listOfEmployeeHours.size()!=0){			
			Iterator iterator=listOfEmployeeHours.iterator();
			int arrayIndex=0;
			while(iterator.hasNext()){
				String[] hourtokens=(String[])iterator.next();
				output[arrayIndex][0]=hourtokens[0];
				output[arrayIndex][1]=hourtokens[1];
				output[arrayIndex][2]=hourtokens[2];
				output[arrayIndex][3]=hourtokens[3];
				output[arrayIndex][4]=hourtokens[4];
				output[arrayIndex][5]=hourtokens[5];
				arrayIndex=arrayIndex+1;
			}			
		}
		
		final JTable budgetTable = new JTable(output, hourColumnNames);
	    ListSelectionModel rowSelection = budgetTable.getSelectionModel();
	    rowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    rowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		rowIndexPointer=""+budgetTable.getSelectedRow();
	    	}
	    });	    
		JScrollPane scrollPane = new JScrollPane(budgetTable);
		scrollPane.setBounds(100, 200, 475, 250);
		myFrame.add(scrollPane, BorderLayout.CENTER);
		
		thisFrame=myFrame;
		myFrame.getContentPane().repaint();
	}
}
