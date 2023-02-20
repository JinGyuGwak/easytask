package easytask.easytask.config.auth.dto;

import easytask.easytask.entity.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;

    //인증된 사용자 정보만 필요하고 그 외에 필요한 정보들은 없다.
    public SessionUser(User user){
        this.name=user.getName();
        this.email= user.getEmail();
    }
}
