package com.thoughtworks.web.controller;

import com.thoughtworks.service.ToolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class ToolController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolController.class);

    @Autowired
    private ToolService toolService;

    @GetMapping(value = "/tool/individual-income")
    public double calculateIndividualIncome(@PathParam("preTaxIncome") double preTaxIncome,
                                            @PathParam("socialSecurityAndProvidentFound")
                                                    double socialSecurityAndProvidentFound) {
        return toolService.calculateIndividualIncome(preTaxIncome, socialSecurityAndProvidentFound);
    }
}
