package easytask.easytask.controller.responseDTO;

import easytask.easytask.entity.SignTask;
import easytask.easytask.entity.skill.ProfessionalSkill;
import easytask.easytask.entity.skill.ProgramSkill;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class SignTaskResponseDto {
    private Long id;
    private Long userId;
    private String email;

    private List<String> professionalSkill = new ArrayList<>();
    private List<String> programSkill = new ArrayList<>();
    private List<String> personalSkill = new ArrayList<>();




    public SignTaskResponseDto(SignTask signTask){
        this.id= signTask.getId();
        this.userId=signTask.getCustomerUser().getId();
        this.email=signTask.getCustomerUser().getEmail();
        for(ProfessionalSkill a : signTask.getProfessionalSkillList()){
            this.professionalSkill.add(a.getSkill());
        }
        for(ProgramSkill a : signTask.getProgramSkillList()){
            this.programSkill.add(a.getSkill());
        }
//        for(PersonalSkill a : signTask.getPersonalSkillList()){
//            this.personalSkill.add(a.getSkill());
//        }

    }
}
