package vilagegaulois;

import personnages.Gaulois;
import produit.Produit;

public interface IEtal {
	void installerVendeur(Gaulois vendeur, Produit produit, int nbProduit);
	void installerProduit(Produit produit, int quantite);
	void occuperEtal(Gaulois vendeur, Produit produit, int quantite);
	boolean isEtalOccupe();
	Gaulois getVendeur();
	int getQuantite();
	Produit getProduit();
	boolean contientProduit(String produit);
	int acheterProduit(int quantiteAcheter);
	void libererEtal();
	String[] etatEtal();
	String afficherEtal();
}
