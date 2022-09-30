package banque;

public interface ICompteASeuil {
    /**
     * Retire un montant du solde du compte
     * @param unMontant montant à retirer
     */
    void retirer(double unMontant) throws BanqueException;
    double getSeuil();
    void setSeuil(double seuil);
}
