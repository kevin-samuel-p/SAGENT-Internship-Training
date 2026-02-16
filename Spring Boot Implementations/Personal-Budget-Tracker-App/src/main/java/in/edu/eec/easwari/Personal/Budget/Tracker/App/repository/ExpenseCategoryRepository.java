package in.edu.eec.easwari.Personal.Budget.Tracker.App.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.ExpenseCategory;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    Optional<ExpenseCategory> findByCategoryName(String categoryName);
}
