package banque;

public class CompteRemunere extends Compte implements ICompteRemunere {

    double taux;

    public CompteRemunere(int numero, double taux) {
        super(numero);
        this.taux = taux;
    }

    public CompteRemunere(int numero, double solde, double taux) {
        super(numero, solde);
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        if(taux > 0 && taux <= 1){
            this.taux = taux;
        }
    }

    /**
     * Calcul le montant des interêts
     * @return interets: le montant des interêts
     */
    public double calculerInterets(){
        double interets = this.getSolde() * this.getTaux();
        return interets;
    }

    /**
     * Ajoute les interêts sur le solde du compte
     */
    public void verserInterets() {
        double interets = this.calculerInterets();
        this.ajouter(interets);
    }

    @Override
    public String toString() {
        return "CompteRemunere{" +
                " numero=" + this.getNumero() +
                " solde=" + this.getSolde() +
                " taux=" + taux +
                " }";
    }
}
