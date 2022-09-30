package banque;

public interface ICompteRemunere {

    /**
     * Calcul les intérets du compte
     * @return les interêts
     */
    double calculerInterets();

    /**
     * Verse les interêts sur le compte
     */
    void verserInterets();
    double getTaux();
    void setTaux(double unTaux);

}
