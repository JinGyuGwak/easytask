package easytask.easytask.entity;

import easytask.easytask.controller.requestDTO.UserRequestDto;
import easytask.easytask.entity.enumClass.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{
    @Id
    @Column(name = "userId", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 30)
    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @ColumnDefault("0")
    private int point; //이용권

    @ColumnDefault("0")
    private int money;

    @OneToMany(mappedBy = "customerUser")
    List<SignTask> signTaskList = new ArrayList<>();

    @OneToMany(mappedBy = "irumiUser")
    List<CompleteTask> irumiList = new ArrayList<>();

    @OneToMany(mappedBy = "customerUser")
    List<CompleteTask> customerList = new ArrayList<>();

    @OneToMany(mappedBy = "irumiUser")
    List<Review> reviewList = new ArrayList<>();

    @Builder
    public User (UserRequestDto userRequestDto){
        this.email= userRequestDto.getEmail();
        this.name= userRequestDto.getName();
        this.password= userRequestDto.getPassword();
    }
    @Builder
    public User(String name, String email, Role role){
        this.name=name;
        this.email=email;
        this.role=role;
    }

    public void patchUser(UserRequestDto userRequestDto){
        this.email= userRequestDto.getEmail();
        this.name= userRequestDto.getName();
        this.password= userRequestDto.getPassword();
    }
    public User update(String name){
        this.name=name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
