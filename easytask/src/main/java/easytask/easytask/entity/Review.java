package easytask.easytask.entity;

import easytask.easytask.entity.enumClass.Recommendation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Id
    @Column(name = "reviewId", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "completeTaskId")
    private CompleteTask completeTask;

    private double totalRating;
    private double professionalSkill;
    private double programSkill;
    private double personalSkill;

    private String reviewText;

    @Enumerated(EnumType.STRING)
    private Recommendation recommendation;

    //역량 부분 세분화 해야합니다~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}
