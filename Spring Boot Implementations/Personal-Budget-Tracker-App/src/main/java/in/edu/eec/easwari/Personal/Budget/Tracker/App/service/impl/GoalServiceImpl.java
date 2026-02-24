package in.edu.eec.easwari.Personal.Budget.Tracker.App.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.GoalRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.GoalResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.User;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserGoal;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper.GoalMapper;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserGoalRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.DashboardService;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.GoalService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalServiceImpl implements GoalService {

    private final UserGoalRepository goalRepository;
    private final UserRepository userRepository;

    private final DashboardService dashboardService;

    @Override
    public GoalResponseDTO createGoal(GoalRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserGoal goal = UserGoal.builder()
                .user(user)
                .goalName(request.getGoalName())
                .targetAmount(request.getTargetAmount())
                .currentAmount(request.getCurrentAmount())
                .targetDate(request.getTargetDate())
                .build();

        UserGoal saved = goalRepository.save(goal);

        return GoalMapper.toDTO(saved);
    }

    @Override
    public GoalResponseDTO updateGoal(Long goalId, Double amount) {
        UserGoal goal = goalRepository.findById(goalId)
            .orElseThrow(() -> new ResourceNotFoundException("Goal not found"));

        goal.setCurrentAmount(
                goal.getCurrentAmount()
                    .add(BigDecimal.valueOf(
                            amount.doubleValue())));

        return GoalMapper.toDTO(
                goalRepository.save(goal));
    }

    @Override
    public void deleteGoal(Long goalId) {
        if (goalId != null) {
            goalRepository.deleteById(goalId);
        }
    }

    @Override
    public List<GoalResponseDTO> getUserGoals(Long userId) {

        return goalRepository.findByUser_UserId(userId)
                .stream()
                .map(GoalMapper::toDTO)
                .toList();
    }

    @Override
    public void allocateSavings(Long userId) {

        BigDecimal balance = dashboardService.getDashboardSummary(userId).getBalance();

        if (balance.compareTo(BigDecimal.ZERO) <= 0) return;

        List<UserGoal> goals =
                goalRepository.findByUser_UserId(userId);

        goals.sort(Comparator.comparing(UserGoal::getTargetDate));

        for (UserGoal goal : goals) {

            BigDecimal remaining = goal
                        .getTargetAmount()
                        .subtract(
                            goal.getCurrentAmount());

            if (remaining.compareTo(BigDecimal.ZERO) <= 0) continue;

            BigDecimal allocation = BigDecimal.valueOf(
                Math.min(balance.doubleValue(), 
                         remaining.doubleValue()));

            goal.setCurrentAmount(
                    goal.getCurrentAmount().add(allocation)
            );

            balance = balance.subtract(allocation);

            if (balance.compareTo(BigDecimal.ZERO) <= 0) 
                break;
        }
    }
}
