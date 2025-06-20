import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

class Library
{
    String[] availableBooks = {"java","html","css","javascript","c","c++","android studio"};
    String[] issuedBooks = new String[0];
    Library()
    {
        detail();
    }

    public void detail()
    {
        int inputnumber;
        System.out.println("\n*************************************\n");
        System.out.println("Your are the librarian");
        System.out.println("Press 1 for Add Book");
        System.out.println("Press 2 for Issue Book");
        System.out.println("Press 3 for Show issued Book");
        System.out.println("Press 4 for Return Book");
        System.out.println("Press 5 for Show Available Books");
        System.out.println("Press 0 for exit");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your choice .........");
        inputnumber = sc.nextInt();
        if(inputnumber==0){

        }
        else if(inputnumber == 1)
        {
            addBook();
            detail();
        }
        else if (inputnumber == 2)
        {
            issueBook();
            detail();
        }
        else if(inputnumber == 3){
            showIssuedBooks();
            detail();
        }
        else if (inputnumber == 4) {
            returnBook();
            detail();
        } else if (inputnumber == 5) {
            showAvailableBooks();
            detail();
        }
        else
        {
            System.out.println("Oops! wrong input , Please try again leter");
            detail();
        }
    }


    public void addBook(){
        System.out.print("Plese enter the book name: ");
        Scanner sc = new Scanner(System.in);
        String newBook = sc.nextLine();
        String[] newAvailableBooks = new String[availableBooks.length+1];
        System.arraycopy(availableBooks, 0, newAvailableBooks, 0, availableBooks.length);
        newAvailableBooks[availableBooks.length] = newBook;
        availableBooks = newAvailableBooks;
        System.out.println(newBook + " is added");
    }


    public void issueBook(){
        showAvailableBooks();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the book number do you want to issue");
        int bookIndexToMove = sc.nextInt() - 1;
        if(bookIndexToMove>=0 && bookIndexToMove < availableBooks.length)
        {
            String bookToMove = availableBooks[bookIndexToMove];
            List<String> issuedList = new ArrayList<>(Arrays.asList(issuedBooks));
            issuedList.add(bookToMove);
            issuedBooks = issuedList.toArray(new String[0]);

            List<String> availableList = new ArrayList<>(Arrays.asList(availableBooks));
            availableList.remove(bookIndexToMove);
            availableBooks=availableList.toArray(new String[0]);
        }
        else
        {
            System.out.println("Invalid book index: " + bookIndexToMove);
        }
    }


    public void showIssuedBooks()
    {
        System.out.println("issued books are");
        for(int i=0;i<issuedBooks.length;i++)
        {
            System.out.println( i+1  + " )." +issuedBooks[i] + "    ");
        }
    }


    public void returnBook(){
        showIssuedBooks();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the book number do you want to issue");
       int bookIndexToMove = sc.nextInt() - 1;
        if(bookIndexToMove>=0 && bookIndexToMove < issuedBooks.length)
        {
            String bookToReturn = issuedBooks[0]; // Get the first element

            // Add to availableBooks (ArrayList)
            List<String> availableList = new ArrayList<>(Arrays.asList(availableBooks));
            availableList.add(bookToReturn);
            availableBooks = availableList.toArray(new String[0]);

            // Remove from issuedBooks (ArrayList)
            List<String> issuedList = new ArrayList<>(Arrays.asList(issuedBooks));
            issuedList.remove(0); // Remove the element at index 0
            issuedBooks = issuedList.toArray(new String[0]);
            System.out.println(bookToReturn + " Book is succesfully returned");
        }
        else
        {
            System.out.println("Invalid book index: " + bookIndexToMove);
        }
    }


    public void showAvailableBooks(){
        System.out.println("Available books are");
        for(int i=0;i<availableBooks.length;i++)
        {
            System.out.println( i+1  + " )." +availableBooks[i] + "    ");
        }

    }
}


public class CWN_C51_EXERCISE_4_ONLINE_LIBRARY {
    public static void main(String[] args) {
        // You have to implement a library using Java Class Library
        // Methods : addBook,issueBook,returnBook,showAvailableBooks
        // Properties: Array to store the available books, Array to store the issued books
        Library l = new Library();
    }
}
