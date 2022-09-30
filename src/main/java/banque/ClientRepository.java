package banque;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Classe qui s'occupe de la persistance de l'entité Client
 * @see banque.Client
 */
public class ClientRepository {

    /**
     * Lance la connexion à la base de données
     */
    public void seConnecter(){

    }

    /**
     * Récupère la liste des clients en base de données
     * @return la liste des clients
     */
    public static List<Client> recupererClients(){

        // liste de clients (pour garder les résultats)
        List<Client> clients = new ArrayList<>();
        try (
                Connection connection = SQLConnector.seConnecter();
                Statement statement = connection.createStatement();
                ResultSet resultat = statement.executeQuery("select id, nom, prenom, dateDeNaissance from utilisateur");
        ) {
            while (resultat.next()) {
                // ** mapping ORM **
                int numero = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                // calculer l'age
                int ageClient = getAge(resultat.getDate("dateDeNaissance"));
                Client client = new Client(numero, nom, prenom, ageClient );
                // ** fin mapping client **

                List<Compte> comptes = recupererComptesClient(client);
                Map<Integer, Compte> comptesClient = compteListToMap(comptes);
                client.setTabCompte(comptesClient);
                clients.add(client);
            }
            // Traitement
        } catch (SQLException sql) {
            // Traitement du probleme
            sql.printStackTrace();
        }
        return clients;
    }

    /**
     * Convertit une liste de compte en map
     * @param comptes Liste de comptes
     * @return Map<Integer, Compte> de comptes
     */
    private static Map<Integer, Compte> compteListToMap(List<Compte> comptes) {
        Map<Integer, Compte> comptesClient = new Hashtable<>();
        for (Compte compte: comptes){
            comptesClient.put(compte.getNumero(), compte);
        }
        return comptesClient;
    }

    /**
     * Trouve l'age à partir d'une date de naissance
     * @param dateDeNaissance date de naissance de l'utilisateur
     * @return age de l'utilisateur
     */
    private static int getAge(java.sql.Date dateDeNaissance){
        int age = 18;
        if(dateDeNaissance != null){
            LocalDate naissanceUtilisateur = dateDeNaissance.toLocalDate();
            LocalDate now = LocalDate.now();
            age = Period.between(naissanceUtilisateur, now).getYears();
        }
        return age;
    }

    /**
     * Récupère les comptes du client.
     * @param client dont ont doit récupérer le compte
     * @return liste des comptes du client
     */
    public static List<Compte> recupererComptesClient(Client client){

        List<Compte> comptes = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;
        try {
                connection = SQLConnector.seConnecter();
                statement = connection.prepareStatement("SELECT id, solde FROM compte WHERE utilisateurId = ?");
                statement.setInt(1, client.getNumero());
                resultat = statement.executeQuery();
                while (resultat.next()){
                    int numero = resultat.getInt("id");
                    double solde = resultat.getDouble("solde");
                    // double taux = resultat.getDouble("taux");
                    // double seuil = resultat.getDouble("seuil");

                    Compte compte = new Compte(numero, solde);
                    comptes.add(compte);
                }
                resultat.close();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultat != null){
                try {
                    resultat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try{
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return comptes;
    }

    public static void main(String[] args){
        List<Client> clients = recupererClients();
        System.out.println(clients);
    }

}
