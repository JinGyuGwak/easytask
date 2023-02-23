package easytask.easytask.service.func;


import easytask.easytask.common.exception.BaseException;
import easytask.easytask.entity.User;
import easytask.easytask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FuncUser {
    private final UserRepository userRepository;



    public boolean checkEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User selectUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new BaseException("존재하지 않는 회원입니다."));
    }
    public User selectUserByEmail(String email){
        return userRepository.findByEmail(email).
                orElseThrow(() -> new BaseException("존재하지 않는 회원입니다."));
    }
    public List<User> selectAllUser(){
        return userRepository.findAll();
    }
}



