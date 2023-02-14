package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.controller.requestDTO.ReviewRequestDto;
import easytask.easytask.controller.responseDTO.GetDetailReviewDto;
import easytask.easytask.controller.responseDTO.GetReviewDto;
import easytask.easytask.controller.responseDTO.ReviewResponseDto;
import easytask.easytask.entity.CompleteTask;
import easytask.easytask.entity.Review;
import easytask.easytask.repository.CompleteTaskRepository;
import easytask.easytask.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final CompleteTaskRepository completeTaskRepository;
    private final ReviewRepository reviewRepository;



    public ReviewResponseDto createReview(Long completeTaskId, ReviewRequestDto requestDto) {
        CompleteTask task = completeTaskRepository.findById(completeTaskId)
                .orElseThrow(() -> new BaseException("해당 업무를 찾을 수 없습니다."));
        Review review = reviewRepository.save((new Review(task,requestDto)));
        return new ReviewResponseDto(review);
    }

    public ReviewResponseDto modifyReview(Long reviewId, ReviewRequestDto requestDto) {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()-> new BaseException("해당 리뷰를 찾을 수 없습니다."));
        review.modifyReview(requestDto);
        return new ReviewResponseDto(review);
    }

    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new BaseException("해당 리뷰를 찾을 수 없습니다."));
        review.deleteReview();

    }

    public List<GetReviewDto> getUserReview(Long irumiId) {
        List<Review> reviewList = reviewRepository.findAllByIrumiUser_Id(irumiId);
        List<GetReviewDto> reviewDtoList = reviewList.stream()
                .map(r -> new GetReviewDto(r))
                .collect(Collectors.toList());
        return reviewDtoList;
    }

    //리뷰상세조회
    public GetDetailReviewDto getDetailUserReview(Long reviewId) {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException("존재하지 않는 리뷰입니다."));
        return new GetDetailReviewDto(review);

    }
}
