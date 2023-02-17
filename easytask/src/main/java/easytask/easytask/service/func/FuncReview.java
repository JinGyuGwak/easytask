package easytask.easytask.service.func;

import easytask.easytask.common.exception.BaseException;
import easytask.easytask.entity.Review;
import easytask.easytask.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FuncReview {
    private final ReviewRepository reviewRepository;

    public Review selectReviewById(Long id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new BaseException("존재하지 않는 리뷰입니다."));
    }

    public List<Review> selectAllReviewByIrumiId(Long id){
        return reviewRepository.findByIrumiId(id);
    }



}
