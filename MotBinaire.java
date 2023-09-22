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

		this.mot = new int[other.mot.length];

		for ( int i : other.mot ) this.mot[cpt++] = i;
	}


	public MotBinaire fabrique( int[] mot )
	{
		int[] tabMot;
		int   cpt = 0;

		tabMot = new int[mot.length];

		for ( int i : mot )
		{
			if ( i != 0 && i != 1 ) return null;

			tabMot[cpt++] = i;
		}

		new MotBinaire(tabMot);

		return this;
	}


	public MotBinaire fabrique ( String mot )
	{
		int[] tabMot;

		tabMot = new int[mot.length()];

		for ( int cpt = 0; cpt < mot.length(); cpt ++ )
		{
			if ( mot.charAt(cpt) != '0' && mot.charAt(cpt) != '1' ) return null;

			tabMot[cpt] = Integer.parseInt( mot.charAt(cpt) + "");
		}

		new MotBinaire(tabMot);

		return this;
	}

}
