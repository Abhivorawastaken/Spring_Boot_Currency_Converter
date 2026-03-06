package com.example.CurrencyConverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log")
public class LogController {

    private static final Logger logger =
            LoggerFactory.getLogger(LogController.class);

    @GetMapping
    public String log() {

        logger.trace("TRACE log");
        logger.debug("DEBUG log");
        logger.info("INFO log");
        logger.warn("WARN log");
        logger.error("ERROR log");

        return "Check console logs";
    }
}