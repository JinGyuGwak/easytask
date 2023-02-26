package easytask.easytask.controller.requestDTO;

import easytask.easytask.entity.Authority;
import easytask.easytask.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
//@Builder
@NoArgsConstructor
public class UserRequestDto {

    private String email;
    private String password;
    private String name;

    private Set<Authority> authorityDtoSet;

    public UserRequestDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.authorityDtoSet = user.getAuthorities();
    }



}
