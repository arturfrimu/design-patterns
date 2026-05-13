package com.arturfrimu.pattern.chain.domain;

import java.util.List;

/**
 * Result of running an {@link ExpenseRequest} through the approval chain.
 *
 * <ul>
 *     <li>{@code status}: {@link Status#APPROVED} or {@link Status#REJECTED}.</li>
 *     <li>{@code approverRole}: the role that finally approved the request,
 *         or {@code null} when the request was rejected by everyone.</li>
 *     <li>{@code trace}: ordered list of roles that touched the request,
 *         starting with the first link in the chain. The last entry is the
 *         role that actually decided.</li>
 * </ul>
 */
public record ApprovalDecision(
        String requestId,
        Status status,
        String approverRole,
        List<String> trace
) {

    public enum Status { APPROVED, REJECTED }
}
