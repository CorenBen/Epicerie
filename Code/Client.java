public abstract class Client {
	Article besoin;
	
	public Client(Article besoin) {
		this.besoin = besoin;
	}
	
	public Article getBesoin() {
		return besoin;
	}
	
	public String toString() {
		return "Nouveau Client : ";
	}
	
	public abstract int getMult();	
	public abstract String blabla();
	public abstract String vendu();
}
