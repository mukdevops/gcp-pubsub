package com.sprintin.assessment.repository.entity;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sprint_files")
public class SprintinFile implements Serializable {

  @Serial private static final long serialVersionUID = -7456374325106509596L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "file_id")
  private Long id;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_size")
  private String fileSize;

  @Column(name = "file_content", columnDefinition = "jsonb")
  private String fileContent;

  @Column(name = "bucket_name")
  private String bucketName;

  @Column(name = "created_at")
  private LocalDateTime createdDt;
}
