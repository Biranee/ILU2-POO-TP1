package villagegaulois;

import java.util.ArrayList;
import java.util.List;

import personnages.Chef;
import villagegaulois.Etal;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	private Marche marche;
	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public class Marche{
		private Etal[] etals;

		public Marche(int nbEtals) {
			this.etals=new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i]= new Etal();
			}
		}
	
	
	public void utiliserEtals(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
		if (indiceEtal >= 0 && indiceEtal < etals.length) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		};
	}
	public int trouverEtalLibre(){
		for (int i =0; i < etals.length; i++) {
			if (etals[i].isEtalOccupe()) {
				return i;
			}
		}
		return -1;
		
	}
	
	public Etal[] trouverEtals(String produit) {
		List<Etal> listeEtals=new ArrayList<>();
		for (Etal etal : etals) {
			if (etal.isEtalOccupe() && etal.contientProduit(produit)) {
				listeEtals.add(etal);
			}
		}
		return listeEtals.toArray(new Etal[listeEtals.size()]);
	}
	
	public Etal trouverVendeur(Gaulois gaulois) {
		for (Etal etal : etals) {
			if (etal.isEtalOccupe() && etal.getVendeur().equals(gaulois)) {
				return etal;
			}
		}
		return null;
	}
	
	public String afficherMarche() {
        StringBuilder chaine = new StringBuilder();
        int nbEtalLibre = 0;
        for (Etal etal : etals) {
            if (etal.isEtalOccupe()) {
                chaine.append(etal.afficherEtal());
            } else {
                nbEtalLibre++;
            }
        }
        chaine.append("Il reste " + nbEtalLibre + " étals non utilisés dans le marché.\n");
        return chaine.toString();
    }
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
	    StringBuilder sb = new StringBuilder();
	    sb.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
	    int indice = marche.trouverEtalLibre();
	    if (indice != -1) {
	        marche.utiliserEtals(indice, vendeur, produit, nbProduit);
	        sb.append("Le vendeur " + vendeur.getNom() + " vend " + produit + " à l'étal n°" + (indice + 1) + ".\n");
	    } else {
	        sb.append("Aucun étal disponible pour " + vendeur.getNom() + ".\n");
	    }
	    return sb.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
	    StringBuilder sb = new StringBuilder();
	    Etal[] etals = marche.trouverEtals(produit);
	    if (etals.length == 0) {
	        sb.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
	    } else {
	        sb.append("Les vendeurs qui proposent des " + produit + " sont :\n");
	        for (Etal etal : etals) {
	            sb.append("- " + etal.getVendeur().getNom() + "\n");
	        }
	    }
	    return sb.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
	    return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
	    Etal etal = rechercherEtal(vendeur);
	    if (etal != null) {
	        return etal.libererEtal();
	    } else {
	        return vendeur.getNom() + " n'est pas installé sur un étal.\n";
	    }
	}
	
	public String afficherMarche() {
	    return marche.afficherMarche();
	}

	
	
	
	
	
	
}