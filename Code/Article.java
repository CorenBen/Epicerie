public abstract class Article {
	int prix;
	String nom;
	float habitude;   /// Si superieur a une certaine valeur, le floatant habitude creera des clients habitues a l'article correspondant
	int categorie;    /// Indique la categorie correspondant aux niveaux de clients
	boolean vege;     /// Determine si l'article est disponible dans une epicerie vegetarienne ou non
	int disponible;   /// Nombre d'article disponible par type
	
//////////////////////////////////////////////////////////////////////////////////////////////////   CONSTRUCTEURS
	
	public Article(int prix, String nom, float habitude) {   /// Constructeur de test
		this.prix = prix;
		this.nom = nom;
		this.habitude = habitude;
		disponible = 0;
	}
	
	public Article(int prix, String nom) { /// Constructeur principal
		this.prix = prix;
		this.nom = nom;
		habitude = 0;
		disponible = 0;
	}
	
	Article(Article a){
		nom = a.nom;
		habitude = a.habitude;
		prix = a.prix;
		disponible = a.disponible;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////     ACCESSEURS
	
	public String getNom() {
		return nom;
	}
	
	public float getHabitude() {
		return habitude;
	}
	
	public int getCategorie() {
		return categorie;
	}
	
	public boolean getVege() {
		return vege;
	}
	
	
	public int getDispo() {
		return disponible;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////	   FONCTIONS
	
	public void stocker() {
		disponible+=1;
	}
	
	public void vider() {
		disponible-=1;
	}
	
	public void changeHab(int c) {
		if(c==0) {
			habitude = 0;
		}
		else{
			habitude+=c;
		}
	}
	
	abstract void pourrir();
	abstract void setPrix();
	abstract void setValeur();
	abstract void setPourri();
	abstract int getPrix();
	abstract int getValeur();
	abstract int getFraicheur();
	
}
