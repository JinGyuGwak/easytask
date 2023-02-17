package easytask.easytask.entity.skill;

import lombok.Getter;

@Getter
public class SkillRating {
    private String skillName;
    private double rating;

    public SkillRating(String skillName, double rating) {
        this.skillName = skillName;
        this.rating = rating;
    }
}
