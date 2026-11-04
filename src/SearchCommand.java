import java.io.IOException;

public class SearchCommand {
    public static void run(BookStore store, String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: search <title>");
            return;
        }
        for (Book book : store.load()) {
            if (book.getTitle().contains(args[1])) {
                System.out.println("- " + book);
            }
        }
    }
}
