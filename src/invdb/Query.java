package invdb;

import java.sql.*;
import java.util.Date;
public class Query {
    public final String jdbcDriver="com.mysql.jdbc.Driver";
    public final String dbURL="jdbc:mysql://localhost:3306/ieee?autoReconnect=true&useSSL=false";
    public final String user="root";
    public final String pass="Comp2650!";
    public Connection conn=null;
	
	
	
	Query(){
		
	}
	
	public int loanItem(String name){
		
		initializeConnection();
		
		try{
			Statement st=conn.createStatement();
			ResultSet avail=st.executeQuery("SELECT * FROM LoanableItem WHERE LoanableItemType=(SELECT LoanableItemType "
					+ "FROM LoanableItem WHERE LoanableItemType=(SELECT LoanableItemType FROM LoanableItemType WHERE LoanableItemTypeName='"+name+"')");
			int available=0;
			while(avail.next()){
				Date LoanedSince=avail.getDate("LoanedSince");
				Date temp=new Date(0);
				if(!LoanedSince.equals(temp))
					available++;
			}
			System.out.println(available+" available");
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return -1;
	}
	
	public void initializeConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL,user,pass);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
