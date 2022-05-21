package com.mybible.mybible.subtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubtransactionServiceImpl implements SubtransactionService {

    @Autowired
    private SubtransactionRepository subtransactionRepository;

    @Override
    public Subtransaction saveSubtransaction(Subtransaction subtransaction) {
        return subtransactionRepository.save(subtransaction);
    }

    @Override
    public List<Subtransaction> getAllSubtransactions() {
        return subtransactionRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Optional<Subtransaction> getSubtransaction(Long subtransactionId) {
        return subtransactionRepository.findBySubtransactionId(subtransactionId);
    }

    @Override
    public Subtransaction updateSubtransaction(Long subtransactionId, Subtransaction subtransaction) {
        return subtransactionRepository.save(subtransaction);
    }

    @Override
    public void deleteSubtransaction(Long subtransactionId) {
        subtransactionRepository.deleteById(subtransactionId);
    }

}
