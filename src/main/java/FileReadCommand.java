import java.io.*;

public class FileReadCommand implements ReadCommand {
    private File file;

    /**
     * constructor method : implements the interface ReadCommand and creates an object of FileReadCommand
     * with a file handler as an instance variable
     *
     * @param file : file handler of the file which has to be read
     */
    public FileReadCommand(File file) {
        this.file = file;
    }

    /**
     * main method : gives back a user whose username matches with the input username and returns null
     * if no such user exists
     *
     * @param username : username which needs to be found in the user registry
     * @return : user whose username matches the inputed username or null in the case no matches
     * @throws IOException : incase the file doesnot exist
     */
    public User readUser(String username) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String userContent;
        User user = null;
        while ((userContent = reader.readLine()) != null) {
            String[] userParameters = userContent.split(",");
            if (userParameters[0].equals(username)) {
                user = new User(userParameters[0], userParameters[1], userParameters[2]);
                break;
            }
        }
        reader.close();
        return user;
    }
}

