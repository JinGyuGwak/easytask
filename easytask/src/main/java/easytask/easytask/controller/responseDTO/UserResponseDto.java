package easytask.easytask.controller.responseDTO;


import easytask.easytask.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String password;
    private String name;

    public UserResponseDto(User user){
        this.id=user.getId();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.name=user.getName();
    }

}
