package com.arturfrimu.pattern.chain.approval;

import java.math.BigDecimal;

public class DirectorApprover extends AbstractExpenseApprover {

    private static final BigDecimal LIMIT = new BigDecimal("10000.00");

    @Override
    protected String role() {
        return "DIRECTOR";
    }

    @Override
    protected BigDecimal limit() {
        return LIMIT;
    }
}
