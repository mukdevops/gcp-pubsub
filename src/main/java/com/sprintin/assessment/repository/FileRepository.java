package com.sprintin.assessment.repository;

import com.sprintin.assessment.repository.entity.SprintinFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<SprintinFile, Long> {}
