import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.List;
import java.io.*;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ship
{
    //ship stats
    private int shipHP; //implementovane
    private double shipEvasionChance; //implementovane
    private int totalshipDMG; //implementovane
    private int weaponRange; //no //implementation
    private int repairParts; //// no implementation
    private String [] cargoSpace = {"Empty space","Empty space","Empty space","Empty space","Empty space"};

    private ShipType shipType;
    private ArrayList<ArrayList<Character>> ship; // IBA NA VYPIS a ARMOR
    private ArrayList<Weapon> weapons;
    private ArrayList<Module> modules;

    public Ship(ShipType shipType)
    {
        //stats for playerShip
        this.shipHP = 300; // Default health
        this.shipEvasionChance = 0.2; // 20% chance to evade
        this.totalshipDMG = 0; // No damage dealt initially
        this.weaponRange = 100; //not used placeholder for future implementation
        this.repairParts = 5; // Starting with 5 repair parts

        this.ship = new ArrayList<ArrayList<Character>>();
        this.shipType = shipType;

        this.weapons = new ArrayList<>();
        this.modules = new ArrayList<>();

        this.loadPlayerShip();
        this.nacitajModulyAZbrane();
    }

    //potom odstran tento geter
    public ArrayList<ArrayList<Character>> getShip(){
        return this.ship;
    }

    public Ship(ShipType shipType,int shipHP,int shipEvasionChance)
    {
        this.ship = new ArrayList<ArrayList<Character>>();
        this.shipType = shipType;
        this.weapons = new ArrayList<>();
        this.loadPlayerShip();
        this.nacitajModulyAZbrane();
    }

    private void loadPlayerShip() {
        ArrayList<ArrayList<Character>> ship = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.shipType.getFileLocation()))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Character> row = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    row.add(c);
                }
                ship.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ship = ship;
    }

    /*
     * odstranit, test na nacitanie lode
    private void printIt(){
    for (ArrayList<Character> row : ship) {
    for (Character c : row) {
    System.out.print(c); 
    }
    System.out.println();
    }
    }*/

    public ArrayList<ArrayList<Character>> getPlayerShip(){
        return this.ship;
    }

    public void nacitajModulyAZbrane(){
        /*
         * Sluzi na nacitanie všetkých "zbraní" a "modulov"
         * Z txt templatu lodí
         * Fungovanie:
         * Skonvertuje nacitany subor z 2DArrayListu do Stringu
         * pomocou metody convertCharsToString.
         * Nasledne prejde celý String(metodou spocitajPocet) pre špecifický modul a 
         * pomocou počtou v txt suboroch vytvorí priliehajúci počet.
         * Inštacií Weapons a Modules a uloží ich do polý
         * weapons a modules.
         * 
         */
        String lod = this.convertCharsToString();
        int pocetRailGun = spocitajPocet(lod, "===@");
        int pocetPlasmaGun = spocitajPocet(lod, "==@");
        int pocetLaserGun = spocitajPocet(lod, "=@");
        int pocetRaketoveSilo = spocitajPocet(lod, "<+++>");
        int commandPostSize1 = spocitajPocet(lod,"<#>");
        int commandPostSize2 = spocitajPocet(lod,"<##>");
        int commandPostSize3 = spocitajPocet(lod,"<###>");
        int pocetT1Trysiek = spocitajPocet(lod,"#|(");
        int pocetT2Trysiek = spocitajPocet(lod, "##|(");
        int pocetT3Trysiek = spocitajPocet(lod, "###||(");

        //Zbrane
        for (int i = 0; i < pocetRailGun; i++ ){
            this.weapons.add(new Weapon(5));
        }
        for (int i = 0; i < pocetPlasmaGun; i++ ){
            this.weapons.add(new Weapon(3));
        }
        for (int i = 0; i < pocetLaserGun; i++ ){
            this.weapons.add(new Weapon(1));
        }
        for (int i = 0; i < pocetRaketoveSilo; i++ ){
            this.weapons.add(new Weapon(10, 3)); //dmg a pocetTorped max 3
        }

        //Comand post size
        for (int i = 0; i < commandPostSize1; i++ ){
            this.modules.add(new Module(40));
        }
        for (int i = 0; i < commandPostSize2; i++ ){
            this.modules.add(new Module(40));
        }
        for (int i = 0; i < commandPostSize3; i++ ){
            this.modules.add(new Module(40));
        }

        //Motori
        for (int i = 0; i < pocetT1Trysiek; i++ ){
            this.modules.add(new Module(20)); //speed 20,40,80... pretože môžem
        }
        for (int i = 0; i < pocetT2Trysiek; i++ ){
            this.modules.add(new Module(40));
        }
        for (int i = 0; i < pocetT3Trysiek; i++ ){
            this.modules.add(new Module(80));
        }

    }

    private static int spocitajPocet(String lod, String pattern) {

        /*
         * PATTERN MATCHER:
         * 1. prejdi ten input file akoby jeden String (cez nejaký InputStream -- toto si vygoogli)
         * 2. checkni či súradnica netvorí pattern
         * 3. maj x a y
         * 4. vždy keď prečíta character, tak x++;
         * 5. vždy keď prečíta '\n', tak y++ a x = 0;
         * 6. keď nájde špecifický character, tak
         *    6.1 ho uloží do lokálnej premennej (asi tiež nejaký String);
         *    6.2 keď nájde ďaľší nešpecifický charakter, tak konvertuje súradnice na Module alebo Weapon, alebo to nechá ako armor
         */

        Pattern regex = Pattern.compile(Pattern.quote(pattern));
        Matcher matcher = regex.matcher(lod);

        int pocet = 0;
        while (matcher.find()) {
            pocet++;
        }

        return pocet;
    }

    public String convertCharsToString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Character> row : this.ship) {
            for (Character ch : row) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

}
