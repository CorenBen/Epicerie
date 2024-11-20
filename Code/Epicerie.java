import java.util.ArrayList;
import java.util.Scanner;

public class Epicerie {
	
	String nom;      /// Nom de l'epicerie
	int budget;      /// Budget de l'epicerie
	boolean vege;    /// Determine le type d'epicerie (alimentaire ou vegetarienne)
	int budgetMatin;   /// Budget du matin
	boolean ouverture = false; /// Determine si la boutique a deja ete ouverte
	
	ArrayList<Article> magasin = new ArrayList<Article>();              /// Liste d'article du magasin
	ArrayList<Client> clientele = new ArrayList<Client>();              /// Liste de clients du magasin
	
	private static int vente = 0;                 /// Nombre de ventes
	private static int jours = 1;                 /// Nombre de jours ecoules
	
	private final int fin = 1000;                 /// Objectif du jeu
	
	private boolean abandon = false;              /// Option Quitter
	
	private static int nb_clients = 1;            /// Taille max de l'ArrayList clientele
	private static int niveau = 0;                /// Niveau du magasin
	private static int mult = 1;                  /// Multiplicateur de benefice
	private boolean multbool = false;             /// Indicateur d'achat de l'amelioration mult
	
	private int prixMag = 5;                  /// Prix amelioration magasin 1 -> 2

//////////////////////////////////////////////////////////////////////////////////////////////////    CONSTRUCTEURS
	public Epicerie(String nom, boolean vege, int budget) { ///Epicerie avance
		this.nom = nom;
		this.vege = vege;
		this.budget = budget;
	}
	public Epicerie(String nom, String type) { ///Epicerie de Depart
		this.nom = nom;
		budget = 10;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////     ACCESSEURS
	
	public int getBudget() {
		return budget;
	}
	public String getNom() {
		return nom;
	}
	public boolean getType() {
		return vege;
	}
	
	public Article getHabMoins() {              /// Retourne le produit du magasin le moins vendu de la semaine
		float hab = 0;
		int j = 0;
		for(int i=0;i<nb_clients;i++) {
			if((magasin.get(i)).getHabitude()>hab) {
				hab = (magasin.get(i)).getHabitude();
				j = i;
			}
		}
		return magasin.get(j);
	}
	
	public Article getHabPlus() {              /// Retourne le produit du magasin le plus vendu de la semaine
		float hab = 100;
		int j = 0;
		for(int i=0;i<nb_clients;i++) {
			if((magasin.get(i)).getHabitude()<hab) {
				hab = (magasin.get(i)).getHabitude();
				j = i;
			}
		}
		return magasin.get(j);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////    FONCTIONS
	
	public void changeBudget(int b) {    /// Ajoute ou retire de l'argent
		budget+=b;
		if(budget<0) { /// Ajuste le budget a zero s'il est negatif 
			budget=0;
		}
	}
	
	public void resetHab() {                           //// Remet les habitudes des clients a zero
		for(int i=0;i<nb_clients;i++) {
			(magasin.get(i)).changeHab(0);
		}
	}
	
	public void vendre(Article a, Client c) {                        /// Vend un article si possible
		if(magasin.isEmpty()) {
			System.out.println("Veuillez remplir votre magasin pour pouvoir vendre.\n");
		}
		else {
			if(magasin.contains(a)) {
				if(a.disponible==1) {
					a.disponible=0;
				}
				else {
					a.disponible-=1;
				}
				c.vendu();
				budget+=mult*a.getValeur()*c.getMult();
			}
			else {
				System.out.println("Veuillez d'abord acheter du "+a.nom+".\n");
			}
		}
	}
	
	public void stocke(Article a) {                   ////////// Stocke un article dans l'epicerie, s'il existe deja augmente le nombre correspondant a l'article
		a.stocker();
	}
	
	public void setMag() {                            ////////// Remplit le magasin de produits disponibles a l'achat et la vente
		if(vege) {
			if(niveau==0) {
				magasin.add(new Miel(2));
			}
			if(niveau==1) {
				magasin.add(new Carotte(12));
				magasin.add(new Oeuf(10));
				magasin.add(new Pomme(8));
			}
			if(niveau==2) {
				magasin.add(new Chou(22));
				magasin.add(new Fromage(20));
				magasin.add(new Poire(24));
			}
			if(niveau==3) {
				magasin.add(new Ananas(50));
				magasin.add(new Artichaut(58));
			}
			if(niveau==4) {
				magasin.add(new Tofu(100));
			}
		}
		else {
			if(niveau==0) {
				magasin.add(new Miel(2));
			}
			if(niveau==1) {
				magasin.add(new Carotte(12));
				magasin.add(new Oeuf(10));
				magasin.add(new Pomme(8));
				magasin.add(new Poulet(14));
			}
			if(niveau==2) {
				magasin.add(new Chou(22));
				magasin.add(new Fromage(20));
				magasin.add(new Poire(24));
				magasin.add(new Boeuf(25));
			}
			if(niveau==3) {
				magasin.add(new Ananas(50));
				magasin.add(new Artichaut(58));
				magasin.add(new Porc(56));
			}
			if(niveau==4) {
				magasin.add(new Poisson(100));
			}
		}
	}
	
	public void ouvrir() {                           /////////// Ouvre la boutique et lance la vague de clients
		if(ouverture) {
			System.out.println("Vous ne pouvez ouvrir votre boutique qu'une seule fois par jour.\n");
			afficheMenu();
		}
		else {
			int clavier;
			System.out.println("/////////////////////////////////////////////////////////////\n");
			System.out.println("Ouverture du magasin :\n\n");
			for(int i=0;i<nb_clients;i++) {
				double rand = (Math.random() * 4) + 1;   /// Variable aleatoire pour decider du type de client
				if(rand<1.5) {
					clientele.add(new Riche(getHabMoins()));     /// On ajoute un client avec pour besoin le produit le moins achete
				}
				else {
					if(rand<2.25) {
						clientele.add(new Habitue(getHabPlus()));     /// On ajoute un client avec pour besoin le produit le plus achete
					}
					else {
						int rand2 = (int)(Math.random() * magasin.size());   /// Variable aleatoire pour decider du besoin du client
						clientele.add(new Classique(magasin.get(rand2)));
					}
				}
			}
			for(int i=0;i<nb_clients;i++) {                                /// Boucle dialogue clients
				System.out.println(clientele.get(i).toString());
				System.out.println(clientele.get(i).blabla());
				if(clientele.get(i).getBesoin().getDispo()==0) {
					System.out.println("Vous ne pouvez repondre aux besoins du client. Le client quitte le magasin.\n");
				}
				else {
					System.out.println(clientele.get(i).getBesoin().getNom()+" en stock : "+clientele.get(i).getBesoin().getDispo()+"\n");
					System.out.println("Vendre  :            Oui (1)                 Non(2)");
					Scanner sc = new Scanner(System.in);
					//On teste la réponse clavier
					do{
			            clavier = sc.nextInt();
			            if((clavier != 1) && (clavier != 2)){
			            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
			            }
			        }while((clavier != 1) && (clavier != 2));
					if(clavier==1) {
						vendre(clientele.get(i).getBesoin(),clientele.get(i));
						System.out.println(clientele.get(i).vendu());
					}
					else {
						System.out.println("Vous refusez de vendre votre produit, le client quitte le magasin furieux.");
					}
				}
			}
			System.out.println("/////////////////////////////////////////////////////////////\n");
			
			ouverture = true;
			afficheMenu();
		}
	}
	
	public void maxMag() {                 //// Max le niveau du magasin
		niveau = 0;
		setMag();
		niveau = 1;
		setMag();
		niveau = 2;
		setMag();
		niveau = 3;
		setMag();
		niveau = 4;
		setMag();
	}
	
	public void setValPrix() {                 //// Mets a jour les valeurs et prix des produits
		for(int i=0;i<magasin.size();i++) {
			(magasin.get(i)).setPrix();
			(magasin.get(i)).setValeur();
		}
	}
	
	public void acheter() {                          /////////// Permet d'augmenter ses stocks de nourriture
		int clavier,clavier2;
		System.out.println("/////////////////////////////////////////////////////////////\n");
		System.out.println("Budget : "+budget+"\n");
		System.out.println("Produits Disponibles a l'achat :\n\n");
		for(int i=0;i<magasin.size();i++) {
			System.out.println((magasin.get(i)).getNom()+"("+(magasin.get(i)).getDispo()+" en stock)  -  Prix : "+(magasin.get(i)).getPrix()+"  -  Valeur a la revente : "+(magasin.get(i)).getValeur()+"  -  Appuyer sur ("+(i+1)+")\n");
		}
		System.out.println("Quitter : Appuyer sur (0)\n");
		System.out.println("/////////////////////////////////////////////////////////////\n");
		Scanner sc = new Scanner(System.in);
		//On teste la réponse clavier
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4) && (clavier != 5) && (clavier != 6) && (clavier != 7) && (clavier != 8) && (clavier != 9) && (clavier != 10) && (clavier != 11) && (clavier != 12) && (clavier != 13) && (clavier != 14) && (clavier != 0) || clavier>magasin.size()){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4) && (clavier != 5) && (clavier != 6) && (clavier != 7) && (clavier != 8) && (clavier != 9) && (clavier != 10) && (clavier != 11) && (clavier != 12) && (clavier != 13) && (clavier != 14) && (clavier != 0) || clavier>magasin.size());
	    if(clavier==0) {afficheMenu();}
	    else {
	    	if(clavier>magasin.size()) {
	    		System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
	    	}
	    	else {
	    		System.out.println("Combien souhaitez-vous en acheter ?\n");
	    		sc = new Scanner(System.in);
	    		do{
	                clavier2 = sc.nextInt();
	                if(clavier2*magasin.get(clavier-1).getPrix() > budget){
	                	System.out.println("Vous ne possedez pas assez d'argent. Tapez '0' pour annuler.\n");
	                }
	            }while(clavier2*magasin.get(clavier-1).getPrix() > budget);
	    		if(clavier2==0) {
	    			acheter();
	    		}
	    		else {
	    			for(int i=0;i<clavier2;i++) {
	    				stocke(magasin.get(clavier-1));
	    				magasin.get(clavier-1).setPourri();
	    			}
	    			budget-=clavier2*magasin.get(clavier-1).getPrix();
		    		System.out.println("Achat effectue\n");
		    		acheter();
	    		}
	    	}
	    }
	}
	
	public void ameliorer() {                        /////////// Permet d'acheter une amelioration pour la boutique
		int clavier;
		System.out.println("/////////////////////////////////////////////////////////////\n");
		System.out.println("Client Supplementaire = 50 pieces (1)                 Extension Marchandise = "+prixMag+" pieces (2)                    Multiplicateur de Benefices = 300 pieces (3)                  Annuler (0)\n");
		Scanner sc = new Scanner(System.in);
		//On teste la réponse clavier
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 0));
		
		if(clavier != 0) {ameliorer2(clavier);}
		else {
			if(clavier == 0) {afficheMenu();}
		}
	}
	
	public void ameliorer2(int c) {     ////////// Deuxieme Menu des ameliorations
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Description (1)                 Acheter (2)                    Annuler (0)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 0));

		if(clavier == 0) {ameliorer();}
		// Descriptions des ameliorations
		if(clavier == 1) {
			if(c==1) {System.out.println("Augmente votre nombre de clients par jour (max = 5).\n");}
			if(c==2) {System.out.println("Ameliore votre magasin et vous permet d'acheter de meilleurs produits (max = 5).\n");}
			if(c==3) {System.out.println("Double vos benefices journaliers.\n");}
			ameliorer2(c);
		}
		
		// Achat des ameliorations
		if(clavier == 2) {
			if(c==1) {
				if(nb_clients<5) {
					if(budget >= 50) {
						System.out.println("Nombre de clients augmentes ("+nb_clients+1+" actuellement).\n");
						budget -= 50;
						nb_clients+=1;
						afficheMenu();
					}
					else {
						System.out.println("Vous ne possedez pas assez d'argent.\n");
						ameliorer();
					}
				}
				else {
					System.out.println("Amelioration deja au max.\n");
					afficheMenu();
				}	
			}
			
			if(c==2) {
				if(niveau<5) {
					if(budget >= prixMag) {
						System.out.println("Amelioration du magasin au niveau "+(niveau+1)+".\n");
						niveau += 1;
						budget -= prixMag;
						if(niveau==1) {
							prixMag = 25;
						}
						if(niveau==2) {
							prixMag = 50;
						}
						if(niveau==3) {
							prixMag = 100;
						}
						if(niveau==4) {
							prixMag = 200;
						}
						setMag();
						setValPrix();
						afficheMenu();
					}
					else {
						System.out.println("Vous ne possedez pas assez d'argent.\n");
						ameliorer();
					}
				}
				else {
					System.out.println("Amelioration deja au max.\n");
					afficheMenu();
				}
			}
			
			if(c==3) {
				if(multbool == false) {
					if(budget >= 100) {
						System.out.println("Augmentation de vos benefices journaliers par deux.\n");
						multbool = true;
						budget -= 100;
						afficheMenu();
					}
					else {
						System.out.println("Vous ne possedez pas assez d'argent.\n");
						ameliorer();
					}
				}
				else {
					System.out.println("Amelioration deja achetee.\n");
					afficheMenu();
				}				
			}
			
		}
	}
	
	public void pourrir() {
		for(int i=0;i<magasin.size();i++) {
			magasin.get(i).pourrir();
			if(magasin.get(i).getFraicheur()<=0) {
				magasin.get(i).vider();
				magasin.get(i).setPourri();
			}
		}
	}
	
////////////////////////////////////////////////////////         CONSOLE  
	
	public static void afficheJour(int n) {                    /////// Affiche le numero du jour courant
		System.out.println("------------    Jour "+n+"    -------------\n");
	}
	
	public void afficheStock() {                        /////// Affiche le stockage du magasin
		System.out.println("/////////////////////////////////////////////////////////////\n");
		System.out.println("Stock :\n\n");
		for(int i=0;i<magasin.size();i++) {
			if((magasin.get(i)).getDispo()!=0) {
				System.out.println((magasin.get(i)).getNom()+" : "+(magasin.get(i)).getDispo()+"\n");
			}
		}
		System.out.println("/////////////////////////////////////////////////////////////\n");
		afficheMenu();
	}
	
	public void afficheMenu() {                         /////// Affiche le menu de navigation principal
		Scanner sc = new Scanner(System.in);
		int clavier;
		System.out.println("Budget = "+budget+"\n");
		System.out.println("Stock (1)                 Acheter (2)                   Ouvrir la boutique (3)                   Ameliorer (4)                     Dormir (5)                   Quitter le jeu (6)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4) && (clavier != 5) && (clavier != 6)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4) && (clavier != 5) && (clavier != 6));
		
		if(clavier==1) {afficheStock();}
		
		if(clavier==2) {acheter();}
		
		if(clavier==3) {ouvrir();}
		
		if(clavier==4) {ameliorer();}
		
		if(clavier==5) {jourSuivant();}
		
		if(clavier==6) {
			System.out.println("Etes vous sur de vouloir quitter le jeu : Oui (1)        Non (2)\n");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 1) && (clavier != 2)){
	            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
	            }
	        }while((clavier != 1) && (clavier != 2));
			if(clavier == 1) {
				budget = fin;
				abandon = true;
			}
			if(clavier == 2) {
				afficheMenu();
			}
		}

	}
	
	public void afficheRecap() {                        /////// Affiche le recapitulatif du jour precedent
		System.out.println("/////////////////////////////////////////////////////////////\n");
		System.out.println("Benefices du jour : "+(budget-budgetMatin)+" pieces\n");
		System.out.println("/////////////////////////////////////////////////////////////\n");
	}
	
	public static void clearScreen() {                  /////// Remplis l'ecran de blanc pour liberer de l'espace
		for(int i = 0; i < 25; i++) 
		    System.out.print("\n");
	}  
	
	public void resume() {                             //////// Resume la partie
		System.out.println("Vous avez atteint la somme requise en "+(jours-1)+" jours.");
	}
	
//////////////////////////////////////////////////////////////    JEU	
	
	public boolean findePartie() {         ///// Declenchement de la fin de la partie
		return budget < fin;
	}	

	
	public void jourSuivant() {            ///// Changements non-visuels avant de passer au jour suivant
		jours+=1;
		ouverture = false;
		clearScreen();
		afficheRecap();
		budgetMatin=budget;
		clientele.clear();
		pourrir();
		Scanner sc = new Scanner(System.in);
	}

	
	public void partie() {                 ////// Fonction principale permettant le jeu
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Vous commencez une nouvelle partie. Votre but est d'atteindre 1000 pièces en tenant votre epicerie.\n");
		System.out.println("Tout d'abord, veuillez choisir votre type d'epicerie :     (1) Alimentaire             (2) Vegetarienne\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2));
		
		if(clavier==1) {
			vege = false;
		}
		else {vege = true;}
		
		setMag();
		setValPrix();

		
		while (findePartie()) {
			afficheJour(jours);
			afficheMenu();
			if(!abandon && budget > fin) {
				System.out.println("Felicitations vous avez gagne ! Le jeu est donc termine.\n");
				resume();
			}
		}
	}
	
}
