import CustomExceptions.InappropiateUsernameException;
import CustomExceptions.IncorrectPasswordException;
import CustomExceptions.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * RegistrationController implements a class that controles business logic while writing a new user
 * to a user registry(file based)
 */
public class RegistrationController {
    private ReadCommand readCommand;
    private WriteCommand writeCommand;
    private UserFactory userFactory;
    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    /**
     * constructor method : Responsible for instanciating an object of Registration Controller class
     *
     * @param readCommand  : FileReadCommand object containing the file handler
     * @param writeCommand : FileWriteCommand object containing the file handler
     */
    public RegistrationController(ReadCommand readCommand, WriteCommand writeCommand) {
        this.readCommand = readCommand;
        this.writeCommand = writeCommand;
    }

    /**
     * constructor method : Responsible for instanciating an object of Registration Controller class specifically
     * for Mockito Testing
     *
     * @param readCommand  : FileReadCommand object containing the file handler
     * @param writeCommand : FileWriteCommand object containing the file handler
     * @param userFactory  : UserFactory mock object for mockito testing
     */
    public RegistrationController(ReadCommand readCommand, WriteCommand writeCommand, UserFactory userFactory) {
        this.readCommand = readCommand;
        this.writeCommand = writeCommand;
        this.userFactory = userFactory;
    }

    /**
     * main method : Responsible for registering a new user in the user registry(file)
     *
     * @param username : contains the username of the user whose record needs to be added
     * @param password : contains the password of the user whose record needs to be added
     * @param role     : contains the password of the user whose record needs to be added
     * @throws InappropiateUsernameException : throws a runtime exception if username is less than 4 and
     *                                       greater than 12 characters
     * @throws IncorrectPasswordException    : throws a runtime exception if password does not contain any
     *                                       special characters
     * @throws UserAlreadyExistsException    : throws a runtime exception if the user entered is already contained
     *                                       in the system
     */
    public void registerNewUser(String username, String password, String role) throws IOException {
        if (username.length() < 4 || username.length() > 12) {
            logger.error("Username Error: Username " +
                    "length should be between 4 and 12 characters ");
            throw new InappropiateUsernameException("Username Error: Username " +
                    "length should be between 4 and 12 characters ");
        }
        if (!(password.matches(".*\\d.*") && password.matches(".*[a-zA-Z].*")
                && password.matches(".*[@#$!].*"))) {
            logger.error("Password Set Error : Password should contain atleast one" +
                    "number and one special character[@#$!]");
            throw new IncorrectPasswordException("Password Set Error : Password should contain atleast one" +
                    "number and one special character[@#$!]");
        }
        if (userFactory == null) {
            userFactory = new UserFactory(username, password, role);
        }
        User userToBeRegistered = userFactory.createUser();

        if (!(this.readCommand.readUser(userToBeRegistered.getUsername()) == null)) {
            logger.error("UserAlreadyExistsError : User with username " +
                    userToBeRegistered.getUsername() + " already exists");
            throw new UserAlreadyExistsException("UserAlreadyExistsError : User with username " +
                    userToBeRegistered.getUsername() + " already exists");
        } else {
            this.writeCommand.writeUser(userToBeRegistered);
        }
    }
}
