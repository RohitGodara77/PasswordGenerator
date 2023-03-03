package passGenPackage;

import java.util.Scanner;

public class Generator {
	
    Characters alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Characters(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    public void mainLoop() {
        System.out.println("Welcome to KIRA Password Services :)");

        String userOption = "-1";

        while (!userOption.equals("4")) {

        	printMenu();
            userOption = keyboard.next();
            final Scanner scn = new Scanner(System.in);

            switch (userOption) {
                case "1" : requestPassword();
                		   continue;
                
                case "2" : checkPassword(scn);
                		   continue;
    
                case "3" : printUsefulInfo();
                		   continue;
                
                case "4" : printQuitMessage();
                		   break;
                	
                default :
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    printMenu();
                
            }
            scn.close(); 
        }
        
    }

    private PasswordStrength GeneratePassword(int length) {
        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = alphabet.getCharacters().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getCharacters().charAt(index));
        }

        return new PasswordStrength(pass.toString());
    }

    private void printUsefulInfo() {
        System.out.println();
        System.out.println("* At least 12 characters long but 14 or more is better.");
        System.out.println("* A combination of uppercase letters, lowercase letters, numbers, and symbols.");
        System.out.println("* Not a word that can be found in a dictionary or the name of a person, character, product, or organization.");
        System.out.println("* Significantly different from your previous passwords.");          
    }

    private void requestPassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams = false;

        System.out.println();
        System.out.println("Hello, welcome to the Password Generator :) answer"
                + " the following questions by Yes or No \n");

        do {
        	System.out.println("Using all types of characters is suggested..");
        	String input = keyboard.nextLine();
        	
        	System.out.println("");
        	
            System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
            input = keyboard.nextLine();

            if (isInclude(input)) IncludeLower = true;

            System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
            input = keyboard.nextLine();

            if (isInclude(input)) IncludeUpper = true;

            System.out.println("Do you want Numbers \"1234...\" to be used? ");
            input = keyboard.nextLine();

            if (isInclude(input)) IncludeNum = true;

            System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
            input = keyboard.nextLine();

            if (isInclude(input)) IncludeSym = true;

            //No Pool Selected
            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your " +
                        "password at least one of your answers should be Yes");
                correctParams = true;
            }

            System.out.println("Great! Now enter the length of the password");
            int length = keyboard.nextInt();

            final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
            final PasswordStrength password = generator.GeneratePassword(length);
            
            System.err.println("Your generated password -> " + password);
         
        } while (correctParams);
    }

    private boolean isInclude(String Input) {
        if (Input.equalsIgnoreCase("yes")) {
            return true;
        }
        else {
            if (!Input.equalsIgnoreCase("no")) {
                PasswordRequestError();
            }
            return false;
        }
    }

    private void PasswordRequestError() {
        System.out.println("You have entered something incorrect let's go over it again \n");
    }

    private void checkPassword(Scanner in) {
        String input;
        //final Scanner in = new Scanner(System.in);

        System.out.print("\nEnter your password:");
        input = in.nextLine();

        final PasswordStrength p = new PasswordStrength(input);

        System.out.println(p.calculateScore());

       //in.close();      //error occurs here, noSuchElementException:
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}