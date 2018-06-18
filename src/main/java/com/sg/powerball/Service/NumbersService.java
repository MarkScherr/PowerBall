/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.powerball.Service;

import com.sg.powerball.DTO.Numbers;
import java.util.List;

/**
 *
 * @author mrsch
 */
public interface NumbersService {

    Result<List<Numbers>> all();

    Result<Numbers> save(Numbers pick);
    
    List<Numbers> search(Numbers numbers);

}