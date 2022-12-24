package streamo.server.auth.bootstrap.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("Username not found for given request");
    }
}
