package in.edu.eec.easwari.Personal.Budget.Tracker.App.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.IncomeSource;

public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long> {
    Optional<IncomeSource> findBySourceNameContainingIgnoreCase(String sourceName);
}
