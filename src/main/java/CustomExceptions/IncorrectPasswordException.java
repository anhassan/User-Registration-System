package CustomExceptions;

/**
 * throws a runtime exception if password does not contain any special characters
 */
public class IncorrectPasswordException extends RuntimeException {
    /**
     * @param error : error message inputed when throwing the exception
     */
    public IncorrectPasswordException(String error) {
        super(error);
    }
}
