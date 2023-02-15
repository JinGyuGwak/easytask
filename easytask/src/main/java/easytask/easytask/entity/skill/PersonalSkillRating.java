package easytask.easytask.entity.skill;


import easytask.easytask.entity.BaseEntity;
import easytask.easytask.entity.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PERSONALSKILLRATING")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalSkillRating extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;

    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    public PersonalSkillRating(String skillName, double rating){
        this.skillName=skillName;
        this.rating=rating;
    }

    public void setReview(Review review){
        this.review=review;
    }



}
