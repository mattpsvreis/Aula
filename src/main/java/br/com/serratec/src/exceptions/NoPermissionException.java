package br.com.serratec.src.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoPermissionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private String value;

	public NoPermissionException(String message) {
		this.message = message;
	}

	public NoPermissionException(String message, String value) {
		this.message = message;
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
