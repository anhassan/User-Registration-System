/**
 * contains a user class whose objects are instanciated by the user factory
 */
public class User {
    private String username;
    private String password;
    private String role;
    private static final String DELIMITER = ",";

    /**
     * constructor method : creates a user object with the given credentials
     *
     * @param username : contains the username of the user whose record needs to be created
     * @param password : contains the password of the user whose record needs to be created
     * @param role     : contains the password of the user whose record needs to be created
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.username + DELIMITER + this.password + DELIMITER + this.role;
    }
}
