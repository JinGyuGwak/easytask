package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.controller.requestDTO.SignTaskRequestDto;
import easytask.easytask.controller.responseDTO.SignTaskResponseDto;
import easytask.easytask.entity.SignTask;
import easytask.easytask.entity.User;
import easytask.easytask.repository.SignTaskRepository;
import easytask.easytask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class SignTaskService {
    private final UserRepository userRepository;
    private final SignTaskRepository signTaskRepository;

    public SignTaskResponseDto registerTask(SignTaskRequestDto request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BaseException("잘못된 요청입니다."));

        SignTask signTask = signTaskRepository.save((new SignTask(user)));
        return new SignTaskResponseDto(signTask);
    }
    public List<SignTaskResponseDto> getTask(){
        List<SignTask> signTask = signTaskRepository.findAllSignTask();
        List<SignTaskResponseDto> result = signTask.stream()
                .map(x -> new SignTaskResponseDto(x))
                .collect(Collectors.toList());
        return result;


    }
}
