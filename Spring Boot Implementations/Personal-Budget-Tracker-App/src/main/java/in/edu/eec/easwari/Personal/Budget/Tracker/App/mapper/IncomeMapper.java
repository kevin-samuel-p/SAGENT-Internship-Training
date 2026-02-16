package in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.IncomeResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserIncome;

public class IncomeMapper {

    public static IncomeResponseDTO toDTO(UserIncome income) {
        return IncomeResponseDTO.builder()
                .incomeId(income.getIncomeId())
                .sourceName(income.getIncomeSource().getSourceName())
                .amount(income.getAmount())
                .incomeDate(income.getIncomeDate())
                .build();
    }
}
