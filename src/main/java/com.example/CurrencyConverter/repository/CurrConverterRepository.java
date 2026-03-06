package com.example.CurrencyConverter.repository;

import com.example.CurrencyConverter.Entity.CurencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrConverterRepository
        extends JpaRepository<CurencyConversion, Long> {
}
