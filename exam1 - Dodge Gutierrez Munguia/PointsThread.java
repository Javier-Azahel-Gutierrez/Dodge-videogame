import javax.swing.*;
import java.awt.event.*;

public class PointsThread extends JLabel implements Runnable {

    private boolean stop=false, pause=false;

    Points puntos;
    JLabel scoreBoard, currentPoints;

    public void run() {
        stop=false;
        int n = 1;
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        out:while (true) {
            try {
                synchronized (this) {
                    while(pause){
                        wait();
                    }
                    if (stop) {
                        currentPoints.setText(Integer.toString(n));
                        break out;
                    }
                }
                Thread.sleep(1000);
                currentPoints.setText(Integer.toString(n));
                n++;
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        
        if (puntos.getRecord()<=n) {
            puntos.setRecord(n);
            scoreBoard.setText(Integer.toString(n));
        }
    }

    synchronized void pause(){

    }
    synchronized void resume(){
        
    }
    synchronized void stop(){
        stop = true;
        notify();
    }
}
