package br.com.serratec.src.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidValueException extends RuntimeException {

	private static final long serialVersionUID = -498977178305381826L;

	private String message;
	private String value;

	public InvalidValueException(String message) {
		this.message = message;
	}

	public InvalidValueException(String message, String value) {
		this.value = value;
		this.message = message + value;
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
