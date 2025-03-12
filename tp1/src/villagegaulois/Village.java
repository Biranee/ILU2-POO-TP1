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

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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
                // On suppose que la méthode acherEtal() existe dans Etal et retourne
                // une chaîne du format : "Assurancetourix vend 5 lyres\n"
                chaine.append(etal.afficherEtal());
            } else {
                nbEtalLibre++;
            }
        }
        chaine.append("Il reste " + nbEtalLibre + " étals non utilisés dans le marché.\n");
        return chaine.toString();
    }

	
	}
	
	
}