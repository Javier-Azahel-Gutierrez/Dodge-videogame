import javax.swing.*;
import java.awt.event.*;

public class Pers extends JLabel implements Runnable, KeyListener {

    private String url1, url2;
    private ImageIcon icon;
    private int posX = 10, posY = 45, fondoX = 0, incrementoSalto = 120;
    private boolean runStatus = false, right = false, left = false, up = false, check = false,
            down = false, stop = false;
    public boolean testE = false;
    private Enemy img3[];
    private JButton btnStart;
    Points puntos;

    public Pers(String url1) {
        this.url1 = url1;
        icon = new ImageIcon(this.getClass().getResource(url1));
        setIcon(icon);
    }

    public void setEnemys(Enemy img3[]) {
        this.img3 = img3;
    }

    public void setButton(JButton btnStart) {
        this.btnStart = btnStart;
    }

    public void run() {
        stop = false;
        right = false;
        left = false;
        up = false;
        check = false;
        down = false;
        runStatus = true;
        int n = 0;

        while (runStatus == true) {
            check = true;
            move();
            heaven();
            n++;
            
            try {
                synchronized (this) {
                    if (stop) {
                        break;
                    }
                }
            } catch (Exception e) {
            }
            check = false;//es el coco del team fortress
        }
    }

    private void heaven() {
        for (int i = 0; i < img3.length; i++) {
            if (img3[i].killed == true) {
                freeze();
            }
        }
    }

    public void freeze() {
        for (int i = 0; i < img3.length; i++) {
            img3[i].killed = true;
            img3[i].restart();
            img3[i].interseccion();
            img3[i].stopHilo();
        }
        JOptionPane.showMessageDialog(null, "Has perdido y has acomulado un total de: "/*puntos */);
        btnStart.setEnabled(true);
        this.stop = true;
    }

    public void move() {
        runStatus = true;
        // if (posX >= 10 && posY >= 40 && posX <= 370 && posY <= 400) {
        if (right) {
            if (posX < 370) {
                posX += 40;
                setIcon(icon);
                try {
                    Thread.sleep(95);
                } catch (Exception e) {
                }
                // System.out.println(posX + "<--X ~ Y-->" + posY);
            }
        }
        if (left) {
            if (posX > 10) {
                posX -= 40;
                setIcon(icon);
                try {
                    Thread.sleep(95);
                } catch (Exception e) {
                }
                // System.out.println(posX + "<--X ~ Y-->" + posY);
            }
        }
        if (up) {
            if (posY > 45) {
                posY -= 40;
                setIcon(icon);
                try {
                    Thread.sleep(95);
                } catch (Exception e) {
                }
                // System.out.println(posX + "<--X ~ Y-->" + posY);
            }
        }
        if (down) {
            if (posY < 375) {
                posY += 40;
                setIcon(icon);
                try {
                    Thread.sleep(95);
                } catch (Exception e) {
                }
                // System.out.println(posX + "<--X ~ Y-->" + posY);
            }
        }
        setBounds(posX, posY, 40, 40);
    }

    synchronized void stopHilo() {
        stop = true;
        right = false;
        left = false;
        up = false;
        check = false;
        down = false;
        notify();
    }


    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
        if (runStatus) {
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = true;
            }
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (ke.getKeyCode() == KeyEvent.VK_UP) {
                up = true;
            }
            if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                down = true;
            }
        }
    }

    public void keyReleased(KeyEvent ke) {
        if (runStatus) {
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_UP) {
                up = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                left = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                down = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_Z) {
                testE = false;
            }
        }
    }

}