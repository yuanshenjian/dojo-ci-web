package com.thoughtworks.service.impl;

import com.thoughtworks.service.ToolService;
import com.thoughtworks.util.IndividualIncomeCalculator;
import org.springframework.stereotype.Service;

@Service
public class ToolServiceImpl implements ToolService {
    @Override
    public double calculateIndividualIncome(double preTaxIncome, double socialSecurityAndProvidentFound) {
        return IndividualIncomeCalculator.builder().preTaxIncome(preTaxIncome)
                .socialSecurityAndProvidentFound(socialSecurityAndProvidentFound)
                .build().postTaxIncome();
    }
}
