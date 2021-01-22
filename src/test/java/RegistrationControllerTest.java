import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import CustomExceptions.InappropiateUsernameException;
import CustomExceptions.IncorrectPasswordException;
import CustomExceptions.UserAlreadyExistsException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

public class RegistrationControllerTest {
    // Initiallizing the user parameters and path required for the test setup
    private static String[] user1Credentials = {"Stephene", "Caught1@2", "Analyst"};
    private static String[] user2Credentials = {"Mark", "Tears$43", "SalesPerson"};
    private static String[] user3Credentials = {"Bob", "Heaven1!2", "Accountant"};
    private static String[] user4Credentials = {"Amanda", "LifeIsShort", "Writer"};
    private static String[] user5Credentials = {"David", "Forbdden3@9", "Teacher"};
    private static final String FILE_PATH = "src/main/resources/userRegistry.txt";
    // Initiallizing the objects required for setup
    private static File file;
    private static ReadCommand readCommand;
    private static WriteCommand writeCommand;
    private static RegistrationController registrationController;

    @Mock
    UserFactory mockedUserFactory;
    RegistrationController mockedRegistrationController;

    @Before
    public void setUp() throws IOException {
        file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        readCommand = new FileReadCommand(file);
        writeCommand = new FileWriteCommand(file);
        writeCommand.writeUser(new User(user2Credentials[0], user2Credentials[1], user2Credentials[2]));
        registrationController = new RegistrationController(readCommand, writeCommand);
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearUp() {
        file.delete();
    }

    @Test
    public void testUserAdded() throws IOException {
        registrationController.registerNewUser(user1Credentials[0], user1Credentials[1], user1Credentials[2]);
        User expectedUserRegistered = readCommand.readUser(user1Credentials[0]);
        User actualUserRegistered = new User(user1Credentials[0], user1Credentials[1], user1Credentials[2]);
        assertEquals(expectedUserRegistered.getUsername(), actualUserRegistered.getUsername());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testUserAlreadyExists() throws IOException {
        registrationController.registerNewUser(user2Credentials[0], user2Credentials[1], user2Credentials[2]);
    }

    @Test(expected = InappropiateUsernameException.class)
    public void testInappropiateUsername() throws IOException {
        registrationController.registerNewUser(user3Credentials[0], user3Credentials[1], user3Credentials[2]);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void testIncorrectPassword() throws IOException {
        registrationController.registerNewUser(user4Credentials[0], user4Credentials[1], user4Credentials[2]);
    }

    @Test
    public void testUserAddedWithMockito() throws IOException {
        User mockedUser = new User(user5Credentials[0], user5Credentials[1], user5Credentials[2]);
        mockedRegistrationController =
                new RegistrationController(readCommand, writeCommand, mockedUserFactory);
        when(mockedUserFactory.createUser()).thenReturn(mockedUser);
        mockedRegistrationController.registerNewUser(user1Credentials[0], user1Credentials[1], user1Credentials[2]);
        User expectedUserRegistered = readCommand.readUser(user5Credentials[0]);
        User actualUserRegistered = new User(user5Credentials[0], user5Credentials[1], user5Credentials[2]);
        assertEquals(expectedUserRegistered.getUsername(), actualUserRegistered.getUsername());
    }

}

