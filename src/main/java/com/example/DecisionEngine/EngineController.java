package com.example.DecisionEngine;

import com.example.DecisionEngine.engineException.DecisionEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api")
@RestController
public class EngineController {

    @Autowired
    private EngineService engineService;

    //send input data from front to service layer for decision

    //POSTMAN
    @GetMapping("/loanDecision/{amount}/{months}/{code}")
    public LoanResponse getLoanDecision(@PathVariable("amount") double amount,
                                  @PathVariable("months") int months,
                                  @PathVariable("code") String code){
            return engineService.getLoanDecision(amount, months, code);
    }

}
