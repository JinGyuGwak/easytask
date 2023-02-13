package easytask.easytask.entity;

import easytask.easytask.controller.requestDTO.ReviewRequestDto;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "irumiId")
    private User irumiUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private User customerUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "completeTaskId")
    private CompleteTask completeTask;

    private double totalRating;
    private double professionalSkillRating;
    private double programSkillRating;
    private double personalSkillRating;

    private String reviewText;

    @Enumerated(EnumType.STRING)
    private Recommendation recommendation;

    public Review(CompleteTask task,ReviewRequestDto requestDto){
        this.completeTask=task;
        this.irumiUser=task.getIrumiUser();
        this.customerUser=task.getCustomerUser();
        this.totalRating=requestDto.getTotalRating();
        this.professionalSkillRating=requestDto.getProfessionalSkillRating();
        this.programSkillRating=requestDto.getProgramSkillRating();
        this.personalSkillRating = requestDto.getPersonalSkillRating();
        this.reviewText=requestDto.getReviewText();
        this.recommendation=requestDto.getRecommendation();
    }
    public void modifyReview(ReviewRequestDto requestDto){
        this.totalRating=requestDto.getTotalRating();
        this.professionalSkillRating=requestDto.getProfessionalSkillRating();
        this.programSkillRating=requestDto.getProgramSkillRating();
        this.personalSkillRating = requestDto.getPersonalSkillRating();
        this.reviewText=requestDto.getReviewText();
        this.recommendation=requestDto.getRecommendation();

    }

    public void deleteReview() {
        this.state=State.INACTIVE;
    }
}
