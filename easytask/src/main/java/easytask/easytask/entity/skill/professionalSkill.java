package easytask.easytask.entity.skill;

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
public class professionalSkill {
    @Id
    @Column(name = "professionalSkillId", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
