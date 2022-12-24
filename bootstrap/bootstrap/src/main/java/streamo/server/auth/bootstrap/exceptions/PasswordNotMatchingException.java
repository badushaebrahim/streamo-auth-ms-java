package streamo.server.auth.bootstrap.exceptions;

public class PasswordNotMatchingException extends RuntimeException{
    public PasswordNotMatchingException(){
        super("Password not matching for given user");
    }
}
