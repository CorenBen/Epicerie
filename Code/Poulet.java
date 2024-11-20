public class Poulet extends Article implements Aliment{
	int prix;
	String nom;
	float habitude;   /// Si superieur a une certaine valeur, le floatant habitude creera des clients habitues a l'article correspondant
	int categorie;    /// Indique la categorie correspondant aux niveaux de clients
	boolean vege;     /// Determine si l'article est disponible dans une epicerie vegetarienne ou non
	int fraicheur;    /// Si cette variable atteint 0, l'article est pourri
	int disponible;   /// Nombre d'article disponible par type
	int valeurMarchande;  /// Valeur a la vente	
	
//////////////////////////////////////////////////////////////////////////////////////////////////   CONSTRUCTEURS
		
	public Poulet(int prix, float habitude) {   /// Constructeur de test
		super(prix,"Poulet",habitude);
		vege = false;
		categorie = 1;
	}
		
	public Poulet(int prix) { /// Constructeur principal
		super(prix,"Poulet");
		vege = false;
		categorie = 1;
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
		fraicheur = 3;
	}
	
	public int getFraicheur() {
		return fraicheur;
	}
	
	public void setPrix() {
		prix = 14;
	}
	
	public int getPrix() {
		return prix;
	}
}

