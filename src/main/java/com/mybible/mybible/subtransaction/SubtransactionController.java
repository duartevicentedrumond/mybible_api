package com.mybible.mybible.subtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/subtransaction")
public class SubtransactionController {

    @Autowired
    private SubtransactionService subtransactionService;

    @PostMapping("/add")
    public Subtransaction add(@RequestBody Subtransaction subtransaction){
        return subtransactionService.saveSubtransaction(subtransaction);
    }

    @GetMapping("/getAll")
    public List<Subtransaction> getAllSubtransactions(){
        return subtransactionService.getAllSubtransactions();
    }

    @RequestMapping("/{subtransactionId}")
    public Optional<Subtransaction> getSubtransaction(@PathVariable Long subtransactionId){
        return subtransactionService.getSubtransaction(subtransactionId);
    }

    @PutMapping("/update/{subtransactionId}")
    public Subtransaction updateSubtransaction(@PathVariable Long subtransactionId, @RequestBody Subtransaction subtransaction){
        return subtransactionService.updateSubtransaction(subtransactionId, subtransaction);
    }

    @DeleteMapping("/delete/{subtransactionId}")
    public void deleteSubtransaction(@PathVariable Long subtransactionId){
        subtransactionService.deleteSubtransaction(subtransactionId);
    }
}