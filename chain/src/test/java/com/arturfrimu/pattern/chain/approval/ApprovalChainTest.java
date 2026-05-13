package com.arturfrimu.pattern.chain.approval;

import com.arturfrimu.pattern.chain.domain.ApprovalDecision;
import com.arturfrimu.pattern.chain.domain.ApprovalDecision.Status;
import com.arturfrimu.pattern.chain.domain.ExpenseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * End-to-end test of the assembled approval chain
 * TEAM_LEAD -> MANAGER -> DIRECTOR -> CEO without Spring.
 */
class ApprovalChainTest {

    private ExpenseApprover chain;

    @BeforeEach
    void wireChain() {
        TeamLeadApprover teamLead = new TeamLeadApprover();
        ManagerApprover manager = new ManagerApprover();
        DirectorApprover director = new DirectorApprover();
        CeoApprover ceo = new CeoApprover();

        teamLead.setNext(manager);
        manager.setNext(director);
        director.setNext(ceo);

        chain = teamLead;
    }

    @Test
    void smallExpenseStopsAtTheTeamLead() {
        ApprovalDecision decision = chain.approve(expense("50.00"));

        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        assertThat(decision.approverRole()).isEqualTo("TEAM_LEAD");
        assertThat(decision.trace()).containsExactly("TEAM_LEAD");
    }

    @Test
    void mediumExpenseFlowsToTheManager() {
        ApprovalDecision decision = chain.approve(expense("500.00"));

        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        assertThat(decision.approverRole()).isEqualTo("MANAGER");
        assertThat(decision.trace()).containsExactly("TEAM_LEAD", "MANAGER");
    }

    @Test
    void biggerExpenseFlowsToTheDirector() {
        ApprovalDecision decision = chain.approve(expense("5000.00"));

        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        assertThat(decision.approverRole()).isEqualTo("DIRECTOR");
        assertThat(decision.trace()).containsExactly("TEAM_LEAD", "MANAGER", "DIRECTOR");
    }

    @Test
    void largeExpenseFlowsAllTheWayToTheCeo() {
        ApprovalDecision decision = chain.approve(expense("50000.00"));

        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        assertThat(decision.approverRole()).isEqualTo("CEO");
        assertThat(decision.trace())
                .containsExactly("TEAM_LEAD", "MANAGER", "DIRECTOR", "CEO");
    }

    @Test
    void hugeExpenseIsRejectedByEveryone() {
        ApprovalDecision decision = chain.approve(expense("500000.00"));

        assertThat(decision.status()).isEqualTo(Status.REJECTED);
        assertThat(decision.approverRole()).isNull();
        assertThat(decision.trace())
                .containsExactly("TEAM_LEAD", "MANAGER", "DIRECTOR", "CEO");
    }

    private static ExpenseRequest expense(String amount) {
        return new ExpenseRequest("e-1", "alice", new BigDecimal(amount), "Any");
    }
}
