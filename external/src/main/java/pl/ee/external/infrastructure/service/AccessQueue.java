package pl.ee.external.infrastructure.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.ee.common.model.IssueEntity;
import pl.ee.external.infrastructure.repository.IssueRepository;
import pl.ee.external.infrastructure.repository.QueueRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class AccessQueue {

  private QueueRepository queueRepository;

  private IssueRepository issueRepository;

  public boolean hasRole(Long queueId) {
    if (queueId == null) {
      return false;
    }

    var roles = SecurityContextHolder.getContext()
      .getAuthentication().getAuthorities().stream()
      .map(GrantedAuthority::toString).collect(Collectors.toList());

    return queueRepository.findById(queueId)
      .map(queueEntity -> queueEntity.getFieldOfStudyEntities().stream()
        .flatMap(fieldOfStudyEntity ->
          Stream.of(fieldOfStudyEntity.getAccessRole()))
        .anyMatch(roles::contains)
      ).orElse(false);
  }

  public boolean hasIssue(Long issueId) {
    if (issueId == null) {
      return false;
    }

    var userIndex = SecurityContextHolder.getContext()
      .getAuthentication()
      .getPrincipal()
      .toString();

    return issueRepository.findById(issueId)
      .map(IssueEntity::getStudentIndex)
      .map(userIndex::equals)
      .orElse(false);
  }
}
