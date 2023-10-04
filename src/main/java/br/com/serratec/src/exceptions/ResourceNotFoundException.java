package br.com.serratec.src.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5482594407568983071L;

	private String message;
	private String value;

	public ResourceNotFoundException(String message) {
		this.message = message;
	}

	public ResourceNotFoundException(String message, String value) {
		this.value = value;
		this.message = message + value;
	}
}
