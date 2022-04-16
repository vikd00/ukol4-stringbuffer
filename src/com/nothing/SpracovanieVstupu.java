package com.nothing;

import java.util.Scanner;

public class SpracovanieVstupu {
    private StringBuffer strBuffer;
    private Scanner scanner;
    private String predpona;
    private String cislo;
    private boolean symetricke;


    public SpracovanieVstupu() {
        this.strBuffer = new StringBuffer();
        this.scanner = new Scanner(System.in);
        this.symetricke = true;
    }

    //Funkcia startujuca program pre nacitavanie cisel
    public void start(){
        boolean pridavam = true;    //Premenna, ktora urcujuca ukoncenie inputu
        String vstup;


        System.out.println("Zadavajte vstup, ale ulozene budu iba cisla, znak '+'\n" + "mezara a escape znak 'q'");
        //While loop, ktora nacitava vstup dokedy uzivatel nezada q -> teda zmeni sa premenna "pridavam" na
        //false
        while(pridavam){
            vstup = nacitajVstup();
            if(vstup != null){
                if(vstup.equals("q")){
                    pridavam = false;
                }else{
                    strBuffer.append(vstup);
                }
            }
        }

        //Ziskame prve 4 cisla pomocou regexu -> +420
        predpona = strBuffer.toString().replaceAll("([0-9]+)","$1").substring(0,4);
        //Ziskame zvysnych 9 cisel pomocou regexu -> xxxxxxxxx
        cislo = strBuffer.toString().replaceAll("([0-9])","$1").substring(4,13);
        //Naformatujeme cislo na +420 xxx xxx xxx
        System.out.println(predpona + " " + cislo.replaceFirst("(\\d{3})(\\d{3})(\\d{3})", "$1 $2 $3"));

        //Zistime ci cislo je simetricke alebo asymetricke
        for(int i = 0; i < 9; i++){
            if(cislo.charAt(i) != cislo.charAt(8-i)){
                symetricke = false;
            }
        }

        //Vypiseme spravu o symetrickosti
        if(symetricke){
            System.out.println("Cislo je symetricke");
        }else{
            System.out.println("Cislo je nesymetricke");
        }
    }

    //Funkcia na nacitavanie jednotlivych ASCII znakov
    public String nacitajVstup() {
        String charakter = this.scanner.next();

        //Ak je znak validny, vraciame ho ak nie vraciame null
        if (!validujVstup(charakter)) {
            return null;
        }

        return charakter;
    }

    private boolean validujVstup(String vstup) {
        //Skontroluj ci je jeden znak
        if (!(vstup.length() == 1)){
            return false;
        }

        //Pomocou funkcie String.matches(regex), overime pomocou regex expression
        //ci je znak cislo 0-9, charakter +, whitespace charakter \\s a escape charakter q
        if(vstup.matches("[0-9+\\sq]+")){
            return true;
        }

        return false;
    }

}
