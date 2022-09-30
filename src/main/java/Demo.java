import banque.Client;
import java.sql.*;

public class Demo {
    public static void main(String[] args){
        // Nom du driver pour acceder a la base de donnees.
        // Lire la documentation associee a sa base de donnees pour le connaitre
        final String dbDriver = "com.mysql.cj.jdbc.Driver"; // Nom long d'une classe
        // URL d'access a la base de donnees.
        final String dbUrl = "jdbc:mysql://localhost:3306/banque"; // banque est le nom de la base
        // Login d'access a la base de donnees.
        final String dbLogin = "root"; // Ou "SA" en HSQL
        // Mot de passe d'access a la base de donnees.
        final String dbPwd = "root"; // Ou "" en HSQL

        // on essaie de charger le Driver MySQL
        try {
            Class.forName(dbDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Declarartion hors du try
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbLogin, dbPwd);
                Statement statement = connection.createStatement();
                ResultSet resultat = statement.executeQuery("select * from utilisateur")
        ) {
            while (resultat.next()) {
                int numero = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                Client client = new Client(numero, nom, prenom, 18);
                System.out.println(client);
            }
            // Traitement
        } catch (SQLException sql) {
            // Traitement du probleme
            sql.printStackTrace();
        }
    }
}
