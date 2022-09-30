package banque;

public class CompteASeuil extends Compte implements ICompteASeuil {

    double seuil;

    public CompteASeuil(int numero, double seuil) {
        super(numero);
        this.seuil = seuil;
    }

    public CompteASeuil(int numero, double solde, double seuil) {
        super(numero, solde);
        this.seuil = seuil;
    }

    @Override
    public double getSeuil() {
        return seuil;
    }

    @Override
    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

    /**
     * Retire un montant du solde du compte à seuil
     * Si le montant retiré faire descendre le solde sous le seuil, le retrait est annulé
     * @param unMontant le montant à retirer
     * @see banque.Compte
     * @throws banque.BanqueException
     */
    @Override
    public void retirer(double unMontant) throws BanqueException{
        if(this.getSolde() - unMontant > this.seuil){
            super.retirer(unMontant);
        }else {
            throw new BanqueException("Retrait refusé, seuil dépassé!");
        }
    }

    @Override
    public String toString() {
        return "CompteASeuil{" +
                " numero=" + this.getNumero() +
                " solde=" + this.getSolde() +
                " seuil=" + seuil +
                " }";
    }
}
