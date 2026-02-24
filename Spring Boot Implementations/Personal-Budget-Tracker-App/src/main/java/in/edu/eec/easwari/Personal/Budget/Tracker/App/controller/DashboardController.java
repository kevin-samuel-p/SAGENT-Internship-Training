package in.edu.eec.easwari.Personal.Budget.Tracker.App.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.DashboardSummaryDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.MonthlyFinanceDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.DashboardService;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ApiResponse;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.util.ResponseUtil;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<DashboardSummaryDTO>> getDashboard(
            @PathVariable Long userId) {

        DashboardSummaryDTO summary =
                dashboardService.getDashboardSummary(userId);

        return ResponseEntity.ok(
                ResponseUtil.success(summary, "Dashboard summary fetched successfully")
        );
    }

    @GetMapping("/analytics/monthly/{userId}")
    public ResponseEntity<ApiResponse<List<MonthlyFinanceDTO>>> monthly(
            @PathVariable Long userId) {

        List<MonthlyFinanceDTO> analytics = 
                dashboardService.getMonthlyAnalytics(userId);
                
        return ResponseEntity.ok(
                ResponseUtil.success(analytics, "Monthly analytics fetched successfully"));
    }
}
