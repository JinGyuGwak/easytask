package easytask.easytask.controller.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import easytask.easytask.entity.CompleteTask;
import easytask.easytask.entity.SignTask;
import easytask.easytask.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CompleteTaskResponseDto {
    private Long signTaskId;
    private String customerEmail;
    private String customerName;

    private String irumiEmail;
    private String irumiName;


    private LocalTime usageTime; //시간은 분 단위
    private double requiredPoint;

    public CompleteTaskResponseDto(CompleteTask completeTask){
        this.signTaskId=completeTask.getSignTask().getId();
        this.customerEmail=completeTask.getSignTask().getCustomerUser().getEmail();
        this.customerName=completeTask.getSignTask().getCustomerUser().getName();
        this.irumiEmail=completeTask.getIrumiUser().getEmail();
        this.irumiName=completeTask.getIrumiUser().getName();
        this.usageTime=completeTask.getUsageTime();
        requiredPoint= completeTask.getMoney();


    }


}
