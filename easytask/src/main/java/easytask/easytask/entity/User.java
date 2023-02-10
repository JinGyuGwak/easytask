package easytask.easytask.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
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

    @ColumnDefault("0")
    private Long point; //이용권

    @ColumnDefault("0")
    private int money;

    @OneToMany(mappedBy = "customerUser")
    List<SignTask> signTaskList = new ArrayList<>();

    @OneToMany(mappedBy = "irumiUser")
    List<CompleteTask> irumiList = new ArrayList<>();

    @OneToMany(mappedBy = "customerUser")
    List<CompleteTask> customerList = new ArrayList<>();
}
