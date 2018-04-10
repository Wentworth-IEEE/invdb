package invdb;

import java.sql.*;
import java.util.Date;
public class Query {
    public static String jdbcDriver="com.mysql.jdbc.Driver";
    public static String dbURL="jdbc:mysql://localhost:3306/ieee?autoReconnect=true&useSSL=false";
    public static String user="root";
    public static String pass="Comp2650!";
    public static Connection conn=null;
	
	
	public static int loanItem(String name, String email){
		//updates loanedSince date and email
		initializeConnection();
		try{
			Statement st=conn.createStatement();
			ResultSet avail=st.executeQuery("SELECT * FROM LoanableItem WHERE LoanableItemType=(SELECT LoanableItemType FROM LoanableItemType WHERE LoanableItemTypeName='"+name+"')");
			int available=0;
			while(avail.next()){
				String emailLoanedTo=avail.getString("EmailLoanedTo");
				if(emailLoanedTo.equals("-"))
					available++;
			}
			//System.out.println(available);
			avail.close();
			if(available==0)
				return 0;
			
			st.executeUpdate("UPDATE LoanableItem SET EmailLoanedTo='"+email+"',LoanedSince=CURDATE() WHERE LoanedSince='1970-01-01' AND LoanableItemType=(SELECT LoanableItemType FROM LoanableItemType WHERE LoanableItemTypeName='"+name+"') LIMIT 1");
	
			return 1;
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return -1;
	}
	
	public static int returnItem(String name,String email){
		//returns 1 if successfully returned, 0 if not (trying to return too many items, etc)
		initializeConnection();
		try{
			Statement st=conn.createStatement();
			st.executeUpdate("UPDATE LoanableItem SET EmailLoanedTo='-', LoanedSince='1970-01-01' WHERE EmailLoanedTo='"+email+"' AND LoanableItemType=(SELECT LoanableItemType FROM LoanableItemType WHERE LoanableItemTypeName='"+name+"') LIMIT 1");
			return 1;
			
		}
		catch(SQLException se){
			se.printStackTrace();
			
		}
		
		return 0;
	}
	
	public static void initializeConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL,user,pass);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
