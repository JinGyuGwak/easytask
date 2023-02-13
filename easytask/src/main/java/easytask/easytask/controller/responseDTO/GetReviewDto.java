package easytask.easytask.controller.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import easytask.easytask.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetReviewDto {
    private String customerName;
    private double totalRating;
    private String reviewText;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime day;

    public GetReviewDto(Review review){
        this.customerName=review.getCompleteTask().getCustomerUser().getName();
        this.totalRating=review.getTotalRating();
        this.reviewText=review.getReviewText();
        this.day=review.getCreatedAt();
    }
}
