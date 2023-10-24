/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.formatters;

import com.qlphongtro.pojo.Roleuser;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author HOME
 */
public class RoleUserFormatter implements Formatter<Roleuser> {

    @Override
    public String print(Roleuser loai, Locale locale) {
        return String.valueOf(loai.getId());

    }

    @Override
    public Roleuser parse(String roleId, Locale locale) throws ParseException {
        int id = Integer.parseInt(roleId);
        return new Roleuser(id);
    }
    
}
