package easytask.easytask.controller;


import easytask.easytask.common.exception.BaseException;
import easytask.easytask.common.jwt.JwtFilter;
import easytask.easytask.common.jwt.TokenProvider;
import easytask.easytask.common.response.BaseResponse;
import easytask.easytask.controller.requestDTO.UserLoginDto;
import easytask.easytask.controller.requestDTO.UserRequestDto;
import easytask.easytask.controller.responseDTO.TokenDto;
import easytask.easytask.controller.responseDTO.UserResponseDto;
import easytask.easytask.entity.User;
import easytask.easytask.service.UserService;
import easytask.easytask.service.func.FuncUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FuncUser funcUser;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;



    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserLoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        System.out.println("jwt = " + jwt);
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/sign-up/user")
    public ResponseEntity<UserRequestDto> signup(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.signup(userRequestDto));
    }

    @GetMapping("/user")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<User> getMyUserInfo(HttpServletRequest request) {

        System.out.println("뭔데");
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{email}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> getUserInfo(@PathVariable String email) {
        System.out.println("email = " + email);
        return ResponseEntity.ok(userService.getUserWithAuthorities(email));
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
