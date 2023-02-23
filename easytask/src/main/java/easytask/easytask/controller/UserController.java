package easytask.easytask.controller;


import easytask.easytask.common.exception.BaseException;
import easytask.easytask.common.response.BaseResponse;
import easytask.easytask.controller.requestDTO.UserRequestDto;
import easytask.easytask.controller.responseDTO.UserResponseDto;
import easytask.easytask.service.UserService;
import easytask.easytask.service.func.FuncUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FuncUser funcUser;


    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }


    @PostMapping("/sign-up/register")
    public BaseResponse<UserResponseDto> registerUser(
            @RequestBody UserRequestDto requestDto){
        if (funcUser.checkEmail(requestDto.getEmail())){
            throw new BaseException("이미 존재하는 이메일입니다.");
        }
        UserResponseDto userResponseDto = userService.registerUser(requestDto);
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
