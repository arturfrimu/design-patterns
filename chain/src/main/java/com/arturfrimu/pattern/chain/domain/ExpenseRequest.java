package com.arturfrimu.pattern.chain.domain;

import java.math.BigDecimal;

public record ExpenseRequest(String id, String employee, BigDecimal amount, String purpose) {
}
