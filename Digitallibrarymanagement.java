import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Digitallibrarymanagement {

    public static void main(String[] args) {
        // Initialize the library system
        LibrarySystem librarySystem = new LibrarySystem();

        // Add some sample books and members
        librarySystem.addBook(new Book("Java Programming", "John Doe", "Programming"));
        librarySystem.addBook(new Book("Design Patterns", "Gang of Four", "Software Engineering"));

        librarySystem.addMember(new Member("Alice", "alice@example.com"));
        librarySystem.addMember(new Member("Bob", "bob@example.com"));

        // Demonstrate functionality
        System.out.println("All Books:");
        List<Book> allBooks = librarySystem.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book);
        }

        System.out.println("\nAll Members:");
        List<Member> allMembers = librarySystem.getAllMembers();
        for (Member member : allMembers) {
            System.out.println(member);
        }
    }
}

class Book {
    private Long id;
    private String title;
    private String author;
    private String category;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

class Member {
    private Long id;
    private String name;
    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

class LibrarySystem {
    private Map<Long, Book> books;
    private Map<Long, Member> members;
    private Long nextBookId;
    private Long nextMemberId;

    public LibrarySystem() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.nextBookId = 1L;
        this.nextMemberId = 1L;
    }

    public void addBook(Book book) {
        book.setId(nextBookId++);
        books.put(book.getId(), book);
    }

    public void addMember(Member member) {
        member.setId(nextMemberId++);
        members.put(member.getId(), member);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }
}
