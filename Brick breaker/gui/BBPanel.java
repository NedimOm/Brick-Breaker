package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import logika.Logika;

/**
 * Klasa Brik brejker panel.
 *
 */
public class BBPanel extends JPanel implements KeyListener, ActionListener{
	BBPlayGrid BBpg;
	/**
	 * Povezivanje s logikom.
	 */
	Logika logika = new Logika();
	/**
	 * Tajmer.
	 */
	private Timer timer;
	/**
	 * Duzina pauze.
	 */
	private int pauza;
	/**
	 * Mapa igrice.
	 */
	private BBPlayGrid mapa;
	/**
	 * Boolean varijabla za provjeru da li je igra u tijeku.
	 */
	boolean play;
	
	/**
	 * Funkcija koja vraca BB playgrid.
	 * @return play grid
	 */
	public BBPlayGrid getBBpg() {
		return BBpg;
	}

	/**
	 * Konstruktor za BBpanel.
	 */
	public BBPanel() {
		logika = new Logika();
		pauza=8;
		play=false;
		mapa = new BBPlayGrid(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer=new Timer(pauza,this);
		timer.start();
		setVisible(true);
	}

	/**
	 * Funkcija koja osluškuje pritiskanje tipke tastature i poziva odgovarajuću funkciju iz logike.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){        
			logika.pomjeriDesno();
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			logika.pomjeriLijevo();
        }		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play){
				play=true;
				logika.igrajBB(false, 0);
				mapa = new BBPlayGrid(3, 7);
				repaint();
			}
        }		
		
	}
	
	/**
	 * Funkcija za održavanje igre aktivnom.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play){			
			logika.trenutnoIgra(mapa);		
			repaint();
		}
	}

	/**
	 * Paint funkcija za ispisivanje pri kraju igre, ispisivanje skora, levela..
	 */
	public void paint(Graphics g)
	{	
		int ukupnoCigli = logika.getUkupnoCigli();
		
		g.setColor(Color.GRAY);
		g.fillRect(1, 1, 692, 592);
		
		mapa.draw((Graphics2D) g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
				
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		int skor = logika.getSkor();
		g.drawString(""+skor, 590,30);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		int level = logika.getLevel();
		g.drawString("Level:"+" "+level, 30,30);
		
		
		
		g.setColor(Color.BLUE);
		int Xigraca = logika.getXigraca();
		g.fillRect(Xigraca, 550, 100, 8);
		
		g.setColor(Color.BLUE);
		int Ylopte = logika.getYlopte();
		int Xlopte = logika.getXlopte();
		g.fillOval(Xlopte, Ylopte, 20, 20);
		
		/**
		 * Ispis za pocetak igre.
		 */
		if(ukupnoCigli == 21 && !play) {	
			g.setColor(Color.BLUE);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("Startaj igru klikom na: ", 195,300);
            
            g.setColor(Color.BLUE);
            g.setFont(new Font("serif",Font.BOLD, 25));           
            g.drawString("TAB + ENTER ", 250,350);
            g.drawString("SRETNO! ", 280,400);
		}
		
		/**
		 * Provjera da li smo prešli trenutni level, pri prelasku levela ubrzava se loptica.
		 */
		if(ukupnoCigli <= 0 && play)
		{
			mapa = new BBPlayGrid(3, 7);
			logika.igrajBB(true, skor);
			repaint();
		}
		
		/**
		 * Provjera da li je loptica pala na dno, tacnije da li je kraj.
		 */
		if(Ylopte > 570)
        {
			 play = false;
			 logika.zaustaviLoptu();
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("Izgubili ste sa skorom: "+skor, 190,300);
             skor=0;
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 25));           
             g.drawString("Za restart pritisnite ENTER.", 190,350);        
        }
		
		g.dispose();
	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

}
