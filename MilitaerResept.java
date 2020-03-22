class MilitaerResept extends HvitResept {
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
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