class MillitaerResept extends HvitResept {
    public MillitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
        legemiddel.settNyPris(0.0);
    }

    @Override
    public String farge() {
        return super.farge();
    }

    @Override
    public Double prisAaBetale() {
        return 0.0;
    }

    public String toString() {
        return "Militaerresept\n" + super.toString();
    }
}
