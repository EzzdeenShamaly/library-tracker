import java.io.IOException;

public class CountCommand {
    public static void run(BookStore store, String[] args) throws IOException {
        System.out.println("Books: " + store.load().size());
    }
}
