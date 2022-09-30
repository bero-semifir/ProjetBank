package banque;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector implements Closeable {

    public static Connection connexion;

    /**
     * Créer une connexion au serveur SQL
     * @return Connection au serveur
     */
    public static Connection seConnecter() throws SQLException {
        // on essaie de charger le Driver MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SQLConnector.connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/banque", "root", "root");
        return SQLConnector.connexion;
    }

    /**
     * Ferme la connexion à la base de données
     * @throws SQLException en cas d'erreur SQL lors de la fermeture
     */
    public static void seDeconnecter() throws SQLException {
        SQLConnector.connexion.close();
    }

    /**
     * Ferme automatiquement la connexion au serveur SQL
     */
    @Override
    public void close(){
        try {
            SQLConnector.seDeconnecter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
