package in.edu.eec.easwari.Personal.Budget.Tracker.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserGoal;

public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
    List<UserGoal> findByUser_UserId(Long userId);
}
