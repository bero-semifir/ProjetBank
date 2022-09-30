import banque.*;

import java.util.*;

public class Main {
    public static void main(String[] args){

        Compte courrant = new Compte(1);
        Compte joint = new Compte(2, 500);
        CompteRemunere livretA = new CompteRemunere(3, 122, 0.075);
        CompteASeuil compteJeune = new CompteASeuil(4, 500, 200);

        // Polymorphisme
        Compte[] comptes = {courrant, joint, livretA, compteJeune};

        Client michel = new Client(1, "Trabendo", "Michel", 59);

        CompeASeuilRemunere casr = new CompeASeuilRemunere(5, 7000,0.1,500);
        try {
            michel.ajouterCompte(casr);
            for (Compte compte : comptes) {
                michel.ajouterCompte(compte);
            }
        } catch (BanqueException erreur) {
            System.err.println(erreur.getMessage());
            erreur.printStackTrace();
        }

        // récup des comptes de Michel
        Map<Integer, Compte> comptesMichel = michel.getTabCompte();
        System.out.println(comptesMichel.toString());
        // itération sur tous les comptes de Michel
        /*
        for(Compte compte: comptesMichel){
            // si le compte est un compte rémunéré
            if(compte instanceof CompteRemunere){
                // downcast du compte en CompteRemunere (Polymorphisme)
                CompteRemunere cr = (CompteRemunere) compte;
                // versement des interets
                cr.verserInterets();
            }
        }
         */

        comptesMichel.forEach((numero, compte) -> {
            if(compte instanceof CompteRemunere){
                // downcast du compte en CompteRemunere (Polymorphisme)
                CompteRemunere cr = (CompteRemunere) compte;
                // versement des interets
                cr.verserInterets();
            }
        });



        System.out.println(comptesMichel);
        try{
            michel.getCompte(5).retirer(7700);
        } catch (BanqueException erreur){
            erreur.printStackTrace();
        }

    }
}
