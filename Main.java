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
        String inputString1, inputString2;
        try {
            File file = new File (inputFile);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()){
                //get 2 input lines
                inputString1 = input.nextLine();
                inputString2 = input.nextLine();

                //declare two 2-D ArrayLists
                ArrayList<ArrayList<Integer>> matrix1 = new ArrayList<>();
                ArrayList<ArrayList<Integer>> matrix2 = new ArrayList<>();

                System.out.println("\n\nPROCESSING A SET OF MATRICES");

                System.out.println("\n\tPROCESSING FIRST MATRIX");
                
                //verify 2-D Matrix
                //if valid, print & process the Matrix
                //if not, print a message
                if (verifyArrayList(inputString1, matrix1) == 1) {  //verifyArrayList checks to see if input string can be loaded into matrix1
                    printArrayList(matrix1);
                    processArrayList(matrix1);
                } else {
                    System.out.println("\tFirst 2-D matrix is not valid.");
                }

                System.out.println("\n\tPROCESSING SECOND MATRIX");
 
                //verify 2-D Matrix
                //if valid, print & process the Matrix
                //if not, print a message
                if (verifyArrayList(inputString2, matrix2) == 1) { //verifyArrayList checks to see if input string can be loaded into matrix2
                    printArrayList(matrix2);
                    processArrayList(matrix2);
                } else {
                    System.out.println("\tSecond 2-D matrix is not valid.");
                }

                System.out.println("\n\tPROCESSING BOTH MATRICES");

                //if both are valid and the sizes are compatible, add the matrices
                //PART2: if both are valid and the sizes are compatible, multiply the matrices
                //if one of the matrices is not valid, print a message
                //if the sizes are not compatible, print a message
                if (matrix1.size() > 0 && matrix2.size() > 0) {
                    addTwoMatrices(matrix1, matrix2);
                } else {
                    System.out.println("\tOne or both matrices are not valid - cannot add.");
                }
                System.out.println("\n\tMULTIPLYING BOTH MATRICES");

                //this if statement checks the if the columns [.get(0).size()] match the rows [.size()]
                if (matrix1.size() > 0 && matrix2.size() > 0 &&
                    matrix1.get(0).size() == matrix2.size() && matrix1.size() == matrix2.get(0).size()) {
                    for (int i = 0; i < matrix1.size(); i++) {
                        ArrayList<Integer> row = new ArrayList<>();
                        for (int j = 0; j < matrix2.get(0).size(); j++) {
                            int sum = 0;
                            for (int k = 0; k < matrix1.get(0).size(); k++) {
                                sum += matrix1.get(i).get(k) * matrix2.get(k).get(j);
                            }
                            row.add(sum);
                        }

                        System.out.println("\t" + row);
                    }
                } else {
                    System.out.println("\tMatrices are incompatible for multiplication.");
                }
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
        if (tokens.length < 2) {
             System.out.println("\tMatrix contains insufficient data.");
            return -1;
        }
 
        
        //use position [0] for number of rows and position[1] for number of cols
        int rowCount, colCount, row, col;
        try {
            rowCount = Integer.parseInt(tokens[0]);
            colCount = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            System.out.println("\tMatrix contains invalid row/col values.");
            return -1;
        }

        //PART 2:  check the row & col values are between 1 and 5 (inclusive)         
        if (rowCount < 1 || rowCount > 5 || colCount < 1 || colCount > 5) {
            System.out.println("\tMatrix row/col values are out of range.");
            return -1;
        }
        //PART 2:  check that tokens.length has enough data for the matrix
         if (tokens.length < 2 + rowCount * colCount) {
            System.out.println("\tMatrix contains insufficient data.");
            return -1;
        }

        //starting at position 2 in tokens, read remaining characters
        //see lecture example for loading individual entries in tokens into the ArrayList
        int tokenPos = 2;
        for (row = 0; row < rowCount; row++) {
            ArrayList<Integer> rowValues = new ArrayList<Integer>();
            for (col = 0; col < colCount; col++) {
                String value = tokens[tokenPos];
                //PART 2: verfiy that each value in token is isBinary
                if (!isDigits(value) || !isBinary(value)) {
                    System.out.println("\tMatrix contains invalid value: " + value);
                    return -1;
                }
                rowValues.add(Integer.parseInt(value));
                tokenPos++;
            }
            arr1.add(rowValues);
        }
        return 1;
    }


    //PRE: accepts a 2-D ArrayList that has been loaded with proper values
    //POST: prints the 2-D ArrayList
    public static void printArrayList(ArrayList<ArrayList<Integer>> arr1) {
    for (ArrayList<Integer> row : arr1) {
        for (Integer val : row) {
            System.out.print("\t" + val + " ");
        }
        System.out.println();
    }
}

    
    //PRE:  Accepts one 2 dimensional ArrayList that has been loaded with proper values
    //POST: Deterimines if the number of rows = number of columns for this ArrayList
    //   	if not, prints the message: "Cannot determine reflexive/symmetric: Invalid dimensions"
    //  	if so, determines if the ArrayList is reflexive or symmetric and prints out a message for each  

    public static void processArrayList(ArrayList<ArrayList<Integer>>  arr1) {
   
        //If the number of rows != number of columns, print message & return
        if (arr1.size() != arr1.get(0).size()) {
            System.out.println("\tCannot determine reflexive/symmetric: Invalid dimensions");
            return;
        }

        //Check for Reflexive
        boolean isReflexive = true;
        for (int i = 0; i < arr1.size(); i++) {
            if (arr1.get(i).get(i) != 1) {
                isReflexive = false;
                break;
            }
        }
        if (isReflexive) {
            System.out.println("\tMatrix is reflexive.");
        } else {
            System.out.println("\tMatrix is not reflexive.");
        }

        //Check for Symmetric
        boolean isSymmetric = true;
        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr1.size(); j++) {
                if (arr1.get(i).get(j) != arr1.get(j).get(i)) {
                    isSymmetric = false;
                    break;
                }
            }
            if (!isSymmetric) {
                break;
            }
        }
        
        //Print results
        if (isSymmetric) {
            System.out.println("\tMatrix is symmetric.");
        } else {
            System.out.println("\tMatrix is not symmetric.");
        }
    }

    //PRE:  Accepts two 2 dimensional ArrayLists that have been loaded with proper values
    //POST: Determine if the the ArrayLists are the same size
    //       if not, print the message: "Unable to add Matrices: Matrices have different sizes." & return
    //       if so, add the corresponding elements (row,col) from each ArrayList and print 
    //
    public static void addTwoMatrices(ArrayList<ArrayList<Integer>>  arr1, ArrayList<ArrayList<Integer>>  arr2) {
        //verify if matrices can be added 
        //If the matrix sizes do not match print message & return
        if (arr1.size() != arr2.size() || arr1.get(0).size() != arr2.get(0).size()) {
            System.out.println("\tUnable to add Matrices: Matrices have different sizes.");
            return;
        }

        //add corresponding elements & print result
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < arr1.size(); i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < arr1.get(i).size(); j++) {
                row.add(arr1.get(i).get(j) + arr2.get(i).get(j));
            }
            result.add(row);
        }
        System.out.println("\tResultant Matrix: " + result);
    }


}
