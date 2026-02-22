package in.edu.eec.easwari.Personal.Budget.Tracker.App.service;

import java.math.BigDecimal;
import java.util.List;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.IncomeSourceDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.IncomeRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.IncomeResponseDTO;

public interface IncomeService {

    IncomeResponseDTO addIncome(IncomeRequestDTO request);
    void removeIncome(Long incomeId);

    List<IncomeResponseDTO> getUserIncomes(Long userId);
    BigDecimal getTotalIncome(Long userId);

    List<IncomeSourceDTO> getAllSources();
}
