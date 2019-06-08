package pl.ee.internal.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ee.common.model.IssueEntity;

import java.time.LocalDateTime;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
}
