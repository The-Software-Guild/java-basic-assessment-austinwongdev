/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Jul 27, 2021
 * purpose: To create a computer game of Rock, Paper, Scissors
 * future optimizations:
 *    *Create custom exception for out of range int inputs
 *    *Display subtotal of results after each round
 *    *Create option for tiebreaker sudden-death
 *    *Reformat comments into Javadoc
 * feedback:
 *    *Do not go beyond requirements on your own
 *       * Ask questions to remove assumptions
 *    *Use unambiguous variable names (playRPS is ambiguous, playGame is better)
 *    *Use static sparingly
 *       * static is only required if the parent method that is calling the method is also static
 *       * you can instantiate your class in the main method to get around calling other static methods
 */

package com.aaw.exercises;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Austin Wong
 */
public class RockPaperScissors {
    
    public static void main(String[] args){
        
        int numRounds;
        int keepPlaying = 1;
        String[] prompt = {
            "0 = No, 1 = Yes",
            "Play again? "
        };
        String errorMsg = "Not a valid option.";
        
        while (keepPlaying == 1){
            numRounds = askToPlay();
            
            // Quit upon invalid input
            if (numRounds == 0){
                return;
            }
            
            playRPS(numRounds);
            keepPlaying = getIntInput(0, 1, prompt, errorMsg, true);
            System.out.println();
        }
        
        System.out.println("Thanks for playing!");
         
    }
    
    // Prompts user for input, displaying error message if input is not within inclusive range of minInt and maxInt
    // Returns 0 if input is invalid and repeat is false
    public static int getIntInput(int minInt, int maxInt, String[] prompt,String errorMsg, boolean repeat){
        Scanner scan = new Scanner(System.in);
        int output;
        
        while(true){
            
            for (int i = 0; i < prompt.length; i++){
                System.out.println(prompt[i]);
            }
            try{
                output = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException ex){
                System.out.println(errorMsg);
                if (!repeat){
                    return 0;
                }
                else{
                    continue;
                }
            }
            // Optimization: Use custom exception and merge with catch statement
            if (output < minInt || output > maxInt){
                System.out.println(errorMsg);
                if (!repeat){
                    return 0;
                }
                else{
                    continue;
                }
            }
            return output;
        }
    }
    
    public static int askToPlay(){
        String[] prompt = {"Let's play Rock, Paper, Scissor!",
            "I can play between 1 and 10 rounds.",
            "How many rounds would you like to play? "
        };
        String errorMsg = "Invalid input. That's not a whole number between 1 and 10.";
        int numRounds = getIntInput(1, 10, prompt, errorMsg, false);
        return numRounds;
    }
    
    public static void playRPS(int numRounds){
        
        Random rand = new Random();
        int numActions = 3;
        String[] actions = {
            "Rock",
            "Paper",
            "Scissors"
        };
        int numTies = 0;
        int numUserWins = 0;
        int numComputerWins = 0;
        String[] prompt = {
            "1 = Rock, 2 = Paper, 3 = Scissors",
            "Choose your move: "
        };
        String errorMsg = "Not a valid option. Try again.\n";
        
        // Iterate through each round
        for (int i = 0; i < numRounds; i++){
            
            System.out.println("\nRound " + (i+1) + ":\n");
            
            // Ask for user action
            int userMove = getIntInput(1, numActions, prompt, errorMsg, true);
            
            // Compare to computer's move
            int computerMove = rand.nextInt(numActions) + 1;
            
            // Tie
            if (computerMove == userMove){
                System.out.println("Tie");
                numTies += 1;
            }
            // Cheatsheet: 1 = Rock, 2 = Paper, 3 = Scissors
            // Computer Win
            else if ((computerMove == 3 && userMove == 2) || 
                    (computerMove == 1 && userMove == 3) || 
                    (computerMove == 2 && userMove == 1)){
                numComputerWins += 1;
                System.out.println("Loss: " + actions[computerMove-1] + " beats " + actions[userMove-1]);
            }
            // User Win
            else{
                numUserWins += 1;
                System.out.println("Win: " + actions[userMove-1] + " beats " + actions[computerMove-1]);
            }
        }
        
        // Output results
        System.out.println();
        if (numUserWins > numComputerWins){
            System.out.println("You won!");
        }
        else if (numUserWins < numComputerWins){
            System.out.println("The computer won.");
        }
        else{
            System.out.println("You tied with the computer.");
        }
        System.out.println("\nTies: " + numTies);
        System.out.println("User wins: " + numUserWins);
        System.out.println("Computer wins: " + numComputerWins);
    }
}
