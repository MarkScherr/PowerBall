/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.powerball.DTO;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mrsch
 */

@Entity
@Table(name = "numbers", uniqueConstraints={@UniqueConstraint(columnNames = {"ball1" , "ball2" ,"ball3" , "ball4" , "ball5"})})
public class Numbers {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pickId;
    
    private LocalDate timePicked;
    @NotNull
    @Size(min = 1, message = "Name is Required")
    @Size(max = 100, message = "Name is too long (100 characters max).")
    private String firstName;
    
    @NotNull
    @Size(min = 1, message = "Name is Required")
    @Size(max = 100, message = "Name is too long (100 characters max).")
    private String lastName;
        
    @NotNull
    @Size(min = 1, message = "E-mail is Required")
    @Size(max = 100, message = "E-mail address  is too long (100 characters max).")
    private String eMail;
    
    @NotNull
    private String  stateName;
    
    @Column(name = "ball1")
    private int ball1;  
    @Column(name = "ball2")
    private int ball2;   
    @Column(name = "ball3")
    private int ball3;
    @Column(name = "ball4")
    private int ball4;
    @Column(name = "ball5")
    private int ball5;

    private  int powerBall;
    private boolean quickPick;
    private boolean active;

    public void setPickId(int pickId) {
        this.pickId = pickId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPickId() {
        return pickId;
    }
    public boolean isQuickPick() {
        return quickPick;
    }

    public void setQuickPick(boolean quickPick) {
        this.quickPick = quickPick;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

        public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getBall1() {
        return ball1;
    }

    public void setBall1(int ball1) {
        this.ball1 = ball1;
    }

    public int getBall2() {
        return ball2;
    }

    public void setBall2(int ball2) {
        this.ball2 = ball2;
    }

    public int getBall3() {
        return ball3;
    }

    public void setBall3(int ball3) {
        this.ball3 = ball3;
    }

    public int getBall4() {
        return ball4;
    }

    public void setBall4(int ball4) {
        this.ball4 = ball4;
    }

    public int getBall5() {
        return ball5;
    }

    public void setBall5(int ball5) {
        this.ball5 = ball5;
    }

    public int getPowerBall() {
        return powerBall;
    }

    public void setPowerBall(int powerBall) {
        this.powerBall = powerBall;
    }
    
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}