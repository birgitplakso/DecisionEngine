package com.example.DecisionEngine;


/*Task:
        Please design a decision engine which takes in personal code, loan amount,
        loan period in months and returns a decision (negative or positive) and the amount.
        The idea of the decision engine is to determine what would be the maximum sum,
        regardless of the person requested loan amount. For example if a person applies for 4000 €,
        but we determine that we would approve a larger sum then the result should be the maximum sum which we would approve.
        Also in reverse, if a person applies for 4000 € and we would not approve it
        then we want to return the largest sum which we would approve,
        for example 2500 €.
        If a suitable loan amount is not found within the selected period,
        the decision engine should also try to find a new suitable period. In real life the solution should connect
        to external registries and compose a comprehensive user profile, but for the sake of simplicity
        this part can be mocked as a hard coded result for certain personal codes.
        As the scope of this solution you only need to support 4 different scenarios
        - a person has debt or a person falls under a different segmentation.
        For example :
        49002010965 - debt
        49002010976 - segment 1 (credit_modifier = 100)
        49002010987 - segment 2 (credit_modifier = 300)
        49002010998 - segment 3 (credit_modifier = 1000)
        If a person has debt then we do not approve any amount.
        If a person has no debt then we take the identifier and use it for calculating person`s credit score taking into account the requested input.
        Constraints:
        Minimum input and output sum can be 2000 €
        Maximum input and output sum can be 10000 €
        Minimum loan period can be 12 months
        Maximum loan period can be 60 months
        Scoring algorithm. For calculating credit score a really primitive algorithm should be implemented.
        You need to divide the credit modifier with the loan amount and multiply the result with the loan period in months.
        If the result is less than 1 then we would not approve such sum, if the result is larger or equal than 1 then we would approve this sum.
        credit score = (credit modifier / loan amount) * loan period
        As a result please provide working code with a single api endpoint. Include tests for code and a guide how to run code locally. Also whenever possible share your thought process. If you have any additional questions, then please feel free to ask them.*/

import com.example.DecisionEngine.engineException.DecisionEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineService {

    @Autowired
    private EngineRepository engineRepository;



//    public static void main(String[] args) {
//        System.out.println(getCreditScore(100,3000, 24));
//
//        System.out.println(findLoanPeriod(100,3000));
//    }


    public LoanResponse getLoanDecision(double amount, int months, String code) {
        //create new loan response object to be sent to front
        LoanResponse loanResponse=new LoanResponse();
        loanResponse.setEnteredAmount(amount);
        loanResponse.setEnteredPeriod(months);
        //  get the credit_modifier from database
        int creditModifier=engineRepository.getCreditModifier(code);
        //  find the credit score of the customer
        double creditScore=getCreditScore(creditModifier, amount, months);
        // find maximum amount what is possible to get


        //check if amount and time-period are valid   (min input 2000 €, max 10000; min/max loan period 12-60 months
        if(amount<2000||amount>10000 || months<12|| months>60){
            throw new DecisionEngineException("Insert valid amount and/or loan period");
        }else if(creditScore==0){
            throw new DecisionEngineException("Error, customer has dept, no amount of loan allowed");
        }else if(creditScore>=1) {
            //in case of modifier equal or bigger than 1 - 1)find max loan amount for requested period
            int maxLoan=findMaxLoanAmount(creditModifier, months);
            loanResponse.setProposedAmount(maxLoan);
            return loanResponse;
        }else {
            // 1) find max sum what would be suitable for the customer(was done before)
            int maxLoan=findMaxLoanAmount(creditModifier, months);
            loanResponse.setProposedAmount(maxLoan);
            // 2)find loan period that would meet the requested sum
            int suggestedLoanPeriod=findLoanPeriod(creditModifier,amount);
            loanResponse.setProposedPeriod(suggestedLoanPeriod);
            return loanResponse;
        }
    }

    private double getCreditScore(int creditModifier, double amount, int months){
        return  ( (double) creditModifier/ amount) * (double)months;
    }

    private int findMaxLoanAmount(int creditModifier, int months){
        //lowest credit score can be 1 -> Max amount=(creditModifier*months)/1
        int maxLoan=creditModifier*months;
        //check if max loan is in bounds:
        if(maxLoan<2000){
            throw new DecisionEngineException("Error, maximum possible loan amount is below minimum output sum");
        }
        else if(maxLoan>2000 && maxLoan<=10000 ){
            return maxLoan;
        }else{
           return 10000;
        }
    }

    private int findLoanPeriod(int creditModifier, double amount){
        //suitable period= amount * 1(lowest possible credit  score) / credit modifier
        int loanPeriod=(int)(amount)/creditModifier;
        //check if loan period is valid:
        if(loanPeriod>60){
            throw new DecisionEngineException("Error, calculated period exceeds the maximum loan period");
        }else if(loanPeriod<12){
            return loanPeriod=12;
        }else{
            return loanPeriod;
        }
    }
}
