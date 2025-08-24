package com.mohan;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
    /**
     * A simple command-line game where the user tries to guess a random number.
     * This program demonstrates user input, loops, conditional statements, and basic error
     handling.
     */
    public class Main {
        public static void main(String[] args) {
// Create a Scanner object to read user input from the console
            Scanner scanner = new Scanner(System.in);
// Create a Random object to generate a random number
            Random random = new Random();
// Generate a random number between 1 and 100 (inclusive)
// The nextInt(100) method generates a number from 0 to 99, so we add 1
            int numberToGuess = random.nextInt(500) + 1;
            int numberOfTries = 0;
            int userGuess = 0;
            boolean hasGuessedCorrectly = false;
            int numberOfGuesses = 10;
            System.out.println("--- Welcome to the Number Guessing Game! ---");
            System.out.println("You have 10 tries only!!!");
            System.out.println("I've picked a number between 1 and 500. Can you guess it?");
// This loop will continue until the user guesses the correct number
            while (!hasGuessedCorrectly && numberOfTries < numberOfGuesses) {
                    System.out.print("Enter your guess: ");
                    numberOfTries++;
                    try {
// Read the user's integer input
                        userGuess = scanner.nextInt();
// Compare the user's guess to the number to guess
                        if (userGuess < 1 || userGuess > 500) {
// Provide feedback for out-of-range guesses
                            System.out.println("Your guess is out of range. Please guess a number between 1 and 100.");
                        } else if (userGuess < numberToGuess) {
// Tell the user their guess was too low
                            System.out.println("Too low! Try a higher number.");
                        } else if (userGuess > numberToGuess) {
// Tell the user their guess was too high
                            System.out.println("Too high! Try a lower number.");
                        } else {
// The guess is correct! Break out of the loop.
                            hasGuessedCorrectly = true;
                        }
                    } catch (InputMismatchException e) {
// Handle cases where the user enters non-numeric input
                        System.out.println("Invalid input! Please enter a whole number.");
// Clear the invalid input from the scanner to prevent an infinite loop
                        scanner.next();
                    }
                }

                if(hasGuessedCorrectly){
                    // The loop has ended, so the user guessed correctly. Print a congratulatory message.
                    System.out.println("\nCongratulations! You guessed the number " + numberToGuess + "!");
                    System.out.println("It took you " + numberOfTries + " tries.");
                }
                else{
                    System.out.println("You ran out of tries, try again!");
                }

// Close the scanner to release system resources
                scanner.close();
            }
        }