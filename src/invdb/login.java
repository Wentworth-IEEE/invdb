/*
 * login.java handles user checking and logging in. 
 * (c) 2018 Christopher Thierauf. This code is Free software (GPLv3+); you are free to use, modify,
 * view source, and distribute as you wish under the terms in the license included.
 */
package invdb;
import java.lang.String;

public class login {
    public String active_user_email = new String("");
    public static boolean privileged = false;
    public static boolean loggedIn = false;
        
    /**
     * Prompt the user for login info, allow access to exec mode
     */
    public static void promptUser(){
        boolean allowBreak = false;

        while(!allowBreak) {
            System.out.println("Welcome to the IEEE inventory system! Type your WIT email below to get started:");
            System.out.print("> ");
            String usermail = ""; // TODO- fix this

            switch(checkUsername(usermail)) {
                case 0:
                    System.out.print("That name wasn't recognized by our system. \n"+
                                     "Are you a member of IEEE, or did you make a typo?\n"+
                                     "Try again, or contact ieee@wit.edu for help.\n");
                    allowBreak = false;
                    break;
                case 1:
                    System.out.println("You are now logged in as "+usermail);
                    allowBreak = true;
                    break;
                case 2:
                    System.out.println("You are now logged in as "+usermail);
                    promptUserForExec(usermail);
                    allowBreak = true;
                    break;
            } // End switch
        } // End while(!allowBreak)
    } // End promptUser()

    /**
     * Check the entered username for validity, permissions
     */
    private static int checkUsername(String usermail) {
        if(checkLoginFile(usermail)) {
            if(checkExecFile(usermail)) {
                return 2;
            }
            return 1;
        }

        if(!usermail.contains("@wit.edu"))
            System.out.println("Email must be a valid wit email address.");
        return 0;
    }

    /**
     * Prompt the user, asking if they'd like to login as an exec.
     */
    private static void promptUserForExec(String usermail) {
        System.out.print("You have a privileged account. Would you like to log in as an executive?");
        if(doYSlashN()){
            if(getExecPassword(usermail) != null) {
                System.out.print("Enter your password.\n> ");
                String enteredText = ""; //TODO- This.
                if(hashpass(enteredText) == getExecPassword(usermail)) {
                    System.out.println("You have logged in as an executive.");
                    privileged = true;
                } else {
                    System.out.println("Password check failed!");
                    privileged = false;
                }
            } else {
                System.out.println("This account has not yet set its exec password. Enter it below.");
            }
        } else {
            System.out.println("Okay. You will be shown the standard user menus.");
        }
    }

    private static boolean checkLoginFile(String usermail) {
		return false;
    }

    private static boolean checkExecFile(String usermail) {
		return false;
    }

    private static boolean doYSlashN() {
		return false;
    }

    private static String getExecPassword(String usermail) {
		return null;
    }

    private static String hashpass(String enteredText) {
		return null;
    }
}