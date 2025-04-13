package scenario;

import personnages.Gaulois;
import produit.Poisson;
import produit.Sanglier;
import vilagegaulois.Etal;
import vilagegaulois.IEtal;

public class ScenarioTest {

    public static void main(String[] args) {
        // Création de Gaulois
        Gaulois obelix = new Gaulois("Obélix", 15);

        // Création des produits
        Sanglier sanglier1 = new Sanglier(15.0, obelix);
        Sanglier sanglier2 = new Sanglier(12.5, obelix);
        Poisson poisson = new Poisson("mardi");

        // Création des étals
        IEtal etalSanglier1 = new Etal<>();
        IEtal etalSanglier2 = new Etal<>();
        IEtal etalPoisson = new Etal<>();
        IEtal etalVide = new Etal<>();

        // Installation des produits
        etalSanglier1.installerProduit(sanglier1, 3);
        etalSanglier2.installerProduit(sanglier2, 2);
        etalPoisson.installerProduit(poisson, 5);

        // Création du marché (tableau non générique)
        IEtal[] marche = new IEtal[4];
        marche[0] = etalSanglier1;
        marche[1] = etalSanglier2;
        marche[2] = etalPoisson;
        marche[3] = etalVide;

        // Affichage des étals
        for (IEtal etal : marche) {
            System.out.println(etal.afficherEtal());
        }
    }
}
