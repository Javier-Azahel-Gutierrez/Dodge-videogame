import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;

public class Ventana extends JFrame {

    Points puntos ;

    public Ventana() {
        initValues();
        puntos = new Points();
    }

    boolean alive = false;

    private void initValues() {
        PointsThread pt = new PointsThread();


        JButton btnStart = new JButton("Start");
        btnStart.setBounds(10, 10, 75, 25);

        Pers img2 = new Pers("images/pc40.png");
        img2.setBounds(10, 45, 40, 40);

        Enemy img3[] = new Enemy[4];
        for (int i = 0; i < img3.length; i++) {
            img3[i] = new Enemy();
            img3[i].setPers(img2);
            img3[i].pt=pt;
            // img3[i].setEnabled(false);
        }
        img2.setEnemys(img3);
        img2.setButton(btnStart);
        // img3.setBounds(10, 125, 80, 40);

        //puntos/records/etc :
        JLabel scoreBoard = new JLabel("0");
        scoreBoard.setBounds(160,10,50,25);
        JLabel currentPoint = new JLabel("0");
        currentPoint.setBounds(260,10,50,25);
        JLabel name_scoreBoard = new JLabel("Record:");
        name_scoreBoard.setBounds(100,10,50,25);
        JLabel name_currentPoint = new JLabel("Points:");
        name_currentPoint.setBounds(200,10,50,25);

        Fondo paisaje = new Fondo("images/fondoX.png");
		paisaje.setBounds(-520, 0, 1097, 494);
         


        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                if (ae.getSource() == btnStart) {
                    currentPoint.setText("0");
                    
                    pt.currentPoints=currentPoint;
                    pt.scoreBoard=scoreBoard;
                    pt.puntos=puntos;
                    
                    Thread player = new Thread(img2);
                    alive = true;
                    player.start();
                    System.out.println("se inicio juego");
                    btnStart.setEnabled(false);

                    Thread obstacles = new Thread(img3[0]);
                    obstacles.start();
                    Thread obstacles2 = new Thread(img3[1]);
                    obstacles2.start();
                    Thread obstacles3 = new Thread(img3[2]);
                    obstacles3.start();
                    Thread obstacles4 = new Thread(img3[3]);
                    obstacles4.start();

                    Thread numbers = new Thread(pt);
                    numbers.start();

                    // sonido = new Sonido("music/Scrimply.wav");
                    // sonido.play();
                }
            }
        };

        btnStart.addActionListener(listener);
        img2.addKeyListener(img2);

        img2.setFocusable(true);
        btnStart.setFocusable(false);

        add(btnStart);
        add(img2);
        add(currentPoint);
        add(scoreBoard);
        add(name_currentPoint);
        add(name_scoreBoard);
        
        for (int i = 0; i < img3.length; i++) {
            add(img3[i]);
        }
        add(paisaje);
        setTitle("Examen-1 - ");
        setSize(436, 494);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}