package br.com.serratec.src.utils;

import java.time.Instant;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorBody {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;

  private Map<String, String> fields;

  // Usar o @AllArgsConstructor para essa classe causa bugs, provavelmente relacionado ao @JsonFormat do Instant.

  public ErrorBody(Instant timestamp, Integer status, String error, String message, String path) {
    super();
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }
}
