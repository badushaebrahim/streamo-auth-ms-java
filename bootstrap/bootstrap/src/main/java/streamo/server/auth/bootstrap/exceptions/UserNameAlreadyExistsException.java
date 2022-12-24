package streamo.server.auth.bootstrap.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException{
    public UserNameAlreadyExistsException(){
        super("UserName already exists");
    }
}
