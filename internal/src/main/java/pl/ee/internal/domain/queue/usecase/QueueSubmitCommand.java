package pl.ee.internal.domain.queue.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.queue.dto.QueueSubmitRequest;
import pl.ee.internal.domain.queue.dto.QueueSubmitResponse;

@Service
public class QueueSubmitCommand {
  public QueueSubmitResponse logic(@NonNull QueueSubmitRequest request) {
    return null;
  }
}
