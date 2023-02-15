package easytask.easytask.controller;


import easytask.easytask.common.response.BaseResponse;
import easytask.easytask.controller.requestDTO.ReviewRequestDto;
import easytask.easytask.controller.responseDTO.GetDetailReviewDto;
import easytask.easytask.controller.responseDTO.ReviewResponseDto;
import easytask.easytask.controller.responseDTO.GetReviewDto;
import easytask.easytask.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public BaseResponse<ReviewResponseDto> createReview(
            @RequestParam Long completeTaskId,
            @RequestBody ReviewRequestDto requestDto){
        ReviewResponseDto reviewResponseDto = reviewService.
                createReview(completeTaskId,requestDto);
        return new BaseResponse<>(reviewResponseDto);
    }
    @PatchMapping("/review")
    public BaseResponse<ReviewResponseDto> modifyReview(
            @RequestParam Long reviewId,
            @RequestBody ReviewRequestDto requestDto){
        ReviewResponseDto reviewResponseDto = reviewService.
                modifyReview(reviewId,requestDto);
        return new BaseResponse<>(reviewResponseDto);
    }
    @DeleteMapping("/review/{reviewId}")
    public BaseResponse<String> deleteReview(
            @PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
        String result = "삭제 완료!";
        return new BaseResponse<>(result);
    }

    //전부조회
    @GetMapping("/review/{irumiId}")
    public BaseResponse<List<GetReviewDto>> getUserReview(
            @PathVariable Long irumiId){
        System.out.println("irumiId = " + irumiId);
        List<GetReviewDto> getReviewDtoList = reviewService.getUserReview(irumiId);
        return new BaseResponse<>(getReviewDtoList);
    }

    //리뷰상세조회
    @GetMapping("/review-detail/{reviewId}")
    public BaseResponse<GetDetailReviewDto> getDetailUserReview(
            @PathVariable Long reviewId){
        GetDetailReviewDto getReviewDtoList = reviewService.getDetailUserReview(reviewId);
        return new BaseResponse<>(getReviewDtoList);
    }

}
