package easytask.easytask.controller.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class SignTaskRequestDto {
    private String email;


    private List<String> professionalSkill;
    private List<String> programSkill;
    private List<String> personalSkill;



}
