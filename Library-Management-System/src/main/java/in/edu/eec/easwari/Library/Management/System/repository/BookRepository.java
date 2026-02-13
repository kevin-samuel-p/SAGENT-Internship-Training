package in.edu.eec.easwari.Library.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Library.Management.System.entity.Book;
import in.edu.eec.easwari.Library.Management.System.enums.BookStatus;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorAuthorNameContainingIgnoreCase(String authorName);
    List<Book> findBySubjectSubjectNameContainingIgnoreCase(String subjectName);
    List<Book> findByBookStatus(BookStatus status);
}