package com.sprintin.assessment.controller;

import com.sprintin.assessment.service.FileReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SprintinController {

  private final FileReaderService fileReaderService;

  @GetMapping("/read-file")
  public String invokeSprintIn(
      @RequestParam String bucketName,
      @RequestParam String fileName,
      @RequestParam String fileSize) {

    log.info("Inside controller");
    fileReaderService.loginIntoGcpStorage(bucketName, fileName, fileSize);
    return new ResponseEntity<>(HttpStatus.OK).toString();
  }
}
