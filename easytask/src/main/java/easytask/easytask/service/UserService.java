package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.controller.requestDTO.UserRequestDto;
import easytask.easytask.controller.responseDTO.UserResponseDto;
import easytask.easytask.entity.Authority;
import easytask.easytask.entity.User;
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
//        if (userRepository.findOneWithAuthoritiesByEmail(userDto.getName()).orElse(null) != null) {
//            throw new BaseException("이미 가입되어 있는 유저입니다.");
//        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .authorities(Collections.singleton(authority))
                .build();

        User user1=userRepository.save(user);

        return new UserRequestDto(user1);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email).get();
    }

    @Transactional(readOnly = true)
    public User getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByEmail).get();

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
