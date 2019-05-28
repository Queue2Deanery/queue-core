package pl.ee.common.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    IssueEntity - Sprawa do Dziekanatu
    Klasa opisująca studenta oczekującego w kolejce
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="issue", schema = "public")
public class IssueEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Builder.Default
  @Column(name="created_at")
  private  LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "started_at")
  private LocalDateTime startedAt;

  @Column(name = "completed_at")
  private LocalDateTime completedAt;

  @EqualsAndHashCode.Exclude
  @ManyToOne
  @JoinColumn(name = "queue_id")
  private QueueEntity queueEntity;

  @EqualsAndHashCode.Exclude
  @ManyToOne
  @JoinColumn(name = "issue_category_id")
  private IssueCategoryEntity issueCategoryEntity;

  @Column(name = "student_index", nullable = false)
  private String studentIndex;

  @Column(name = "student_comment")
  private String studentComment;

}
