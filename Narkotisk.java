class Narkotisk extends Legemiddel {

    protected int narkotiskStyrke;

    public Narkotisk(String navn, Double pris, Double virkestoff, int styrke) {
        super(navn,pris,virkestoff);
        this.narkotiskStyrke = styrke;
    }
    public int hentNarkotiskStyrke() {
        return narkotiskStyrke;
    }
    @Override
    public  String toString() {
        return super.toString() + "\nStyrke: " + narkotiskStyrke;
    }
}
