package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.controller.requestDTO.ReviewRequestDto;
import easytask.easytask.controller.responseDTO.GetDetailReviewDto;
import easytask.easytask.controller.responseDTO.GetReviewDto;
import easytask.easytask.controller.responseDTO.ReviewResponseDto;
import easytask.easytask.controller.responseDTO.SkillDetailResponseDto;
import easytask.easytask.entity.CompleteTask;
import easytask.easytask.entity.Review;
import easytask.easytask.entity.skill.ProfessionalSkillRating;
import easytask.easytask.entity.skill.ProgramSkillRating;
import easytask.easytask.entity.skill.SkillRating;
import easytask.easytask.repository.CompleteTaskRepository;
import easytask.easytask.repository.ReviewRepository;
import easytask.easytask.service.func.FuncReview;
import easytask.easytask.service.func.FuncTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final CompleteTaskRepository completeTaskRepository;
    private final ReviewRepository reviewRepository;
    private final FuncReview funcReview;
    private final FuncTask funcTask;

    private void addSkillRatings(List<Map<String, Double>> details) {
        if (details != null) {
            for(Map<String,Double> a : details){
                String skill = a.keySet().iterator().next();
                Double rating = a.values().iterator().next();
            }
        }
    }
    public ReviewResponseDto createReview(Long completeTaskId, ReviewRequestDto requestDto) {
        CompleteTask task = funcTask.selectCompleteTaskById(completeTaskId);
        Review review = new Review(task,requestDto);

//        addSkillRatings(requestDto.getProfessionalDetail());
//        addSkillRatings(requestDto.getProgramDetail());
        //어떻게 줄이지
        if (requestDto.getProfessionalDetail() != null) {
            for (Map<String, Double> a : requestDto.getProfessionalDetail()) {
                String skill = a.keySet().iterator().next();
                Double rating = a.values().iterator().next();
                review.addProfessionalSkillRating(new ProfessionalSkillRating(skill, rating));
            }
        }
        if (requestDto.getProgramDetail() != null) {
            for (Map<String, Double> a : requestDto.getProgramDetail()) {
                String skill = a.keySet().iterator().next();
                Double rating = a.values().iterator().next();
                review.addProgramSkillRating(new ProgramSkillRating(skill, rating));
            }
        }

        Review saveReview = reviewRepository.save(review);
        return new ReviewResponseDto(saveReview);
    }


    public ReviewResponseDto modifyReview(Long reviewId, ReviewRequestDto requestDto) {
        Review review=funcReview.selectReviewById(reviewId);
        review.modifyReview(requestDto);
        return new ReviewResponseDto(review);
    }

    public void deleteReview(Long reviewId) {
        Review review=funcReview.selectReviewById(reviewId);
        review.deleteReview();

    }

    public List<GetReviewDto> getUserReview(Long irumiId) {
        List<Review> reviewList = funcReview.selectAllReviewByIrumiId(irumiId);
        List<GetReviewDto> reviewDtoList = reviewList.stream()
                .map(r -> new GetReviewDto(r))
                .collect(Collectors.toList());
        return reviewDtoList;
    }

    //리뷰상세조회
    public GetDetailReviewDto getDetailUserReview(Long reviewId) {
        System.out.println("reviewId = " + reviewId);
        Review review=funcReview.selectReviewById(reviewId);

        return new GetDetailReviewDto(review);
    }

    public SkillDetailResponseDto getSkillDetail(Long completeTaskId) {
        CompleteTask completeTask = funcTask.selectCompleteTaskById(completeTaskId);
        return new SkillDetailResponseDto(completeTask.getSignTask());
    }



}
