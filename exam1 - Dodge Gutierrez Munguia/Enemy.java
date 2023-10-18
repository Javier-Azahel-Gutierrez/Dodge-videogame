import java.awt.event.KeyEvent;
import java.util.Random;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.geom.*;

//hacer que los enemigos aparezcan hasta despues de darle start, BIEN
//darle una velocidad diferente a cada enemigo
//si es posible que vaya aumentado en tiempo
//hacer que cuando la imagen salga de la pantalla vuelva a aparecer con coordenadas actualizadas del jugardor 
// se puede con un if usando la variable independiente para crear una funcion para actualizar la posicion

public class Enemy extends JLabel implements Runnable {
    PointsThread pt;
    // atributos
    boolean runStatus, killed, stop;
    String urlS, urlD, urlU;
    ImageIcon iconS, iconU, iconD;
    private Pers img2;
    int lastPosition, numb, from, Vel = 1, sturt, CarrilX, CarrilY, time;
    // lastposition
    // numb = utilizada para ver que tipo de enemigo se crea side/up/down
    // from = para saber si el de side inicia del lado izq o derecho
    // VelX & VelY = velocidad a la que se moveran
    // sturt = para saber con que velocidad iniciaran
    int ListVelocity[] = { 1, 2, 3, 4 }; // velocidad inicial de los enemigos

    // Constructor
    public Enemy() {
        /*
         * numb = (int) (Math.random() * 3 + 1);
         * urlS = "images/elira.png";
         * iconS = new ImageIcon(this.getClass().getResource(urlS));
         * urlD = "images/eliraD.png";
         * iconD = new ImageIcon(this.getClass().getResource(urlD));
         * urlU = "images/eliraU.png";
         * iconU = new ImageIcon(this.getClass().getResource(urlU));
         * if (numb == 1) {
         * setIcon(iconS);
         * setBounds(10, 125, 80, 40);
         * }
         * if (numb == 2) {
         * setIcon(iconD);
         * setBounds(10, 125, 40, 80);
         * }
         * if (numb == 3) {
         * setIcon(iconU);
         * setBounds(10, 125, 40, 80);
         * }
         */
    }

    // llamado a la clase del personaje controllable para despues obtener sus
    // coordenadas
    public void setPers(Pers img2) {
        this.img2 = img2;
    }

    public void Side_enemy() {
        urlS = "images/elira.png";
        iconS = new ImageIcon(this.getClass().getResource(urlS));
        setIcon(iconS);
        from = (int) (Math.random() * 2 + 1);
        if (from == 1) {// de izq a derecha
            setBounds(-50, img2.getY(), 80, 40);
            sturt = (int) (Math.random() * 3 + 1);
            System.out.println("se creo de izq a derecha");

        } else {// derecha a izq
            setBounds(510, img2.getY(), 80, 40);
            sturt = (int) (Math.random() * 3 + 1);
            System.out.println("se creo de derecha a izq");
        }
    }

    public void Up_enemy() {// crea enemigo que ira hacia arriba
        urlU = "images/eliraU.png";
        iconU = new ImageIcon(this.getClass().getResource(urlU));
        setIcon(iconU);
        setBounds(img2.getX(), 520, 40, 80);
    }

    public void Down_enemy() {// crea enemigo que ira hacia abajo
        urlD = "images/eliraD.png";
        iconD = new ImageIcon(this.getClass().getResource(urlD));
        setIcon(iconD);
        setBounds(img2.getX(), -100, 40, 80);
    }

    public void create_Enemy() {
        numb = (int) (Math.random() * 3 + 1);
        //numb = 3;
        if (numb == 1) {// numb 1 es de izq - der
            Side_enemy();
        }
        if (numb == 2) {
            Up_enemy();
            System.out.println("se creo de hacia arriba");
        }
        if (numb == 3) {
            Down_enemy();
            System.out.println("se creo de hacia abajo");
        }
    }

    public void run() {
        killed = false;
        stop = false;
        try {// tiempo que se le dara al jugador de moverse de su posicion inicial
            Thread.sleep(3000);
            create_Enemy();
        } catch (Exception e) {
        }

        runStatus = true;
        while (runStatus == true) {
            // ancho=436,alto= 494);
            CarrilX = img2.getX();
            CarrilY = img2.getY();
            // a partir de aqui es el mov del enemigo
            if (numb == 1) {// side to side
                if (from == 1) {// movimiento de izq a derecha
                    for (lastPosition = this.getX(); lastPosition <= 550; lastPosition += Vel) {
                        interseccion();
                        if (killed==true) {
                            break;
                        }
                        setIcon(iconS);
                        setBounds(lastPosition, CarrilY, getWidth(), getHeight());

                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                        }
                    } // end for
                    setBounds(-50, img2.getY(), 80, 40);
                    if (time % 15 == 0) {
                        if (Vel < 4) {
                            Vel++;
                        }
                    }
                } else {// movimiento de derecha a izq
                    for (lastPosition = this.getX(); lastPosition >= -100; lastPosition -= Vel) {
                        interseccion();
                        if (killed==true) {
                            break;
                        }
                        setIcon(iconS);
                        setBounds(lastPosition, CarrilY, getWidth(), getHeight());
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                        }
                    } // end for*/
                    setBounds(510, img2.getY(), 80, 40);
                    if (time % 15 == 0) {
                        if (Vel < 4) {
                            Vel++;
                        }
                    }
                }
            }
            if (numb == 2) {// Up enemy
                for (lastPosition = this.getY(); lastPosition >= -100; lastPosition -= Vel) {
                    interseccion();
                    if (killed==true) {
                        break;
                    }
                    setIcon(iconU);
                    //System.out.println(CarrilX);
                    setBounds(CarrilX, lastPosition, getWidth(), getHeight());

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                } // end for
                setBounds(img2.getX(), 520, 40, 80);
                if (time % 15 == 0) {
                    if (Vel < 4) {
                        Vel++;
                    }
                }
            }
            if (numb == 3) {// Down Enemy
                for (lastPosition = this.getY(); lastPosition <= 520; lastPosition += Vel) {
                    interseccion();
                    if (killed==true) {
                        break;
                    }
                    setIcon(iconD);
                    setBounds(CarrilX, lastPosition, getWidth(), getHeight());

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                } // end for
                setBounds(img2.getX(), -100, 40, 80);
                if (time % 15 == 0) {
                    if (Vel < 4) {
                        Vel++;
                    }
                }
            }
            try {
                synchronized (this) {
                    // Porcion de codigo sincronizada
                    if (stop) {
                        // rompemos el ciclo
                        break;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
        } // end while
    }// end run

    public void interseccion() {//checar si enemigo colisiona con jugador
        //Sacamos las areas de los enemigos
        Area Area_enemy = new Area(this.getBounds());

        // area del personaje
        Area img2Area = new Area(img2.getBounds());

        //verificamos si hay colision con el personaje
            if (Area_enemy.intersects(img2Area.getBounds2D())) {
                killed = true;
                pt.stop();
                stopHilo();
            }
    }

    synchronized void stopHilo() {
        stop = true;
        restart();
        notify();
    }

    public void restart() {
        if (numb == 1) {
            if (from == 1) {
                setBounds(-120, img2.getY(), 80, 40);
            }else{
                setBounds(510, img2.getY(), 80, 40);
            }
        }
        if (numb == 2) {
            setBounds(img2.getX(), 520, 40, 80);
        }
        if (numb == 3) {
            setBounds(img2.getX(), -100, 40, 80);
        }
        killed = false;
    }

}
