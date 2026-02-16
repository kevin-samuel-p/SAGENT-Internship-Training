package in.edu.eec.easwari.Personal.Budget.Tracker.App.service;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.DashboardSummaryDTO;

public interface DashboardService {

    DashboardSummaryDTO getDashboardSummary(Long userId);
}
