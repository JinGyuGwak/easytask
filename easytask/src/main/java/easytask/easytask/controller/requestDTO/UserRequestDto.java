package easytask.easytask.controller.requestDTO;

import easytask.easytask.controller.responseDTO.UserResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;
    private String name;

}
