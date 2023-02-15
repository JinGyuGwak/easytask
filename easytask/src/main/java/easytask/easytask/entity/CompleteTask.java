package easytask.easytask.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "COMPLETETASK")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompleteTask extends BaseEntity{
    @Id
    @Column(name = "completeTaskId", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "signTaskId")
    private SignTask signTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "irumiId")
    private User irumiUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private User customerUser;

    private LocalTime usageTime;

    private int money;

    @OneToOne(mappedBy = "completeTask",fetch = FetchType.LAZY)
    private Review review;

    public CompleteTask(SignTask signTask, User irumiUser,LocalTime usageTime){
        this.signTask=signTask;
        this.customerUser=signTask.getCustomerUser();
        this.irumiUser=irumiUser;
        this.usageTime=usageTime;
        this.money=usageTime.getHour()*60*160
                + usageTime.getMinute()*160
                + usageTime.getSecond()*160/60;
    }





}
