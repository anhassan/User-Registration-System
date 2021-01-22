import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the functionality of the FileWriteCommand writing both single and
 * multiple users
 */
public class FileWriteCommandTest {
    // Initiallizing the user parameters and path required for the test setup
    private static String[] user1Credentials = {"John", "CatchMe", "Developer"};
    private static String[] user2Credentials = {"Meghan", "GotYou", "Manager"};
    private static final String FILE_PATH = "src/main/resources/userRegistry.txt";
    // Initiallizing the objects required for setup
    private static User user1;
    private static User user2;
    private static File file;
    private static BufferedReader bufferedReader;
    private static FileWriteCommand fileWriteCommand;

    @Before
    public void setUp() throws Exception {
        user1 = new User(user1Credentials[0], user1Credentials[1], user1Credentials[2]);
        user2 = new User(user2Credentials[0], user2Credentials[1], user2Credentials[2]);
        file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        bufferedReader = new BufferedReader(new FileReader(file));
        fileWriteCommand = new FileWriteCommand(file);
    }

    @After
    public void tearDown() throws Exception {
        bufferedReader.close();
        file.delete();
    }

    @Test
    public void testWritingSingleUser() throws IOException {
        // expected values
        int expectedRecords = 1;
        String[] expectedUserCredentials = user1Credentials;
        // actual Values
        int actualRecords = 0;
        String[] actualUserCredentials = null;

        //action
        fileWriteCommand.writeUser(user1);
        String userContent;
        while ((userContent = bufferedReader.readLine()) != null) {
            actualUserCredentials = userContent.split(",");
            actualRecords += 1;
        }
        //assertions
        assertEquals(expectedRecords, actualRecords);
        Assert.assertArrayEquals(expectedUserCredentials, actualUserCredentials);
    }

    @Test
    public void testWritingMultipleUsers() throws IOException {
        //expected Values
        int expectedRecords = 2;
        List<List<String>> expectedCredentials = new ArrayList<List<String>>
                (Arrays.asList(Arrays.asList(user1Credentials), Arrays.asList(user2Credentials)));
        // actual Values
        int actualRecords = 0;
        List<List<String>> actualCredentials = new ArrayList<List<String>>();
        //action
        fileWriteCommand.writeUser(user1);
        fileWriteCommand.writeUser(user2);

        String userContent;
        while ((userContent = bufferedReader.readLine()) != null) {
            actualRecords += 1;
            actualCredentials.add(Arrays.asList(userContent.split(",")));
        }
        //assertions
        assertEquals(expectedRecords, actualRecords);
        assertEquals(expectedCredentials.size(), actualCredentials.size());
        assertTrue(expectedCredentials.size() == actualCredentials.size() &&
                expectedCredentials.containsAll(actualCredentials) && actualCredentials.containsAll(expectedCredentials));
    }


}

