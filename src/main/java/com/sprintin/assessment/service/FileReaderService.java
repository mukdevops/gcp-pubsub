package com.sprintin.assessment.service;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.sprintin.assessment.config.SprintInConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;

@Slf4j
@Service
@AllArgsConstructor
public class FileReaderService {

    private final SprintInConfiguration sprintInConfiguration;

    public void loginIntoGcpStorage() {
        final String FILE_NAME = "sprintin.txt";
        final Storage storage = StorageOptions.getDefaultInstance().getService();

        final Blob blob = storage.get(sprintInConfiguration.getBucketName(), FILE_NAME);
        final ReadChannel readChannel = blob.reader();
        final BufferedReader reader = new BufferedReader(Channels.newReader(readChannel, "UTF-8"));

        try {
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }


//    private String getBucket() {
//        return sprintInConfiguration.sprintinFileBucketName();
//    }


//    protected Runnable ingestFile() {
//
//        return () -> {
//            final ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//            try (final PipedOutputStream pipedOutputStream = new PipedOutputStream();
//                 final PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);) {
//
//                executorService.execute(downloadfile(pipedOutputStream));
//                executorService.execute(parseFile(pipedInputStream));
//
//            } catch (final InterruptedException | IOException interruptedException) {
//
//            }
//        };
//
//    }
//
//
//    protected Runnable downloadfile(final PipedOutputStream pipedOutputStream) {
//
//        final String fileName = "";
//        final String filePath = "";
//        return () -> {
//            try (pipedOutputStream) {
//                final ReadChannel readChannel = storage.reader(filePath, filePath);
//                final InputStream inputStream = Channels.newInputStream(readChannel);
//                inputStream.transferTo(pipedOutputStream);
//            } catch (final IOException ioException) {
//                log.info("exception : " + ioException.getMessage());
//            }
//        };
//    }
//
//    protected Runnable parseFile(final PipedInputStream pipedInputStream) {
//
//        final String fileName = "";
//        final String filePath = "";
//        return () -> {
//            try (pipedOutputStream) {
//                final ReadChannel readChannel = storage.reader(filePath, filePath);
//                final InputStream inputStream = Channels.newInputStream(readChannel);
//                inputStream.transferTo(pipedOutputStream);
//            } catch (final IOException ioException) {
//                log.info("exception : " + ioException.getMessage());
//            }
//        };
//    }
}
