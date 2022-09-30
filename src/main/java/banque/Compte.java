package banque;

import java.util.Objects;

public class Compte {
    private double solde;
    private int numero;

    public Compte(int numero) {
        this(numero, 0);
    }

    public Compte(int numero, double solde ) {
        this.solde = solde;
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    protected void setSolde(double solde) {
        this.solde = solde;
    }

    public int getNumero() {
        return numero;
    }

    /**
     * Ajoute un montant du solde du compte
     * @param unMontant
     */
    public void ajouter(double unMontant){
        this.solde += unMontant;
    }

    /**
     * Retire un montant du solde du compte
     * @param unMontant
     */
    public void retirer(double unMontant) throws BanqueException{
        this.ajouter(-unMontant);
        //this.solde -= unMontant;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "solde=" + solde +
                ", numero=" + numero +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compte)) return false;
        Compte compte = (Compte) o;
        return getNumero() == compte.getNumero();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSolde(), getNumero());
    }
}
