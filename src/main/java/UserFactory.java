/**
 * A factory design pattern for producing user objects
 */
public class UserFactory extends User {
    /**
     * constructor method : inherits and uses the user class constructor
     *
     * @param username : contains the username of the user whose object needs to instanciated
     * @param password : contains the password of the user whose record needs to instanciated
     * @param role     : contains the password of the user whose record needs to instanciated
     */
    public UserFactory(String username, String password, String role) {
        super(username, password, role);
    }

    /**
     * creates an user object using factory design pattern
     *
     * @return an user created via the user factory class
     */
    public User createUser() {
        return new User(this.getUsername(), this.getPassword(), this.getRole());
    }
}
