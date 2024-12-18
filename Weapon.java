
public class Weapon
{
    private int weaponDMG; 
    private int pocetTorped; //chcem max 3 rakety pre raketomet
    private final boolean maKonecneAmmo;
    //    private int typRakety; //neimplementovane zatial
    
    /*
     * ROZDELENIE TURRET vs ROCKET LAUNCHER:
     * - atribút int pocetAmmo (pocetTorped); //DONE
     * - final atribút boolean maNekonecneAmmo;
     * - v metóde strielaj:
     *     1. logika zamerania, strelby (najlepsie vsetko v zvlast metodach a zavolat ich)
     *     2. logika spotreby ammo ALE iba (najlepsie tiez v zvlast metode a zavolat ju v if-e)
     *           if (!this.maNekonecneAmmo) { 
     *              // logika spotreby ammo
     *              // logika update charov na zbrani aby odrazali skutocnost
     *           }
     */
    
    
    public Weapon(int dmg)
    {
        this.weaponDMG = dmg;
        this.maKonecneAmmo = false;
    }

    public Weapon(int dmg,int pocetTorped){
        this.weaponDMG = dmg;
        this.pocetTorped = pocetTorped;
        this.maKonecneAmmo = true;
    }

    public int getPocetTorped(){
        return this.pocetTorped;
    }

    public void setPocietRakiet(int pridavaneTorpeda){
        switch (pocetTorped) {
            case 0:
                this.pocetTorped =  (pocetTorped + pridavaneTorpeda) % 3;
                System.out.println("Modul doplnený!");
                System.out.println("Modul status: " + this.pocetTorped + "/" + "3");
                break;

            case 1:
                this.pocetTorped =  (pocetTorped + pridavaneTorpeda) % 3;
                System.out.println("Torpedove komory v module sú pripravené!");
                System.out.println("Modul status: " + this.pocetTorped + "/" + "3");
                break;

            case 2:
                this.pocetTorped =  (pocetTorped + pridavaneTorpeda) % 3;
                System.out.println("Torpedove komory v module sú pripravené!");
                System.out.println("Modul status: " + this.pocetTorped + "/" + "3");
                break;

            case 3:
                System.out.println("Všetky torpedove komory v module sú plne pripravené!");
                System.out.println("Modul status: " + this.pocetTorped + "/" + "3");    
                break;

            default:
                System.out.println("Chyba systemov, neznama chyba! Torpeda sa nedoplnali.");    
                System.out.println("Modul status: " + this.pocetTorped + "/" + "3");    

        }
    }
}
