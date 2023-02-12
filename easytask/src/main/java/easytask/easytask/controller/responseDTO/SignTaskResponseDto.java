package easytask.easytask.controller.responseDTO;

import easytask.easytask.entity.SignTask;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignTaskResponseDto {
    private Long id;
    private Long userId;
    private String email;

    public SignTaskResponseDto(SignTask signTask){
        this.id= signTask.getId();
        this.userId=signTask.getCustomerUser().getId();
        this.email=signTask.getCustomerUser().getEmail();
    }
}
