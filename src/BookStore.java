import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Reads and writes the book list in a small JSON file. */
public class BookStore {
    private static final Pattern BOOK = Pattern.compile(
        "\\{\\s*\"title\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"\\s*,\\s*\"author\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"\\s*\\}");

    private final Path file;

    public BookStore(String fileName) {
        this.file = Paths.get(fileName);
    }

    public List<Book> load() throws IOException {
        List<Book> books = new ArrayList<>();
        if (!Files.exists(file)) {
            return books;
        }
        String json = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        Matcher matcher = BOOK.matcher(json);
        while (matcher.find()) {
            books.add(new Book(unescape(matcher.group(1)), unescape(matcher.group(2))));
        }
        return books;
    }

    public void save(List<Book> books) throws IOException {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            json.append("  {\"title\": \"").append(escape(book.getTitle()))
                .append("\", \"author\": \"").append(escape(book.getAuthor())).append("\"}");
            json.append(i < books.size() - 1 ? ",\n" : "\n");
        }
        json.append("]\n");
        Files.write(file, json.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static String escape(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String unescape(String text) {
        return text.replaceAll("\\\\(.)", "$1");
    }
}
