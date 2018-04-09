/*
 * Row: a single row in the database.
 */
package invdb;

public class Row {
	private int rowNumber;
	private int columns;
	private String[] entries;
	private int entriesCounter;
	
	Row(){
		rowNumber=0;
		columns=1;
		entries=new String[columns];
		entriesCounter=0;
	}
	
	Row(int n,int w){
		rowNumber=n;
		columns=w;
		entries=new String[columns];
		entriesCounter=0;
	}
	
	public int getRowNumber(){
		return rowNumber;
	}
	
	public void setRowNumber(int n){
		rowNumber=n;
	}
	
	
	
	public void addEntry(String s){
		if(entriesCounter<columns){
			entries[entriesCounter]=s;
			entriesCounter++;
		}
	}
	
	public String[] getEntries(){
		return entries;
	}
	
	public int getWidth(){
		int width=0;
		
		for(int i=0;i<entries.length;i++){
			width+=entries[i].length();
		}
		return width;
	}
	
}
