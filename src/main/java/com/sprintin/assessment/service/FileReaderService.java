package com.sprintin.assessment.service;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FileReaderService {

  public void loginIntoGcpStorage(final String bucketName, final String fileName) {

    final Storage storage = StorageOptions.getDefaultInstance().getService();

    final Blob blob = storage.get(bucketName, fileName);

    try (final ReadChannel readChannel = blob.reader();
        final BufferedReader reader =
            new BufferedReader(Channels.newReader(readChannel, StandardCharsets.UTF_8))) {
      String line = reader.readLine();

      while (line != null) {
        log.info("line ->{}", line);
        // read next line
        line = reader.readLine();
      }
    } catch (final IOException e) {
      log.error("Exception Occurred{}", e.getMessage());
    }
  }
}
