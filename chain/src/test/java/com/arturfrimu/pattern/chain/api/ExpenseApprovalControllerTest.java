package com.arturfrimu.pattern.chain.api;

import com.arturfrimu.pattern.chain.domain.ApprovalDecision;
import com.arturfrimu.pattern.chain.domain.ApprovalDecision.Status;
import com.arturfrimu.pattern.chain.domain.ExpenseRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestRestTemplate
class ExpenseApprovalControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void teamLeadApprovesSmallExpense() {
        ApprovalDecision decision = callApprove(new ExpenseRequest(
                "e-1", "alice", new BigDecimal("75.00"), "Books"));

        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        assertThat(decision.approverRole()).isEqualTo("TEAM_LEAD");
        assertThat(decision.trace()).containsExactly("TEAM_LEAD");
    }

    @Test
    void directorApprovesAFewThousand() {
        ApprovalDecision decision = callApprove(new ExpenseRequest(
                "e-2", "bob", new BigDecimal("4200.00"), "Team offsite"));

        assertThat(decision.status()).isEqualTo(Status.APPROVED);
        assertThat(decision.approverRole()).isEqualTo("DIRECTOR");
        assertThat(decision.trace())
                .containsExactly("TEAM_LEAD", "MANAGER", "DIRECTOR");
    }

    @Test
    void hugeExpenseIsRejectedByTheWholeChain() {
        ApprovalDecision decision = callApprove(new ExpenseRequest(
                "e-3", "carol", new BigDecimal("250000.00"), "New office"));

        assertThat(decision.status()).isEqualTo(Status.REJECTED);
        assertThat(decision.approverRole()).isNull();
        assertThat(decision.trace())
                .containsExactly("TEAM_LEAD", "MANAGER", "DIRECTOR", "CEO");
    }

    private ApprovalDecision callApprove(ExpenseRequest request) {
        ResponseEntity<ApprovalDecision> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/expenses/approve",
                request,
                ApprovalDecision.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        ApprovalDecision body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.requestId()).isEqualTo(request.id());
        return body;
    }
}
