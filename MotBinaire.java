import java.util.Arrays;

import java.lang.Math;


public class MotBinaire
{
	private int[] mot;


	private MotBinaire ( int[] mot )
	{
		this.mot = mot;
	}

	public MotBinaire ( MotBinaire other )
	{
		int cpt = 0;

		this.mot = new int[other.nbBits()];

		for ( int i : other.mot ) this.mot[cpt++] = i;
	}


	public static MotBinaire fabrique( int[] mot )
	{
		int[] tabMot;
		int   cpt = 0;

		tabMot = new int[mot.length];

		for ( int i : mot )
		{
			if ( i != 0 && i != 1 ) return null;

			tabMot[cpt++] = i;
		}

		return new MotBinaire(tabMot);
	}


	public static MotBinaire fabrique ( String mot )
	{
		int[] tabMot;

		tabMot = new int[mot.length()];

		for ( int cpt = 0; cpt < mot.length(); cpt ++ )
		{
			if ( mot.charAt(cpt) != '0' && mot.charAt(cpt) != '1' ) return null;

			tabMot[cpt] = Integer.parseInt( mot.charAt(cpt) + "");
		}

		return new MotBinaire(tabMot);
	}


	public MotBinaire addition (MotBinaire a, MotBinaire b)
	{
		int[] tabMot;

		if ( a.nbBits() != b.nbBits() ) return null;

		tabMot = new int[a.nbBits()];

		for ( int cpt = 0; cpt < tabMot.length; cpt ++ )
			tabMot[cpt] = a.mot[cpt] + b.mot[cpt] == 2 ? 0 : a.mot[cpt] + b.mot[cpt];

		return new MotBinaire(tabMot);
	}


	public int poids ()
	{
		int poids = 0;

		for ( int i : this.mot )
			if ( i == 1 ) poids ++;

		return poids;
	}


	public int nbBits () { return mot.length; }


	public int distance ( MotBinaire other )
	{
		MotBinaire addition = this.addition(this, other);

		if ( addition == null ) return -1;

		return this.addition(this, other).poids();
	}


	public MotBinaire sousMot ( int deb, int fin )
	{
		int[] tabMot;

		tabMot = new int[fin - deb];

		for ( int cpt = deb; cpt <= fin; cpt ++ )
			tabMot[cpt] = this.mot[cpt];

		return MotBinaire.fabrique(tabMot);
	}


	public int  get(int i         ) { return this.mot[i];              }
	public void set(int i, int bit) { if (bit == 1) this.mot[i] = bit; }


	public String toString () { return Arrays.toString(this.mot); }

	// public boolean equals(Object other)
	// {
	// 	if ( other == null ) return false;

	// 	if ( other.nbBits() != this.nbBits() ) return false;

	// 	return true;
	// }

	public static MotBinaire[] genererMots(int n)
	{
		MotBinaire[] tab;
		int[][] tabListeMots;
		int bin = 0;
		int res = 0;

		tab          = new MotBinaire[(int) (Math.pow(n, n))];
		tabListeMots = new int       [(int) (Math.pow(n, n))][n];

		for ( int cpt1 = 0; cpt1 < tabListeMots.length; cpt1 ++ )
		{
			for ( int cpt2 = n-1; cpt2 <= 0; cpt2 ++ )
			{
				tabListeMots[cpt1][cpt2] = res%2;
				res = tabListeMots[cpt1][cpt2];
			}

			res = ++bin;
		}

		for ( int i = 0; i < tab.length; i ++ ) tab[i] = MotBinaire.fabrique(tabListeMots[i]);

		return tab;
	}

}
