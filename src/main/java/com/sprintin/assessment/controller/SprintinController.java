package com.sprintin.assessment.controller;

import com.sprintin.assessment.service.FileReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SprintinController {

    private final FileReaderService fileReaderService;

    @GetMapping("/test")
    void invokeSprintIn() {

        log.info("Inside controller");
        fileReaderService.loginIntoGcpStorage();
    }
}
