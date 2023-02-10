package easytask.easytask.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    private LocalDateTime takeTime;

    private int money;

    @OneToOne(mappedBy = "completeTask",fetch = FetchType.LAZY)
    private Review review;



}
