package com.example.kafkatest.dto;

import com.example.kafkatest.emums.CurrencyType;
import com.example.kafkatest.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyDto {

    @JsonSerialize(using = BigDecimalSerializer.class)
    BigDecimal amount;

    CurrencyType currencyType;
}
