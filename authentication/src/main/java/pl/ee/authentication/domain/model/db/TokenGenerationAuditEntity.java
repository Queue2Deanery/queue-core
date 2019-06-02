package pl.ee.authentication.domain.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token_generation_audit", schema = "public", catalog = "authentication")
public class TokenGenerationAuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "ip_address", nullable = false)
  private String ipAddress;

  @Column(name = "user_index", nullable = false)
  private String userIndex;

  @Builder.Default
  @Column(nullable = false, name = "created_at")
  private LocalDateTime createdAt = LocalDateTime.now();

}
