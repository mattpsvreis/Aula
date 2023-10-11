package br.com.serratec.src.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.serratec.src.utils.ErrorBody;

@RestControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoPermissionException.class)
	public ResponseEntity<ErrorBody> noPermissionException(NoPermissionException e, HttpServletRequest request) {

		String error = "No permission for endpoint";

		HttpStatus status = HttpStatus.FORBIDDEN;

		ErrorBody err = new ErrorBody(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorBody> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		String error = "Resource not found";

		HttpStatus status = HttpStatus.NOT_FOUND;

		ErrorBody err = new ErrorBody(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorBody> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e,
			HttpServletRequest request) {
		String error = "Resource already exists";

		HttpStatus status = HttpStatus.CONFLICT;

		ErrorBody err = new ErrorBody(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(FieldCantBeNullException.class)
	public ResponseEntity<ErrorBody> handleFieldCantBeNullException(FieldCantBeNullException e,
			HttpServletRequest request) {
		String error = "Field can't be null";

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ErrorBody err = new ErrorBody(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(InvalidValueException.class)
	public ResponseEntity<ErrorBody> handleInvalidValueException(InvalidValueException e, HttpServletRequest request) {
		String error = "Invalid value: " + e.getValue();

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ErrorBody err = new ErrorBody(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<ErrorBody> handleHttpServerErrorException(HttpServerErrorException e,
			HttpServletRequest request) {
		HttpStatus status = e.getStatusCode();

		ErrorBody error = new ErrorBody(Instant.now(), status.value(), "Erro interno de comunicação: ", e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(RequestRejectedException.class)
	public ResponseEntity<ErrorBody> handleRequestRejectedException(HttpServerErrorException e,
			HttpServletRequest request) {

		HttpStatus status = e.getStatusCode();

		ErrorBody error = new ErrorBody(Instant.now(), status.value(), "Requisição negada: " + e.getMessage(),
				e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}
}
