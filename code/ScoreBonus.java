/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import java.awt.Image;

/**
 *
 * @author LUL
 */
public class ScoreBonus extends Bonus{
    public ScoreBonus(int x, int y, Image image, int amount, int width, int height){
        super(x,y,image, amount,  width,  height);
    }
    public void bonus(Pacman p){
        p.setScore(p.getScore() + bonusAmount);
    }
}
