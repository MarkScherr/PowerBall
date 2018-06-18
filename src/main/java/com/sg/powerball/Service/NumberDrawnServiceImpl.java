/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.powerball.Service;

import com.sg.powerball.DAO.NumbersDrawnRepository;
import com.sg.powerball.DTO.NumbersDrawn;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mrsch
 */
@Service
public class NumberDrawnServiceImpl implements NumbersDrawnService{
    
    @Autowired
    private NumbersDrawnRepository repo;
    
    
    @Override
    public Result<List<NumbersDrawn>> all() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result<NumbersDrawn> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result<NumbersDrawn> save(NumbersDrawn draw) {
        Result<NumbersDrawn> result = validate(draw);
        if (result.isSuccess()) {
            draw = repo.save(draw);
            result.setPayload(draw);
        }
        return result;
    }  
        private Result<NumbersDrawn> validate(NumbersDrawn numbers) {
        Result<NumbersDrawn> result = new Result<>();
        
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<NumbersDrawn>> errs = validator.validate(numbers);
        
        for(ConstraintViolation<NumbersDrawn> err : errs) {
            result.addMessage(err.getMessage());
        }
        
        return result;
    }
        
    public NumbersDrawn generateAutoPicks(NumbersDrawn numbers) {
       Random rdm = new Random();
       int randomBall2 = 0;
       int randomBall3 = 0;
       int randomBall4 = 0;
       int randomBall5 = 0;
        
       
         numbers.setBall1(rdm.nextInt(69)+1);
      do {
           randomBall2 = rdm.nextInt(69) +1;
           numbers.setBall2(randomBall2);
       }   while(randomBall2 == numbers.getBall1());
       
        do {
           randomBall3 = rdm.nextInt(69) +1;
           numbers.setBall3(randomBall3);
       }   while(randomBall3 == numbers.getBall1() || randomBall3 == numbers.getBall2());
        
        do {
           randomBall4 = rdm.nextInt(69) +1;
           numbers.setBall4(randomBall4);
       }   while(randomBall4 == numbers.getBall1() || randomBall4 == numbers.getBall2() 
                || randomBall4 == numbers.getBall3());
        
        do {
           randomBall5 = rdm.nextInt(69) +1;
           numbers.setBall5(randomBall5);
       }   while(randomBall5 == numbers.getBall1() || randomBall5 == numbers.getBall2() 
                || randomBall5 == numbers.getBall3() || randomBall5 == numbers.getBall4());
        
        numbers.setPowerBall(rdm.nextInt(26) +1);
        return numbers;
    }
}
