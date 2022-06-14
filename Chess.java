import java.awt.*; //Color, Graphics, BorderLayout
import javax.swing.*; //JFrame, JTextLabel,
import java.io.File;
import java.awt.event.*;
import java.util.*;
/**
 * Write a description of class Chess here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Chess{
    public static void main(String[] args){
        Board board = new Board();
        
        JFrame frame = new JFrame();
        frame.setSize(800,800+22+20);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(board);
        
        frame.add(board.status,BorderLayout.NORTH);
        //frame.add(board.status1, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}




