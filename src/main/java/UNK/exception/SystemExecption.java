package UNK.exception;

public class SystemExecption extends BaseException{
    public SystemExecption(String code) {
        this(code,null);
    }

    public SystemExecption(String code, String message) {
        this(code,message,null);
    }

    public SystemExecption(String code, String message, Throwable cause) {
        super("SYS" + code, "系统异常" + message, cause);
    }
}
