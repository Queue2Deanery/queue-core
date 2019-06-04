package pl.ee.external.domain.queue.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ee.external.domain.queue.dto.QueuesResponse;

import java.util.List;

@AllArgsConstructor
@Service
public class UserQueuesQuery {

  public QueuesResponse logic(List<String> roles) {
    return null;
  }

}
