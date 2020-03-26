//IN1010
//Oblig 4
//Del D

public class Lege implements Comparable<Lege> {

    protected static int nyId = 0;
    protected int id;
    protected String navn;
    protected Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();

    public Lege(String navn) {
        this.navn = navn;
        id = nyId;
        nyId++;
    }

    public String hentNavn() {
        return navn;
    }

    public String toString() {
            return "Navn: " + navn + "\nTittel: Lege";
    }

    public Lenkeliste<Resept> hentUtskrevedeResepter() {
        return utskrevedeResepter;
    }

    @Override
    public int compareTo(Lege a) {
        return hentNavn().compareTo(a.hentNavn());
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit)
            throws UlovligUtskrift {
        if (!(this instanceof Spesialist)) {
            if (legemiddel instanceof Narkotisk) {
                throw new UlovligUtskrift(this, legemiddel, pasient.hentPasientID());
            }
        }
        HvitResept nyHvitResept = new HvitResept(legemiddel,this,pasient,reit);
        utskrevedeResepter.leggTil(nyHvitResept);
        return nyHvitResept;
    }

    public MillitaerResept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasient, int
            reit) throws UlovligUtskrift {
        if (!(this instanceof Spesialist)) {
            if (legemiddel instanceof Narkotisk) {
                throw new UlovligUtskrift(this, legemiddel, pasient.hentPasientID());
            }
        }
        MillitaerResept nyMillitaerResept = new MillitaerResept(legemiddel,this,pasient,reit);
        utskrevedeResepter.leggTil(nyMillitaerResept);
        return nyMillitaerResept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient)
            throws UlovligUtskrift {
        if (!(this instanceof Spesialist)) {
            if (legemiddel instanceof Narkotisk) {
                throw new UlovligUtskrift(this, legemiddel, pasient.hentPasientID());
            }
        }
        PResept nyPResept = new PResept(legemiddel,this,pasient);
        utskrevedeResepter.leggTil(nyPResept);
        return nyPResept;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws
            UlovligUtskrift {
        if (!(this instanceof Spesialist)) {
            if (legemiddel instanceof Narkotisk) {
                throw new UlovligUtskrift(this, legemiddel, pasient.hentPasientID());
            }
        }
        BlaaResept nyBlaaResept = new BlaaResept(legemiddel,this,pasient,reit);
        utskrevedeResepter.leggTil(nyBlaaResept);
        return nyBlaaResept;
    }
}
