package pl.ee.external.domain.queue.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ee.external.domain.queue.dto.QueueRequest;
import pl.ee.external.domain.queue.dto.QueueResponse;
import pl.ee.common.exception.EntityNotFoundException;
import pl.ee.external.infrastructure.repository.QueueRepository;

@Service
public class QueueQuery {

  private QueueRepository queueRepository;

  @Autowired
  public QueueQuery(QueueRepository queueRepository) {
    this.queueRepository = queueRepository;
  }

  public QueueResponse logic(QueueRequest request) {
    queueRepository.findById(request.getQueueId()).orElseThrow(() -> new EntityNotFoundException("test"));
    return null;
  }
}
