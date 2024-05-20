package com.sprintin.assessment.service;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.sprintin.assessment.repository.entity.SprintinFile;
import jakarta.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FileReaderService {

  /// private final FileRepository fileRepository;

  @Transactional
  public void loginIntoGcpStorage(
      final String bucketName, final String fileName, final String fileSize) {

    final Storage storage = StorageOptions.getDefaultInstance().getService();

    final Blob blob = storage.get(bucketName, fileName);

    try (final ReadChannel readChannel = blob.reader();
        final BufferedReader reader =
            new BufferedReader(Channels.newReader(readChannel, StandardCharsets.UTF_8))) {
      String line = reader.readLine();
      saveSprintFileContent(bucketName, fileName, fileSize, reader);
      while (line != null) {
        log.info("line ->{}", line);
        // read next line
        line = reader.readLine();
      }
    } catch (final IOException e) {
      log.error("IOException Occurred{}", e.getMessage());
    } catch (final Exception e) {
      log.error("Exception Occurred{}", e.getMessage());
    }
  }

  private void saveSprintFileContent(
      String bucketName, String fileName, String fileSize, BufferedReader reader) {
    SprintinFile sprintinFileEntity =
        SprintinFile.builder()
            .fileName(fileName)
            .fileContent(reader.toString())
            .fileSize(fileSize)
            .bucketName(bucketName)
            .createdDt(LocalDateTime.now())
            .build();
    log.info("file content size {}", sprintinFileEntity.getFileSize());
    // fileRepository.save(sprintinFileEntity);
  }
}
