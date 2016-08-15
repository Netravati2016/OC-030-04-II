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

public class AssignEmployee{
	JTextField projectNumber,projectName,projectManager,developerName;
	JFrame thisFrame;
	String rowIndexPointer="";
	String thisUserRole="";
	String thisUserType="";
	String thisUserId="";
	public AssignEmployee(final JFrame myFrame,final String userRole,final String userType,final String userID){
		new UserOptions(myFrame,userRole,userType,userID);		
		thisUserRole=userRole;
		thisUserType=userType;
		thisUserId=userID;
		
		int length=150;
		myFrame.setTitle(String.format("%1$"+length+ "s", "Assign Developers to Projects"));
		
		JLabel titleLabel = new JLabel("ASSIGN DEVELOPER TO PROJECTS");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLabel.setBounds(250, 100, 350, 40);
		myFrame.add(titleLabel);
		
		ArrayList listOfProjects=new ArrayList();
		int totalColumns=4;
		String assignprojectColumnNames[] = {"Project Number", "Project Name", "Project Manager", "Developer Name"};
		String outdata[][]=new String[0][totalColumns];
		if(listOfProjects!=null && listOfProjects.size()!=0){			
			Iterator iterator=listOfProjects.iterator();
			int arrayIndex=0;
			while(iterator.hasNext()){
				String[] assignprojectTokens=(String[])iterator.next();
				outdata[arrayIndex][0]=assignprojectTokens[0];
				outdata[arrayIndex][1]=assignprojectTokens[1];
				outdata[arrayIndex][2]=assignprojectTokens[2];
				outdata[arrayIndex][3]=assignprojectTokens[3];
				arrayIndex=arrayIndex+1;
			}			
		}
		
		final JTable assignprojectTable = new JTable(outdata, assignprojectColumnNames);
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
		
		JButton addProjectButton = new JButton("Assign");
		addProjectButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		addProjectButton.setBounds(50, 600, 200, 30);		
		myFrame.add(addProjectButton);
		
		JButton updateProjectButton = new JButton("Update");
		updateProjectButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		updateProjectButton.setBounds(260, 600, 200, 30);
		myFrame.add(updateProjectButton);
		
		titleLabel = new JLabel("Project Number");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		titleLabel.setBounds(100, 150, 100, 25);
		myFrame.add(titleLabel);
		
		String projectNumberLabels[] = {"1001","1002","1003","1004"};
	    final JComboBox projectNumberComboBox = new JComboBox(projectNumberLabels);	    
	    projectNumberComboBox.setMaximumRowCount(7);
	    projectNumberComboBox.setFont(new Font("Times New Roman", Font.ITALIC, 16));
	    projectNumberComboBox.setSelectedIndex(0);
	    projectNumberComboBox.setBounds(250, 148, 250, 25);
	    myFrame.add(projectNumberComboBox);
		
		titleLabel = new JLabel("Project Name");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		titleLabel.setBounds(100, 180, 100, 25);
		myFrame.add(titleLabel);
		
		String projectNameLabels[] = {"Project Name","Project Name"};
	    final JComboBox projectNameComboBox = new JComboBox(projectNameLabels);	    
	    projectNameComboBox.setMaximumRowCount(7);
	    projectNameComboBox.setFont(new Font("Times New Roman", Font.ITALIC, 16));
	    projectNameComboBox.setSelectedIndex(0);
	    projectNameComboBox.setBounds(250, 178, 250, 25);
	    myFrame.add(projectNameComboBox);
		
		titleLabel = new JLabel("Project Manager");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		titleLabel.setBounds(100, 210, 125, 25);
		myFrame.add(titleLabel);
		
		String projectManagerLabels[] = {"Project Manager","Project Manager"};
	    final JComboBox projectManagerComboBox = new JComboBox(projectManagerLabels);	    
	    projectManagerComboBox.setMaximumRowCount(7);
	    projectManagerComboBox.setFont(new Font("Times New Roman", Font.ITALIC, 16));
	    projectManagerComboBox.setSelectedIndex(0);
	    projectManagerComboBox.setBounds(250, 208, 250, 25);
	    myFrame.add(projectManagerComboBox);

		titleLabel = new JLabel("Developer Name");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		titleLabel.setBounds(100, 240, 100, 25);
		myFrame.add(titleLabel);
		
		String developerNameLabels[] = {"Developer Name","Developer Name"};
	    final JComboBox developerNameComboBox = new JComboBox(developerNameLabels);	    
	    developerNameComboBox.setMaximumRowCount(7);
	    developerNameComboBox.setFont(new Font("Times New Roman", Font.ITALIC, 16));
	    developerNameComboBox.setSelectedIndex(0);
	    developerNameComboBox.setBounds(250, 238, 250, 25);
	    myFrame.add(developerNameComboBox);

		addProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		updateProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		thisFrame=myFrame;
		myFrame.getContentPane().repaint();
    }
}
