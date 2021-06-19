package com.example.kafkatest.dto;

import lombok.Data;

@Data
public class TransactionDto {

    Long sourceId;

    Long destinationId;

    MoneyDto money;
}
