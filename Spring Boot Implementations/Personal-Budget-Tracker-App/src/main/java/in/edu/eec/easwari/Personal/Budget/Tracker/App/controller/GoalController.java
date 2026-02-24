package in.edu.eec.easwari.Personal.Budget.Tracker.App.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.GoalRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.GoalResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.GoalService;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ApiResponse;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<ApiResponse<GoalResponseDTO>> createGoal(
            @RequestBody GoalRequestDTO request) {

        GoalResponseDTO response = goalService.createGoal(request);

        return ResponseEntity.ok(
                ResponseUtil.success(response, "Goal created successfully")
        );
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long goalId) {
        goalService.deleteGoal(goalId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<GoalResponseDTO>>> getUserGoals(
            @PathVariable Long userId) {

        List<GoalResponseDTO> goals =
                goalService.getUserGoals(userId);

        return ResponseEntity.ok(
                ResponseUtil.success(goals, "User goals fetched successfully")
        );
    }

    @PostMapping("/allocate/{userId}")
    public ResponseEntity<Void> allocate(@PathVariable Long userId) {
        goalService.allocateSavings(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/contribute/{goalId}")
    public ResponseEntity<ApiResponse<GoalResponseDTO>> contribute(
            @PathVariable Long goalId,
            @RequestParam Double amount) {

        GoalResponseDTO response = goalService.updateGoal(goalId, amount);
        return ResponseEntity.ok(
                ResponseUtil.success(response, "Contribution successful"));
    }
}
