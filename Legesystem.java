

import java.util.*;
import java.io.*;
import java.io.PrintWriter;

public class Legesystem{
  private static Liste<Pasient> pasientListe = new Lenkeliste<Pasient>();
  private static Liste<Legemiddel> legemidlerListe = new Lenkeliste<Legemiddel>();
  private static Liste<Lege> legeListe = new SortertLenkeliste<Lege>();
  private static Liste<Resept> reseptListe = new Lenkeliste<Resept>();
    // Oppretter lister som lagrer objektene i legesystemet



    public static void main(String[] args){
      int x = 1;
      File fil;
      fil = new File("tekstfil.txt");
      lesFraFil(fil);

//Deloppgave E2, en start
      while (x != 0){
        Scanner input = new Scanner(System.in);
        System.out.println("\n \n Du har naa foelgende valgmuligheter:" + " \n"
        + "0. Avslutt " + " \n"
        + "1. Skriv ut fullstending liste over pasienter, leger, legemidler og resepter " + " \n"
        + "2. Opprette og legge til nye elementer i systemet" + " \n"
        + "3. Bruke en gitt resept fra listen til en pasient" + " \n"
        + "4. Skrive ut statistikk" + " \n"
        + "5. Skrive alle data til fil" + " \n");
        x = input.nextInt();

        if (x == 1){
          skrivUtAlt();
        } else if(x == 2){
          opprettOgLeggTilElement();
        }else if(x == 3){
          brukResept();
        }else if(x == 4){
          statistikk();
        }else if(x == 5){
          skrivTilFil();
        }
      }
    }


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
            // Legger til alle pasientene i filen
            if(info[1].compareTo("Pasienter") == 0){
              String[] tempListe;
              String navn;
              String id;
              //Flytter lenkeliste for pasienter ut
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
                    Pasient nyPasient = new Pasient(navn, id);
                    pasientListe.leggTil(nyPasient);
                  }
              }


            //Legger inn Legemidlene
            else if(info[1].compareTo("Legemidler") == 0){
              String navnLegemiddel;
              Double prisLegemiddel;
              Double virkestoffLegemiddel;
              int styrkeLegemiddel;
                while(scanner.hasNextLine()){
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


                      Narkotisk nyttLegemiddel = new Narkotisk(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel, styrkeLegemiddel);
                      legemidlerListe.leggTil(nyttLegemiddel);

                    }
                    else if(legemiddel[1].compareTo("vanedannende") == 0){

                      navnLegemiddel = legemiddel[0];
                      prisLegemiddel = Double.valueOf(legemiddel[2]);
                      virkestoffLegemiddel = Double.valueOf(legemiddel[3]);
                      styrkeLegemiddel = Integer.parseInt(legemiddel[4]);

                      Vanedannende nyttLegemiddel = new Vanedannende(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel, styrkeLegemiddel);
                      legemidlerListe.leggTil(nyttLegemiddel);

                    }else if (legemiddel[1].compareTo("vanlig") == 0){
                      navnLegemiddel = legemiddel[0];
                      prisLegemiddel = Double.valueOf(legemiddel[2]);
                      virkestoffLegemiddel = Double.valueOf(legemiddel[3]);

                      Vanlig nyttLegemiddel = new Vanlig(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel);
                      legemidlerListe.leggTil(nyttLegemiddel);

                    }
                }
            }

            //Legger inn leger
            else if(info[1].compareTo("Leger") == 0){

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

                      Lege nyLege = new Lege(navnLege);
                      legeListe.leggTil(nyLege);

                    }else{
                      navnLege = info[0];
                      kontrollIdLege = Integer.parseInt(info[1]);

                      Spesialist nyLege = new Spesialist(navnLege, kontrollIdLege);
                      legeListe.leggTil(nyLege);
                    }
                }
            }
            //Legger inn Resepter
            else if(info[1].compareTo("Resepter") == 0){

                while(scanner.hasNextLine()){

                    innlest = scanner.nextLine();
                    info = innlest.split(",");
                    //# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])
                    Legemiddel reseptLegemiddel = legemidlerListe.hent(Integer.parseInt(info[0])); //
                    Pasient reseptPasient = pasientListe.hent(Integer.parseInt(info[2]));


                    if (info[3].compareTo("hvit") == 0){
                      for(Lege i : legeListe){
                        if (i.hentNavn().compareTo(info[1]) == 0){
                          try{
                            Resept resept = i.skrivHvitResept(reseptLegemiddel, reseptPasient, Integer.parseInt(info[4]));
                            reseptListe.leggTil(resept);
                            reseptPasient.leggTilResept(resept);
                          }
                          catch(UlovligUtskrift e){
                            System.out.println("En feil maa ha skjedd");
                          }
                        }
                      }
                    }
                    else if (info[3].compareTo("blaa") == 0){
                      for(Lege i : legeListe){
                        if (i.hentNavn().compareTo(info[1]) == 0){
                          try{
                            Resept resept = i.skrivBlaaResept(reseptLegemiddel, reseptPasient, Integer.parseInt(info[4]));
                            reseptListe.leggTil(resept);
                            reseptPasient.leggTilResept(resept);
                          }
                          catch(UlovligUtskrift e){
                            System.out.println("En feil maa ha skjedd");
                          }
                        }
                      }
                    }
                    else if(info[3].compareTo("millitaer") == 0){
                      for(Lege i : legeListe){
                        if (i.hentNavn().compareTo(info[1]) == 0){
                          try{
                            Resept resept = i.skrivMillitaerResept(reseptLegemiddel, reseptPasient, Integer.parseInt(info[4]));
                            reseptListe.leggTil(resept);
                            reseptPasient.leggTilResept(resept);
                          }
                          catch(UlovligUtskrift e){
                            System.out.println("En feil maa ha skjedd");
                          }
                        }
                      }
                    }
                    else if(info[3].compareTo("p") == 0){
                      for(Lege i : legeListe){
                        if (i.hentNavn().compareTo(info[1]) == 0){
                          try{
                            Resept resept = i.skrivPResept(reseptLegemiddel, reseptPasient);
                            reseptListe.leggTil(resept);
                            reseptPasient.leggTilResept(resept);
                          }
                          catch(UlovligUtskrift e){
                            System.out.println("En feil maa ha skjedd");
                          }
                        }
                      }
                    }

                  }
                    // kan skille ut hjelpemetoder som leter gjennom listene og returnerer riktig objekt, ut ifra informasjonen som ble lest inn
                }
            }
    }
    private static void skrivUtAlt(){
          //Skriv ut pasienter, legemidler, leger og resepter
          System.out.println("Pasienter \n");
          for(Pasient i: pasientListe){
            System.out.println("\n" + i + "\n");
            for(Resept j : i.hentReseptStabel()){
              System.out.print("\n" + j.toString() + "\n");
            }
          }
          System.out.println("\n Legemidler \n");
          for(Legemiddel i: legemidlerListe){
            System.out.println(i + "\n");
          }
          System.out.println("\n Leger \n");
          for(Lege i: legeListe){
            System.out.println(i + "\n");
          }
          System.out.println("\n Resepter \n");
          for(Resept i: reseptListe){
            System.out.println(i + "\n");
          }
    }
    private static void opprettOgLeggTilElement(){
          //Skal vaere mulig aa opprette et av foelgende objekt Lege, Pasient, Resept eller Legemiddel
          //Sjekk at det er mulig aa opprette objektet
          int detSomSkalLeggesTil=1;

          while (detSomSkalLeggesTil != 0){
            Scanner input = new Scanner(System.in);
            System.out.println("Du har naa foelgende valgmuligheter:" + " \n"
            + "0. Avslutt/Gaa tilbake " + " \n"
            + "1. Legg til en pasient \n"
            + "2. Legg til en lege \n"
            + "3. Legg til et legemiddel \n"
            + "4. Legg til en resept \n");
            detSomSkalLeggesTil = input.nextInt();

            if (detSomSkalLeggesTil == 1){
              //Legg til pasient
              Scanner pasient = new Scanner(System.in);
              System.out.println("Hva er navnet paa pasienten? ");
              String navn = pasient.nextLine();
              System.out.println("Hva er personnummeret til pasienten? ");
              String id = pasient.nextLine();
              Pasient nyPasient = new Pasient(navn, id);
              pasientListe.leggTil(nyPasient);

            } else if(detSomSkalLeggesTil == 2){
              //Legg til lege
              Scanner lege = new Scanner(System.in);
              System.out.println("Er legen en spesialist? Ja/Nei ");
              String jaNeiSvar = lege.nextLine();

                if (jaNeiSvar.equals("Nei")){
                  System.out.println("Hva er navnet paa legen ");
                  String navnLege = lege.nextLine();
                  Lege nyLege = new Lege(navnLege);
                  legeListe.leggTil(nyLege);
                }else if(jaNeiSvar.equals("Ja")){
                  System.out.println("Hva er navnet paa spesialisten? ");
                  String navnLege = lege.nextLine();
                  System.out.println("Hva er spesialistens kontrollid? ");
                  int kontrollIdLege = lege.nextInt();
                  Spesialist nyLege = new Spesialist(navnLege, kontrollIdLege);
                  legeListe.leggTil(nyLege);
                }
            } else if(detSomSkalLeggesTil == 3){
              //Legg til legemiddel
              Scanner legemiddel = new Scanner(System.in);
              System.out.println("Hva slags legemiddel er det? Vanlig/Vanedannende/Narkotisk ");
              String typeLegemiddel = legemiddel.nextLine();

                if (typeLegemiddel.equals("Vanlig")){
                  System.out.println("Hva er navnet paa legemiddelet? ");
                  String navnLegemiddel = legemiddel.nextLine();
                  System.out.println("Hva er prisen? ");
                  Double prisLegemiddel = legemiddel.nextDouble();
                  System.out.println("Hvilket virkestoff? ");
                  Double virkestoffLegemiddel = legemiddel.nextDouble();
                  Vanlig nyttLegemiddel = new Vanlig(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel);
                  legemidlerListe.leggTil(nyttLegemiddel);

                }else if(typeLegemiddel.equals("Vanedannende")){

                  System.out.println("Hva er navnet paa legemiddelet? ");
                  String navnLegemiddel = legemiddel.nextLine();
                  System.out.println("Hva er prisen? ");
                  Double prisLegemiddel = legemiddel.nextDouble();
                  System.out.println("Hvilket virkestoff? ");
                  Double virkestoffLegemiddel = legemiddel.nextDouble();
                  System.out.println("Hvilken styrke? ");
                  int styrkeLegemiddel = legemiddel.nextInt();
                  Vanedannende nyttLegemiddel = new Vanedannende(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel, styrkeLegemiddel);
                  legemidlerListe.leggTil(nyttLegemiddel);

                }else if(typeLegemiddel.equals("Narkotisk")){

                  System.out.println("Hva er navnet paa legemiddelet? ");
                  String navnLegemiddel = legemiddel.nextLine();
                  System.out.println("Hva er prisen? ");
                  Double prisLegemiddel = legemiddel.nextDouble();
                  System.out.println("Hvilket virkestoff? ");
                  Double virkestoffLegemiddel = legemiddel.nextDouble();
                  System.out.println("Hvilken styrke? ");
                  int styrkeLegemiddel = legemiddel.nextInt();
                  Narkotisk nyttLegemiddel = new Narkotisk(navnLegemiddel, prisLegemiddel, virkestoffLegemiddel, styrkeLegemiddel);
                  legemidlerListe.leggTil(nyttLegemiddel);
                }
            }else if(detSomSkalLeggesTil == 4){
              //Legg til resept
              Scanner resept = new Scanner(System.in);
              System.out.println("Hva slags resept er det? Hvit/Blaa/Militaer/P ");
              String typeResept = resept.nextLine();

                if (typeResept.equals("Hvit")){
                  System.out.println("Hvem er legen? ");
                  for (Lege l : legeListe){
                    System.out.println(l.toString());
                  }
                  String reseptLegeNavn = resept.nextLine();

                  for(Lege lege : legeListe){
                    if(lege.hentNavn().compareTo(reseptLegeNavn)==0){
                      Lege reseptLege = lege;
                      System.out.println("Hva er pasientens personnr? ");
                      for (Pasient p : pasientListe){
                        System.out.println(p.toString());
                      }
                      String reseptPasientPersonnummer = resept.nextLine();
                      for (Pasient pasient : pasientListe){
                        if(pasient.hentPasientFodselsnr().compareTo(reseptPasientPersonnummer)==0){
                          Pasient reseptPasient = pasient;
                          System.out.println("Hva er navnet paa legemiddelet? ");
                          for (Legemiddel l : legemidlerListe){
                            System.out.println(l.toString());
                          }
                          String reseptLegemiddelNavn = resept.nextLine();
                          for(Legemiddel legemiddel : legemidlerListe){
                            if(legemiddel.hentNavn().compareTo(reseptLegemiddelNavn)==0){
                              Legemiddel reseptLegemiddel = legemiddel;
                              System.out.println("Hvor mange reit? ");
                              int reseptReit = resept.nextInt();
                              try{
                                Resept res = reseptLege.skrivHvitResept(reseptLegemiddel, reseptPasient, reseptReit);
                                reseptListe.leggTil(res);
                                reseptPasient.leggTilResept(res);
                              }
                              catch(UlovligUtskrift e){
                                System.out.println("En feil maa ha skjedd");
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }else if(typeResept.equals("Blaa")){
                  System.out.println("Hvem er legen? ");
                  for (Lege l : legeListe){
                    System.out.println(l.toString());
                  }
                  String reseptLegeNavn = resept.nextLine();

                  for(Lege lege : legeListe){
                    if(lege.hentNavn().compareTo(reseptLegeNavn)==0){
                      Lege reseptLege = lege;
                      System.out.println("Hva er pasientens personnr? ");
                      for (Pasient p : pasientListe){
                        System.out.println(p.toString());
                      }
                      String reseptPasientPersonnummer = resept.nextLine();
                      for (Pasient pasient : pasientListe){
                        if(pasient.hentPasientFodselsnr().compareTo(reseptPasientPersonnummer)==0){
                          Pasient reseptPasient = pasient;
                          System.out.println("Hva er navnet paa legemiddelet? ");
                          for (Legemiddel l : legemidlerListe){
                            System.out.println(l.toString());
                          }
                          String reseptLegemiddelNavn = resept.nextLine();
                          for(Legemiddel legemiddel : legemidlerListe){
                            if(legemiddel.hentNavn().compareTo(reseptLegemiddelNavn)==0){
                              Legemiddel reseptLegemiddel = legemiddel;
                              System.out.println("Hvor mange reit? ");
                              int reseptReit = resept.nextInt();
                              try{

                                Resept res = reseptLege.skrivBlaaResept(reseptLegemiddel, reseptPasient, reseptReit);
                                reseptListe.leggTil(res);
                                reseptPasient.leggTilResept(res);
                              }
                              catch(UlovligUtskrift e){
                                System.out.println("En feil maa ha skjedd");
                              }
                            }
                          }
                        }
                      }
                    }
                  }

                }else if(typeResept.equals("Militaer")){

                  System.out.println("Hvem er legen? ");
                  for (Lege l : legeListe){
                    System.out.println(l.toString());
                  }
                  String reseptLegeNavn = resept.nextLine();

                  for(Lege lege : legeListe){
                    if(lege.hentNavn().compareTo(reseptLegeNavn)==0){
                      Lege reseptLege = lege;
                      System.out.println("Hva er pasientens personnr? ");
                      for (Pasient p : pasientListe){
                        System.out.println(p.toString());
                      }
                      String reseptPasientPersonnummer = resept.nextLine();
                      for (Pasient pasient : pasientListe){
                        if(pasient.hentPasientFodselsnr().compareTo(reseptPasientPersonnummer)==0){
                          Pasient reseptPasient = pasient;
                          System.out.println("Hva er navnet paa legemiddelet? ");
                          for (Legemiddel l : legemidlerListe){
                            System.out.println(l.toString());
                          }
                          String reseptLegemiddelNavn = resept.nextLine();
                          for(Legemiddel legemiddel : legemidlerListe){
                            if(legemiddel.hentNavn().compareTo(reseptLegemiddelNavn)==0){
                              Legemiddel reseptLegemiddel = legemiddel;
                              System.out.println("Hvor mange reit? ");
                              int reseptReit = resept.nextInt();
                              try{

                                Resept res = reseptLege.skrivMillitaerResept(reseptLegemiddel, reseptPasient, reseptReit);
                                reseptListe.leggTil(res);
                                reseptPasient.leggTilResept(res);
                              }
                              catch(UlovligUtskrift e){
                                System.out.println("En feil maa ha skjedd");
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }else if(typeResept.equals("P")){
                    System.out.println("Hvem er legen? ");
                    for (Lege l : legeListe){
                      System.out.println(l.toString());
                    }
                    String reseptLegeNavn = resept.nextLine();

                    for(Lege lege : legeListe){
                      if(lege.hentNavn().compareTo(reseptLegeNavn)==0){
                        Lege reseptLege = lege;
                        System.out.println("Hva er pasientens personnr? ");
                        for (Pasient p : pasientListe){
                          System.out.println(p.toString());
                        }
                        String reseptPasientPersonnummer = resept.nextLine();
                        for (Pasient pasient : pasientListe){
                          if(pasient.hentPasientFodselsnr().compareTo(reseptPasientPersonnummer)==0){
                            Pasient reseptPasient = pasient;
                            System.out.println("Hva er navnet paa legemiddelet? ");
                            for (Legemiddel l : legemidlerListe){
                              System.out.println(l.toString());
                            }
                            String reseptLegemiddelNavn = resept.nextLine();
                            for(Legemiddel legemiddel : legemidlerListe){
                              if(legemiddel.hentNavn().compareTo(reseptLegemiddelNavn)==0){
                                Legemiddel reseptLegemiddel = legemiddel;

                                try{

                                  Resept res = reseptLege.skrivPResept(reseptLegemiddel, reseptPasient);
                                  reseptListe.leggTil(res);
                                  reseptPasient.leggTilResept(res);
                                }
                                catch(UlovligUtskrift e){
                                  System.out.println("En feil maa ha skjedd");
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                }
            }
          }
    }
    private static void brukResept(){
      Scanner pasient = new Scanner(System.in);
      Scanner resept = new Scanner(System.in);

      System.out.println("Hvilken pasient vil du se resepter for? \n");
      for(Pasient p: pasientListe){
        System.out.println(p.hentPasientID()+ ": " + p.toString() + "\n");
      }
      System.out.println("\n");

      int valgtPasient = pasient.nextInt();

      System.out.println("Hvilken resept vil du bruke? ");

      for(Pasient passient : pasientListe){
        if (passient.hentPasientID() == valgtPasient){
          for(Resept ressept : passient.hentReseptStabel()){
            System.out.println(ressept.hentId() + ": " + ressept.hentLegemiddel().hentNavn() + "(" + ressept.hentReit() + "reit )");
          }
        }
      }
      int valgtResept = resept.nextInt();
      for(Pasient passient : pasientListe){
        if (passient.hentPasientID() == valgtPasient){
          for(Resept ressept : passient.hentReseptStabel()){
            if(ressept.hentId() == valgtResept){
              ressept.bruk();
              System.out.println("Brukte resept paa " + ressept.hentLegemiddel().hentNavn() + " gjenvaerende " + ressept.hentReit() + " reit )");
            }
          }
        }
      }
    }
    private static void statistikk(){
      int x = 0;
      Scanner input = new Scanner(System.in);
      System.out.println("\n Hva slags statistikk vil du se:" + " \n"
      + "0. Avslutt/Gaa tilbake " + " \n"
      + "1. Skriv ut totalt antall utskrevne resepter paa vanedannende legemidler " + " \n"
      + "2. Skriv ut totalt antall utskrevne resepter paa narkotiske legemidler" + " \n"
      + "3. Skriv ut liste over leger som har utskrevet resepter med narkotiske legemidler, og henholsvis antall" + " \n"
      + "4. Skriv ut liste over pasienter som har mottatt resepter med narkotiske legemidler, og henholdsvis antall" + " \n");
      x = input.nextInt();


      if (x == 1){//Totalt antall utskrevne resepter paa vanedannende legemidler
        int vanedannendeTeller = 0;
        for (Resept resept:reseptListe){
          if(resept.hentLegemiddel() instanceof Vanedannende){
            vanedannendeTeller++;
          }
        }
        System.out.println("Totalt antall utskrevne resepter paa vanedannende legemidler: " + vanedannendeTeller);
      } else if(x == 2){ //Totalt antall utskrevne resepter paa narkotiske legemidler
        int narkotiskTeller = 0;
        for (Resept resept:reseptListe){
          if(resept.hentLegemiddel() instanceof Narkotisk){
            narkotiskTeller++;
          }
        }
        System.out.println("Totalt antall utskrevne resepter paa narkotiske legemidler: " + narkotiskTeller);
      }else if(x == 3){
        for (Lege lege:legeListe){//Statistikk om mulig misbruk av narkotika skal vises på følgende måte:   List opp navnene på alle leger (i alfabetisk rekkefølge) som har skrevet ut minst en resept på narkotiske legemidler, og antallet slike resepter per lege.
          int legeReseptTeller = 0;
          for(Resept legeResept:lege.hentUtskrevedeResepter()){
            if (legeResept.hentLegemiddel() instanceof Narkotisk){
              legeReseptTeller++;
            }
          }
          if(legeReseptTeller>0){
            System.out.println(lege.hentNavn() + " har skrevet ut " + legeReseptTeller + " resept(er) med narkotiske legemidler \n");
          }
        }
      }else if(x == 4){
        for (Pasient pasient:pasientListe){//Statistikk om mulig misbruk av narkotika skal vises på følgende måte:  List opp navnene på alle pasienter som har minst en gyldig resept på narkotiske legemidler, og for disse, skriv ut antallet per pasient.
          int pasientReseptTeller = 0;
          for(Resept pasientResept:pasient.hentReseptStabel()){
            if (pasientResept.hentLegemiddel() instanceof Narkotisk){
              pasientReseptTeller++;
            }
          }
          if(pasientReseptTeller>0){
            System.out.println(pasient.hentPasientNavn() + " har faatt " + pasientReseptTeller + " resept(er) med narkotiske legemidler \n");
          }
        }
      }
    }
    private static void skrivTilFil(){
      PrintWriter fil = null;
      try {
            fil = new PrintWriter("LegesystemTilFil.txt");
          }
      catch (Exception e) {
            System.out.println("Kan ikke lage filen");
              System.exit(1);
          }

        fil.print("# Pasienter (navn, fnr) \n");
        for(Pasient p : pasientListe){
          fil.print(p.hentPasientNavn() + "," + p.hentPasientFodselsnr() + "\n");
        }
        fil.print("# Legemidler (navn,type,pris,virkestoff,[styrke]) \n");
        for(Legemiddel l : legemidlerListe){
          if (l instanceof Narkotisk){
            Narkotisk narkotiskLegemiddel = (Narkotisk) l;

            fil.print(narkotiskLegemiddel.hentNavn() + "," + "narkotisk," + narkotiskLegemiddel.hentPris() + "," + narkotiskLegemiddel.hentVirkestoff() + "," //Pris blir null fordi det er en MillitaerResept, der pris settes lik null
            + Integer.toString(narkotiskLegemiddel.hentNarkotiskStyrke()) + "\n");
          }else if (l instanceof Vanedannende){
            Vanedannende vanedannendeLegemiddel = (Vanedannende) l;
            fil.print(l.hentNavn() + "," + "vanedannende," + l.hentPris() + "," + l.hentVirkestoff() + ","
            + Integer.toString(vanedannendeLegemiddel.hentVanedannendeStyrke()) + "\n");
          }else if (l instanceof Vanlig){
            fil.print(l.hentNavn() + "," + "vanlig," + l.hentPris() + "," + l.hentVirkestoff() + ","  + "\n");
          }
        }
        fil.print("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
        for(Lege le : legeListe){
          if(le instanceof Spesialist){
            Spesialist spesialistKontrollID = (Spesialist) le;
            fil.print(le.hentNavn() + "," + Integer.toString(spesialistKontrollID.hentKontrollID()) + "\n");
          }else {
            fil.print(le.hentNavn() + "," + "0" + "\n");
          }
        }
        fil.print("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
        for(Resept resept : reseptListe){
          if(resept instanceof PResept){
            fil.print(Integer.toString(resept.hentLegemiddel().hentId()) + "," + resept.hentLege().hentNavn() + ","
            + Integer.toString(resept.hentPasientId()) + "," + "p," + Integer.toString(resept.hentReit()) + "\n");
          }else if (resept instanceof MillitaerResept){
            fil.print(Integer.toString(resept.hentLegemiddel().hentId()) + ", " + resept.hentLege().hentNavn() + ","
            + Integer.toString(resept.hentPasientId()) + "," + "millitaer," + Integer.toString(resept.hentReit()) + "\n");
          }else if (resept instanceof BlaaResept){
            fil.print(Integer.toString(resept.hentLegemiddel().hentId()) + ", " + resept.hentLege().hentNavn() + ","
            + Integer.toString(resept.hentPasientId()) + "," + "blaa," + Integer.toString(resept.hentReit()) + "\n");
          }else if (resept instanceof HvitResept){
            fil.print(Integer.toString(resept.hentLegemiddel().hentId()) + ", " + resept.hentLege().hentNavn() + ","
            + Integer.toString(resept.hentPasientId()) + "," + "hvit," + Integer.toString(resept.hentReit()) + "\n");
          }
        }
        fil.println();
        fil.close();
        System.out.print("Data er skrevet ut til fil. \n");
    }

}
