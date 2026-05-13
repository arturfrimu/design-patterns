package com.arturfrimu.pattern.chain.approval;

import java.math.BigDecimal;

public class TeamLeadApprover extends AbstractExpenseApprover {

    private static final BigDecimal LIMIT = new BigDecimal("100.00");

    @Override
    protected String role() {
        return "TEAM_LEAD";
    }

    @Override
    protected BigDecimal limit() {
        return LIMIT;
    }
}
