package in.edu.eec.easwari.Personal.Budget.Tracker.App.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.IncomeSourceDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.IncomeRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.IncomeResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.IncomeService;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ApiResponse;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.util.ResponseUtil;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<ApiResponse<IncomeResponseDTO>> addIncome(
            @RequestBody IncomeRequestDTO request) {

        IncomeResponseDTO response = incomeService.addIncome(request);

        return ResponseEntity.ok(
                ResponseUtil.success(response, "Income added successfully")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeIncome(@PathVariable Long id) {
        incomeService.removeIncome(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<IncomeResponseDTO>>> getUserIncome(
            @PathVariable Long userId) {

        List<IncomeResponseDTO> incomes =
                incomeService.getUserIncomes(userId);

        return ResponseEntity.ok(
                ResponseUtil.success(incomes, "User incomes fetched successfully")
        );
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<ApiResponse<BigDecimal>> getTotalIncome(
            @PathVariable Long userId) {

        BigDecimal total =
                incomeService.getTotalIncome(userId);

        return ResponseEntity.ok(
                ResponseUtil.success(total, "Total income calculated successfully")
        );
    }

    @GetMapping("/sources/all")
    public ResponseEntity<ApiResponse<List<IncomeSourceDTO>>> getAllSources() {

        List<IncomeSourceDTO> sources = 
                incomeService.getAllSources();
        
        return ResponseEntity.ok(
                ResponseUtil.success(sources, "Retrieved all sources successfully")
        );
    }
}
