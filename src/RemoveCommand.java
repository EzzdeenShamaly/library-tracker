import java.io.IOException;
import java.util.List;

public class RemoveCommand {
    public static void run(BookStore store, String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: remove <title>");
            return;
        }
        List<Book> books = store.load();
        if (books.removeIf(book -> book.getTitle().equals(args[1]))) {
            store.save(books);
            System.out.println("Removed: " + args[1]);
        }
    }
}
