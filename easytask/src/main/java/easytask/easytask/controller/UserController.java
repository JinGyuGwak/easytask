package easytask.easytask.controller;


import easytask.easytask.common.response.BaseResponse;
import easytask.easytask.controller.requestDTO.UserRequestDto;
import easytask.easytask.controller.responseDTO.UserResponseDto;
import easytask.easytask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up/register")
    public BaseResponse<UserResponseDto> registerUser(
            @RequestBody UserRequestDto request){
        UserResponseDto userResponseDto = userService.registerUser(request);
        return new BaseResponse<>(userResponseDto);
    }

    @PatchMapping("/client/my-account")
    public BaseResponse<UserResponseDto> patchUser(
            @RequestParam Long id,
            @RequestBody UserRequestDto request){
        UserResponseDto userResponseDto = userService.patchUser(id, request);
        return new BaseResponse<>(userResponseDto);
    }
}
