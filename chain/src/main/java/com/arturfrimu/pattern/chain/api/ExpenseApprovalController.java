package com.arturfrimu.pattern.chain.api;

import com.arturfrimu.pattern.chain.approval.ExpenseApprover;
import com.arturfrimu.pattern.chain.domain.ApprovalDecision;
import com.arturfrimu.pattern.chain.domain.ExpenseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseApprovalController {

    private final ExpenseApprover approvalChain;

    @PostMapping("/approve")
    public ApprovalDecision approve(@RequestBody ExpenseRequest request) {
        return approvalChain.approve(request);
    }
}
