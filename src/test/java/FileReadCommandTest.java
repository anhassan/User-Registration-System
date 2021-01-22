import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Test the functionality of the FileReadCommand reading both single and
 * multiple users
 */
public class FileReadCommandTest {
    // Initiallizing the user parameters and path required for the test setup
    private static String[] user1Credentials = {"Mohanty", "LetItGo", "Architect"};
    private static String[] user2Credentials = {"David", "HappyCoding", "Sr.Developer"};
    private static final String FILE_PATH = "src/main/resources/userRegistry.txt";
    // Initiallizing the objects required for setup
    private static User user1;
    private static User user2;
    private static File file;
    private BufferedWriter bufferedWriter;
    private FileReadCommand fileReadCommand;

    @Before
    public void setUp() throws IOException {
        user1 = new User(user1Credentials[0], user1Credentials[1], user1Credentials[2]);
        user2 = new User(user2Credentials[0], user2Credentials[1], user2Credentials[2]);
        file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    }

    @After
    public void tearUp() throws IOException {
        bufferedWriter.close();
        file.delete();
    }

    @Test
    public void testReadingSingleUser() throws IOException {
        // arrange
        bufferedWriter.write(user1.toString());
        bufferedWriter.close();
        // action
        fileReadCommand = new FileReadCommand(file);
        User actualUser = fileReadCommand.readUser(user1.getUsername());
        // assert
        assertEquals(user1.toString(), actualUser.toString());
    }

    @Test
    public void testReadingMultipleUsers() throws IOException {
        // arrange
        bufferedWriter.write(user1.toString());
        bufferedWriter.newLine();
        bufferedWriter.write(user2.toString());
        bufferedWriter.close();
        // action
        fileReadCommand = new FileReadCommand(file);
        User actualUser1 = fileReadCommand.readUser(user1.getUsername());
        User actualUser2 = fileReadCommand.readUser(user2.getUsername());
        // assert
        Assert.assertArrayEquals(new String[]{user1.toString(), user2.toString()},
                new String[]{actualUser1.toString(), actualUser2.toString()});
    }
}
