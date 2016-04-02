/*
 * Mini Gpa calculator (NOT BGC)
 * Professor: Best
 * Class: CST 1201
 * Version: 0.5
 */
package gpa.calculator;

// Lets get our scanner class
import java.util.Scanner;

// Lets get out input output classes
import java.io.*;

/**
 *
 * @author James Bernard
 */
public class GPACalculator {
    // Name of program
    private static String program_name = "Mini GPA";
    // Author of program
    private static String author = "James Bernard";
    // add your name if you heavily modefied program
    private static String editor;
    // This will be used in many menu's thus will change accordingly.
    private static String user_selection;

    /**
     * Start of program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Step 1 - Show our intro to user
        intro();
        // Step 2 - Provide main menu to user so we can grab a user_selection from its input
        main_menu();
        // Step 3 - run a task chosen in the main menu.
        task_runner();
    }
    
    /**
     * This method is used to display an intro to the program user, define all pre menu actions here.
     */
    public static void intro(){        
        alert("Welcome to " + program_name, 1);
        alert("By: " + author, 1);        
    }
    /**
     * Display any errors to the user along with its status code, so we can find it and debug it.
     * @param status
     * @param msg 
     */
    public static void error(int status, String msg){
        rows(1);
        cells(10);
        alert("Error [" + status + "]: " + msg, 1);
    }
    
    
    /**
     * This is the main menu for the program, define user options here, it will use the "user_selection" property to hold any valid choices made here.
     */
    public static void main_menu(){
        // We will be using the scanner class agaisnt inputs on the system for this menus input
        Scanner input = new Scanner(System.in);
        // This will allow us to exit menu loop
        Boolean valid = true;
        // Option variable to hold initial user choice
        String option_str;
        // Main options are integers
        int option;
        // Displain menu index: 0 (Main Menu)
        menu_display(0);
        
        // Menu loop
        do{
            // Reset valid selection
            valid = true;
            
            // Initial user selection
            option_str = input.nextLine();
            // Check if option equals to an event trigger
            if(option_str.equals("m") || option_str.equals("M")){
                // Display main menu options.
                menu_display(0);
                // Wait for new selection
                option_str = input.nextLine();
            }
            // Try to parse the selection to a intiger "1" => 1
            try{
                option = Integer.parseInt(option_str);
            }catch(NumberFormatException nfe){ // if we get a number format exception
                // Set user selection to -1 (which doesnt exist)
                option = -1;
            }
            // Switch based on user selection setting user selection accordingly
            switch(option){
                case 1:
                    user_selection = "calculate-gpa";
                    break;
                case 2:
                    user_selection = "view-gpa";
                    break;
                case 0:
                    quit(); // Quit program
                default: // If an option was chosen other than what we defined
                    // Stop program from exiting menu loop
                    valid = false;
                    // Display errors to user.
                    error(001, "Invalid Choice of (" + option_str + ")");
                    cells(10);
                    alert("Try Again (m - Show Menu): ");
                    break;
            }
            
        }while(!valid); // Break out of menu loop when valid == true;
        // end of menu loop
    }
    
    /**
     * Here we will run a task depending on user selection
     */
    public static void task_runner(){
        rows(1);
        
        switch(user_selection){
            case "calculate-gpa":
                alert("Going to calculate GPA", 1);
                break;
            case "view-gpa":
                alert("View GPA of a student", 1);
                break;
            default:
                error(003, "Task (" + user_selection + ") does not exist!");
                System.exit(0);
                break;
        }   
    }
    public static void quit(){
        rows(2);
        cells(10);
        alert("Good Bye!", 1);
        System.exit(0);
    }
    
    /** 
     * Creates rows before an alert;
     * @param amount 
     */
    public static void rows(int amount){
        for(int i = 0; i < amount; i++) alert(" ", 1);
    }
    
    /**
     * Creates cells before an alert
     * @param amount 
     */
    public static void cells(int amount){
         for(int i = 0; i< amount; i++) alert(" ");
    }
    
    /**
     * Default alert method.
     * Passes message to main alert method with the option of 0 -> print
     * @param msg 
     */
    public static void alert(String msg){
        alert(msg, 0);
    }
    
    /**
     * Main alert method
     * @param msg Message to output to System.out
     * @param option [0] -> print || [1] -> println
     */
    public static void alert(String msg, int option){
        switch(option){
            case 1:
                System.out.println(msg);
                break;
            default:
                System.out.print(msg);
                break;
        }
    }
    
    /**
     * This method will take in an array of grades, calculate it than spit out the average.
     * @param grades
     * @return gpa average
     */
    public static Double calculate(Double[] grades){
        // Count total of grades in the array;
        int count = grades.length;
        // Set and initialize total of all grades added up (0.00 for now)
        Double total = 0.00;
        // Set and initialize the sum of all the grades devided by the total (0.00) for now
        Double gpa = 0.00;
        // Itterate over each grade within the grades array list.
        for(Double grade : grades){
            // Inriment the total by current grade.
            total += grade;
        }
        // Calculate gpa based on total devided by count of grades.
        gpa = total / count;
        // Return result to be used;
        return gpa;
    }
    
    public static void save(String[] lines){
        
    }
    /**
     * A method used to writing content to a file
     * @param fileName This is a string of the file to write or append to.
     * @param contents The content you wish to be appended or written.
     * @throws IOException 
     */
    public static void writeToFile(String fileName, String content) throws IOException{
        File thisFile = new File(fileName);
        PrintWriter pW;
        
        if(thisFile.exists()){
            pW = new PrintWriter(new FileWriter(fileName, true));
            pW.print(content);
        }else{
            pW = new PrintWriter(new FileWriter(fileName));
            pW.print(content);
        }
    }
    
    /**
     * Array to block method will convert your string array to a block of text, with each element seperated by a \n (new line)
     * @param list of strings
     * @return formated string
     */
    public static String arrayToBlock(String[] list){
        // initialize formated string;
        String formated = "";
        // Iterate through each element of our string array of list as a line
        for(String line : list){
            // Append this line to previously formated line.
            formated += ("\n" + line);
        }
        // Return the block of strings
        return formated;
    }
    
    /**
     * This method displays a menu defined by the program to the user.
     * @param index of menu
     */
    public static void menu_display(int index){
        switch(index){
            case 0: // Main Menu
                rows(2);
                cells(5);
                // Title
                alert("Main Menu", 1);
                // Options
                cells(5);
                alert("[1] - Calculate GPA", 1);
                cells(5);
                alert("[2] - View GPA", 1);
                cells(5);
                alert("[0] - Quit Program", 1);
                // End of options
                rows(1);
                cells(5);
                // Used as a carret for user input
                alert("Choice > ");
                break;
            default:
                error(002, "Program error, menu #"+ index +" doesnt exist");
                System.exit(0);
                break;
        }
    }
}
