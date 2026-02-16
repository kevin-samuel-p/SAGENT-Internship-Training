package in.edu.eec.easwari.Personal.Budget.Tracker.App.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.IncomeRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.IncomeResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.IncomeSource;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.User;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserIncome;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper.IncomeMapper;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.IncomeSourceRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserIncomeRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.IncomeService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IncomeServiceImpl implements IncomeService {

    private final UserIncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final IncomeSourceRepository sourceRepository;

    @Override
    public IncomeResponseDTO addIncome(IncomeRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        IncomeSource source = sourceRepository.findById(request.getSourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Income source not found"));

        UserIncome income = UserIncome.builder()
                .user(user)
                .incomeSource(source)
                .amount(request.getAmount())
                .incomeDate(request.getIncomeDate())
                .build();

        UserIncome saved = incomeRepository.save(income);

        return IncomeMapper.toDTO(saved);
    }

    @Override
    public List<IncomeResponseDTO> getUserIncomes(Long userId) {
        return incomeRepository.findByUser_UserId(userId)
                .stream()
                .map(IncomeMapper::toDTO)
                .toList();
    }

    @Override
    public BigDecimal getTotalIncome(Long userId) {
        return incomeRepository.getTotalIncomeByUser(userId);
    }
}
