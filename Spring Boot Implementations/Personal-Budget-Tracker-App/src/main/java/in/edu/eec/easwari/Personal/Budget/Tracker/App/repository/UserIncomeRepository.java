package in.edu.eec.easwari.Personal.Budget.Tracker.App.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserIncome;

public interface UserIncomeRepository extends JpaRepository<UserIncome, Long> {
    List<UserIncome> findByUser_UserId(Long userId);
    List<UserIncome> findByUser_UserIdAndIncomeDateBetween(
            Long userId, 
            LocalDate start, 
            LocalDate end);

    @Query("""
           SELECT COALESCE(SUM(ui.amount), 0)
           FROM UserIncome ui
           WHERE ui.user.userId = :userId
           AND MONTH(ui.incomeDate) = :month
           AND YEAR(ui.incomeDate) = :year
           """)
    BigDecimal getMonthlyIncome(Long userId, int month, int year);
    BigDecimal getTotalIncomeByUser(Long userId);
}
