package in.edu.eec.easwari.Personal.Budget.Tracker.App.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.BudgetRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.BudgetResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ApiResponse;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.BudgetService;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<ApiResponse<BudgetResponseDTO>> setBudget(
            @RequestBody BudgetRequestDTO request) {

        BudgetResponseDTO response = budgetService.setBudget(request);

        return ResponseEntity.ok(
                ResponseUtil.success(response, "Budget set successfully"));
    }

    @GetMapping("/{userId}/{month}/{year}")
    public ResponseEntity<ApiResponse<List<BudgetResponseDTO>>> getMonthlyBudgets(
            @PathVariable Long userId,
            @PathVariable Integer month,
            @PathVariable Integer year) {

        List<BudgetResponseDTO> budgets =
                budgetService.getMonthlyBudgets(userId, month, year);

        return ResponseEntity.ok(
                ResponseUtil.success(budgets, "Budgets fetched successfully"));
    }
}
