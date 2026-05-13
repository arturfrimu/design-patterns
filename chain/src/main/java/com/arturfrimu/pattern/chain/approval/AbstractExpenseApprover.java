package com.arturfrimu.pattern.chain.approval;

import com.arturfrimu.pattern.chain.domain.ApprovalDecision;
import com.arturfrimu.pattern.chain.domain.ApprovalDecision.Status;
import com.arturfrimu.pattern.chain.domain.ExpenseRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Skeleton handler that implements the chain-walking algorithm.
 * Concrete subclasses only need to declare their {@link #role()} and their
 * {@link #limit()}; the algorithm in {@link #approve(ExpenseRequest)} is the
 * same for every link of the chain.
 */
public abstract class AbstractExpenseApprover implements ExpenseApprover {

    private ExpenseApprover next;

    public void setNext(ExpenseApprover next) {
        this.next = next;
    }

    protected abstract String role();

    protected abstract BigDecimal limit();

    @Override
    public ApprovalDecision approve(ExpenseRequest request) {
        if (request.amount().compareTo(limit()) <= 0) {
            return new ApprovalDecision(
                    request.id(),
                    Status.APPROVED,
                    role(),
                    List.of(role())
            );
        }
        if (next == null) {
            return new ApprovalDecision(
                    request.id(),
                    Status.REJECTED,
                    null,
                    List.of(role())
            );
        }
        ApprovalDecision downstream = next.approve(request);
        List<String> trace = new ArrayList<>(downstream.trace().size() + 1);
        trace.add(role());
        trace.addAll(downstream.trace());
        return new ApprovalDecision(
                request.id(),
                downstream.status(),
                downstream.approverRole(),
                List.copyOf(trace)
        );
    }
}
