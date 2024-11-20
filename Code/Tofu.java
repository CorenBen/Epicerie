public class Tofu extends Article implements Aliment{
	int prix;
	String nom;
	float habitude;   /// Si superieur a une certaine valeur, le floatant habitude creera des clients habitues a l'article correspondant
	int categorie;    /// Indique la categorie correspondant aux niveaux de clients
	boolean vege;     /// Determine si l'article est disponible dans une epicerie vegetarienne ou non
	int fraicheur;    /// Si cette variable atteint 0, l'article est pourri
	int disponible;   /// Nombre d'article disponible par type	
	int valeurMarchande;  /// Valeur a la vente	

	
//////////////////////////////////////////////////////////////////////////////////////////////////   CONSTRUCTEURS
		
	public Tofu(int prix, float habitude) {   /// Constructeur de test
		super(prix,"Tofu",habitude);
		vege = false;
		categorie = 4;
	}
		
	public Tofu(int prix) { /// Constructeur principal
		super(prix,"Tofu");
		fraicheur = 2;
		vege = false;
		categorie = 4;
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
		fraicheur = 1;
	}
	
	public int getFraicheur() {
		return fraicheur;
	}
	
	public void setPrix() {
		prix = 120;
	}
	
	public int getPrix() {
		return prix;
	}
}

