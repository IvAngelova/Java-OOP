package T08_ExceptionsAndErrorHandling.custom_exception;

public class InvalidPersonNameException extends RuntimeException{
    public InvalidPersonNameException(String message) {
        super(message);
    }
}
