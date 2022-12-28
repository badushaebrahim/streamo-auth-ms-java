package streamo.server.auth.bootstrap.exceptions;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(){
        super("Generated Token has Expired");
    }
}
