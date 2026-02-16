package in.edu.eec.easwari.Personal.Budget.Tracker.App.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserBudget;

public interface UserBudgetRepository extends JpaRepository<UserBudget, Long> {
    Optional<UserBudget> findByUser_UserIdAndCategory_CategoryIdAndMonthAndYear(
            Long userId,
            Long categoryId,
            Integer month,
            Integer year);

    List<UserBudget> findByUser_UserIdAndMonthAndYear(
            Long userId,
            Integer month,
            Integer year);
}