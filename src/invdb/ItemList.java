/*
 * This ItemList object reads in pieces of the database and creates an easy to use wrapper.
 * 
 * (c) 2018 Christopher Thierauf. This code is Free software (GPLv3+); you are free to use, modify,
 * view source, and distribute as you wish under the terms in the license included.
 */
package invdb;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.*;

public class ItemList {
    public int numberOfLoanables=0;
    public final String jdbcDriver="com.mysql.jdbc.Driver";
    public final String dbURL="jdbc:mysql://localhost:3306/ieee?autoReconnect=true&useSSL=false";
    public final String user="root";
    public final String pass="Comp2650!";
    public Connection conn=null;
    public ArrayList<Row> rows;
    
    
	ItemList() {
		rows=new ArrayList<Row>();
		initializeConnection();
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
	
	
	
	public void createItemList(int type){
		try{
			int rowNumber=1;
			switch(type){
				case 0:
					Statement loanSt=conn.createStatement();
					ResultSet loan=loanSt.executeQuery("SELECT * FROM LoanableItemType");
					Row header=new Row(0,3);
					header.addEntry("#");
					header.addEntry("Item Name");
					header.addEntry("Availability");
					rows.add(header);
					
					while(loan.next()){
						String name=loan.getString("LoanableItemTypeName");
						
						int available=0;
						Statement tempSt=conn.createStatement();
						ResultSet avail=tempSt.executeQuery("SELECT * FROM LoanableItem WHERE LoanableItemType=(SELECT LoanableItemType FROM LoanableItemType WHERE LoanableItemTypeName='"+name+"')");
						while(avail.next()){
							Date LoanedSince=avail.getDate("LoanedSince");
							Date temp=new Date(0);
							if(!LoanedSince.equals(temp))
								available++;
						}
						tempSt.close();	
						
						Row r=new Row(rowNumber,3);
						r.addEntry(Integer.toString(rowNumber));
						r.addEntry(name);
						r.addEntry(Integer.toString(available)+" available");
						rows.add(r);
						
						rowNumber++;
					}
					numberOfLoanables=rowNumber-1;
					loan.close();
					
					Statement consumSt=conn.createStatement();
					ResultSet consum=consumSt.executeQuery("SELECT * FROM ConsumableItem");
					while(consum.next()){
						String name=consum.getString("ItemName");
						int inStock=consum.getInt("InStock");
						
						Row r=new Row(rowNumber,3);
						r.addEntry(Integer.toString(rowNumber));
						r.addEntry(name);
						if(inStock==1)
							r.addEntry("Available");
						else
							r.addEntry("None available");
						rows.add(r);
						
						rowNumber++;
					}
					consum.close();	
					padStrings();
					break;// end of case 0
					
				case 1:
					//case for showing current loans on loanable items
					
					Statement st=conn.createStatement();
					ResultSet rs=st.executeQuery("SELECT * FROM LoanableItem WHERE LoanedSince!='1970-01-01'");
					Row header1=new Row(0,4);
					header1.addEntry("#");
					header1.addEntry("Item Name");
					header1.addEntry("Email Address");
					header1.addEntry("Loaned Since");
					rows.add(header1);
					
					while(rs.next()){
						String name=rs.getString("ItemName");
						String email=rs.getString("EmailLoanedTo");
						Date loanedSince=rs.getDate("LoanedSince");
						DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
						
						Row r=new Row(rowNumber,4);
						r.addEntry(Integer.toString(rowNumber));
						r.addEntry(name);
						r.addEntry(email);
						r.addEntry(df.format(loanedSince));
						rows.add(r);
						
						
						rowNumber++;
					}
					numberOfLoanables=rowNumber-1;
					rs.close();
					padStrings();
					break; //end of case 1
			}
			System.out.println();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	
	
	public Row getHeader() {
		return rows.get(0);
	}
	
	public int getNumberOfRows() {
		//size of rows, not counting the header
		return rows.size();
	}

	public Row getRow(int i) {
		return rows.get(i);
	}
	
	public int getMaxLength(){
		int maxLength=0;
		
		for(int i=1;i<rows.size();i++){
			String[] s=rows.get(i).getEntries();
			for(int j=0;j<s.length;j++){
				if(s[j].length()>maxLength)
					maxLength=s[j].length();
			}
		}
		
		return maxLength;
	}
	
	public int getNumberOfLoanables(){
		return numberOfLoanables;
	}
	
	public void padStrings(){
		int maxLength=getMaxLength();
		
		for(int i=0;i<rows.size();i++){
			String[] s=rows.get(i).getEntries();
			for(int j=1;j<s.length;j++){
				int sLength=s[j].length();
				s[j]= String.format("%1$-"+maxLength+"s", s[j]);
			}
		}
	}
}