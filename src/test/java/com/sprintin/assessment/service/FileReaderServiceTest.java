package com.sprintin.assessment.service;

import com.sprintin.assessment.repository.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FileReaderServiceTest {

  @InjectMocks FileReaderService fileReaderServiceMock;

  @Mock FileRepository fileRepository;

  @BeforeEach
  public void setUp() {}

  @Test
  void test_saveFileContent_intoDatabase() {}
}
