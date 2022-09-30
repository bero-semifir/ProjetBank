package banque;

public class CompeASeuilRemunere extends CompteRemunere implements ICompteASeuil {

    private double seuil;

    public CompeASeuilRemunere(int numero, double taux, double seuil) {
        super(numero, taux);
        this.seuil = seuil;
    }

    public CompeASeuilRemunere(int numero, double solde, double taux, double seuil) {
        super(numero, solde, taux);
        this.seuil = seuil;
    }

    @Override
    public double getSeuil() {
        return this.seuil;
    }

    @Override
    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

    /**
     * Retire un montant du solde du compte. Si le seuil est dépassé une exception est levée
     * @param unMontant
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

}
