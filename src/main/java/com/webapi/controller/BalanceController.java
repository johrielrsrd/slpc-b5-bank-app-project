package com.webapi.controller;

import com.webapi.entity.Balance;
import com.webapi.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/balance")
public class BalanceController {
    @Autowired
    private BalanceRepository balanceRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Balance> getAllBalances(){
        return balanceRepository.findAll();
    }
}
