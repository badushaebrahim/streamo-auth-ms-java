package streamo.server.auth.bootstrap.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User not found for given request");
    }
}