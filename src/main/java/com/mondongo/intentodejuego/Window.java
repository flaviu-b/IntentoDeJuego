/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mondongo.intentodejuego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.CharBuffer;
import javax.swing.JFrame;

/**
 *
 * @author Flavi
 */
public class Window extends JFrame implements Runnable{
    
    public static final int width = 800, height = 600;
    private Canvas canvas;
    private Thread thread;
    
    
    public Window(){
        setTitle("Couscous No Fierro");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(true);
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        new Window().start();
    }

    @Override
    public void run() {
        
        
        
        stop();
    }
    
    private void start(){
        
        thread  = new Thread(this);
        thread.start();
        
        
    }
    
    private void stop(){
        try {
            thread.join();
        } catch (Exception e) {
        }
    }

}
