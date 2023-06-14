package gui;

import javax.swing.JFrame;

/**
 * Klasa Brik brejker frame.
 *
 */

public class BBFrame extends JFrame{
	/**
	 * Kreiranje panela.
	 */
	BBPanel bbpanel = new BBPanel();
	/**
	 * Konstruktor za BBFrame.
	 */
	public void BBFrame() {
		bbpanel = new BBPanel();
	}
	
	
	/**
	 * Funkcija za pocetak igre koja kreira prozor i postavlja panel u njega.
	 */
	public void start() {
		bbpanel = new BBPanel();
		this.setBounds(10, 10, 700, 600);
		this.setTitle("Brik brejker");		
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(bbpanel);
	    this.setVisible(true);
	}
	
	/**
	 * Funkcija za vracanje panela.
	 * @return panel
	 */
	public BBPanel getBBPanel() {
		return bbpanel;
	}
}
