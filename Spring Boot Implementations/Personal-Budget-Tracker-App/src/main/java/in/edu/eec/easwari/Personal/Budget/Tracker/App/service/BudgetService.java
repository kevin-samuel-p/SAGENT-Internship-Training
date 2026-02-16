package in.edu.eec.easwari.Personal.Budget.Tracker.App.service;

import java.util.List;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.BudgetRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.BudgetResponseDTO;

public interface BudgetService {

    BudgetResponseDTO setBudget(BudgetRequestDTO request);

    List<BudgetResponseDTO> getMonthlyBudgets(Long userId, Integer month, Integer year);
}
