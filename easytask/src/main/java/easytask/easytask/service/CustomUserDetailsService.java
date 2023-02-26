package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.entity.User;
import easytask.easytask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email) //유저 정보를 권한정보와 함께 가져온다.
                .map(a -> createUser(email, a)) //userRepository 에서 받은 객체 a(유저)와 파라미터로 받은 email 로 createUser 호출
                .orElseThrow(() -> new BaseException("DB에 등록되지 않은 유저입니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String email, User user) {
//        if (!user.isActivated()) {
//            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
//        }

        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities); //해당정보(이메일, 비밀번호,권한) 를 기반으로 userdetails.User 객체를 생성해서 리턴함
    }
}
