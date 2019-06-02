package pl.ee.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Immutable
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "active_issue_view", schema = "public", catalog = "queue")
public class ActiveIssueViewEntity {
  @Id
  @Column(name = "id")
  private Long id;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "started_at")
  private LocalDateTime startedAt;
  @Column(name = "student_index")
  private String studentIndex;
  @Column(name = "queue_id")
  private Long queueId;
  @Column(name = "issue_category_name")
  private String name;
  @Column(name = "estimated_time_in_sec")
  private Long estimatedTimeInSec;

}
