package com.arturfrimu.pattern.chain.config;

import com.arturfrimu.pattern.chain.approval.CeoApprover;
import com.arturfrimu.pattern.chain.approval.DirectorApprover;
import com.arturfrimu.pattern.chain.approval.ExpenseApprover;
import com.arturfrimu.pattern.chain.approval.ManagerApprover;
import com.arturfrimu.pattern.chain.approval.TeamLeadApprover;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Builds the approval chain once at startup and exposes only the entry point
 * (the team lead) as a Spring bean. Read top to bottom to see the order in
 * which approvers will be asked: TEAM_LEAD -> MANAGER -> DIRECTOR -> CEO.
 */
@Configuration
public class ApprovalChainConfiguration {

    @Bean
    public ExpenseApprover approvalChain() {
        TeamLeadApprover teamLead = new TeamLeadApprover();
        ManagerApprover manager = new ManagerApprover();
        DirectorApprover director = new DirectorApprover();
        CeoApprover ceo = new CeoApprover();

        teamLead.setNext(manager);
        manager.setNext(director);
        director.setNext(ceo);

        return teamLead;
    }
}
