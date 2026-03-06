package com.example.CurrencyConverter.model;

public class CurConverterResponse {

    private String from;
    private String to;
    private double amount;
    private double rate;
    private double convertedAmount;

    public CurConverterResponse(String from, String to, double amount,
                                double rate, double convertedAmount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.convertedAmount = convertedAmount;
    }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }

    public void setTo(String to) { this.to = to; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public double getRate() { return rate; }

    public void setRate(double rate) { this.rate = rate; }

    public double getConvertedAmount() { return convertedAmount; }

    public void setConvertedAmount(double convertedAmount) { this.convertedAmount = convertedAmount; }
}
