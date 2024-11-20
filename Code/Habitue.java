public class Habitue extends Client{
	Article besoin;
	int mult;
	
	public Habitue(Article besoin) {
		super(besoin);
		mult = 2;
	}
	public String toString() {
		return super.toString()+"Client habitue \n\n";
	}
	
	public int getMult() {
		return mult;
	}
	
	public String blabla() {
		int rand = (int)(Math.random() * 3) + 1;
		if(rand==1) {
			return "'Salut c'est moi l'habitue, j'ai vu que vous vendiez beaucoup de "+getBesoin().getNom()+", est-ce qu'il vous en reste ?'\n";
		}
		if(rand==2) {
			return "'Bonjour, je suis le client habituel. Je viens pour un/une "+getBesoin().getNom()+", vous en avez ?'\n";
		}
		return "'Il vous reste un/une "+getBesoin().getNom()+" en stock ?'\n";
	}
	
	public String vendu() {
		int rand = (int)(Math.random() * 3) + 1;
		if(rand==1) {
			return "'Merci a vous !'\n";
		}
		if(rand==2) {
			return "'Merci, bonne journee !'\n";
		}
		return "'Merci, au revoir !'\n";
	}
}
