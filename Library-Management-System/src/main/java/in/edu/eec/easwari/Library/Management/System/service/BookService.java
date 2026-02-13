package in.edu.eec.easwari.Library.Management.System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Library.Management.System.entity.Book;
import in.edu.eec.easwari.Library.Management.System.enums.BookStatus;
import in.edu.eec.easwari.Library.Management.System.repository.BookRepository;

@Service
public class BookService {
    
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByBookStatus(BookStatus.AVAILABLE);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                             .orElseThrow(() -> new RuntimeException("Book not found!"));
    }

    public List<Book> searchByTitle(String bookTitle) {
        return bookRepository.findByBookTitleContainingIgnoreCase(bookTitle);
    }

    public List<Book> searchByAuthor(String authorName) {
        return bookRepository.findByAuthorAuthorNameContainingIgnoreCase(authorName);
    }

    public List<Book> searchBySubject(String subjectName) {
        return bookRepository.findBySubjectSubjectNameContainingIgnoreCase(subjectName);
    }

    public Book updateBookStatus(Long bookId, BookStatus status) {
        Book book = getBookById(bookId);
        book.setBookStatus(status);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
