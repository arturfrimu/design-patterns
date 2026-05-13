package com.arturfrimu.pattern.chain.approval;

import com.arturfrimu.pattern.chain.domain.ApprovalDecision;
import com.arturfrimu.pattern.chain.domain.ApprovalDecision.Status;
import com.arturfrimu.pattern.chain.domain.ExpenseRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TeamLeadApproverTest {

    private final TeamLeadApprover teamLead = new TeamLeadApprover();

    @Test
    void approvesAmountAtOrBelowItsLimit() {
        ExpenseRequest request = new ExpenseRequest("e-1", "alice",
                new BigDecimal("100.00"), "Office supplies");

        ApprovalDecision decision = teamLead.approve(request);

        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        assertThat(decision.approverRole()).isEqualTo("TEAM_LEAD");
        assertThat(decision.trace()).containsExactly("TEAM_LEAD");
    }

    @Test
    void rejectsWhenAmountExceedsLimitAndThereIsNoNext() {
        ExpenseRequest request = new ExpenseRequest("e-2", "alice",
                new BigDecimal("250.00"), "Team lunch");

        ApprovalDecision decision = teamLead.approve(request);

        assertThat(decision.status()).isEqualTo(Status.REJECTED);
        assertThat(decision.approverRole()).isNull();
        assertThat(decision.trace()).containsExactly("TEAM_LEAD");
    }

    @Test
    void delegatesToNextWhenAmountExceedsLimitAndPrependsItselfToTheTrace() {
        ExpenseApprover next = mock(ExpenseApprover.class);
        when(next.approve(any())).thenReturn(new ApprovalDecision(
                "e-3", Status.APPROVED, "MANAGER", List.of("MANAGER")));
        teamLead.setNext(next);

        ExpenseRequest request = new ExpenseRequest("e-3", "alice",
                new BigDecimal("250.00"), "Team lunch");

        ApprovalDecision decision = teamLead.approve(request);

        verify(next).approve(request);
        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        assertThat(decision.approverRole()).isEqualTo("MANAGER");
        assertThat(decision.trace()).containsExactly("TEAM_LEAD", "MANAGER");
    }

    @Test
    void doesNotCallNextWhenItCanApproveItself() {
        ExpenseApprover next = mock(ExpenseApprover.class);
        teamLead.setNext(next);

        ExpenseRequest request = new ExpenseRequest("e-4", "alice",
                new BigDecimal("50.00"), "Stickers");

        ApprovalDecision decision = teamLead.approve(request);

        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        verify(next, never()).approve(any());
    }
}
