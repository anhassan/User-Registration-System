import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * interface that is implemented by FileReadCommand
 */
public interface ReadCommand {
    /**
     * method declaration only as the method body is implemented by FileReadCommand
     */
    public User readUser(String username) throws IOException;
}
