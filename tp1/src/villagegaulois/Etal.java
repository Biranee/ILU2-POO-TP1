package villagegaulois;

import personnages.Gaulois;

public class Etal {
    private Gaulois vendeur;
    private String produit;
    private int quantiteDebutMarche;
    private int quantite;
    private boolean etalOccupe = false;  // false par défaut

    public boolean isEtalOccupe() {
        return etalOccupe;
    }
    
    public Gaulois getVendeur() {
        return vendeur;
    }
    
    public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
        this.vendeur = vendeur;
        this.produit = produit;
        this.quantite = quantite;
        this.quantiteDebutMarche = quantite;
        this.etalOccupe = true;
    }
    
    public String libererEtal() {
        if (!etalOccupe) {
            throw new IllegalStateException("L'étal n'est pas occupé");
        }
        etalOccupe = false;
        StringBuilder sb = new StringBuilder("Le vendeur " + vendeur.getNom() + " quitte son étal, ");
        int produitVendu = quantiteDebutMarche - quantite;
        if (produitVendu > 0) {
            sb.append("il a vendu " + produitVendu + " parmi " + produit + ".\n");
        } else {
            sb.append("il n'a malheureusement rien vendu.\n");
        }
        return sb.toString();
    }
    
    public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
        if (acheteur == null) {
            throw new IllegalArgumentException("L'acheteur ne doit pas être null");
            
        }
        if (quantiteAcheter < 1) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        if (!etalOccupe) {
            throw new IllegalStateException("L'étal n'est pas occupé");
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(acheteur.getNom() + " veut acheter " + quantiteAcheter + " " + produit + " à " + vendeur.getNom());
        
        if (quantite == 0) {
            sb.append(", malheureusement il n'y en a plus !");
            quantiteAcheter = 0;
        } else if (quantiteAcheter > quantite) {
            sb.append(", comme il n'y en a plus que " + quantite + ", " + acheteur.getNom() + " vide l'étal de " + vendeur.getNom() + ".\n");
            quantiteAcheter = quantite;
            quantite = 0;
        } else {
            quantite -= quantiteAcheter;
            sb.append(". " + acheteur.getNom() + ", est ravi de tout trouver sur l'étal de " + vendeur.getNom() + "\n");
        }
        return sb.toString();
    }
    
    public String afficherEtal() {
        if (etalOccupe) {
            return vendeur.getNom() + " vend " + quantite + " " + produit + "\n";
        }
        return "";
    }
    
    public boolean contientProduit(String produit) {
        return produit.equals(this.produit);
    }
}
