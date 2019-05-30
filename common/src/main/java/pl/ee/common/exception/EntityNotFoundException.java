package pl.ee.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends javax.persistence.EntityNotFoundException {

  public EntityNotFoundException(String entityIdentifier) {
    super(entityIdentifier);
  }
}
