package invoicedatabase;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import au.com.bytecode.opencsv.CSVReader;

public class InvoiceDatabase {
	public Connection invoiceCreationDBConnection(){
		Connection sqlconnection=null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String mysqConnectionPath="jdbc:mysql://localhost/divya";
			sqlconnection =DriverManager.getConnection(mysqConnectionPath,"root","unoscm2010");  
		}catch (Exception e){
			e.printStackTrace();
		}
		return sqlconnection;
	}	
	
	public void saveclients(String number,String name,String addressline1,String addressline2,String city,String state,String zip,String email,String contact,String invoicefreq,String billingterms,String invoicegrouping) throws SQLException {
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="insert into client_data (number,name,addressline1,addressline2,city,state,zip,email,contact,invoicefreq,billingterms,invoicegrouping,inactive) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setInt(1,Integer.parseInt(number));
				sqlstatement.setString(2,name);
				sqlstatement.setString(3,addressline1);
				sqlstatement.setString(4,addressline2);
				sqlstatement.setString(5,city);
				sqlstatement.setString(6,state);
				sqlstatement.setString(7,zip);
				sqlstatement.setString(8,email);
				sqlstatement.setString(9,contact);
				sqlstatement.setString(10,invoicefreq);
				sqlstatement.setString(11,billingterms);
				sqlstatement.setString(12,invoicegrouping);
				sqlstatement.setString(13,"N");    		
				sqlstatement.addBatch();
				sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateclients(String originalnumber,String number,String name,String addressline1,String addressline2,String city,String state,String zip,String email,String contact,String invoicefreq,String billingterms,String invoicegrouping) throws SQLException {
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update client_data set number="+number+",name='"+name+"',addressline1='"+addressline1+"',addressline2='"+addressline2+"',city='"+city+"',state='"+state+"',zip="+zip+",email='"+email+"',contact='"+contact+"',invoicefreq='"+invoicefreq+"',billingterms='"+billingterms+"',invoicegrouping='"+invoicegrouping+"' where number="+originalnumber;
				sqlstatement=sqlconnection.createStatement();
				sqlstatement.executeUpdate(sql);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void inactiveclients(String number) throws SQLException {
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update client_data set inactive='Y' where number="+number;
				sqlstatement=sqlconnection.createStatement();
				sqlstatement.executeUpdate(sql);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void inactiveprojects(String number) throws SQLException {
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update project_data set inactive='Y' where projectnumber="+number;
				sqlstatement=sqlconnection.createStatement();
				sqlstatement.executeUpdate(sql);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void inactivepeopledata(String name) throws SQLException {
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update people_data set inactive='Y' where name='"+name+"'";
				sqlstatement=sqlconnection.createStatement();
				sqlstatement.executeUpdate(sql);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveprojects(String client,String projectnumber,String projectname,String startdate,String enddate,String status,String projectmanager,String clientcontact,String budget) throws SQLException {
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="insert into project_data (client,projectnumber,projectname,startdate,enddate,status,projectmanager,clientcontact,budget,inactive) values (?,?,?,?,?,?,?,?,?,?)";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setInt(1,Integer.parseInt(client));
				sqlstatement.setInt(2,Integer.parseInt(projectnumber));
				sqlstatement.setString(3,projectname);
				sqlstatement.setString(4,startdate);
				sqlstatement.setString(5,enddate);
				sqlstatement.setString(6,status);
				sqlstatement.setString(7,projectmanager);
				sqlstatement.setString(8,clientcontact);
				sqlstatement.setDouble(9,Integer.parseInt(budget));
				sqlstatement.setString(10,"N");    		
				sqlstatement.addBatch();
				sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateprojects(String originalnumber,String client,String projectnumber,String projectname,String startdate,String enddate,String status,String projectmanager,String clientcontact,String budget) throws SQLException {
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update project_data set client="+client+",projectnumber="+projectnumber+",projectname='"+projectname+"',startdate='"+startdate+"',enddate='"+enddate+"',status='"+status+"',projectmanager='"+projectmanager+"',clientcontact='"+clientcontact+"',budget="+budget+" where projectnumber="+originalnumber;
				System.out.println(sql);
				sqlstatement=sqlconnection.createStatement();
				sqlstatement.executeUpdate(sql);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void savepeopledata(String name,String title,String billrate,String role) throws SQLException{
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String password="";
				if(role.equalsIgnoreCase("Project Manager")){
					password="projectmanager";
				}else if(role.equalsIgnoreCase("Developer")){
					password="developer";
				}else if(role.equalsIgnoreCase("Accountant")){
					password="accountant";
				}
				String sql="insert into people_data (name,title,billrate,role,inactive,password) values (?,?,?,?,?,?)";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setString(1,name);
				sqlstatement.setString(2,title);
				sqlstatement.setString(4,role);
				sqlstatement.setInt(3,Integer.parseInt(billrate));
				sqlstatement.setString(5,"N"); 
				sqlstatement.setString(6,password);
				sqlstatement.addBatch();
				sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void updatepeopledata(String originalname,String name,String title,String billrate,String role) throws SQLException{
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update people_data set name='"+name+"',title='"+title+"',billrate="+billrate+",role='"+role+"' where name='"+originalname+"'";
				System.out.println(sql);
				sqlstatement=sqlconnection.createStatement();
				sqlstatement.executeUpdate(sql);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void savecompanydata(String name,String addressline1,String addressline2,String city,String state,String zip) throws SQLException{
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="insert into company_data (name,addressline1,addressline2,city,state,zip) values (?,?,?,?,?,?)";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setString(1,name);
				sqlstatement.setString(2,addressline1);
				sqlstatement.setString(3,addressline2);	    		
				sqlstatement.setString(4,city);
				sqlstatement.setString(5,state);
				sqlstatement.setInt(6,Integer.parseInt(zip));
				sqlstatement.addBatch();
	    		sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveassigndeveloperdata(String client,String projectnumber,String developername) throws SQLException{
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="insert into developer_assignto_project_data (client,projectnumber,developername) values (?,?,?)";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setInt(1,Integer.parseInt(client));
				sqlstatement.setInt(2,Integer.parseInt(projectnumber));
				sqlstatement.setString(3,developername);
				sqlstatement.addBatch();
	    		sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void savedeveloperhoursdata(String client,String projectnumber,String developername,String workhours,String startdate,String enddate,String billrate) throws SQLException{
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="insert into developer_hours_data (client,projectnumber,developername,workhours,startdate,enddate,billrate,hoursapprove) values (?,?,?,?,?,?,?,?)";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setInt(1,Integer.parseInt(client));
				sqlstatement.setInt(2,Integer.parseInt(projectnumber));
				sqlstatement.setString(3,developername);
				sqlstatement.setInt(4,Integer.parseInt(workhours));
				sqlstatement.setString(5,startdate);
				sqlstatement.setString(6,enddate);
				sqlstatement.setInt(7,Integer.parseInt(billrate));
				sqlstatement.setString(8,"PENDING");
				sqlstatement.addBatch();
	    		sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void savepassworddata(String type,String password) throws SQLException{
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update people_data set password=? where role=?";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setString(1,password);
				sqlstatement.setString(2,type);
				sqlstatement.addBatch();
	    		sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void updatedeveloperhoursdata(String client,String projectnumber,String developername,String date,String hours) throws SQLException{
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update developer_hours_data set workhours=?,hoursapprove=? where startdate=? and client=? and projectnumber=? and developername=?";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setInt(1,Integer.parseInt(hours));
				sqlstatement.setString(2,"APPROVED");
				sqlstatement.setString(3,date);
				sqlstatement.setString(4,client);
				sqlstatement.setString(5,projectnumber);
				sqlstatement.setString(6,developername);
				sqlstatement.addBatch();
	    		sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void updatecompanydata(String name,String addressline1,String addressline2,String city,String state,String zip) throws SQLException{
		Connection sqlconnection=null;
		PreparedStatement sqlstatement=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				String sql="update company_data set name=?,addressline1=?,addressline2=?,city=?,state=?,zip=?";
				sqlstatement=sqlconnection.prepareStatement(sql);
				sqlstatement.setString(1,name);
				sqlstatement.setString(2,addressline1);
				sqlstatement.setString(3,addressline2);	    		
				sqlstatement.setString(4,city);
				sqlstatement.setString(5,state);
				sqlstatement.setInt(6,Integer.parseInt(zip));
				sqlstatement.addBatch();
	    		sqlstatement.executeBatch();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList selectpeopledata(){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from people_data where inactive='N'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[4];
	    				people_data[0]=rs.getString("name");
	    				people_data[1]=rs.getString("title");
	    				people_data[2]=rs.getString("role");
	    				people_data[3]=""+rs.getInt("billrate");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public String[] getdeveloperdata(String name){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		String[] people_data=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from people_data where name='"+name+"' and inactive='N'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			while(rs.next()){
	    				people_data=new String[4];
	    				people_data[0]=rs.getString("name");
	    				people_data[1]=rs.getString("title");
	    				people_data[2]=rs.getString("role");
	    				people_data[3]=""+rs.getInt("billrate");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return people_data;
    }
	
	public ArrayList assigndeveloperdata(){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select dapd.projectnumber,pd.projectname,pd.projectmanager,dapd.developername from developer_assignto_project_data dapd,project_data pd where pd.client=dapd.client and pd.projectnumber=dapd.projectnumber";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[4];
	    				people_data[0]=""+rs.getInt("projectnumber");
	    				people_data[1]=rs.getString("projectname");
	    				people_data[2]=rs.getString("projectmanager");
	    				people_data[3]=rs.getString("developername");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList assigndevelopersdata(String developername){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from developer_assignto_project_data dapd where developername='"+developername+"'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[3];
	    				people_data[0]=""+rs.getInt("client");
	    				people_data[1]=rs.getString("projectnumber");
	    				people_data[2]=rs.getString("developername");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList assigndeveloperdata(String username){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select dapd.projectnumber,pd.projectname,pd.projectmanager,dapd.developername from developer_assignto_project_data dapd,project_data pd where pd.client=dapd.client and pd.projectnumber=dapd.projectnumber and pd.projectmanager='"+username+"'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[4];
	    				people_data[0]=""+rs.getInt("projectnumber");
	    				people_data[1]=rs.getString("projectname");
	    				people_data[2]=rs.getString("projectmanager");
	    				people_data[3]=rs.getString("developername");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList assigndeveloperprojectdata(String username){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select dapd.projectnumber,pd.projectname,pd.projectmanager,dapd.developername from developer_assignto_project_data dapd,project_data pd where pd.client=dapd.client and pd.projectnumber=dapd.projectnumber and dapd.developername='"+username+"'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[4];
	    				people_data[0]=""+rs.getInt("projectnumber");
	    				people_data[1]=rs.getString("projectname");
	    				people_data[2]=rs.getString("projectmanager");
	    				people_data[3]=rs.getString("developername");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList developerhoursdata(){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from developer_hours_data";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[8];
	    				people_data[0]=""+rs.getInt("client");
	    				people_data[1]=rs.getString("projectnumber");
	    				people_data[2]=rs.getString("developername");
	    				people_data[3]=rs.getString("workhours");
	    				people_data[4]=rs.getString("startdate");
	    				people_data[5]=rs.getString("enddate");
	    				people_data[6]=rs.getString("billrate");
	    				people_data[7]=rs.getString("hoursapprove");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList developerapprovedhoursdata(){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from developer_hours_data where hoursapprove='APPROVED'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[8];
	    				people_data[0]=""+rs.getInt("client");
	    				people_data[1]=rs.getString("projectnumber");
	    				people_data[2]=rs.getString("developername");
	    				people_data[3]=rs.getString("workhours");
	    				people_data[4]=rs.getString("startdate");
	    				people_data[5]=rs.getString("enddate");
	    				people_data[6]=rs.getString("billrate");
	    				people_data[7]=rs.getString("hoursapprove");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList developerhoursapprovedata(String name){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select dad.client,dad.projectnumber,dad.developername,dad.workhours,dad.startdate,dad.enddate,dad.billrate,dad.hoursapprove from developer_hours_data dad,project_data pd where pd.projectnumber=dad.projectnumber and pd.client=dad.client and pd.projectmanager='"+name+"' and pd.inactive='N' and dad.hoursapprove='PENDING'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[8];
	    				people_data[0]=""+rs.getInt("client");
	    				people_data[1]=rs.getString("projectnumber");
	    				people_data[2]=rs.getString("developername");
	    				people_data[3]=rs.getString("workhours");
	    				people_data[4]=rs.getString("startdate");
	    				people_data[5]=rs.getString("enddate");
	    				people_data[6]=rs.getString("billrate");
	    				people_data[7]=rs.getString("hoursapprove");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList developerhoursdata(String name){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from developer_hours_data where developername='"+name+"'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] people_data=new String[8];
	    				people_data[0]=""+rs.getInt("client");
	    				people_data[1]=rs.getString("projectnumber");
	    				people_data[2]=rs.getString("developername");
	    				people_data[3]=rs.getString("workhours");
	    				people_data[4]=rs.getString("startdate");
	    				people_data[5]=rs.getString("enddate");
	    				people_data[6]=rs.getString("billrate");
	    				people_data[7]=rs.getString("hoursapprove");
	    				peopledatalist.add(people_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList developerdata(){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList peopledatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select name from people_data where role='developer'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			peopledatalist=new ArrayList();
	    			while(rs.next()){
	    				peopledatalist.add(rs.getString("name"));
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return peopledatalist;
    }
	
	public ArrayList selectcompanydata(){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList companydatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from company_data";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			companydatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] company_data=new String[6];
	    				company_data[0]=rs.getString("name");
	    				company_data[1]=rs.getString("addressline1");
	    				company_data[2]=rs.getString("addressline2");
	    				company_data[3]=rs.getString("city");
	    				company_data[4]=rs.getString("state");
	    				company_data[5]=""+rs.getInt("zip");
	    				companydatalist.add(company_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return companydatalist;
    }
	
	public ArrayList selectprojectdata(){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList projectdatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from project_data where inactive='N'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			projectdatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] project_data=new String[9];
	    				project_data[0]=""+rs.getInt("client");
	    				project_data[1]=""+rs.getInt("projectnumber");
	    				project_data[2]=rs.getString("projectname");
	    				project_data[3]=rs.getString("startdate");
	    				project_data[4]=rs.getString("enddate");
	    				project_data[5]=rs.getString("status");
	    				project_data[6]=rs.getString("projectmanager");
	    				project_data[7]=rs.getString("clientcontact");
	    				project_data[8]=""+rs.getDouble("budget");
	    				projectdatalist.add(project_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return projectdatalist;
    }
	
	public ArrayList selectprojectdata(String projectmanager){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList projectdatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from project_data where projectmanager='"+projectmanager+"' and inactive='N'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			projectdatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] project_data=new String[9];
	    				project_data[0]=""+rs.getInt("client");
	    				project_data[1]=""+rs.getInt("projectnumber");
	    				project_data[2]=rs.getString("projectname");
	    				project_data[3]=rs.getString("startdate");
	    				project_data[4]=rs.getString("enddate");
	    				project_data[5]=rs.getString("status");
	    				project_data[6]=rs.getString("projectmanager");
	    				project_data[7]=rs.getString("clientcontact");
	    				project_data[8]=""+rs.getDouble("budget");
	    				projectdatalist.add(project_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return projectdatalist;
    }
	
	public ArrayList selectclients(){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		ArrayList clientdatalist=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from client_data where inactive='N'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			clientdatalist=new ArrayList();
	    			while(rs.next()){
	    				String[] client_data=new String[12];
	    				client_data[0]=""+rs.getInt("number");
	    				client_data[1]=rs.getString("name");
	    				client_data[2]=rs.getString("addressline1");
	    				client_data[3]=rs.getString("addressline2");
	    				client_data[4]=rs.getString("city");
	    				client_data[5]=rs.getString("state");
	    				client_data[6]=""+rs.getInt("zip");
	    				client_data[7]=rs.getString("email");
	    				client_data[8]=rs.getString("contact");
	    				client_data[9]=rs.getString("invoicefreq");
	    				client_data[10]=rs.getString("billingterms");
	    				client_data[11]=rs.getString("invoicegrouping");
	    				clientdatalist.add(client_data);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return clientdatalist;
    }
	
	public String[] userloginAuthentication(String loginid,String password){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		String[] people_data=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from people_data where name='"+loginid+"' and password='"+password+"'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			while(rs.next()){
	    				people_data=new String[4];
	    				people_data[0]=rs.getString("name");
	    				people_data[1]=rs.getString("title");
	    				people_data[2]=""+rs.getInt("billrate");
	    				people_data[3]=rs.getString("role");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return people_data;
    }
	
	public String[] projectmanagerproject(String name){
		Connection sqlconnection=null;
		Statement sqlstatement=null;
		String[] project_data=null;
		try{
			sqlconnection=invoiceCreationDBConnection();
			if(sqlconnection!=null){
				sqlstatement = sqlconnection.createStatement();
				String sql="select * from people_data where projectmanager='"+name+"'";
	            ResultSet rs = sqlstatement.executeQuery(sql);
	    		if(rs!=null){
	    			while(rs.next()){
	    				project_data=new String[9];
	    				project_data[0]=""+rs.getInt("client");
	    				project_data[1]=""+rs.getInt("projectnumber");
	    				project_data[2]=rs.getString("projectname");
	    				project_data[3]=rs.getString("startdate");
	    				project_data[4]=rs.getString("enddate");
	    				project_data[5]=rs.getString("status");
	    				project_data[6]=rs.getString("projectmanager");
	    				project_data[7]=rs.getString("clientcontact");
	    				project_data[8]=""+rs.getDouble("budget");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }
        return project_data;
    }
	
	public void importcompanydataintodatabase(){
		try{
			CSVReader csvreader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\invoiceimportdata\\company_data.csv"));
			List<String[]> listOfClients=csvreader.readAll();
			int indexpointer=0;
			for(int index=0;index<listOfClients.size();index++){
				String[] savecompanyt=(String[])listOfClients.get(index);
				if(indexpointer!=0){
					savecompanydata(savecompanyt[0],savecompanyt[1],savecompanyt[2],savecompanyt[3],savecompanyt[4],savecompanyt[5]);
				}
				indexpointer++;
			}								
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void importclientsdataintodatabase(){
		try{
			CSVReader csvreader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\invoiceimportdata\\client_data.csv"));
			List<String[]> listOfClients=csvreader.readAll();
			int indexpointer=0;
			for(int index=0;index<listOfClients.size();index++){
				String[] savepclient=(String[])listOfClients.get(index);
				if(indexpointer!=0){
					saveclients(savepclient[0],savepclient[1],savepclient[2],savepclient[3],savepclient[4],savepclient[5],savepclient[6],savepclient[7],savepclient[8],savepclient[9],savepclient[10],savepclient[11]);
				}
				indexpointer++;
			}								
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void importprojectsdataintodatabase(){
		try{
			CSVReader csvreader=new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\invoiceimportdata\\project_data.csv"));
			List<String[]> listOfProjects=csvreader.readAll();
			int indexpointer=0;
			for(int index=0;index<listOfProjects.size();index++){
				String[] saveproject=(String[])listOfProjects.get(index);
				if(indexpointer!=0){
					saveprojects(saveproject[0],saveproject[1],saveproject[2],saveproject[3],saveproject[4],saveproject[5],saveproject[6],saveproject[7],saveproject[8]);
				}
				indexpointer++;
			}								
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	public void importemployeesdataintodatabase(){
		try{
			CSVReader csvreader=new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\invoiceimportdata\\people_data.csv"));
			List<String[]> listOfEmployees=csvreader.readAll();
			int indexpointer=0;
			for(int index=0;index<listOfEmployees.size();index++){
				String[] savepeopledata=(String[])listOfEmployees.get(index);
				if(indexpointer!=0){
					savepeopledata(savepeopledata[0],savepeopledata[1],savepeopledata[2],savepeopledata[3]);
				}
				indexpointer++;
			}								
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
}
