package easytask.easytask.entity;

import easytask.easytask.controller.requestDTO.ReviewRequestDto;
import easytask.easytask.entity.enumClass.Recommendation;
import easytask.easytask.entity.skill.PersonalSkillRating;
import easytask.easytask.entity.skill.ProfessionalSkillRating;
import easytask.easytask.entity.skill.ProgramSkillRating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private double professionalSkill;
    private double programSkill;
    private double personalSkill;


    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    List<ProfessionalSkillRating> professionalSkillRatingList = new ArrayList<>();
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    List<ProgramSkillRating> programSkillRatingList = new ArrayList<>();
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    List<PersonalSkillRating> personalSkillRatingList = new ArrayList<>();


    private String reviewText;

    @Enumerated(EnumType.STRING)
    private Recommendation recommendation;

    public Review(CompleteTask task,ReviewRequestDto requestDto){
        this.completeTask=task;
        this.irumiUser=task.getIrumiUser();
        this.customerUser=task.getCustomerUser();
        this.totalRating=requestDto.getTotalRating();
        this.professionalSkill=requestDto.getProfessionalSkillRating();
        this.programSkill=requestDto.getProgramSkillRating();
        this.personalSkill = requestDto.getPersonalSkillRating();
        this.reviewText=requestDto.getReviewText();
        this.recommendation=requestDto.getRecommendation();
    }
    public void modifyReview(ReviewRequestDto requestDto){
        this.totalRating=requestDto.getTotalRating();
        this.professionalSkill=requestDto.getProfessionalSkillRating();
        this.programSkill=requestDto.getProgramSkillRating();
        this.personalSkill = requestDto.getPersonalSkillRating();
        this.reviewText=requestDto.getReviewText();
        this.recommendation=requestDto.getRecommendation();
    }
    public void addProfessionalSkillRating(ProfessionalSkillRating rating){
        this.professionalSkillRatingList.add(rating);
        rating.setReview(this);

    }

    public void addPersonalSkillRating(PersonalSkillRating rating){
        this.personalSkillRatingList.add(rating);
        rating.setReview(this);
    }

    public void addProgramSkillRating(ProgramSkillRating rating){
        this.programSkillRatingList.add(rating);
        rating.setReview(this);
    }


    public void deleteReview() {
        this.state=State.INACTIVE;
    }
}
