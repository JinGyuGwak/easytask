package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.controller.requestDTO.CompleteTaskRequestDto;
import easytask.easytask.controller.requestDTO.SignTaskRequestDto;
import easytask.easytask.controller.responseDTO.CompleteTaskResponseDto;
import easytask.easytask.controller.responseDTO.SignTaskResponseDto;
import easytask.easytask.entity.CompleteTask;
import easytask.easytask.entity.SignTask;
import easytask.easytask.entity.User;
import easytask.easytask.repository.CompleteTaskRepository;
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
public class TaskService {
    private final UserRepository userRepository;
    private final SignTaskRepository signTaskRepository;
    private final CompleteTaskRepository completeTaskRepository;

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

    public CompleteTaskResponseDto completion(CompleteTaskRequestDto requestDto){
        SignTask signTask = signTaskRepository.findById(requestDto.getSignTaskId())
                .orElseThrow(() -> new BaseException("존재하지 않는 업무입니다."));
        signTask.completion();

        User irumiUser = userRepository.findById(requestDto.getIrumiId())
                .orElseThrow(()-> new BaseException("존재하지 않는 회원입니다."));

        CompleteTask completeTask = completeTaskRepository.
                save((new CompleteTask(signTask,irumiUser,requestDto.getUsageTime())));

        return new CompleteTaskResponseDto(completeTask);
    }
}
