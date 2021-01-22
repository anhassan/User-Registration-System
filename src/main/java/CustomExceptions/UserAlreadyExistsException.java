package CustomExceptions;

/**
 * throws a runtime exception if the user entered is already contained in the system
 */
public class UserAlreadyExistsException extends RuntimeException {
    /**
     * @param error : error message inputed when throwing the exception
     */
    public UserAlreadyExistsException(String error) {
        super(error);
    }
}
