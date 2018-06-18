/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.powerball.DTO;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mrsch
 */
@Entity
public class NumbersDrawn {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int drawId;
    private LocalDate timePicked;
    private int ball1;
    private int ball2;
    private int ball3;
    private int ball4;
    private int ball5;
    private int powerBall;


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
    
}
