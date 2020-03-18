

import java.util.*;
import java.io.*;

public class Legesystem{
    // Opprett lister som lagrer objektene i legesystemet

    public static void main(String[] args){
      int x = 1;
      File fil;
//Deloppgave E2, en start
      while (x != 0){
        Scanner input = new Scanner(System.in);
        System.out.println("Du har naa foelgende valgmuligheter:" + " \n"
        + "0. Avslutt " + " \n"
        + "1. Skriv ut fullstending liste over pasienter, leger, legemidler og resepter " + " \n"
        + "2. Opprette og legge til nye elementer i systemet" + " \n"
        + "3. Bruke en gitt resept fra listen til en pasient" + " \n"
        + "4. Skrive ut statistikk" + " \n"
        + "5. Skrive alle data fra fil" + " \n");
        x = input.nextInt();

        if (x == 1){
          fil = new File("tekstfil.txt");
          lesFraFil(fil);
        }

      }

      //lesFraFil()


    }
//Deloppgave E1
    private static void lesFraFil(File fil){
        Scanner scanner = null;
        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen, starter opp som et tomt Legesystem");
            return;
        }

        String innlest = scanner.nextLine();


        while(scanner.hasNextLine()){
            String[] info = innlest.split(" ");
            String[] tempListe;
            String navn;
            String id;
            // Legger til alle pasientene i filen
            if(info[1].compareTo("Pasienter") == 0){

              Liste<Pasient> pasientListe = new Lenkeliste<Pasient>();

                while(scanner.hasNextLine()) {
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til pasienter, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til legemiddler
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
              }


            //Legger inn Legemidlene
            else if(info[1].compareTo("Legemidler") == 0){

              Liste<Legemiddel> legemidlerListe = new Lenkeliste<Legemiddel>();

                while(scanner.hasNextLine()){
                  String navnLegemiddel;
                  Double prisLegemiddel;
                  Double virkestoffLegemiddel;
                  int styrkeLegemiddel;

                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til legemidler, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til leger
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    String[] legemiddel = innlest.split(",");
                    if(legemiddel[1].compareTo("narkotisk") == 0){

                      navnLegemiddel = legemiddel[0];
                      prisLegemiddel = Double.valueOf(legemiddel[2]);
                      virkestoffLegemiddel = Double.valueOf(legemiddel[3]);
                      styrkeLegemiddel = Integer.parseInt(legemiddel[4]);

                      System.out.println(navnLegemiddel);
                      System.out.println(prisLegemiddel);

                      Narkotisk nyttLegemiddel = new Narkotisk(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel, styrkeLegemiddel);
                      legemidlerListe.leggTil(nyttLegemiddel);

                    }
                    else if(legemiddel[1].compareTo("vanedannende") == 0){
                      navnLegemiddel = legemiddel[0];
                      prisLegemiddel = Double.valueOf(legemiddel[2]);
                      virkestoffLegemiddel = Double.valueOf(legemiddel[3]);
                      styrkeLegemiddel = Integer.parseInt(legemiddel[4]);

                      System.out.println(navnLegemiddel);
                      System.out.println(prisLegemiddel);

                      Vanedannende nyttLegemiddel = new Vanedannende(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel, styrkeLegemiddel);
                      legemidlerListe.leggTil(nyttLegemiddel);

                    }else if (legemiddel[1].compareTo("vanlig") == 0){
                      navnLegemiddel = legemiddel[0];
                      prisLegemiddel = Double.valueOf(legemiddel[2]);
                      virkestoffLegemiddel = Double.valueOf(legemiddel[3]);

                      System.out.println(navnLegemiddel);
                      System.out.println(prisLegemiddel);

                      Vanlig nyttLegemiddel = new Vanlig(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel);
                      legemidlerListe.leggTil(nyttLegemiddel);

                    }

                }
            }
            //Legger inn leger
            else if(info[1].compareTo("Leger") == 0){
              Liste<Lege> legeListe = new Lenkeliste<Lege>();
                while(scanner.hasNextLine()){
                  String navnLege;
                  int kontrollIdLege;

                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til leger, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til resepter
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    info = innlest.split(",");
                    int kontrollid = Integer.parseInt(info[1]);
                    if(kontrollid == 0){
                      navnLege = info[0];

                      System.out.println(navnLege);


                      Lege nyLege = new Lege(navnLege);
                      legeListe.leggTil(nyLege);

                      //Maa sorteres
                    }else{
                      navnLege = info[0];
                      kontrollIdLege = Integer.parseInt(info[1]);

                      System.out.println(navnLege);
                      System.out.println(kontrollIdLege);

                      Spesialist nyLege = new Spesialist(navnLege, kontrollIdLege);
                      legeListe.leggTil(nyLege);

                      //Maa sorteres
                    }
                }

            }
            //Legger inn Resepter
            else if(info[1].compareTo("Resepter") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    info = innlest.split(", ");
                    //
                    // Her må du finne legen, legemiddelet, og pasienten som ligger
                    // i lenkelistene utifra informasjonen.
                    //
                    // Dette burde skilles ut i hjelpemetoder leter gjennom listene
                    // og returnerer riktig objekt, ut ifra informasjonen som ble lest inn
                    //
                    // Opprett et reseptobjekt med skrivResept funksjonen i legen,
                    // og legg det til i en lenkeliste
                    //
                    // Dersom legeobjektene dine oppretter PResepter, kan du ignorere reit
                    //
                    //
                }
            }
        }
    }
}
