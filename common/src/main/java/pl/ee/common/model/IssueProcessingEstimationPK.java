package pl.ee.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueProcessingEstimationPK implements Serializable {

  @Column(name = "queue_id")
  private Long queueId;

  @Column(name = "issue_category_id")
  private Long issueCategoryId;
}
