package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.controller.requestDTO.UserRequestDto;
import easytask.easytask.controller.responseDTO.UserResponseDto;
import easytask.easytask.entity.User;
import easytask.easytask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto registerUser(UserRequestDto request){
        User user = userRepository.save(new User(request)); //id값 받아오자
        return new UserResponseDto(user);
    }

    public UserResponseDto patchUser(Long id,UserRequestDto request){

        User user = userRepository.findById(id)
                .orElseThrow(()-> new BaseException("존재하지 않는 회원 입니다."));
        user.patchUser(request); //id값 받아오자
        return new UserResponseDto(user);
    }


}
