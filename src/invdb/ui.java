/*
 * ui.java provides a simple, text-based UI for the invdb system.
 *
 * (c) 2018 Christopher Thierauf. This code is Free software (GPLv3+); you are free to use, modify,
 * view source, and distribute as you wish under the terms in the license included.
 */
package invdb;
import java.io.*;
import java.util.Scanner;

public class ui {
    public static final boolean powerOn = true;
    static Scanner input=new Scanner(System.in);

	public static void printTable(ItemList t) {
        printHeader(t);
        printEntries(t);
    }

    public static void printHeader(ItemList t) {
        printSingleEntry(t.getHeader());
    }


    public static void printEntries(ItemList t) {
        for(int i = 0; i < t.getNumberOfRows(); i++)
            printSingleEntry(i, t);
    }

    public static void printSingleEntry(int i, ItemList t) {
        printSingleEntry(t.getRow(i));
    }

    public static void printSingleEntry(Row r) {
        String[] s=r.getEntries();
    	
    	
        for(int i=0;i<s.length;i++){
        	System.out.print(s[i]);
        	if(i<s.length-1)
        		System.out.print(" | ");
        }
        System.out.print("\n");
        
        if(r.getRowNumber()==0){
        	int width=r.getWidth();
        	while(width>0){
        		System.out.print("=");
        		width--;
        	}
        	System.out.print("\n");
        }
    }

	public static void welcome() {
		System.out.println("Hello, welcome to the IEEE Database Client!");
		
	}

	public static void showPrivMenu() {
		ItemList allItems=new ItemList();
		allItems.createItemList(1);
		printEntries(allItems);
	}

	public static void showMenu(){
		ItemList allItems=new ItemList();
		allItems.createItemList(0);
		printEntries(allItems);
		System.out.println("\nSelect an item:");
		System.out.print(">");
		int selection=input.nextInt();
		promptRegularUser(allItems,selection);
		
	}

	public static void goodbye() {
		input.close();
		System.out.println("Have a nice day!");
		System.exit(0);
		
	}
	
	public static void promptRegularUser(ItemList itemList,int selection){
		Row r=itemList.getRow(selection);
		
		System.out.println("Do you want to (l)oan this item, (r)eturn this item, or go (b)ack?");
		System.out.print(">");
		Scanner input=new Scanner(System.in);
		String in=input.next().toLowerCase();
		char c=in.charAt(0);
		boolean acceptable=false;
		if(c=='l' || c=='r' || c=='b' || c=='a')
			acceptable=true;
		while(!acceptable){
			System.out.println("Sorry, didn't catch that. Do you want to (l)oan this item, (r)eturn this item, or go (b)ack?");
			System.out.print(">");
			in=input.nextLine();
			c=in.charAt(0);
			if(c=='l' || c=='r' || c=='b' || c=='a')
				acceptable=true;
		}
		
		if(c=='l')
			loanItem(r,itemList);
		if(c=='r')
			returnItem(r,itemList);
		if(c=='b')
			showMenu();
		if(c=='a'){
			showPrivMenu();
			promptRegularUser(itemList,selection);
		}
	}
	
	public static void loanItem(Row r,ItemList itemList){
		//trim whitespace off row entries
		String[] s=r.getEntries();
		for(int i=0;i<s.length;i++){
			s[i]=s[i].trim();
		}
		
		//if consumable, check for in stock
		if(Integer.parseInt(s[0])<=itemList.numberOfLoanables){
			String itemName=s[1];
			int result=Query.loanItem(itemName,login.active_user_email);
			switch(result){
			case -1:
				System.out.println("SQL Error");
				break;
			case 0:
				System.out.println("Sorry, there are insufficient items available");
				break;
			case 1:
				System.out.println("Sucessfully loaned "+itemName);
			}
		}
		else{
			System.out.println("It's consumable, go get it!");
		}
		
		System.out.println("Is there anything else you'd like to do? (y/n)");
		System.out.print(">");
		Scanner input=new Scanner(System.in);
		String in=input.nextLine().toLowerCase();
		if(in.equals("n"))
			goodbye();
		else{
			showMenu();
		}
	}//end of loanItem
	
	public static void returnItem(Row r,ItemList itemList){
		String[] s=r.getEntries();
		for(int i=0;i<s.length;i++){
			s[i]=s[i].trim();
		}
		
		String itemName=s[1];
		int result=Query.returnItem(itemName, login.active_user_email);
		if(result==1)
			System.out.println("Successfully returned "+itemName);
		else
			System.out.println("No loan for "+itemName+" was found under your email");
		
		System.out.println("Is there anything else you'd like to do? (y/n)");
		System.out.print(">");
		Scanner input=new Scanner(System.in);
		String in=input.nextLine().toLowerCase();
		if(in.equals("n"))
			goodbye();
		else{
			showMenu();
		}
	}
}

