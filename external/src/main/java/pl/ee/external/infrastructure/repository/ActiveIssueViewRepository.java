package pl.ee.external.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ee.common.model.ActiveIssueViewEntity;

public interface ActiveIssueViewRepository extends JpaRepository<ActiveIssueViewEntity, Long> {
}
