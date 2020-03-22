
import java.util.Scanner;

public class test{

  public static void main(String[] args){
    //Scanner scanner = null;
    String innlest = null;
    String[] tempListe;
    String navn;
    String id;
    String s = "Jens Hans Olsen,11111143521 \n Petrolina Swiq,24120099343 \n Sven Svendsen,10111224244 \n Juni Olsen,21049563451 \n#";

    Scanner scanner = new Scanner(s);

    Liste<Pasient> pasientListe = new Lenkeliste<Pasient>();

    while(scanner.hasNextLine()) {

      innlest = scanner.nextLine();
      if(innlest.charAt(0) == '#'){
            break;
      }
      tempListe = innlest.split(",");
      navn = tempListe[0];
      id = tempListe[1];
       //Om vi er ferdig med å legge til pasienter, bryt whileløkken,
       //slik at vi fortsetter til koden for å legge til legemiddler
       System.out.println(navn);
       System.out.println(id);
       Pasient nyPasient = new Pasient(navn, id);
       pasientListe.leggTil(nyPasient);

    }



    //System.out.println(innlest);
    //for (i = 0;      //
      //navn = innlest[i].split(",")
      //id = innlest[i+1].split(",")


     //pasientListe.leggTil(Pasient(navn , id));
       //MERK:  Her må du legge til pasienten i en lenkeliste
  }

}
