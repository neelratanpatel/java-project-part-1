import java.util.Random;
import java.util.Scanner;

public class CWN_C20_EXERCISE_2_ROCK_PAPER_SCISSOR_GAME {

    public static void main(String[] args) {
        // create instance of Random class
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int user = 0;
        int computer = 0;
        System.out.println("press 0 for rock");
        System.out.println("press 1 for scissor");
        System.out.println("press 2 for paper");
        System.out.println("\n**********************************\n");


        for(int i=1;i<=5;i++)
        {
            // Generate random integers in range 0 to 2
            int rand_int1 = rand.nextInt(3);
            System.out.print("you press : ");
            int man = sc.nextInt();
                if(man>=3)
                {
                    System.out.println("invalid user input");
//                    System.out.println("user won "+user+" computer won "+computer);
//                    System.out.println("lap "+i+" is complete");
                    break;
                }
            // Print random integers
            System.out.print("computer press: ");
            if(rand_int1 == 0){System.out.println("rock");}
            else if (rand_int1 == 1) { System.out.println("scissor");}
            else if (rand_int1 == 2) { System.out.println("paper");}






                if(man == 0 && rand_int1 == 0) {
                    System.out.println("game is tie");
                }
                else if (man == 0 && rand_int1 == 1) {
                    System.out.println("user won");
                    user = ++user;
                }
                else if (man == 0 && rand_int1 == 2) {
                    System.out.println("computer won");
                    computer = ++computer;
                }
                else if (man == 1 && rand_int1 == 0) {
                    System.out.println("computer won");
                    computer = ++computer;
                }
                else if (man == 1 && rand_int1 == 1) {
                    System.out.println("game is tie");
                }
                else if (man == 1 && rand_int1 == 2){
                    System.out.println("user won");
                    user = ++user;
                }
                else if(man == 2 && rand_int1 == 0){
                    System.out.println("user won");
                    user = ++user;
                }
                else if(man == 2 && rand_int1 == 1){
                    System.out.println("computer won");
                    computer = ++computer;
                }
                else if(man == 2 && rand_int1 == 2){
                    System.out.println("game is tie");
                }


            System.out.println("user won "+user+" computer won "+computer);
            System.out.println("lap "+i+" is complete\n");
            System.out.println("**********************************\n");
        }
        if (user>computer)
        {
            System.out.println("gmae winner is user");
        }
        else if(computer>user)
        {
            System.out.println("game winner is computer");
        }
        else
        {
            System.out.println("all game is tie");
        }

    }
}
