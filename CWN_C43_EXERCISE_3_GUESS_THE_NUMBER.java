

import java.util.Random;
import java.util.Scanner;

/**
 * @author Neel
 * @version 0.1
 * @since 2004
 * @see <a href="https://docs.oracle.com/en/java/javase/14/docs/api/index.html></a>  Java Docs

 */

class Game {
    int noOfGuesses = 0;
    int rand_int1;
    int input;
    public Game()
    {
        Random rand = new Random();
        rand_int1 = rand.nextInt(100);
        takeUserInput();
        isCorrectNumber();
    }
    public int getNoOfGuesses()
    {
        noOfGuesses = noOfGuesses + 1;
        return noOfGuesses;
    }
    public void setNoOfGuesses()
    {
        System.out.println("You guess the number " + noOfGuesses + " times");
    }
    public void takeUserInput()
    {
        System.out.println("\n ************************************ \n");
        System.out.print("gess the number ");
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        getNoOfGuesses();
        setNoOfGuesses();
    }
    public void isCorrectNumber()
    {
        if(rand_int1>input)
        {
            System.out.println("your guess is less than number");
            takeUserInput();
            isCorrectNumber();
        }
        else if(rand_int1<input)
        {
            System.out.println("your guess is greater than number");
            takeUserInput();
            isCorrectNumber();
        }
        else if (rand_int1 == input)
        {
            System.out.println("WoW ! you guess the number");
            System.out.println("the number is " + rand_int1);
        }
    }
}

public class CWN_C43_EXERCISE_3_GUESS_THE_NUMBER {
    public static void main(String[] args) {
        Game user1 = new Game();
    }
}
