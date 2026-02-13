package in.edu.eec.easwari.Library.Management.System.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Library.Management.System.entity.Borrowing;
import in.edu.eec.easwari.Library.Management.System.service.BorrowingService;
import in.edu.eec.easwari.Library.Management.System.service.PaymentService;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {
    private final BorrowingService borrowingService;
    private final PaymentService paymentService;

    public BorrowingController(BorrowingService borrowingService,
                               PaymentService paymentService) {
        this.borrowingService = borrowingService;
        this.paymentService = paymentService;
    }

    /*
     * Member requests a book
     * POST /api/borrowings/request?userId={userId}&bookId={userId}
     */
    @PostMapping("/request")
    public Borrowing requestBook(@RequestParam Long user, 
                                 @RequestParam Long book) {
        return borrowingService.requestBorrowing(user, book);
    }

    /*
     * Librarian issues book
     * PUT /api/borrowings/{id}/issue
     */
    @PutMapping("/{id}/issue")
    public Borrowing issueBook(@PathVariable Long id) {
        return borrowingService.issueBook(id);
    }

    /*
     * User pays fine
     * PUT /api/borrowings/{id}/pay
     */
    @PutMapping("/{id}/pay")
    public Borrowing payFine(@PathVariable Long id) {
        return paymentService.payFine(id);
    }

    /*
     * Get Active Loans By User
     * GET /api/borrowings/user/{userId}/active
     */
    @GetMapping("/user/{id}/active")
    public List<Borrowing> getActiveLoansByUser(@PathVariable Long id) {
        return borrowingService.getActiveLoansByUser(id);
    }
}
