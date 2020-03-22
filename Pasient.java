public class Pasient {

    static int IDTeller = 0;
    private int pasientID;
    private String  navn;
    private String fodselsnr;
    private Stabel<Resept> reseptStabel = new Stabel<Resept>();

    public Pasient(String navn, String fodselsnr) {
        this.navn = navn;
        this.fodselsnr = fodselsnr;
        this.pasientID = IDTeller;
        IDTeller++;
    }

    public String toString() {
            return "Navn: " + navn + ", Foedselsnummer: " + fodselsnr;
    }

    public Stabel<Resept> hentReseptStabel() {
        return reseptStabel;
    }

    public int hentPasientID() {
        return pasientID;
    }

    public String hentPasientFodselsnr() {
        return fodselsnr;
    }
    public String hentPasientNavn() {
        return navn;
    }

    public void leggTilResept(Resept resept) {
        reseptStabel.leggPaa(resept);
    }
}
