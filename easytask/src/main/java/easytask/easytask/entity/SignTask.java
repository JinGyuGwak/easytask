package easytask.easytask.entity;


import easytask.easytask.entity.skill.ProfessionalSkill;
import easytask.easytask.entity.skill.ProgramSkill;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SIGNTASK")
public class SignTask extends BaseEntity{

    @Id
    @Column(name = "signTaskId", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId" )
    private User customerUser;

    private String userEmail;

    @OneToMany(mappedBy = "signTask", cascade = CascadeType.ALL)
    List<ProgramSkill> programSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "signTask", cascade = CascadeType.ALL)
    List<ProfessionalSkill> professionalSkillList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "signTask", cascade = CascadeType.ALL)
//    List<PersonalSkill> personalSkillList = new ArrayList<>();

    public SignTask(User user){
        this.customerUser=user;
        this.userEmail=user.getEmail();
    }
    public void completion()
    {
        this.state=State.INACTIVE;
    }

    public void addProgramSkill(ProgramSkill programSkill){
        this.programSkillList.add(programSkill);
        programSkill.setSignTask(this);
    }
    public void addProfessionalSkill(ProfessionalSkill professionalSkill){
        this.professionalSkillList.add(professionalSkill);
        professionalSkill.setSignTask(this);
    }
//    public void addPersonalSkill(PersonalSkill personalSkill){
//        this.personalSkillList.add(personalSkill);
//        personalSkill.setSignTask(this);
//    }
}
