package easytask.easytask.controller.responseDTO;


import easytask.easytask.entity.Review;
import easytask.easytask.entity.enumClass.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponseDto {
    private Long completeTaskId;

    private double totalRating;
    private double professionalSkillRating;
    private double programSkillRating;
    private double personalSkillRating;

    private String reviewText;
    private Recommendation recommendation;

    private String customerName;
    private String irumiName;

    public ReviewResponseDto(Review review){
        this.completeTaskId=review.getCompleteTask().getId();
        this.totalRating= review.getTotalRating();
        this.professionalSkillRating=review.getProfessionalSkill();
        this.programSkillRating=review.getProgramSkill();
        this.personalSkillRating=review.getPersonalSkill();
        this.reviewText=review.getReviewText();
        this.recommendation=review.getRecommendation();

        this.customerName=review.getCompleteTask().getCustomerUser().getName();
        this.irumiName=review.getCompleteTask().getIrumiUser().getName();

    }

}
