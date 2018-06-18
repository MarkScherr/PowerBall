/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.powerball.DAO;

import com.sg.powerball.DTO.NumbersDrawn;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mrsch
 */
public interface NumbersDrawnRepository extends JpaRepository<NumbersDrawn, Integer>{
    NumbersDrawn findByDrawId(String drawId);
}
