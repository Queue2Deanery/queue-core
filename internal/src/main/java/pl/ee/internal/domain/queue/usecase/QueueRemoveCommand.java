package pl.ee.internal.domain.queue.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.queue.dto.QueueRemoveResponse;

@Service
public class QueueRemoveCommand {
  public QueueRemoveResponse logic(@NonNull Long queueId) {

    return null;
  }
}
