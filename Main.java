import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Game{
    int noOfGuesses = 0;
    int rand_int1;
    int input;
    ArrayList<Integer> inputs = new ArrayList<>();
    String sInputs = "";

    public Game(){

        Random rand = new Random();
        rand_int1 = rand.nextInt(100);
        takeUserInput();
        isCorrectNumber();
    }
    public int getNoOfGuesses(){

        noOfGuesses = noOfGuesses + 1;
        return noOfGuesses;
    }
    public void setNoOfGuesses()
    {
        System.out.println("you guess the number " + noOfGuesses + " times");
    }
    public void takeUserInput(){
        System.out.println("\n ****************************************** ");
        System.out.println("guess the number");
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        inputs.add(input);
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
        } else if (rand_int1<input) {
            System.out.println("your guess is greater than number");
            takeUserInput();
            isCorrectNumber();
        } else if (rand_int1 == input) {
            System.out.println("wow ! you guess the number");
            System.out.println("the number is " + rand_int1);
            for(Integer num : inputs){
                sInputs += num +",";
            }
            System.out.println("your guess numbers is " + sInputs);
        }
    }

}


public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/db_2";
    private static final String username = "root";
    private static final String password = "NeelArc1234";


    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.println("This game is Guess The Number");
                System.out.println("And numbers Between 0 - 100");
                System.out.println();
                System.out.println(" ----------- Enter your choice ------------- ");
                System.out.println("Start game press - 1");
                System.out.println("Show History press - 2");
                System.out.println("Delete History press - 3");
                System.out.println("Leave this game press - 0");
                System.out.println("----------------------------------------------");
                String string = scanner.next();
                int choice  = Integer.parseInt(String.valueOf(string));
                if (choice == 0){
                    break;
                }
                if(choice == 1)
                {
                    System.out.println("Enter your name");
                    String name = scanner.next();
                    System.out.println("user name is - "+name);
                    LocalDateTime dt = LocalDateTime.now();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy" + " H:m" +" a " + " --E");
                    String mydate = dt.format(dtf);
                    System.out.println(mydate);
                    Game game = new Game();
                    String query = "INSERT INTO guessTheNumber(name,date,timeGuess,guessNumber,guessNumbers) VALUES (?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,name);
                    preparedStatement.setString(2,mydate);
                    preparedStatement.setInt(3,game.noOfGuesses);
                    preparedStatement.setInt(4,game.rand_int1);
                    preparedStatement.setString(5,game.sInputs);

                    preparedStatement.addBatch();

                    int [] arr = preparedStatement.executeBatch();
                    for(int i = 0;i<arr.length;i++)
                    {
                        if(arr[i] == 0 ){
                            System.out.println("data not stored");
                        }
                    }
                }
                if(choice == 2)
                {
                    System.out.println("------------------Enter Your Choice------------------");
                    System.out.println("Show all - 1");
                    System.out.println("Random   - 2");
                    System.out.println("-----------------------------------------------------");
                    int choice2 = scanner.nextInt();
                    if(choice2 == 1)
                    {
                        String query = "select * from guessTheNumber";
                        ResultSet resultSet = statement.executeQuery(query);
                        System.out.println("\nAll History is show in below");

                        while(resultSet.next())
                        {
                            System.out.printf("----------------- %d ----------------- \n",resultSet.getInt("id"));
                            System.out.println("Name : " + resultSet.getString("name"));
                            System.out.println("Date : " + resultSet.getString("date"));
                            System.out.println("Time Guess : " + resultSet.getInt("timeGuess"));
                            System.out.println("Guess Number : " + resultSet.getInt("guessNumber"));
                            System.out.println("Guess Numbers : " + resultSet.getString("guessNumbers"));
                        }
                    }
                    if (choice2 == 2)
                    {
                        String query = "select * from guessTheNumber";
                        ResultSet resultSet = statement.executeQuery(query);
                        System.out.println("\nAll History is show in below");
                        while(resultSet.next())
                        {
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");

                            System.out.println("Id : " + id + " -> Name : " + name);
                        }
                        query = "SELECT * FROM guessTheNumber WHERE id = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        System.out.println("------------Enter id-------------");
                        int id = scanner.nextInt();
                        preparedStatement.setInt(1,id);
                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()){
                            System.out.println("id : " +resultSet.getInt("id"));
                            System.out.println("Name : " +resultSet.getString("name"));
                            System.out.println("Date : " + resultSet.getString("date"));
                            System.out.println("Time Guess : " + resultSet.getInt("timeGuess"));
                            System.out.println("Guess Number : " + resultSet.getInt("guessNumber"));
                            System.out.println("Guess Numbers : " + resultSet.getString("guessNumbers"));
                        }
                        else {
                            System.out.println("id not found");
                        }
                    }
                }
                if(choice == 3)
                {
                    System.out.println("------------------Enter Your Choice------------------");
                    System.out.println("Delete all - 1");
                    System.out.println("Delete Random   - 2");
                    System.out.println("-----------------------------------------------------");
                    int choice2 = scanner.nextInt();
                    if (choice2 == 1)
                    {
                        String query = "DELETE FROM guessTheNumber";
                        int rowsAffected = statement.executeUpdate(query);
                        if(rowsAffected>0)
                        {
                            System.out.println("History Deleted Successfully");
                        }
                        else{
                            System.out.println("There is no data for delete");
                        }
                    }
                    if (choice2 == 2)
                    {
                        String query = "select * from guessTheNumber";
                        ResultSet resultSet = statement.executeQuery(query);
                        System.out.println("\nAll History is show in below");
                        while(resultSet.next())
                        {
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");

                            System.out.println("Id : " + id + " -> Name : " + name);
                        }
                        System.out.println("Enter the Id for Delete");
                        int id = scanner.nextInt();
                        query = "DELETE FROM guessTheNumber WHERE id = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1,id);
                        int rowsAffected = preparedStatement.executeUpdate();
                        if(rowsAffected>0)
                        {
                            System.out.println("History Deleted Successfully");
                        }
                        else
                        {
                            System.out.println("History Not Deleted");
                        }
                    } else {
                        System.out.println("Invalid input");
                    }
                }
                System.out.println("\n--------For play another round press(Y/N)---------");
                String choice1 = scanner.next();
                if(choice1.toUpperCase().equals("N"))
                {
                    break;
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}