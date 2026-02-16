package in.edu.eec.easwari.Personal.Budget.Tracker.App.service;

import java.util.List;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.GoalRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.GoalResponseDTO;

public interface GoalService {

    GoalResponseDTO createGoal(GoalRequestDTO request);

    List<GoalResponseDTO> getUserGoals(Long userId);
}
