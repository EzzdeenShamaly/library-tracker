import java.io.IOException;
import java.util.List;

public class ListCommand {
    public static void run(BookStore store, String[] args) throws IOException {
        List<Book> books = store.load();
        if (books.isEmpty()) {
            System.out.println("No books yet.");
            return;
        }
        for (Book book : books) {
            System.out.println("- " + book);
        }
    }
}
