package easytask.easytask.controller.responseDTO;

import easytask.easytask.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetDetailReviewDto {

    private String customerName;

    private double professionalSkillRating;
    private double programSkillRating;
    private double personalSkillRating;

    private String reviewText;

    public GetDetailReviewDto(Review review){
        this.customerName=review.getCustomerUser().getName();
        this.professionalSkillRating= review.getProfessionalSkill();
        this.programSkillRating=review.getProgramSkill();
        this.personalSkillRating=review.getPersonalSkill();
        this.reviewText=review.getReviewText();
    }

}
