/*
 * invdb
 * The IEEE Inventory Database system
 * This code allows for tracking, maintaining, and loaning of items in the inventory.
 *
 * (c) 2018 Christopher Thierauf. This code is Free software (GPLv3+); you are free to use, modify,
 * view source, and distribute as you wish under the terms in the license included.
 */

package invdb;

import invdb.ItemList;
import invdb.login;
import invdb.ui;
import invdb.logger;

public class invdb {
    /**
     * Main code execution point. Prompts for login, verifies user, then allows for using the system.
     */
    public static void main(String[] args) {
    	/*
    	 * Pseudocode for this bit
    	 * - Start Logger
    	 * - Give UI welcome screen
    	 * - Allow login
    	 * - Show user-appropriate logged in screen
    	 * - Prompt for action
    	 * - Follow action
    	 * - Repeat
    	 */
        logger.startLogger(); 	// Start the logger
        ui.welcome();			// Power-on welcome
        
        while(ui.powerOn) {					// This next bit should go forever (until stopped)
	        login.promptUser();			// Prompt the user for login stuff.
	        while(login.loggedIn) {		// Show menus only while logged in- keep asking for login otherwise.
		        if(login.privileged)
		        	ui.showPrivMenu();  // Privileged users get the privileged menu
		        else
		        	ui.showMenu();		// Non-privileged users get the normal menu
	        }
        }
        //logger.stopLogger();
        //ui.goodbye();
    }
}

