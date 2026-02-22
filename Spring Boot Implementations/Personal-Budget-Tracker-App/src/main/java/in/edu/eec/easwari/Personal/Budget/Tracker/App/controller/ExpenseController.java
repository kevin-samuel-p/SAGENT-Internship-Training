package in.edu.eec.easwari.Personal.Budget.Tracker.App.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.ExpenseCategoryDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.ExpenseRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.ExpenseResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.ExpenseService;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ApiResponse;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.util.ResponseUtil;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponseDTO>> addExpense(
            @RequestBody ExpenseRequestDTO request) {

        ExpenseResponseDTO response = expenseService.addExpense(request);

        return ResponseEntity.ok(
                ResponseUtil.success(response, "Expense added successfully")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeExpense(@PathVariable Long id) {
        expenseService.removeExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ExpenseResponseDTO>>> getUserExpenses(
            @PathVariable Long userId) {

        List<ExpenseResponseDTO> expenses =
                expenseService.getUserExpenses(userId);

        return ResponseEntity.ok(
                ResponseUtil.success(expenses, "User expenses fetched successfully")
        );
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<ApiResponse<BigDecimal>> getTotalExpenses(
            @PathVariable Long userId) {

        BigDecimal total =
                expenseService.getTotalExpenses(userId);

        return ResponseEntity.ok(
                ResponseUtil.success(total, "Total expenses calculated successfully")
        );
    }

    @GetMapping("/categories/all")
    public ResponseEntity<ApiResponse<List<ExpenseCategoryDTO>>> getAllCategories() {
        
        List<ExpenseCategoryDTO> categories = 
                expenseService.getAllCategories();

        return ResponseEntity.ok(
                ResponseUtil.success(categories, "Retrieved all categories successfully")
        );
    }
}
