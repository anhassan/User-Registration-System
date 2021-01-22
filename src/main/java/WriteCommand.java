import java.io.IOException;

/**
 * interface that is implemented by FileWriteCommand
 */
public interface WriteCommand {
    /**
     * method declaration only as the method body is implemented by FileWriteCommand
     */
    public void writeUser(User user) throws IOException;
}
