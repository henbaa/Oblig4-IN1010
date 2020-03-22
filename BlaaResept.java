class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);  
    }

    @Override
    public String farge() {
        return "blaa";
    }

    @Override
    public Double prisAaBetale() {
        return legemiddel.hentPris() * 0.25;
    }

    public String toString() {
        return "Type: " + this.farge() + "resept\n" + super.toString() + "\nPris: " + this.prisAaBetale();
    }
}
