package com.example.CurrencyConverter.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "conversions")
public class CurencyConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_currency", nullable = false, length = 3)
    @NotBlank
    @Size(min = 3, max = 3)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false, length = 3)
    @NotBlank
    @Size(min = 3, max = 3)
    private String toCurrency;

    @Column(nullable = false)
    @Positive
    private double amount;

    private double rate;

    private double convertedAmount;

    private LocalDateTime timestamp;

    @PrePersist
    public void setTimestampAutomatically() {
        this.timestamp = LocalDateTime.now();
    }

    public CurencyConversion() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFromCurrency() { return fromCurrency; }

    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }

    public String getToCurrency() { return toCurrency; }

    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public double getRate() { return rate; }

    public void setRate(double rate) { this.rate = rate; }

    public double getConvertedAmount() { return convertedAmount; }

    public void setConvertedAmount(double convertedAmount) { this.convertedAmount = convertedAmount; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
