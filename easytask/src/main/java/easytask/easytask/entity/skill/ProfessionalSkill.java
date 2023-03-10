package easytask.easytask.entity.skill;

import easytask.easytask.entity.BaseEntity;
import easytask.easytask.entity.SignTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PROFESSIONALSKILL")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfessionalSkill extends BaseEntity {
    @Id
    @Column(name = "professionalSkillId", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signTaskId")
    private SignTask signTask;

    public ProfessionalSkill(String skill){
        this.skill=skill;
    }

    public void setSignTask(SignTask signTask){
        this.signTask=signTask;
    }


}
