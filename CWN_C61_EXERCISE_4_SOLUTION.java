
class Library1{
    String[] books;
    int no_of_books;
    Library1(){
        this.books = new String[100];
        this.no_of_books = 0;
    }
    void addBook(String book){
        this.books[no_of_books] = book;
        no_of_books++;
        System.out.println(book + "has been added");
    }
    void showAvailableBooks(){
        System.out.println("avilable books are");
        for(String book: this.books)
        {
            if(book==null)
            {
                continue;
            }
            System.out.println("* "+book);
        }
    }
    void issueBook(String book){
        for(int i = 0;i<this.books.length;i++)
        {
            if(this.books[i].equals(book)){
                System.out.println("The book has been issued!");
                this.books[i] = null;
                return;
            }
            System.out.println("* "+book);
        }
        System.out.println("this book does not exist");
    }
    void returnBook(String book){
        addBook(book); 
    }
}

public class CWN_C61_EXERCISE_4_SOLUTION {
    public static void main(String[] args) {
        //You have to implement a library using Java Class "Library"
        //Methods: addBook,IssueBook, returnBook, showAvailableBooks
        //Properties: Array to store the available books,
        // Array to store the issued books

        Library1 centralLibrary = new Library1();
        centralLibrary.addBook("Think and grow rich");
        centralLibrary.addBook("c++");
        centralLibrary.addBook("java");

        centralLibrary.issueBook("c++");
        centralLibrary.showAvailableBooks();

    }
}
