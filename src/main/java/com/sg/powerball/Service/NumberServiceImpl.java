/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.powerball.Service;

import com.sg.powerball.DAO.PowerBallRepository;
import com.sg.powerball.DTO.Numbers;
import com.sg.powerball.DTO.NumbersDrawn;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.transaction.Transactional;
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
public class NumberServiceImpl implements NumbersService{
    
    @Autowired
    private PowerBallRepository repo;
    
    @Override
    public List<Numbers> search(Numbers numbers) {
        List<Numbers> returnList = new ArrayList<>();
        if(!numbers.getLastName().equals("") || !numbers.getFirstName().equals("") || !numbers.geteMail().equals("") || 
                !numbers.getStateName().equals("") || numbers.getPickId() != 0) {
            returnList = repo.findNumbers(numbers.getPickId(), numbers.getFirstName(), numbers.getLastName(), 
                    numbers.geteMail(), numbers.getStateName());
        }
        return returnList;
    }
    
    
    @Override
    public Result<List<Numbers>> all() {
        Result<List<Numbers>> result = new Result<>();
        result.setPayload(repo.findAll());
        return result;  
    }

    @Override
    public Result<Numbers> save(Numbers pick) {
        Result<Numbers> result = validate(pick);
        if (result.isSuccess()) {
            pick = repo.save(pick);
            result.setPayload(pick);
        }
        return result;
    }
    
    public Result<Numbers> checkNumbers(Numbers numbers) {
        Result<Numbers> result = new Result<>();
        if(numbers.getBall1() != numbers.getBall2() && numbers.getBall1() != numbers.getBall3() && 
                numbers.getBall1() != numbers.getBall4() && numbers.getBall1() != numbers.getBall5() &&
                numbers.getBall2() != numbers.getBall3() && numbers.getBall2() != numbers.getBall4() &&
                numbers.getBall2() != numbers.getBall5() && numbers.getBall3() != numbers.getBall4() &&
                numbers.getBall3() != numbers.getBall5() && numbers.getBall4() != numbers.getBall5()) {
            result.isSuccess();
        } else {
            result.addMessage("The five numbers need to be unique");
        }
        return result;
    }
    

    
    private Result<Numbers> validate(Numbers numbers) {
        Result<Numbers> result = new Result<>();
        
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Numbers>> errs = validator.validate(numbers);
        
        for(ConstraintViolation<Numbers> err : errs) {
            result.addMessage(err.getMessage());
        }
        
        return result;
    }

    public Numbers generateAutoPicks(Numbers numbers) {
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
    
    @Transactional
    public void update() {
       List<Numbers> numberList = all().getPayload();
        for (Numbers numbers : numberList) {
            repo.updateActive(numbers.getPickId() , false);
        }
       
    }

    public Numbers findBestActiveNumbers(NumbersDrawn numbers) {
        List<Integer> numberPickList = new ArrayList<>();
        numberPickList.add(numbers.getBall1());
        numberPickList.add(numbers.getBall2());
        numberPickList.add(numbers.getBall3());
        numberPickList.add(numbers.getBall4());
        numberPickList.add(numbers.getBall5());
        
        List<Numbers> activeNumbers = repo.findActive(true);
        
        int largestMatchingIndex = 0;
        int matches = 0;
        int mostMatched = 0;
        for(int i = 0 ; i < activeNumbers.size() ; i++) {
            for (int number : numberPickList) {
                if (activeNumbers.get(i).getBall1() == number) {
                    matches++;
                }
                if (activeNumbers.get(i).getBall2() == number) {
                    matches++;
                }
                if (activeNumbers.get(i).getBall3() == number) {
                    matches++;
                }
                if (activeNumbers.get(i).getBall4() == number) {
                    matches++;
                }
                if (activeNumbers.get(i).getBall5() == number) {
                    matches++;
                }
               
            }
            if (activeNumbers.get(i).getPowerBall() == numbers.getPowerBall()) {
                matches++;
            }
            
            if(matches > mostMatched) {
                mostMatched = matches;
                largestMatchingIndex = i;
            }
            matches = 0;
        }
        if(mostMatched == 0) {
            return null;
        }
        return activeNumbers.get(largestMatchingIndex);
        
    }



    
}
