import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteCommand implements WriteCommand {
    private File file;

    /**
     * constructor method : implements the interface WriteCommand and creates an object of
     * FileWriteCommand with a file handler as an instance variable
     *
     * @param file : file handler of the file which has to be read
     */
    public FileWriteCommand(File file) {
        this.file = file;
    }

    /**
     * main method : presists a user in the user registry(file system) whose object is given
     * as a parameter to the method
     *
     * @param user : user to be entered in the user registry(file system)
     * @throws IOException : in case the file does not exists
     */
    public void writeUser(User user) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true));
        writer.write(user.toString());
        writer.newLine();
        writer.close();
    }
}
