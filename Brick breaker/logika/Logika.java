package logika;

import java.awt.Rectangle;
import java.util.Random;

import gui.BBPlayGrid;

/**
 * Klasa za logiku igre.
 *
 */
public class Logika {
	/**
	 * Inicijalizacija potrebnih varijabli.
	 */
	private int skor;
	private int ukupnoCigli;
	private int pauza;
	private int Xigraca;
	private int Xlopte;
	private int Ylopte;
	private int smjerLoptePoX;
	private int smjerLoptepPoY;
	private int brzina=-1;
	private int level=1;
	
	/**
	 * Konstruktor za klasu logike.
	 */
	public Logika() {
		skor = 0;
		ukupnoCigli = 21;
		pauza=10;
		Xigraca = 310;
		brzina=1;
		level=1;
		Xlopte = 350;
		Ylopte = 530;
		
		Random random = new Random();
		int n = random.nextInt(0,2) - 2;  
		System.out.println("2Ispis n-a" + n);
		smjerLoptePoX = n;
		smjerLoptepPoY = -2;
		
	}
	
	/**
	 * Funkcija za pomjeranje ploce igraca u desno.
	 */
	public void pomjeriDesno() {
		if(Xigraca <= 580){
			Xigraca+=20;
		}
	}
	
	/**
	 * Funkcija za pomjeranje ploce igraca u lijevo.
	 */
	public void pomjeriLijevo() {
		if(Xigraca >=10){
			Xigraca-=20;
		}
	}
	
	/**
	 * Funkcija za pocetak igre, povecavanje skora i levela.
	 */
	public void igrajBB(boolean da, int skorr) {
		Xlopte = 350;
		Ylopte = 530;
		Random random = new Random();
		int n = random.nextInt(0,2) - 2;
		System.out.println("1Ispis n-a" + n);
		smjerLoptePoX = n;
		smjerLoptepPoY = -2;
		Xigraca = 310;
		skor = 0;
		ukupnoCigli = 21;
		if(da) {
			brzina++;
			level++;
			skor=skorr;
		}
		else {
			brzina=1;
			level=1;
			skor=0;
		}
	}

	/**
	 * Funkcija za trenutnu igru, provjeravanje da li se lopta sijece sa plocom igraca, tj. da li je loptica udarila od plocu.
	 * Kao i provjera da li loptica udarila neku ciglu.
	 */
	
	public void trenutnoIgra(BBPlayGrid mapa){
		if(new Rectangle(Xlopte, Ylopte, 20, 20).intersects(new Rectangle(Xigraca, 550, 30, 8)))
		{
			smjerLoptepPoY = -smjerLoptepPoY;
			smjerLoptePoX = -2;
		}
		else if(new Rectangle(Xlopte, Ylopte, 20, 20).intersects(new Rectangle(Xigraca + 70, 550, 30, 8)))
		{
			smjerLoptepPoY = -smjerLoptepPoY;
			smjerLoptePoX = smjerLoptePoX + 1;
		}
		else if(new Rectangle(Xlopte, Ylopte, 20, 20).intersects(new Rectangle(Xigraca + 30, 550, 40, 8)))
		{
			smjerLoptepPoY = -smjerLoptepPoY;
		}
				
		A: for(int i = 0; i<mapa.mapa.length; i++)
		{
			for(int j =0; j<mapa.mapa[0].length; j++)
			{				
				if(mapa.mapa[i][j] > 0)
				{
					int ciglaX = j * mapa.sirinaCigle + 70;
					int ciglaY = i * mapa.visinaCigle + 55;
					int ciglaSirina = mapa.sirinaCigle;
					int ciglaVisina = mapa.visinaCigle;
					
					Rectangle pravougaonik = new Rectangle(ciglaX, ciglaY, ciglaSirina, ciglaVisina);					
					Rectangle loptica = new Rectangle(Xlopte, Ylopte, 20, 20);
					Rectangle cigla = pravougaonik;
					
					if(loptica.intersects(cigla))
					{					
						mapa.postaviVrijednost(0, i, j);
						skor+=10;	
						ukupnoCigli--;
						
						if(Xlopte + 19 <= cigla.x || Xlopte + 1 >= cigla.x + cigla.width)	
						{
							smjerLoptePoX = -smjerLoptePoX;
						}
						else
						{
							smjerLoptepPoY = -smjerLoptepPoY;				
						}
						
						break A;
					}
				}
			}
		}
		
		/**
		 * Pomjeranje loptice u trenutnom smjeru.
		 */
		Xlopte += brzina*smjerLoptePoX;
		Ylopte += brzina*smjerLoptepPoY;
		
		/**
		 * Provjera da li je lopta udarila lijevu ivicu.
		 */
		if(Xlopte < 0)
		{
			smjerLoptePoX = -smjerLoptePoX;
		}
		
		/**
		 * Provjera da li je lopta udarila gornju ivicu.
		 */
		if(Ylopte < 0)
		{
			smjerLoptepPoY = -smjerLoptepPoY;
		}
		
		/**
		 * Provjera da li je lopta udarila desnu ivicu.
		 */
		if(Xlopte > 670)
		{
			smjerLoptePoX = -smjerLoptePoX;
		}		
	}
	
	/**
	 * Getter za trenutni skor.
	 * @return skor
	 */
	public int getSkor() {
		return skor;
	}
	
	/**
	 * Getter za trenutnu x koordinatu ploce.
	 * @return Xigraca
	 */
	public int getXigraca() {
		return Xigraca;
	}
	
	/**
	 * Getter za trenutnu x koordinatu lopte.
	 * @return Xlopte
	 */
	public int getXlopte() {
		return Xlopte;
	}
	
	/**
	 * Getter za trenutnu y koordinatu lopte.
	 * @return Ylopte
	 */
	public int getYlopte() {
		return Ylopte;
	}
	
	/**
	 * Getter za trenutni broj cigli.
	 * @return ukupnoCigli
	 */
	public int getUkupnoCigli() {
		return ukupnoCigli;
	}
	
	/**
	 * Getter za trenutni level.
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Funkcija za zaustavljanje lopte pri kraju igre.
	 */
	public void zaustaviLoptu() {
		smjerLoptePoX = 0;
		smjerLoptepPoY = 0;
	}
}
