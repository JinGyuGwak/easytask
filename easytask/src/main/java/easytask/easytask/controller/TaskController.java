package easytask.easytask.controller;

import easytask.easytask.common.response.BaseResponse;
import easytask.easytask.controller.requestDTO.CompleteTaskRequestDto;
import easytask.easytask.controller.requestDTO.SignTaskRequestDto;
import easytask.easytask.controller.responseDTO.CompleteTaskResponseDto;
import easytask.easytask.controller.responseDTO.SignTaskResponseDto;
import easytask.easytask.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/work-request")
    public BaseResponse<SignTaskResponseDto> registerTask(
            @RequestBody SignTaskRequestDto request){
        SignTaskResponseDto signTaskResponseDto = taskService.registerTask(request);
        return new BaseResponse<>(signTaskResponseDto);
    }
    @GetMapping("/work-request")
    public BaseResponse<List<SignTaskResponseDto>> getTask(){
        List<SignTaskResponseDto> signTaskResponseDto = taskService.getTask();
        return new BaseResponse<>(signTaskResponseDto);
    }
    @PostMapping("/completion")
    public BaseResponse<CompleteTaskResponseDto> completion(
            @RequestBody CompleteTaskRequestDto requestDto){
        CompleteTaskResponseDto completeTaskResponseDto
                = taskService.completion(requestDto);
        return new BaseResponse<>(completeTaskResponseDto);
    }
}
