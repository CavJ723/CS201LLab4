package CS201LLab4Base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to My 2-D ArrayList Processor!\n");

        //Create a Scanner object for standard input
        Scanner stdInput = new Scanner(System.in);

        //Prompt the user for which file to use
        System.out.print("\nEnter which file to process (v(valid) or i(invalid): ");
        String inputType = stdInput.nextLine().toLowerCase();
        String inputFile;
        char type = inputType.charAt(0);

        //if 'v' use the valid data file, if i, use the invalid data file
        if (type == 'v')
            inputFile = "dataValid.txt";
        else if (type == 'i')
            inputFile = "dataInvalid.txt";
        else{
            System.out.println("Entry is invalid - program ending");
            stdInput.close();
            return;
        }
        stdInput.close();

        //try is provided here for the Scanner input for the file
        String inputStr1, inputStr2;        
        try {
            File file = new File (inputFile);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()){
                //get 2 input lines
                

                //declare two 2-D ArrayLists

                System.out.println("\n\nPROCESSING A SET OF MATRICES");

                System.out.println("\n\tPROCESSING FIRST MATRIX");
                
                //verify 2-D Matrix
                //if valid, print & process the Matrix
                //if not, print a message

                System.out.println("\n\tPROCESSING SECOND MATRIX");
 
                //verify 2-D Matrix
                //if valid, print & process the Matrix
                //if not, print a message

                System.out.println("\n\tPROCESSING BOTH MATRICES");

                //if both are valid and the sizes are compatible, add the matrices
                //PART2: if both are valid and the sizes are compatible, multiply the matrices
                //if one of the matrices is not valid, print a message
                //if the sizes are not compatible, print a message
 
            }
           input.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found: " + inputFile);
            return;
        }

    }

    //PRE:  accepts a string
    //POST: determines that each character in the string is a digit
    //      returns true if so, false if not
    public static boolean isDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!(Character.isDigit(str.charAt(i))))
                return false;
        }
        return true;
    }

    //PRE: accepts a string
    //POST: verifies that the string only contains 0s and 1s
    //      returns true if so, false if not
    public static boolean isBinary(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0' || str.charAt(i) == '1')
                continue;
            else
                return false;
        }
        return true;
    }

    //PRE: This function will accept the input string & an empty 2-D ArrayList
    //POST: if ‘valid’ return 1, if not print a message and return -1
	//      if ‘valid’ load the ArrayList with the values in the input string and return 1

    public static int verifyArrayList(String inputLine, ArrayList<ArrayList<Integer>> arr1) {
        String[] tokens = inputLine.trim().split(" ");

        //test to make sure there are at least 2 tokens for row & col
 
        
        //use position [0] for number of rows and position[1] for number of cols
        int row, col;
        try {
            row = Integer.parseInt(tokens[0]);
            col = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            System.out.println("\tMatrix contains invalid row/col values.");
            return -1;
        }

        //PART 2:  check the row & col values are between 1 and 5 (inclusive) 
        //PART 2:  check that tokens.length has enough data for the matrix


        //starting at position 2 in tokens, read remaining characters
        //see lecture example for loading individual entries in tokens into the ArrayList
        //PART 2:  verfiy that each value in token is isBinary
         

        return 0;
    }


    //PRE: accepts a 2-D ArrayList that has been loaded with proper values
    //POST: prints the 2-D ArrayList
    public static void printArrayList(ArrayList<ArrayList<Integer>> arr1) {
        //print 2-D ArrayList

    }

    
    //PRE:  Accepts one 2 dimensional ArrayList that has been loaded with proper values
    //POST: Deterimines if the number of rows = number of columns for this ArrayList
    //   	if not, prints the message: "Cannot determine reflexive/symmetric: Invalid dimensions"
    //  	if so, determines if the ArrayList is reflexive or symmetric and prints out a message for each  

    public static void processArrayList(ArrayList<ArrayList<Integer>>  arr1) {
   
        //If the number of rows != number of columns, print message & return

        //Check for Reflexive
        
        //Check for Symmetric
        
        //Print results
        
    }

    //PRE:  Accepts two 2 dimensional ArrayLists that have been loaded with proper values
    //POST: Determine if the the ArrayLists are the same size
    //       if not, print the message: "Unable to add Matrices: Matrices have different sizes." & return
    //       if so, add the corresponding elements (row,col) from each ArrayList and print 
    //
    public static void addTwoMatrices(ArrayList<ArrayList<Integer>>  arr1, ArrayList<ArrayList<Integer>>  arr2) {
        //verify if matrices can be added 
        //If the matrix sizes do not match print message & return

        //add corresponding elements & print result
 
    }


}
