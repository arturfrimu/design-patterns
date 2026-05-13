package com.arturfrimu.pattern.chain.approval;

import java.math.BigDecimal;

public class ManagerApprover extends AbstractExpenseApprover {

    private static final BigDecimal LIMIT = new BigDecimal("1000.00");

    @Override
    protected String role() {
        return "MANAGER";
    }

    @Override
    protected BigDecimal limit() {
        return LIMIT;
    }
}
