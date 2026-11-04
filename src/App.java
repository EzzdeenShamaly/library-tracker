import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        print_banner();
        if (args.length == 0) {
            printUsage();
            return;
        }
        BookStore store = new BookStore("books.json");
        switch (args[0]) {
            case "add":
                AddCommand.run(store, args);
                break;
            case "list":
                ListCommand.run(store, args);
                break;
            case "search":
                SearchCommand.run(store, args);
                break;
            case "remove":
                RemoveCommand.run(store, args);
                break;
            default:
                printUsage();
        }
    }

    static void print_banner() {
        System.out.println("=== Library Tracker - Java Project ===");
    }

    static void printUsage() {
        System.out.println("Commands: add <title> <author> | list | search <title> | remove <title>");
    }
}
