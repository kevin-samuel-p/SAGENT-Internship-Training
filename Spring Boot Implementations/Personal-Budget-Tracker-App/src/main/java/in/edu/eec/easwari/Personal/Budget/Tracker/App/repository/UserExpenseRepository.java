package in.edu.eec.easwari.Personal.Budget.Tracker.App.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserExpense;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    List<UserExpense> findByUser_UserId(Long userId);
    List<UserExpense> findByUser_UserIdAndExpenseDateBetween(
            Long userId,
            LocalDate start,
            LocalDate end);

    @Query("""
           SELECT COALESCE(SUM(ue.amount), 0)
           FROM UserExpense ue
           WHERE ue.user.userId = :userId
           """)
    BigDecimal getTotalExpensesByUser(Long userId);

    @Query("""
           SELECT COALESCE(SUM(ue.amount), 0)
           FROM UserExpense ue
           WHERE ue.user.userId = :userId
           AND MONTH(ue.expenseDate) = :month
           AND YEAR(ue.expenseDate) = :year
           """)
    BigDecimal getMonthlyExpenses(Long userId, int month, int year);

    @Query("""
           SELECT ue.category.categoryName,
                  COALESCE(SUM(ue.amount), 0)
           FROM UserExpense ue
           WHERE ue.user.userId = :userId
           AND MONTH(ue.expenseDate) = :month
           AND YEAR(ue.expenseDate) = :year
           GROUP BY ue.category.categoryName
           """)
    List<Object[]> getMonthlyExpensesByCategory(Long userId, int month, int year);
}