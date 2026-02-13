package in.edu.eec.easwari.Library.Management.System.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Library.Management.System.entity.Book;
import in.edu.eec.easwari.Library.Management.System.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /*
     * Get all books
     * GET /api/books
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    /*
     * Get available books
     * GET /api/books/available
     */
    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    /*
     * Search by title
     * GET /api/books/search/title?value=java
     */
    @GetMapping("/search/title")
    public List<Book> searchByTitle(@RequestParam String title) {
        return bookService.searchByTitle(title);
    }

    /*
     * Search by author
     */
    @GetMapping("/search/author")
    public List<Book> searchByAuthor(@RequestParam String author) {
        return bookService.searchByAuthor(author);
    }

    /*
     * Search by subject
     */
    @GetMapping("/search/subject")
    public List<Book> searchBySubject(@RequestParam String subject) {
        return bookService.searchBySubject(subject);
    }
}
