package easytask.easytask.controller.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CompleteTaskRequestDto {
    private Long signTaskId;
    private Long irumiId;

    @JsonFormat(pattern = "kk:mm:ss")
    private LocalTime usageTime;
}
