package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.controller.requestDTO.UserRequestDto;
import easytask.easytask.controller.responseDTO.UserResponseDto;
import easytask.easytask.entity.User;
import easytask.easytask.entity.enumClass.Role;
import easytask.easytask.repository.UserRepository;
import easytask.easytask.service.func.FuncUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FuncUser funcUser;


    @Transactional
    public UserRequestDto signup(UserRequestDto userDto) {
        if (funcUser.checkEmail(userDto.getEmail())){ //존재하면 1
            throw new BaseException("이미 존재하는 이메일입니다.");
        }
        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .role(Role.ROLE_USER)
                .build();

        System.out.println("user.getRoleKey() = " + user.getRoleKey());
        User saveUser=userRepository.save(user);
        return new UserRequestDto(saveUser);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(String email) {
        return userRepository.findOneByEmail(email).get();
    }

    public UserResponseDto registerUser(UserRequestDto requestDto){

        User user = userRepository.save(new User(requestDto)); //id값 받아오자
        return new UserResponseDto(user);
    }

    public UserResponseDto patchUser(Long id,UserRequestDto request){
        User user = funcUser.selectUserById(id);
        user.patchUser(request);
        return new UserResponseDto(user);
    }

}
