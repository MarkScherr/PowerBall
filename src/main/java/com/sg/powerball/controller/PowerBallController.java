/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.powerball.controller;

import com.sg.powerball.DTO.Numbers;
import com.sg.powerball.DTO.NumbersDrawn;
import com.sg.powerball.Service.NumberDrawnServiceImpl;
import com.sg.powerball.Service.NumberServiceImpl;
import com.sg.powerball.Service.Result;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mrsch
 */
@Controller
public class PowerBallController {
    
    @Autowired
    private NumberServiceImpl numberService;
    
    @Autowired
    private NumberDrawnServiceImpl numberDrawnService;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/quickPick") 
    public String quickPick(Model model) {
        model.addAttribute("numbers" , new Numbers());
        return "quickPick";
    }
    
   @PostMapping("/quickPick") 
   public String quickPick(@Valid Numbers numbers, BindingResult result, Model model) {
       Numbers newNumbers = numberService.generateAutoPicks(numbers);
       
       if (result.hasErrors()) {
           return "quickPick";
       }
       
       numbers.setActive(true);
       newNumbers.setQuickPick(true);
       save(newNumbers);
      
       model.addAttribute("numbers" , newNumbers);
       return "/quickPickResults";
   }
   
    @GetMapping("/pickNumbers") 
    public String pickNumbers(Model model) {
        model.addAttribute("numbers" , new Numbers());
        return "pickNumbers";
    }
    
    @PostMapping("/pickNumbers") 
    public String pickNumbers(@Valid Numbers numbers, BindingResult result, Model model) {
       Result<Numbers> numberResult = numberService.checkNumbers(numbers);
      
       if (!numberResult.isSuccess() || result.hasErrors()) {
         //  result.rejectValue("ballError", "error in balls");
           model.addAttribute("numbers", numbers);
           return "pickNumbers";
       }
       
       numbers.setActive(true);
       numbers.setQuickPick(false);
       save(numbers);
       return "redirect:/";
   }
    
    @GetMapping("/drawNumbers") 
    public String drawNumbers(Model model) {
         model.addAttribute("numbersDrawn" , new NumbersDrawn());
        return "drawNumbers";
    }
   
    @PostMapping("/drawNumbers")
    public String drawNumbers(NumbersDrawn numbers, Model model) {
        NumbersDrawn newNumbers = numberDrawnService.generateAutoPicks(numbers);
        Numbers bestActive = numberService.findBestActiveNumbers(numbers);
        numberService.update();
        save(newNumbers);
        
        model.addAttribute("numbers" , newNumbers);
        model.addAttribute("bestActive" , bestActive);
        return "/drawNumbersResults";
    }
    
    @GetMapping("/searchForPick") 
    public String search(Model model) {
        model.addAttribute("numbers" , new Numbers());
        return "searchForPick";
    }
    
    @PostMapping("/searchForPick")
    public String search(Numbers number, Model model) {
        List<Numbers> numberList = numberService.search(number);
        model.addAttribute("numbers", numberList);
        return "searchResults";
    }
    
   private boolean save(Numbers numbers) {
        Result<Numbers> saveResult = numberService.save(numbers);
        if (!saveResult.isSuccess()) {
                return false;
            }
        return true;
    }
   
      private boolean save(NumbersDrawn numbers) {
        Result<NumbersDrawn> saveResult = numberDrawnService.save(numbers);
        if (!saveResult.isSuccess()) {
                return false;
            }
        return true;
    }
    

}
