package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Klasa Brik brejker play grid.
 */
public class BBPlayGrid{
	/**
	 * Inicijalizacija matrice.
	 */
	public int mapa[][];
	/**
	 * Inicijalizacija sirine cigle.
	 */
	public int sirinaCigle;
	/**
	 * Inicijalizacija visine cigle.
	 */
	public int visinaCigle;
	
	/**
	 * Konstruktor za klasu BBPlayGrid.
	 */
	public BBPlayGrid (int red, int kol)
	{
		mapa = new int[red][kol];		
		for(int i = 0; i<mapa.length; i++)
		{
			for(int j =0; j<mapa[0].length; j++)
			{
				mapa[i][j] = 1;
			}			
		}
		
		sirinaCigle = 540/kol;
		visinaCigle = 150/red;
	}	
	
	/**
	 * Funkcija za iscrtavanje matrice cigli.
	 */
	public void draw(Graphics2D g)
	{
		for(int i = 0; i<mapa.length; i++)
		{
			for(int j =0; j<mapa[0].length; j++)
			{
				if(mapa[i][j] > 0)
				{
					g.setColor(Color.white);
					g.fillRect(j * sirinaCigle + 70, i * visinaCigle + 55, sirinaCigle, visinaCigle);
					
					//Okvir za ciglu.
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.black);
					g.drawRect(j * sirinaCigle + 70, i * visinaCigle + 55, sirinaCigle, visinaCigle);				
				}
			}
		}
	}
	
	/**
	 * Funkcija za uklanjanje pogođene cigle.
	 */
	public void postaviVrijednost(int vrijednost, int red, int kol){
		mapa[red][kol] = vrijednost;
	}
}
