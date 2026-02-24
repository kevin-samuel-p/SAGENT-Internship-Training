package in.edu.eec.easwari.Personal.Budget.Tracker.App.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.BudgetUsageDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.MonthlyFinanceDTO;
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
        SELECT new in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.MonthlyFinanceDTO(
            MONTH(e.expenseDate),
            CAST(0 as java.math.BigDecimal),
            COALESCE(SUM(e.amount), CAST(0 as java.math.BigDecimal))
        )
        FROM UserExpense e
        WHERE e.user.userId = :userId
        GROUP BY MONTH(e.expenseDate)
    """)
    List<MonthlyFinanceDTO> getMonthlyExpenses(Long userId);

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

    @Query("""
        SELECT new in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.BudgetUsageDTO(
            c.categoryName,
            b.monthlyLimit,
            COALESCE(SUM(e.amount), 0),
            (COALESCE(SUM(e.amount), 0) * 100 / b.monthlyLimit)
        )
        FROM UserBudget b
        JOIN b.category c
        LEFT JOIN UserExpense e
            ON e.category = c
            AND e.user = b.user
            AND FUNCTION('MONTH', e.expenseDate) = b.month
            AND FUNCTION('YEAR', e.expenseDate) = b.year
        WHERE b.user.userId = :userId
        GROUP BY c.categoryName, b.monthlyLimit
    """)
    List<BudgetUsageDTO> getBudgetUsage(Long userId);
}