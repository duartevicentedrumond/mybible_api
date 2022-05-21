package com.mybible.mybible.subtransaction;

import java.util.List;
import java.util.Optional;

public interface SubtransactionService {

    Subtransaction saveSubtransaction(Subtransaction subtransaction);
    List<Subtransaction> getAllSubtransactions();
    Optional<Subtransaction> getSubtransaction(Long subtransactionId);
    Subtransaction updateSubtransaction(Long subtransactionId, Subtransaction subtransaction);
    void deleteSubtransaction(Long subtransactionId);
}
