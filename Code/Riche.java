public class Riche extends Client{
	Article besoin;
	int mult;
	
	public Riche(Article besoin) {
		super(besoin);
		mult = 3;
	}
	public String toString() {
		return super.toString()+"Client riche et peu sympathique \n\n";
	}
	
	public int getMult() {
		return mult;
	}
	
	
	public String blabla() {
		int rand = (int)(Math.random() * 3) + 1;
		if(rand==1) {
			return "'Un/une "+getBesoin().getNom()+".'\n";
		}
		if(rand==2) {
			return "'Un/une "+getBesoin().getNom()+" pour moi.'\n";
		}
		return "'Je veux un/une "+getBesoin().getNom()+", vite !'\n";
	}
	
	public String vendu() {
		int rand = (int)(Math.random() * 3) + 1;
		if(rand==1) {
			return "'Merci.'\n";
		}
		if(rand==2) {
			return "*Part sans vous remercier*\n";
		}
		return "'Au revoir.'\n";
	}
}
