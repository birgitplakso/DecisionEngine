package com.example.DecisionEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class EngineController {

    @Autowired
    private EngineService engineService;

    //send input data from front to service layer for decision

    //POSTMAN teha-->
    @GetMapping("/loanDecision/{amount}/{months}/{code}")
    public String getLoanDecision(@PathVariable("amount") double amount,
                                  @PathVariable("months") int months,
                                  @PathVariable("code") String code){
        return engineService.getLoanDecision(amount, months, code);
    }


}
