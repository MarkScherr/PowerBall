/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.powerball.Service;

import com.sg.powerball.DTO.NumbersDrawn;
import java.util.List;

/**
 *
 * @author mrsch
 */
public interface NumbersDrawnService {
    Result<List<NumbersDrawn>> all();

    Result deleteById(int id);

    Result<NumbersDrawn> findById(int id);

    Result<NumbersDrawn> save(NumbersDrawn draw);
 
}
