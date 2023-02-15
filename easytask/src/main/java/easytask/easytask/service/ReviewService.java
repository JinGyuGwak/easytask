package easytask.easytask.service;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.controller.requestDTO.ReviewRequestDto;
import easytask.easytask.controller.responseDTO.GetDetailReviewDto;
import easytask.easytask.controller.responseDTO.GetReviewDto;
import easytask.easytask.controller.responseDTO.ReviewResponseDto;
import easytask.easytask.entity.CompleteTask;
import easytask.easytask.entity.Review;
import easytask.easytask.entity.skill.PersonalSkillRating;
import easytask.easytask.entity.skill.ProfessionalSkillRating;
import easytask.easytask.entity.skill.ProgramSkillRating;
import easytask.easytask.repository.CompleteTaskRepository;
import easytask.easytask.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        Review review = new Review(task,requestDto);

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
        if (requestDto.getPersonalDetail() != null) {
            for (Map<String, Double> a : requestDto.getPersonalDetail()) {
                String skill = a.keySet().iterator().next();
                Double rating = a.values().iterator().next();
                review.addPersonalSkillRating(new PersonalSkillRating(skill, rating));
            }
        }
        Review saveReview = reviewRepository.save(review);
        return new ReviewResponseDto(saveReview);
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
        List<Review> reviewList = reviewRepository.findByIrumiId(irumiId);
        List<GetReviewDto> reviewDtoList = reviewList.stream()
                .map(r -> new GetReviewDto(r))
                .collect(Collectors.toList());
        return reviewDtoList;
    }

    //리뷰상세조회
    public GetDetailReviewDto getDetailUserReview(Long reviewId) {
        System.out.println("reviewId = " + reviewId);
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException("존재하지 않는 리뷰입니다."));

        return new GetDetailReviewDto(review);
    }
}
