package CustomExceptions;

/**
 * throws a runtime exception if username is less than 4 and greater than 12 characters
 */
public class InappropiateUsernameException extends RuntimeException {
    /**
     * @param error : error message inputed when throwing the exception
     */
    public InappropiateUsernameException(String error) {
        super(error);
    }
}
