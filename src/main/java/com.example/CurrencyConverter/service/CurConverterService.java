package com.example.CurrencyConverter.service;

import com.example.CurrencyConverter.Entity.CurencyConversion;
import com.example.CurrencyConverter.model.CurConverterResponse;
import com.example.CurrencyConverter.repository.CurrConverterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CurConverterService {

        private static final Logger logger =
                LoggerFactory.getLogger(CurConverterService.class);

        private final RestTemplate restTemplate;
        private final CurrConverterRepository currConverterRepository;

        public CurConverterService(RestTemplate restTemplate,
                                   CurrConverterRepository currConverterRepository) {
                this.restTemplate = restTemplate;
                this.currConverterRepository = currConverterRepository;
        }

        public CurConverterResponse convert(String from, String to, double amount) {

                CurConverterResponse response = calculateConversion(from, to, amount);

                CurencyConversion conversion = new CurencyConversion();
                conversion.setFromCurrency(from);
                conversion.setToCurrency(to);
                conversion.setAmount(amount);
                conversion.setRate(response.getRate());
                conversion.setConvertedAmount(response.getConvertedAmount());

                currConverterRepository.save(conversion);

                return response;
        }

        @Cacheable("exchangeRates")
        public CurConverterResponse calculateConversion(String from, String to, double amount) {

                from = from.toUpperCase();
                to = to.toUpperCase();

                logger.info("Fetching exchange rate {} -> {}", from, to);

                String apiUrl =
                        "https://v6.exchangerate-api.com/v6/eff7a0498004b557d231c498/latest/" + from;

                Map response;

                try {
                        response = restTemplate.getForObject(apiUrl, Map.class);
                } catch (Exception ex) {
                        throw new RuntimeException("Invalid Country Code");
                }

                if (response == null || "error".equals(response.get("result"))) {
                        throw new RuntimeException("Invalid Country Code");
                }

                Map rates = (Map) response.get("conversion_rates");

                if (rates == null || !rates.containsKey(to)) {
                        throw new RuntimeException("Invalid Country Code");
                }

                double rate = ((Number) rates.get(to)).doubleValue();
                double convertedAmount = amount * rate;

                return new CurConverterResponse(from, to, amount, rate, convertedAmount);
        }

        public List<CurencyConversion> getAllConversions() {
                return currConverterRepository.findAll();
        }

        public CurencyConversion updateConversion(Long id, double amount) {

                CurencyConversion existing = currConverterRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Conversion not found"));

                CurConverterResponse response =
                        calculateConversion(existing.getFromCurrency(),
                                existing.getToCurrency(),
                                amount);

                existing.setAmount(amount);
                existing.setRate(response.getRate());
                existing.setConvertedAmount(response.getConvertedAmount());

                return currConverterRepository.save(existing);
        }

        public void deleteConversion(Long id) {

                if (!currConverterRepository.existsById(id)) {
                        throw new RuntimeException("Conversion not found");
                }

                currConverterRepository.deleteById(id);
        }
}
