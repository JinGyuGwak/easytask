package easytask.easytask.controller;

import easytask.easytask.common.response.BaseResponse;
import easytask.easytask.controller.requestDTO.SignTaskRequestDto;
import easytask.easytask.controller.requestDTO.UserRequestDto;
import easytask.easytask.controller.responseDTO.SignTaskResponseDto;
import easytask.easytask.controller.responseDTO.UserResponseDto;
import easytask.easytask.service.SignTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SignTaskController {
    private final SignTaskService signTaskService;

    @PostMapping("/work-request")
    public BaseResponse<SignTaskResponseDto> registerTask(
            @RequestBody SignTaskRequestDto request){
        SignTaskResponseDto signTaskResponseDto = signTaskService.registerTask(request);
        return new BaseResponse<>(signTaskResponseDto);
    }
    @GetMapping("/work-request")
    public BaseResponse<List<SignTaskResponseDto>> getTask(){
        List<SignTaskResponseDto> signTaskResponseDto = signTaskService.getTask();
        return new BaseResponse<>(signTaskResponseDto);
    }


}
