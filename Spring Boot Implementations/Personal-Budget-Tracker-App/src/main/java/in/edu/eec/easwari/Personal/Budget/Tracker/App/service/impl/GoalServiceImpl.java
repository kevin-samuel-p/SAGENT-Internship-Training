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
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.GoalService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalServiceImpl implements GoalService {

    private final UserGoalRepository goalRepository;
    private final UserRepository userRepository;

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
    public List<GoalResponseDTO> getUserGoals(Long userId) {

        return goalRepository.findByUser_UserId(userId)
                .stream()
                .map(GoalMapper::toDTO)
                .toList();
    }
}
