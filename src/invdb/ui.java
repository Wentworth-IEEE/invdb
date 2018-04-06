/*
 * ui.java provides a simple, text-based UI for the invdb system.
 *
 * (c) 2018 Christopher Thierauf. This code is Free software (GPLv3+); you are free to use, modify,
 * view source, and distribute as you wish under the terms in the license included.
 */
package invdb;

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
		// TODO Auto-generated method stub
		powerOn = showPrivFrontMenu();		
	}

	public static void showMenu() {
		powerOn = showFrontMenu();
	}

	private static boolean showFrontMenu() {
		// TODO Auto-generated method stub
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
				"Make a selection (or press 'e' for exit): ");
		// Read user input here
		char userInput = '\0';
		
		switch(userInput) {
		case '1':
			// Find items.
			showFindMenu();
			break;
		case '2':
			// Loan items.
			showLoanMenu();
			break;
		case '3':
			// Return items.
			showReturnMenu();
			break;
		case '4':
			// View current loans.
			showLoanMenu();
			break;
		case 'e':
			// Exit
			return false;
		default:
			System.out.println("Didn't recognize that command- try again.");
			return true;
		}
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
		// Read user input here
				char userInput = '\0';
				
				switch(userInput) {
				case '1':
					// Find items.
					showFindMenu();
					break;
				case '2':
					// Loan items.
					showLoanMenu();
					break;
				case '3':
					// Return items.
					showReturnMenu();
					break;
				case '4':
					// View current loans.
					showLoanMenu();
					break;
				case '5':
					showShippingTable();
					break;
				case '6':
					showEditItemsTable();
					break;
				case '7':
					showViewAllLoans();
					break;
				case 'e':
					// Exit
					return false;
				default:
					System.out.println("Didn't recognize that command- try again.");
					return true;
				}
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
		// TODO Auto-generated method stub
		
	}

	private static void showFindMenu() {
		// TODO Auto-generated method stub
		
	}

	public static void goodbye() {
		// TODO Auto-generated method stub
		
	}
}

