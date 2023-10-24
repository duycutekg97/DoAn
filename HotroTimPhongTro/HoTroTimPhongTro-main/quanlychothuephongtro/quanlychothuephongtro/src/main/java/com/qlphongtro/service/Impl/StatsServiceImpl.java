/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.repository.StatsRepository;
import com.qlphongtro.service.StatsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOME
 */
@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsRepository statsRepository;

    @Override
    public int getNumberUser(Map<String, String> params,int quarter) {
        return this.statsRepository.getNumberUser(params, quarter);
    }

    @Override
    public int getNumberUserMonthOfYear(Map<String, String> params, int month) {
        return this.statsRepository.getNumberUserMonthOfYear(params,month);
    }
}   
