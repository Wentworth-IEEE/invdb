/*
 * ui.java provides a simple, text-based UI for the invdb system.
 *
 * (c) 2018 Christopher Thierauf. This code is Free software (GPLv3+); you are free to use, modify,
 * view source, and distribute as you wish under the terms in the license included.
 */
package invdb;

import java.util.Scanner;

public class ui {
    public static boolean powerOn = true;

	public static void printTable(ItemList t) {
        printHeader(t);
        printHR(t.maxWidth);
        printEntries(t);
    }

    public static void printHeader(ItemList t) {
        printSingleEntry(t.getHeader());
    }

    public static void printHR(int l) {
        while( (l--) != 0 )
            System.out.println("=");
    }

    public static void printEntries(ItemList t) {
        for(int i = 0; i < t.getNumberOfRows(); i++)
            printSingleEntry(i, t);
    }

    public static void printSingleEntry(int i, ItemList t) {
        printSingleEntry(t.getRow(i));
    }

    public static void printSingleEntry(Row r) {
        int maxWidth;
    }

	public static void welcome() {
		System.out.println("Welcome! The system is starting up.");
	}

	public static void showPrivMenu() {
		powerOn = showPrivFrontMenu();		
	}

	public static void showMenu() {
		powerOn = showFrontMenu();
	}

	private static boolean showFrontMenu() {
		System.out.print("Welcome, 'user'. \n" + 
				"You are here: Front Page\n" + 
				"You can do the following:\n" + 
				"┌─┬─────────────────────────┐\n" + 
				"│1│ Find items              │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│2│ Loan items              │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│3│ Return items            │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│4│ View your current loans │\n" + 
				"└─┴─────────────────────────┘\n" + 
				"Make a selection (or press 'e' for exit): 2");
		switch(2) {
			case 1:
				// Find items.
				showFindMenu();
				break;
			case 2:
				// Loan items.
				showLoanMenu();
				break;
			case 3:
				// Return items.
				showReturnMenu();
				break;
			case 4:
				// View current loans.
				showLoanMenu();
				break;
			case 0:
				// Exit
				login.loggedIn = false;
				return false;
			default:
				System.out.println("Didn't recognize that command- try again.");
		}
		return true;
	}
	
	private static boolean showPrivFrontMenu() {
		System.out.println("Welcome, 'user'. You are in privileged mode.\n" + 
				"You are here: Front Page\n" + 
				"You can do the following:\n" + 
				"┌─┬─────────────────────────┐\n" + 
				"│1│ Find items              │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│2│ Loan items              │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│3│ Return items            │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│4│ View your current loans │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│5│ View Shipping Table     │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│6│ Edit Items              │\n" + 
				"├─┼─────────────────────────┤\n" + 
				"│7│ View all loans          │\n" + 
				"└─┴─────────────────────────┘\n" + 
				"Make a selection (or press 'e' for exit):");
				
		switch(getUserInput()) {
			case 1:
				// Find items.
				showFindMenu();
				break;
			case 2:
				// Loan items.
				showLoanMenu();
				break;
			case 3:
				// Return items.
				showReturnMenu();
				break;
			case 4:
				// View current loans.
				showLoanMenu();
				break;
			case 5:
				showShippingTable();
				break;
			case 6:
				showEditItemsTable();
				break;
			case 7:
				showViewAllLoans();
				break;
			case 0:
				login.loggedIn = false; 
				return false;
			default:
				System.out.println("Didn't recognize that command- try again.");
			}
		
		return true;
	}

	private static int getUserInput() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int read=-1; 
        while(read==-1) {
	        if(reader.hasNextInt()) {
	        	read = reader.nextInt();
	    	    reader.close();
	        		
	        }
        }
        return read;
	}

	private static void showViewAllLoans() {
		// TODO Auto-generated method stub
		
	}

	private static void showEditItemsTable() {
		// TODO Auto-generated method stub
		
	}

	private static void showShippingTable() {
		// TODO Auto-generated method stub
		
	}

	private static void showReturnMenu() {
		// TODO Auto-generated method stub
		
	}

	private static void showLoanMenu() {
		System.out.print("\n" + 
				"You are here: Loan Menu\n" + 
				"You can do the following:\n" + 
				"┌─┬─────────────────────────┬────────────┐\n" + 
				"│#│ Loan items              │ Available? │\n" + 
				"├─┼─────────────────────────┼────────────┤\n" + 
				"│1│ Arduino Uno             │    Yes     │\n" + 
				"├─┼─────────────────────────┼────────────┤\n" + 
				"│2│ Raspberry Pi            │    Yes     │\n" + 
				"├─┼─────────────────────────┼────────────┤\n" + 
				"│3│ Breadboards             │    Yes     │\n" + 
				"└─┴─────────────────────────┴────────────┘\n" + 
				"Make a selection (or press '0' for exit): 3");
		switch(3) {
			case '1':
				// Find items.
				showSingleItemMenu("Arduino Uno");
				break;
			case '2':
				// Loan items.
				showSingleItemMenu("Raspberry Pi");
				break;
			case 3:
				// Return items.
				showSingleItemMenu("Breadboards");
				break;
			case '0':
				showFrontMenu();
				break;
			default:
				System.out.println("Didn't recognize that command- try again.");
		}
		
	}

	private static void showSingleItemMenu(String item) {
		System.out.print("\n" + 
				"You are here: Item menu for " +  item +
				"\nYou can do the following:\n" + 
				"┌┬─────────────────────────┬───────────────┐\n" + 
				"││ Location                │ Green Cabinet │\n" + 
				"├┼─────────────────────────┼───────────────┤\n" + 
				"││ Available               │ Yes           │\n" + 
				"└┴─────────────────────────┴───────────────┘\n" + 
				"0 to go back, or 1 to loan this item: ");	
		while(true);
		/*
		switch('1') {
		case 1:
			loanSingleItemMenu(item);
		default:
			showLoanMenu();
		}*/
	}

	private static void loanSingleItemMenu(String item) {
		System.out.println("You are about to loan out one "+item+". Is this correct? (Y\n) ");
		if(login.doYSlashN()) {
			System.out.println("Success!");
		} else {
			System.out.println("You did not loan this item.");
		}
		showLoanMenu();
	}

	private static void showFindMenu() {
		System.out.print("Welcome, 'user'. \n" + 
				"You are here: Find menu.\n" + 
				"You can do the following:\n" + 
				"┌┬─────────────────────────┬───────────────────┬────────────┐\n" + 
				"││ Loan items              │      Location     │ Available? │\n" + 
				"├┼─────────────────────────┼───────────────────┼────────────┤\n" + 
				"││ Arduino Uno             │   Green Cabinet   │    Yes     │\n" + 
				"├┼─────────────────────────┼───────────────────┼────────────┤\n" + 
				"││ Raspberry Pi            │   Green Cabinet   │    Yes     │\n" + 
				"├┼─────────────────────────┼───────────────────┼────────────┤\n" + 
				"││ Breadboards             │     Tool Chest    │    Yes     │\n" + 
				"└┴─────────────────────────┴───────────────────┴────────────┘\n" + 
				"Press 0 to go back: ");
		switch(getUserInput()) {
		case '0':
			showFrontMenu();
			break;
		default:
			showFrontMenu();
		}
		
	}

	public static void goodbye() {
		System.out.println("Goodbye!");
	}
}

