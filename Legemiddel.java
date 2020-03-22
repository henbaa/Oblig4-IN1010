abstract class Legemiddel {
    protected static int nyId = 0;
    protected int id;
    protected String navn;
    protected Double pris;
    protected Double virkestoff;

    public Legemiddel(String navn,Double pris,Double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        id =nyId;
        nyId++;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public Double hentPris() {
        return pris;
    }

    public Double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(Double nyPris) {
        pris = nyPris;
    }

    @Override
    public String toString() {
        return "Legemiddel: " + navn + "\nId: " + id + "\nPris: " + pris + "\nVirkestoff: " + virkestoff;
    }
}