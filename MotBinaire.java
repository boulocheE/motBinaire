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

		if ( a == null  || b == null  ) return null;
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

		if ( deb < 0 || deb >= this.nbBits() || fin < 0 || fin >= this.nbBits() ) return -1;

		tabMot = new int[fin - deb];

		for ( int cpt = deb; cpt <= fin; cpt ++ )
			tabMot[cpt] = this.mot[cpt];

		return MotBinaire.fabrique(tabMot);
	}


	public int  get(int i         )
	{
		if ( i < 0 || i >= this.nbBits() ) return -1;

		return this.mot[i];
	}

	public void set(int i, int bit)
	{
		if (bit == 1 && i >= 0 && i < this.nbBits()) 
			this.mot[i] = bit;
	}


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


	public static void main(String[] a)
	{
		MotBinaire test1 = fabrique("001");
		MotBinaire test2 = fabrique(new int[] {1,0,0, 1});
		MotBinaire test3 = new MotBinaire(test2);
		MotBinaire test4 = fabrique("dfgh");

		System.out.println( "|-------------------------------|" );
		System.out.println( "|    Test création des MOTS     |" );
		System.out.println( "|-------------------------------|" );

		System.out.println( "TEST 1 : " + test1 );
		System.out.println( "TEST 2 : " + test2 );
		System.out.println( "TEST 3 : " + test3 );
		System.out.println( "TEST 4 : " + test4 );


		System.out.println();


		System.out.println( "|-------------------------------|" );
		System.out.println( "|         Test des POIDS        |" );
		System.out.println( "|-------------------------------|" );

		System.out.println( "TEST 1 : " + test1.poids() );
		System.out.println( "TEST 2 : " + test2.poids() );
		System.out.println( "TEST 3 : " + test3.poids() );


		System.out.println();


		System.out.println( "|-------------------------------|" );
		System.out.println( "|        Test des DISTANCE      |" );
		System.out.println( "|-------------------------------|" );

		System.out.println( "TEST 1 → 2 : " + test1.distance(test1) );
		System.out.println( "TEST 2 → 3 : " + test2.distance(test2) );
		System.out.println( "TEST 3 → 1 : " + test3.distance(test3) );
		System.out.println( "TEST 2 → 1 : " + test1.distance(test2) );
		System.out.println( "TEST 3 → 2 : " + test2.distance(test3) );
		System.out.println( "TEST 1 → 3 : " + test3.distance(test1) );


		System.out.println();


		System.out.println( "|-------------------------------|" );
		System.out.println( "|        Test de la TAILLE      |" );
		System.out.println( "|-------------------------------|" );

		System.out.println( "TEST 1 : " + test1.nbBits() );
		System.out.println( "TEST 2 : " + test2.nbBits() );
		System.out.println( "TEST 3 : " + test3.nbBits() );


		System.out.println( test1.nbBits() );


		System.out.println( "|-------------------------------|" );
		System.out.println( "|       Test des SOUS-MOTS      |" );
		System.out.println( "|-------------------------------|" );

		System.out.println( "TEST 0 à 0 : " + test2.sousMot(0, 0) );
		System.out.println( "TEST 0 à 2 : " + test1.sousMot(0, 2) );
		System.out.println( "TEST 0 à 3 : " + test3.sousMot(0, 3) );
		System.out.println( "TEST 0 à 4 : " + test3.sousMot(0, 4) );
		System.out.println( "TEST 1 à 0 : " + test2.sousMot(1, 0) );
		System.out.println( "TEST 1 à 2 : " + test2.sousMot(1, 2) );
		System.out.println( "TEST 1 à 3 : " + test3.sousMot(1, 3) );
		System.out.println( "TEST 1 à 4 : " + test3.sousMot(1, 4) );


		System.out.println( "|-------------------------------|" );
		System.out.println( "|           Test de GET         |" );
		System.out.println( "|-------------------------------|" );

		System.out.print( "TEST 1\t :" );
		for ( int i = 0; i < test1.nbBits(); i ++ )
			System.out.print( i + " : " + test1.get(i) );

		System.out.print( "TEST 2\t :" );
		for ( int i = 0; i < test2.nbBits(); i ++ )
			System.out.print( i + " : " + test1.get(i) );

		System.out.print( "TEST 3\t :" );
		for ( int i = 0; i < test3.nbBits(); i ++ )
			System.out.print( i + " : " + test1.get(i) );


		System.out.println();


		System.out.println( "|-------------------------------|" );
		System.out.println( "|           Test de SET         |" );
		System.out.println( "|-------------------------------|" );

		System.out.print( "TEST 1 : \t avant - " + test1 + "\taprès" );
		test1.set(0, 1);
		System.out.print( test1 + "\n" );

		System.out.print( "TEST 2 : \t avant - " + test1 + "\taprès" );
		test1.set(0, 6);
		System.out.print( test1 + "\n" );

		System.out.print( "TEST 3 : \t avant - " + test1 + "\taprès" );
		test1.set(5, 6);
		System.out.print( test1 + "\n" );


		System.out.println();


		System.out.println( "|-------------------------------|" );
		System.out.println( "|          Test TOSTRING        |" );
		System.out.println( "|-------------------------------|" );

		System.out.println( "TEST 1 : " + test1 );
		System.out.println( "TEST 2 : " + test2 );
		System.out.println( "TEST 3 : " + test3 );
		System.out.println( "TEST 4 : " + test4 );

	}

}
