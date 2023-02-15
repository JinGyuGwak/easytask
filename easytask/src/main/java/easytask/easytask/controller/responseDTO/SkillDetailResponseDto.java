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
public class SkillDetailResponseDto {

    private List<String> professionalSkill = new ArrayList<>();
    private List<String> programSkill = new ArrayList<>();


    public SkillDetailResponseDto(SignTask signTask) {
        for (ProfessionalSkill a : signTask.getProfessionalSkillList()) {
            this.professionalSkill.add(a.getSkill());
        }
        for (ProgramSkill a : signTask.getProgramSkillList()) {
            this.programSkill.add(a.getSkill());
        }
    }

}
