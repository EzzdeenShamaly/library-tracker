import java.io.IOException;
import java.util.List;

public class AddCommand {
    public static void run(BookStore store, String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Usage: add <title> <author>");
            return;
        }
        if (args[1].trim().isEmpty() || args[2].trim().isEmpty()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }
        List<Book> books = store.load();
        books.add(new Book(args[1], args[2]));
        store.save(books);
        System.out.println("Added: " + args[1]);
    }
}
