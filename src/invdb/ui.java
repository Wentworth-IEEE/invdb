/*
 * ui.java provides a simple, text-based UI for the invdb system.
 *
 * (c) 2018 Christopher Thierauf. This code is Free software (GPLv3+); you are free to use, modify,
 * view source, and distribute as you wish under the terms in the license included.
 */
package invdb;

public class ui {
    public static final boolean powerOn = true;

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
		// TODO Auto-generated method stub
		
	}

	public static void showPrivMenu() {
		// TODO Auto-generated method stub
		
	}

	public static void showMenu() {
		// TODO Auto-generated method stub
		
	}

	public static void goodbye() {
		// TODO Auto-generated method stub
		
	}
}

