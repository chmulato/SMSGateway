package csonline.net.br.smsgateway.exception;

public class AppException extends Exception {
    
    private static final long serialVersionUID = 1L;
    private String message;
    
    public AppException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
