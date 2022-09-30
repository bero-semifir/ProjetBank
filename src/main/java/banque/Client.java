package banque;

import java.util.*;

public class Client {
    private int numero;
    private String nom;
    private String prenom;
    private int age;
    private Map<Integer, Compte> tabCompte = new Hashtable<>(5);

    public Client(int numero, String nom, String prenom, int age) {
        this(numero, nom, prenom, age, new Hashtable<>());
    }

    public Client(int numero, String nom, String prenom, int age, Compte[] tabCompte) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        if (tabCompte != null){
            this.setTabCompte(tabCompte);
        }else{
            this.tabCompte = new Hashtable<>(5);
        }
    }

    public Client(int numero, String nom, String prenom, int age, Map<Integer, Compte> tabCompte) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.tabCompte = tabCompte;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<Integer, Compte> getTabCompte() {
        return this.tabCompte;
    }

    public void setTabCompte(Compte[] tabCompte) {
        if(tabCompte.length<=5){
            for (Compte compte: tabCompte){
                this.tabCompte.put(compte.getNumero(), compte);
            }
        }
    }

    public void setTabCompte(Map<Integer, Compte> tabCompte) {
        if(tabCompte.size() <= 5){
            this.tabCompte = tabCompte;
        }
    }

    /**
     * Ajoute un compte au Client
     * @throws banque.BanqueException
     * @param unCompte le compte à ajouter au client
     */
    public void ajouterCompte(Compte unCompte) throws BanqueException{
        if(this.tabCompte.size() == 5){
            throw new BanqueException("Nombre maximum de compte atteint");
        }
        this.tabCompte.put(unCompte.getNumero(), unCompte);
    }

    /**
     * Récupère le compte du client par son numéro de compte
     * @param numeroCompte le numero du compte à récupérer
     * @return Compte si il existe
     */
    public Compte getCompte(int numeroCompte) throws BanqueException{
        if (this.tabCompte.get(numeroCompte) != null){
            return this.tabCompte.get(numeroCompte);
        }
        throw new BanqueException("Aucun compte avec ce numero");
    }

    @Override
    public String toString() {
        return "Client{" +
                "numero=" + numero +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", tabCompte=" + tabCompte +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return getNumero() == client.getNumero();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getNom(), getPrenom(), getAge(), getTabCompte());
    }
}
