package vilagegaulois;

import personnages.Gaulois;
import produit.Produit;

public class Etal<P extends Produit> implements IEtal {

	private Gaulois vendeur;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;
	private P produit;

	@Override
	public void installerVendeur(Gaulois vendeur, Produit produit, int nbProduit) {
		this.vendeur = vendeur;
		this.produit = (P) produit;
		this.quantite = nbProduit;
		this.quantiteDebutMarche = nbProduit;
		this.etalOccupe = true;
	}

	@Override
	public void installerProduit(Produit produit, int quantite) {
		this.produit = (P) produit;
		this.quantite = quantite;
		this.quantiteDebutMarche = quantite;
		this.etalOccupe = true;
	}

	@Override
	public Produit getProduit() {
		return produit;
	}

	@Override
	public String afficherEtal() {
		if (produit == null || quantite <= 0) {
			return "Étal vide.\n";
		}
		return produit.decrireProduit() + " (x" + quantite + ")\n";
	}

	@Override
	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	@Override
	public Gaulois getVendeur() {
		return vendeur;
	}

	@Override
	public int getQuantite() {
		return quantite;
	}

	@Override
	public void occuperEtal(Gaulois vendeur, Produit produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = (P) produit;
		this.quantite = quantite;
		this.quantiteDebutMarche = quantite;
		this.etalOccupe = true;
	}

	@Override
	public boolean contientProduit(String nomProduit) {
		return produit != null && produit.getNom().equalsIgnoreCase(nomProduit);
	}

	@Override
	public int acheterProduit(int quantiteAcheter) {
		if (quantite == 0) {
			return 0;
		}
		int quantiteVendue = Math.min(quantiteAcheter, quantite);
		quantite -= quantiteVendue;
		return quantiteVendue;
	}

	@Override
	public void libererEtal() {
		this.etalOccupe = false;
	}

	@Override
	public String[] etatEtal() {
		String[] donneesVente = new String[5];
		donneesVente[0] = String.valueOf(etalOccupe);
		if (etalOccupe) {
			donneesVente[1] = vendeur.getNom();
			donneesVente[2] = produit.getNom();
			donneesVente[3] = String.valueOf(quantiteDebutMarche);
			donneesVente[4] = String.valueOf(quantiteDebutMarche - quantite);
		}
		return donneesVente;
	}
}
