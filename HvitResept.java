class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel,utskrivendeLege,pasient,reit); 
    }

    @Override
    public String farge() {
        return "hvit";
    }

    @Override
    public Double prisAaBetale() {
        return legemiddel.hentPris();
    }

    public String toString() {
        return "Type: " + this.farge() +"resept\n" + super.toString() + "\nPris: " + this.prisAaBetale();
    }
}
