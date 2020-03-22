class Vanedannende extends Legemiddel {
    protected int vanedannendeStyrke;

    public Vanedannende(String navn, Double pris, Double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.vanedannendeStyrke = styrke;
    }

    public int hentVanedannendeStyrke() {
        return vanedannendeStyrke;
    }

    public String toString() {
        return super.toString() + "\nStyrke: " + vanedannendeStyrke;
    }
}