public class Ananas extends Article implements Aliment{
	int prix;
	String nom;
	float habitude;   /// Si superieur a une certaine valeur, le floatant habitude creera des clients habitues a l'article correspondant
	int categorie;    /// Indique la categorie correspondant aux niveaux de clients
	boolean vege;     /// Determine si l'article est disponible dans une epicerie vegetarienne ou non
	int fraicheur;    /// Si cette variable atteint 0, l'article est pourri
	int disponible;   /// Nombre d'article disponible par type
	int valeurMarchande;  /// Valeur a la vente	
	
//////////////////////////////////////////////////////////////////////////////////////////////////   CONSTRUCTEURS
		
	public Ananas(int prix, float habitude) {   /// Constructeur de test
		super(prix,"Ananas",habitude);
		vege = true;
		categorie = 3;	
	}
		
	public Ananas(int prix) { /// Constructeur principal
		super(prix,"Ananas");
		vege = true;
		categorie = 3;
	}
	
	public void pourrir() {
		fraicheur-=1;
	}
	
	public void inflation(int p) {
		prix+=p;
	}
	
	public void deflation(int p) {
		prix-=p;
	}
	
	public int getValeur() {
		return valeurMarchande;
	}
	public void setValeur() {
		valeurMarchande = prix + categorie*4;		
	}
	
	public void setPourri() {
		fraicheur = 5;
	}
	
	public int getFraicheur() {
		return fraicheur;
	}
	
	public void setPrix() {
		prix = 50;
	}
	
	public int getPrix() {
		return prix;
	}
}

