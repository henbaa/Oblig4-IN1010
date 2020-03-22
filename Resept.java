abstract class Resept {
    protected static int nyId;
    protected int id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasient.hentPasientID();
        this.reit = reit;
        id = nyId;
        nyId++;
    }

    public int hentId() {
        return id;

    }
    public Legemiddel hentLegemiddel() {
        return legemiddel;

    }
    public Lege hentLege() {
        return utskrivendeLege;

    }
    public int hentPasientId() {
        return pasientId;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return "Resept-id: " + id + "\nLegemiddel: " + legemiddel.hentNavn() +
                "\nUtskrivende lege: " + utskrivendeLege.hentNavn() + "\nPasient-id: " +
                pasientId + "\nReit: " + reit;
    }

    abstract public String farge();

    abstract public Double prisAaBetale();

}
