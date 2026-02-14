package in.edu.eec.easwari.Library.Management.System.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Library.Management.System.entity.Book;
import in.edu.eec.easwari.Library.Management.System.entity.Borrowing;
import in.edu.eec.easwari.Library.Management.System.entity.User;
import in.edu.eec.easwari.Library.Management.System.enums.BookStatus;
import in.edu.eec.easwari.Library.Management.System.enums.PaymentStatus;
import in.edu.eec.easwari.Library.Management.System.enums.RequestStatus;
import in.edu.eec.easwari.Library.Management.System.repository.BookRepository;
import in.edu.eec.easwari.Library.Management.System.repository.BorrowingRepository;
import in.edu.eec.easwari.Library.Management.System.repository.UserRepository;

@Service
public class BorrowingService {
    
    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowingService(BorrowingRepository borrowingRepository, 
                            UserRepository userRespository, 
                            BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userRepository = userRespository;
        this.bookRepository = bookRepository;
    }

    /* Member Calls */
    public Borrowing requestBorrowing(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found!"));

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Book not found!"));

        if (book.getBookStatus() != BookStatus.AVAILABLE)
            throw new RuntimeException("Book not available!");

        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setRequestStatus(RequestStatus.REQUESTED);
        borrowing.setPaymentStatus(PaymentStatus.NOT_APPLICABLE);

        return borrowingRepository.save(borrowing);
    }

    /* Librarian Calls */
    public Borrowing issueBook(Long requestId) {
        
        Borrowing borrowing = borrowingRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found!"));

        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setDueDate(LocalDate.now().plusDays((14)));
        borrowing.setRequestStatus(RequestStatus.ISSUED);

        Book book = borrowing.getBook();
        book.setBookStatus(BookStatus.ISSUED);

        return borrowingRepository.save(borrowing);
    }

    /* Librarian Calls */
    public Borrowing returnBook(Long requestId) {

        Borrowing borrowing = borrowingRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found!"));

        borrowing.setReturnDate(LocalDate.now());
        borrowing.setRequestStatus(RequestStatus.RETURNED);

        Book book = borrowing.getBook();
        book.setBookStatus(BookStatus.AVAILABLE);

        return borrowingRepository.save(borrowing);
    }

    public List<Borrowing> getBorrowingHistory(Long userId) {
        return borrowingRepository.findByUserUserId(userId);
    }

    public List<Borrowing> getActiveLoansByUser(Long userId) {
        return borrowingRepository.findByUserUserIdAndRequestStatus(userId, RequestStatus.ISSUED);
    }

    public List<Borrowing> getActiveLoans() {
        return borrowingRepository.findByRequestStatus(RequestStatus.ISSUED);
    }

    public List<Borrowing> getOverdueLoans() {
        return borrowingRepository
                .findByRequestStatusAndDueDateBefore(
                        RequestStatus.ISSUED,
                        LocalDate.now()
                );
    }
}
