/*Honesty/introductory statement: this is a program for the mini-project #1: classical encryption.
all work is my own, I was also shown instruction on subtracting the 'a' bias from the 
mini-project instructions, written by P.M. Osera and adapted by Sam Rebelski*/

import java.io.PrintWriter;
public class Cypher{//class declaration

/*ErrorHandling is a function that checks the number if parameters needed in a given instance,
and check's whether or not the given strings are valid ("code" or "decode")

@Pre: sequence is an array of strings, could be empty, numParametersNeeded is a valid integer,
functionName is a valid function name
@Post: returns if sequence and parameters are not satisfactory*/
    public static void ErrorHandling(String[] sequence, int NumParametersNeeded, String functionName)
    {
        if(sequence.length < NumParametersNeeded)//handling a lack of sufficient input
        {
            System.err.printf("Error in %s function: you have entered insufficient commands", functionName);
            System.exit(1);
        }
        else if(sequence[0].compareTo("encode") != 0 && sequence[0].compareTo("decode") != 0)//handling improper command
        {
            System.err.println("your first input must be either 'code' or 'decode'");
            System.exit(1);
        }
    }
    
    /*CaesarCypher takes a number n, and an array of strings from the commans line, and
    based on whether you give the command "code" or "decode" it will add or subtract the bias.
    The program then returns the modified, encoded or decoded sequence
    
    @pre: sequence is an array of strings, n is a valid integer
    @post: CaesarCypher returns a string of an encoded or decoded input string that was found at index 1 of the
    sequence array*/
    public static String CaesarCypher(int n, String[] sequence)
    {
        ErrorHandling(sequence, 2, "CaesarCypher");//calling a helper error handler to ckeck for parameters and correctness of parameters
        if(sequence[0].compareTo("encode") == 0)//if the command is to cypher
        {
            char[] inputArr = sequence[1].toCharArray(); //converting the string to an array to mutate it
            for(int i = 0; i < inputArr.length; i++)
            {
                int x = (((inputArr[i] - 'a') + n) % 26) + 'a';//converting inputArr to a val between 0 and 26 "basing it", and adding the n code bias, then reconverting to the char range
                inputArr[i] = (char) x;//casting the new val to a char, and setting it in the array
            }
            String returnString = new String(inputArr);//converting our new char array to a string
            return returnString;
        }
        if(sequence[0].compareTo("decode") == 0)//if the command is decypher
        {
            char[] inputArr = sequence[1].toCharArray(); //converting the string to an array to mutate it
            for(int i = 0; i < inputArr.length; i++)
            {
                int x = (inputArr[i] - 'a') - n;//converting inputArr to a val between 0 and 26 ("basing it"), and subtracting n
                if(x < 0)
                {
                    x = x * -1; //getting the absolute value of x
                    x = (26 - x) + 'a';//wrapping it back around and resetting it into the character range
                    inputArr[i] = (char) x;//casting the new val to a char, and setting it in the array
                    
                }
                else
                {
                    x = x + 'a';
                    inputArr[i] = (char) x;//casting the new val to a char, and setting it in the array
                }
            }
            String returnString = new String(inputArr);//converting our new char array to a string
            return returnString;
        }
        return null;
    }

    /*CaesarCypher takes an array of strings from the commans line, and
    based on whether you give the command "code" or "decode" it will add or subtract the bias,
    which is aquired from the integer values of the strings found in the cypher string at the
    third command-line index
    
    @pre: sequence is an array of strings
    @post: VigenereCypher returns a string of an encoded or decoded input string that was found at index 1 of the
    sequence array*/
    public static String VigenereCypher(String[] sequence)
    {
        ErrorHandling(sequence, 3, "VigenereCypher");//calling a helper error handler to ckeck for parameters and correctness of parameters
        if(sequence[0].compareTo("encode") == 0)//if we are commanded to Cypher
        {
            char[] inputArr = sequence[1].toCharArray(); //converting the string to an array to mutate it
            for(int i = 0; i < inputArr.length; i++)
            {
                int currentCypherStringIndex = (int) sequence[2].toCharArray()[i % sequence[2].length()];//getting the cypher index of the vigenere cypher
                int curCypherVal = currentCypherStringIndex - 'a';
                int x = (((inputArr[i] - 'a') + curCypherVal) % 26) + 'a';//converting inputArr to a val between 0 and 26 "basing it", and adding the vigenere cypher bias, then reconverting to the char range
                inputArr[i] = (char) x;//casting the new val to a char, and setting it in the array
            }
            String returnString = new String(inputArr);
            return returnString;
        }
        else if(sequence[0].compareTo("decode") == 0)//if the command is decypher
        {
            char[] inputArr = sequence[1].toCharArray(); //converting the string to an array to mutate it
            for(int i = 0; i < inputArr.length; i++)
            {
                int currentCypherStringIndex = (int) sequence[2].toCharArray()[i % sequence[2].length()];//getting the cypher index of the vigenere cypher
                int curCypherVal = currentCypherStringIndex - 'a';
                int rawModifiedVal = (inputArr[i] - 'a') - curCypherVal;//converting inputArr to a val between 0 and 26 ("basing it"), and subtracting the vigenere cypher value
                if(rawModifiedVal < 0)
                {
                    rawModifiedVal = rawModifiedVal * -1; //getting the absolute value of rawModifiedVal
                    rawModifiedVal = (26 - rawModifiedVal) + 'a';//wrapping it back around and resetting it into the character range
                    inputArr[i] = (char) rawModifiedVal;//casting the new val to a char, and setting it in the array
                    
                }
                else
                {
                    rawModifiedVal = rawModifiedVal + 'a';
                    inputArr[i] = (char) rawModifiedVal;//casting the new val to a char, and setting it in the array
                }
            }
            String returnString = new String(inputArr);
            return returnString;
        }
        return null;
    }

    /*all printing occurs here from  the main function */
    public static void main(String[] args)
    {
        PrintWriter pen = new PrintWriter(System.out, true);
        for(int i = 0; i < 26; i++)//calling caesarCypher on all i vals
        {
            String st = CaesarCypher(i, args);
            pen.printf("n = %d: %s\n", i, st);
        }
        String VigeStr = VigenereCypher(args); //calling VirgeneCypher
        pen.printf("Calling Vigenere Cypher: %s\n", VigeStr);
        pen.flush();//flushing the pen
    }
}