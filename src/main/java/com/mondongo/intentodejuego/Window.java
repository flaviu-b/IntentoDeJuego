/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mondongo.intentodejuego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.nio.CharBuffer;
import javax.swing.JFrame;

/**
 *
 * @author Flavi
 */
public class Window extends JFrame implements Runnable {     // Esta clase es la ventana en la que va a correr el juego.

    public static final int width = 1600, height = 1200;                    // Seteamos constantes para el tamaño de la ventana
    private Canvas canvas;                                                          // Creamos el canvas para poder dibujar
    private Thread thread;                                                          // Creamos el thread "hilo" que usaremos para la logica del juego
    private boolean running;                                                    //Creamos booleano para el while principl
    private BufferStrategy bs;
    private Graphics g;

    private final int fps = 60;
    private double targetTime = 1000000000/fps;
    private double delta = 0;
    private int averageFps = fps;

    public Window() {                                                                  // Constructor de la ventana
        setTitle("Couscous No Fierro");                                         // Seteamos titulo 
        setSize(width, height);                                                      // Seteamos tamaño del a ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // Esta opcion hace que al cerrar la ventana el proceso acabe (si no se pone se cerrara la ventana pero no el proceso)
        setResizable(false);                                                          // Opcion para evitar que se modifique el tamaño de la ventana
        setLocationRelativeTo(null);                                            // Opcion para que se abra en medio de la pantalla
        setVisible(true);                                                               // Opcion para que se vea la ventana

        //Con esteo ya estaria seteada la ventana ahora pasamos al Canvas
        canvas = new Canvas();                                                  // Creamos un canvas
        canvas.setPreferredSize(new Dimension(width, height));  // Le damos dimensiones a canvas (la misma que la de la ventana)
        canvas.setMaximumSize(new Dimension(width, height)); // La dimension maxima sera la de la ventana 
        canvas.setMinimumSize(new Dimension(width, height));  // La dimension minima sera la de la ventana 
        canvas.setFocusable(true);                                              // Esto es para recibir entradas por parte de teclado
        
        add(canvas);
    }

    public static void main(String[] args) {
        new Window().start();
    }
    int x = 5;
    private void update(){
        x++;
    }
    
    private void draw(){
        bs = canvas.getBufferStrategy();
        
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //-----Zona en la que se dibuja--------
        
        g.clearRect(0, 0, width, height);

        g.setColor(Color.MAGENTA);

        g.drawString(""+averageFps, 10, 10);

        //--------------------------------
        g.dispose();
        bs.show();
    }
    
    
    @Override
    public void run() {          // Sobreescribimos el unico medodo de la interficie Runnable en este metodo colocaremos el while principal

        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;


        while (running) {
            
            now = System.nanoTime();
            delta += (now-lastTime)/targetTime;      //se va sumando el tienpo que ha pasado en cada iteración dividido entre el tiempo que deberia pasar por fotograma (si esta division da 1 significa que estamos yendo a 60 fps) 
            time += (now-lastTime);
            lastTime = now;

            if (delta>=1) {
                update();
                draw();
                delta--;
                frames++;    
            }
            if (time >= 1000000000) {
                averageFps = frames;
                frames = 0;
                time = 0;
            }
        }
        
        stop();
    }

    private void start() {                                                             //metodo para iniciar el hilo

        thread = new Thread(this);                                            //lo instanciamos
        thread.start();                                                                 //hace que se inicie el hilo?(cuando se ejecuta esta linea el codigo pasa a run() )
        running = true;
    }

    private void stop() {                                                            //metodo para parar el hilo
        try {
            thread.join();                                                             // .join() le dice al hilo principal que espere hasta que el hilo "thread" que es el que he creado yo termine de ejecutar sus instruciones
            running = false;
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }

}
