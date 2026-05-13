package com.arturfrimu.pattern.chain.approval;

import java.math.BigDecimal;

public class CeoApprover extends AbstractExpenseApprover {

    private static final BigDecimal LIMIT = new BigDecimal("100000.00");

    @Override
    protected String role() {
        return "CEO";
    }

    @Override
    protected BigDecimal limit() {
        return LIMIT;
    }
}
