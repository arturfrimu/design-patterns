package com.arturfrimu.pattern.chain.approval;

import com.arturfrimu.pattern.chain.domain.ApprovalDecision;
import com.arturfrimu.pattern.chain.domain.ExpenseRequest;

/**
 * Handler interface of the Chain of Responsibility pattern.
 * Each implementation either approves the request or hands it off to the next
 * approver in the chain.
 */
public interface ExpenseApprover {

    ApprovalDecision approve(ExpenseRequest request);
}
