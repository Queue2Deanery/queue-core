package pl.ee.external.domain.queue.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ee.common.model.QueueEntity;
import pl.ee.external.domain.queue.dto.QueuesResponse;
import pl.ee.external.infrastructure.repository.QueueRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QueuesQuery {

  private QueueRepository queueRepository;

  public QueuesResponse logic() {
    List<QueueEntity> queueEntities = queueRepository.findAll();
    List<QueuesResponse.ShortQueueResponse> shortQueueResponses = queueEntities.stream()
            .map(queueEntity ->
                    QueuesResponse.ShortQueueResponse.builder()
                            .id(queueEntity.getId())
                            .name(queueEntity.getName())
                            .registrationEnd(queueEntity.getRegistrationEnd())
                            .registrationStart(queueEntity.getRegistrationStart())
                            .build())
            .collect(Collectors.toList());
    return QueuesResponse.builder().queues(shortQueueResponses).build();
  }

}
