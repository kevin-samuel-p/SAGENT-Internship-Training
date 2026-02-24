package in.edu.eec.easwari.Personal.Budget.Tracker.App.service;

import java.util.List;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.DashboardSummaryDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.MonthlyFinanceDTO;

public interface DashboardService {

    DashboardSummaryDTO getDashboardSummary(Long userId);
    List<MonthlyFinanceDTO> getMonthlyAnalytics(Long userId);
}
