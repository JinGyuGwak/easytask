package easytask.easytask.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    public BaseException(String message) { super(message); }
}