package easytask.easytask.controller.responseDTO;

import easytask.easytask.entity.Review;
import easytask.easytask.entity.skill.ProfessionalSkillRating;
import easytask.easytask.entity.skill.ProgramSkillRating;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class GetDetailReviewDto {

    private String customerName;

    private double professionalSkillRating;
    private double programSkillRating;
    private double personalSkillRating;

    private List<Map<String, Double>> professionalDetail = new ArrayList<>();
    private List<Map<String, Double>> programDetail = new ArrayList<>();




    private String reviewText;

    public GetDetailReviewDto(Review review){
        this.customerName=review.getCustomerUser().getName();
        this.professionalSkillRating= review.getProfessionalSkill();
        this.programSkillRating=review.getProgramSkill();
        this.personalSkillRating=review.getPersonalSkill();
        this.reviewText=review.getReviewText();

        //엔티티인데 다형성을 이용하는 방법?
        for(ProfessionalSkillRating a : review.getProfessionalSkillRatingList()){
            Map<String, Double> newMap = new HashMap<>();
            newMap.put(a.getSkillName(),a.getRating());
            this.professionalDetail.add(newMap);
        }
        for(ProgramSkillRating a : review.getProgramSkillRatingList()){
            Map<String, Double> newMap = new HashMap<>();
            newMap.put(a.getSkillName(),a.getRating());
            this.programDetail.add(newMap);
        }

    }


}
