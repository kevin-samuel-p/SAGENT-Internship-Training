package in.edu.eec.easwari.Library.Management.System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Library.Management.System.entity.Author;
import in.edu.eec.easwari.Library.Management.System.entity.Book;
import in.edu.eec.easwari.Library.Management.System.entity.Subject;
import in.edu.eec.easwari.Library.Management.System.enums.BookStatus;
import in.edu.eec.easwari.Library.Management.System.repository.AuthorRepository;
import in.edu.eec.easwari.Library.Management.System.repository.BookRepository;
import in.edu.eec.easwari.Library.Management.System.repository.SubjectRepository;

@Service
public class InventoryService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final SubjectRepository subjectRepository;

    public InventoryService(BookRepository bookRepository, 
                            AuthorRepository authorRepository, 
                            SubjectRepository subjectRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
    }

    public Book addBook(String title, String authorName, String subjectName) {

        Author author = authorRepository
                .findByAuthorName(authorName)
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setAuthorName(authorName);
                    return authorRepository.save(newAuthor);
                });

        Subject subject = subjectRepository
                .findBySubjectName(subjectName)
                .orElseGet(() -> {
                    Subject newSubject = new Subject();
                    newSubject.setSubjectName(subjectName);
                    return subjectRepository.save(newSubject);
                });

        Book book = new Book();
        book.setBookTitle(title);
        book.setAuthor(author);
        book.setSubject(subject);
        book.setBookStatus(BookStatus.AVAILABLE);

        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, String bookTitle, String authorName, String subjectName) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        if (bookTitle != null) book.setBookTitle(bookTitle);

        if (authorName != null) {
            Author author = authorRepository
                .findByAuthorName(authorName)
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setAuthorName(authorName);
                    return authorRepository.save(newAuthor);
                });
            book.setAuthor(author);
        }

        if (subjectName != null) {
            Subject subject = subjectRepository
                .findBySubjectName(subjectName)
                .orElseGet(() -> {
                    Subject newSubject = new Subject();
                    newSubject.setSubjectName(subjectName);
                    return subjectRepository.save(newSubject);
                });
            book.setSubject(subject);
        }
        
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Book markDamaged(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        book.setBookStatus(BookStatus.DAMAGED);
        return bookRepository.save(book);
    }

    public Book markLost(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found!"));
        
        book.setBookStatus(BookStatus.LOST);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
