package com.example.CurrencyConverter.controller;

import com.example.CurrencyConverter.Entity.CurencyConversion;
import com.example.CurrencyConverter.model.CurConverterResponse;
import com.example.CurrencyConverter.service.CurConverterService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurConverterController {

    private static final Logger logger =
            LoggerFactory.getLogger(CurConverterController.class);

    private final CurConverterService curConverterService;

    public CurConverterController(CurConverterService curConverterService) {
        this.curConverterService = curConverterService;
    }

    @GetMapping("/convert")
    public CurConverterResponse convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {

        logger.info("Conversion request received: {} -> {} amount {}", from, to, amount);

        return curConverterService.convert(from, to, amount);
    }

    @PostMapping("/conversions")
    public CurConverterResponse createConversion(
            @Valid @RequestBody CurencyConversion request) {

        logger.info("Creating new conversion {} -> {} amount {}",
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getAmount());

        return curConverterService.convert(
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getAmount());
    }

    @GetMapping("/conversions")
    public List<CurencyConversion> getAllConversions() {
        logger.info("Fetching all conversions");
        return curConverterService.getAllConversions();
    }

    @PutMapping("/conversions/{id}")
    public CurencyConversion updateConversion(
            @PathVariable Long id,
            @RequestParam double amount) {

        logger.info("Updating conversion id {} with new amount {}", id, amount);

        return curConverterService.updateConversion(id, amount);
    }

    @DeleteMapping("/conversions/{id}")
    public String deleteConversion(@PathVariable Long id) {

        logger.warn("Deleting conversion with id {}", id);

        curConverterService.deleteConversion(id);
        return "Conversion deleted successfully";
    }
}
