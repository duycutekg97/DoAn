/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlphongtro.repository;

import java.util.Map;

/**
 *
 * @author HOME
 */
public interface StatsRepository {

    int getNumberUser(Map<String, String> params,int quarter);

    int getNumberUserMonthOfYear(Map<String, String> params, int month);

}
