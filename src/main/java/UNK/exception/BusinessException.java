package UNK.exception;

public class BusinessException extends BaseException{
    public BusinessException(String code) {
        this(code,null);
    }

    public BusinessException(String code, String message) {
        this(code, message,null);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super("Business" + code, "业务异常" + message, cause);
    }
}
