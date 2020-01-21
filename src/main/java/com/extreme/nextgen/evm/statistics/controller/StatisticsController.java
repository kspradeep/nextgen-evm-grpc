package com.extreme.nextgen.evm.statistics.controller;

import com.extreme.nextgen.evm.statistics.client.StatisticsClientSpringBoot;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statistics", produces = "application/json")
public class StatisticsController {
    @Autowired
    private StatisticsClientSpringBoot statisticsClientSpringBoot;

    @RequestMapping(value = "/test")
    public ResponseEntity<Object> getMessage() throws InvalidProtocolBufferException {

        return new ResponseEntity<>(statisticsClientSpringBoot.doUnaryCall(), HttpStatus.OK);
    }
}
