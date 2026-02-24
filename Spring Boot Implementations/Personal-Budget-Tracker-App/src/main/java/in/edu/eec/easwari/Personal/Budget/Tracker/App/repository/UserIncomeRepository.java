package in.edu.eec.easwari.Personal.Budget.Tracker.App.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.MonthlyFinanceDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserIncome;

public interface UserIncomeRepository extends JpaRepository<UserIncome, Long> {
    List<UserIncome> findByUser_UserId(Long userId);
    List<UserIncome> findByUser_UserIdAndIncomeDateBetween(
            Long userId, 
            LocalDate start, 
            LocalDate end);

    @Query("""
        SELECT new in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.MonthlyFinanceDTO(
            MONTH(i.incomeDate),
            COALESCE(SUM(i.amount), CAST(0 as java.math.BigDecimal)),
            CAST(0 as java.math.BigDecimal)
        )
        FROM UserIncome i
        WHERE i.user.userId = :userId
        GROUP BY MONTH(i.incomeDate)
    """)
    List<MonthlyFinanceDTO> getMonthlyIncome(Long userId);

    @Query("""
           SELECT COALESCE(SUM(ui.amount),0)
           FROM UserIncome ui
           WHERE ui.user.userId = :userId
           """)
    BigDecimal getTotalIncome(Long userId);
}
