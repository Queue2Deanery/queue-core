package pl.ee.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Immutable
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "issue_processing_estimation_view", schema = "public", catalog = "queue")
public class IssueProcessingEstimationViewEntity {

  @EmbeddedId
  private IssueProcessingEstimationPK issueProcessingEstimationPK;

  @Column(name = "queue_name")
  private String queueName;

  @Column(name = "issue_category_name")
  private String issueCategoryName;

  @Column(name = "estimated_time_in_sec")
  private Long estimatedTimeInSec;

}
