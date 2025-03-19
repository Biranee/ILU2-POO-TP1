package histoire;

import villagegaulois.Etal;
import villagegaulois.Village;
import exeptions.VillageSansChefException;
import personnages.Gaulois;

public class ScenarioCasDegrade {
	
    public static void main(String[] args) {
    	Village villageSansChef = new Village("SansChef", 10, 6);
    	// Ne pas définir de chef ici !
    	try {
    	    System.out.println(villageSansChef.afficherVillageois());
    	} catch (VillageSansChefException e) {
    	    System.err.println("Erreur : " + e.getMessage());
    	}

        // Création d'un vendeur et d'un acheteur pour les tests
        Gaulois vendeur = new Gaulois("VendeurTest", 100);
        Gaulois acheteur = new Gaulois("AcheteurTest", 50);
        
        // ------------------------------------------
        // Test 1 : Acheteur null
        // ------------------------------------------
        Etal etal1 = new Etal();
        // Pour ce test, il n'est pas nécessaire d'occuper l'étal,
        // car la vérification de l'acheteur se fait en premier.
        System.out.println("Test 1 : Acheteur null");
        String resultat = etal1.acheterProduit(10, null);
        System.out.println("Résultat : " + resultat);
        System.out.println("------------------------------------------");
        
        // ------------------------------------------
        // Test 2 : Quantité négative ou nulle
        // ------------------------------------------
        // On occupe l'étal pour que le test porte sur la quantité
        Etal etal2 = new Etal();
        etal2.occuperEtal(vendeur, "pommes", 20);
        System.out.println("Test 2 : Quantité négative ou nulle");
        try {
            // Quantité nulle, devrait lever IllegalArgumentException
            etal2.acheterProduit(0, acheteur);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------");
        
        // ------------------------------------------
        // Test 3 : Etal non occupé
        // ------------------------------------------
        Etal etal3 = new Etal();  // Etal non occupé
        System.out.println("Test 3 : Etal non occupé");
        try {
            etal3.acheterProduit(5, acheteur);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------");
        
        // ------------------------------------------
        // Test 4 : Achat normal
        // ------------------------------------------
        Etal etal4 = new Etal();
        etal4.occuperEtal(vendeur, "bananes", 10);
        System.out.println("Test 4 : Achat normal");
        try {
            String res = etal4.acheterProduit(3, acheteur);
            System.out.println("Résultat : " + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------");
    }
}
