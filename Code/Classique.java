public class Classique extends Client{
	Article besoin;
	int mult;
	
	public Classique(Article besoin) {
		super(besoin);
		mult = 1;
	}
	public String toString() {
		return super.toString()+"Client classique \n\n";
	}
	
	public int getMult() {
		return mult;
	}
	
	public String blabla() {
		int rand = (int)(Math.random() * 3) + 1;
		if(rand==1) {
			return "'Bonjour, vous avez un/une "+getBesoin().getNom()+" ?'\n";
		}
		if(rand==2) {
			return "'Je viens pour un/une "+getBesoin().getNom()+", vous en avez ?'\n";
		}
		return "'Je cherche un/une "+getBesoin().getNom()+".'\n";
	}
	
	public String vendu() {
		int rand = (int)(Math.random() * 3) + 1;
		if(rand==1) {
			return "'Merci !'\n";
		}
		if(rand==2) {
			return "'Bonne journee !'\n";
		}
		return "'Au revoir !'\n";
	}
}
