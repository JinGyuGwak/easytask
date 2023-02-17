package easytask.easytask.service.func;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.entity.CompleteTask;
import easytask.easytask.entity.SignTask;
import easytask.easytask.repository.CompleteTaskRepository;
import easytask.easytask.repository.SignTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FuncTask {
    private final SignTaskRepository signTaskRepository;
    private final CompleteTaskRepository completeTaskRepository;

    public SignTask selectSignTaskById(Long id){
        return signTaskRepository.findById(id)
                .orElseThrow(() -> new BaseException("존재하지 않는 업무입니다."));
    }

    public List<SignTask> selectAllSignTask(){
        return signTaskRepository.findAllSignTask();
    }

    public CompleteTask selectCompleteTaskById(Long id){
        return completeTaskRepository.findById(id)
                .orElseThrow(() -> new BaseException("존재하지 않는 업무입니다."));
    }

}
