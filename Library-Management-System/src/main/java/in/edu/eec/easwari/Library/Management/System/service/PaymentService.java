package in.edu.eec.easwari.Library.Management.System.service;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Library.Management.System.entity.Borrowing;
import in.edu.eec.easwari.Library.Management.System.enums.PaymentStatus;
import in.edu.eec.easwari.Library.Management.System.repository.BorrowingRepository;

@Service
public class PaymentService {
    
    private final BorrowingRepository borrowingRepository;

    public PaymentService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public Borrowing setFine(Long requestId, Double fineAmount) {

        Borrowing borrowing = borrowingRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found!"));

        borrowing.setFineAmount(fineAmount);

        if (fineAmount != null && fineAmount > 0) {
            borrowing.setPaymentStatus(PaymentStatus.PENDING);
        } else {
            borrowing.setPaymentStatus(PaymentStatus.NOT_APPLICABLE);
        }

        return borrowingRepository.save(borrowing);
    }

    public Borrowing payFine(Long requestId) {

        Borrowing borrowing = borrowingRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found!"));

        borrowing.setPaymentStatus(PaymentStatus.PAID);

        return borrowingRepository.save(borrowing);
    }
}
