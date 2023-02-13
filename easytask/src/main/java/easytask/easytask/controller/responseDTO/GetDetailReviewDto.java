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
        this.professionalSkillRating= review.getProfessionalSkillRating();
        this.programSkillRating=review.getProgramSkillRating();
        this.personalSkillRating=review.getPersonalSkillRating();
        this.reviewText=review.getReviewText();
    }

}
