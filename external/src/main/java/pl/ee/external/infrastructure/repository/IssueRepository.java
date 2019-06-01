package pl.ee.external.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ee.common.model.IssueEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {

  @Query("delete from IssueEntity ie where ie.studentIndex = :userIndex and ie.startedAt is null and ie.queueId = :queueId")
  public void deleteAwaitingIssueByQueueIdAndUser(@Param("queueId") Long queueId, @Param("userIndex") String userIndex);

  public List<IssueEntity> findByQueueIdAndCompletedAt(Long queueId, LocalDateTime completedAt);
}
