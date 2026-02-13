package in.edu.eec.easwari.Library.Management.System.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Library.Management.System.entity.Borrowing;
import in.edu.eec.easwari.Library.Management.System.enums.RequestStatus;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUserUserId(Long userId);
    List<Borrowing> findByRequestStatus(RequestStatus status);
    List<Borrowing> findByRequestStatusAndDueDateBefore(RequestStatus status, LocalDate date);
    List<Borrowing> findByUserUserIdAndRequestStatus(Long userId, RequestStatus status);
}