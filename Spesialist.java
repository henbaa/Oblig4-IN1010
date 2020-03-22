class Spesialist extends Lege implements Godkjenningsfritak {
    protected int kontrollID;

    public Spesialist(String navn, int kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }
    @Override
    public int hentKontrollID() {
        return kontrollID;
    }
    public String toString() {
        return "Navn: " + navn + "\nTittel: Spesialist" +  "\nKontroll-ID: " + kontrollID;
    }
}