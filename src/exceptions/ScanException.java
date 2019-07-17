package exceptions;

public class ScanException extends RuntimeException {
	
	public ScanException() {
		this.getMessage();
	}
	
	@Override
	public String getMessage() {
		return "Scanner error!";
	}
}
