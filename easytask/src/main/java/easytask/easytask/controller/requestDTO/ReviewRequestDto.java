package easytask.easytask.controller.requestDTO;


import easytask.easytask.entity.enumClass.Recommendation;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {

    private double totalRating;
    private double professionalSkillRating;
    private double programSkillRating;
    private double personalSkillRating;

    private String reviewText;
    private Recommendation recommendation;

}
