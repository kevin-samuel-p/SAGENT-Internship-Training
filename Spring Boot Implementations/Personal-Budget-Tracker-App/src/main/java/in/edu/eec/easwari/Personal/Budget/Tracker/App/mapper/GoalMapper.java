package in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.GoalResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserGoal;

public class GoalMapper {

    public static GoalResponseDTO toDTO(UserGoal goal) {
        return GoalResponseDTO.builder()
                .goalId(goal.getUserGoalId())
                .goalName(goal.getGoalName())
                .targetAmount(goal.getTargetAmount())
                .currentAmount(goal.getCurrentAmount())
                .targetDate(goal.getTargetDate())
                .build();
    }
}
