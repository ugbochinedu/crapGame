package DiceGame;

import java.security.SecureRandom;
import java.util.Scanner;

public class DiceGame {
    Scanner input = new Scanner(System.in);
    SecureRandom random = new SecureRandom();
    private int sumOfDice;
    private int wager;
    private enum Status {WON, CONTINUE, LOST}
    private int bankBalance = 1000;
    private int sum;
    private int myPoint;
    Status gameStatus;

    public int sumFromRolledDice(){
        int firstRoll = 1 + random.nextInt(6);
        int secondRoll = 1 + random.nextInt(6);

        sum = firstRoll + secondRoll;

        return sum;
    }

    public void checkWager(){
        System.out.println("You have " + bankBalance + " as your bank balance");
        System.out.println();
        System.out.println("Enter wager to bet");
        wager = input.nextInt();

        if(wager <= bankBalance){
            switchSum();
        }else {
            System.out.println("You have insufficient fund.\nDeposit To play");
        }
    }

    public void playerWins(){
        if (gameStatus == Status.WON) {
            bankBalance += wager;
            System.out.println("Player wins.");
            System.out.println("Your new Balance is " + bankBalance);
        }
    }

    public void playerLosses(){
        if (gameStatus == Status.LOST) {
            bankBalance -= wager;
            System.out.println("Player losses");
            System.out.println("Your new Balance is " + bankBalance);
        }
    }

    public void switchSum(){
        sumOfDice = sumFromRolledDice();
        myPoint = 0;

        switch (sum) {
            case 7, 11 -> gameStatus = Status.WON;
            case 2, 3, 12 -> gameStatus = Status.LOST;
            default -> {
                gameStatus = Status.CONTINUE;
                myPoint = sumOfDice;
                System.out.println(myPoint);
                continueToPlay();
            }
        }
        displayGameStatus();
        doYouWishToContinue();
    }

    public void continueToPlay(){
        while (gameStatus == Status.CONTINUE){
            sumOfDice = sumFromRolledDice();
            if (sumOfDice == myPoint){
                gameStatus = Status.WON;
            } else if (sumOfDice == 7){
                gameStatus = Status.LOST;
            }
        }
    }

    public void displayGameStatus(){
        if (gameStatus == Status.WON){
            System.out.println(sum);
            playerWins();
        }else{
            System.out.println(sum);
            playerLosses();
        }
    }

    public void doYouWishToContinue(){
        System.out.println("Do you wish to continue, Press 1 to continue or 0 to exit");
        int userInput = input.nextInt();

        if (userInput == 1){
            checkWager();
        }else if(userInput == 0) {
            System.exit(0);
        }else {
            doYouWishToContinue();
        }
    }
}
