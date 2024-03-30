package py.com.progweb.prueba.exception;

public class DatabaseException extends RuntimeException {
    
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
