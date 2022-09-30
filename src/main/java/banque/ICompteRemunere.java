package banque;

public interface ICompteRemunere {

    /**
     * Calcul les intérets du compte
     * @return les interêts
     */
    public double calculerInterets();

    /**
     * Verse les interêts sur le compte
     */
    public void verserInterets();
    public double getTaux();
    public void setTaux(double unTaux);

}
