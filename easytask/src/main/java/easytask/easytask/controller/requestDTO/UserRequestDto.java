package easytask.easytask.controller.requestDTO;

import easytask.easytask.entity.User;
import easytask.easytask.entity.enumClass.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String email;
    private String password;
    private String name;

    private Role role;

    public UserRequestDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.role = user.getRole();
    }



}
