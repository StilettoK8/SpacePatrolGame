
public class Module
{
    int repairTimeLeft; //cas do konca opravi normalne nastaveny na 0
    int repairTime;
    int speed;
    
    // chcel by som tu dat este x y relativnu poziciu na lodi ako identifikator
    
    /*
     * Trafi napr. zbran:
     * 1. trafi napr. symbol "="
     * 2. moze byt armor alebo turret
     * 3. hlada smerom doprava (+x)
     *    3.1 ak najde "=", hlada dalej smerom doprava (+x)
     *    3.2 ak najde "@", vie ze trafil zbran -> posun sa na krok 4
     *    3.3 ak najde nieco ine, vie ze trafil armor -> spracuj
     * 4. nasiel zbran. Ide na povodne miesto. Prejde array list zbrani:
     *      while (this.ship.getChar(trafeneX, trafeneY) == '=' {
     *          for (Weapon w : this.weapons) { // tento loop v triede Ship
     *              int wX = w.getX();
     *              int wY = w.getY();
     *              if (wX == trafeneX && wY == trafeneY) {
     *                  // tu vykona trafenie zbrane
     *              }
     *          } // ak tam nie je ziadna zbran, posun dolava a hladaj znova
     *          trafeneX--;
     *      }
     */
    
    public Module(int speed)
    {
    //motori konstruktor
    this.repairTimeLeft = 0;
    int repairTime = 2;
    this.speed = speed;
    }
    
    public Module(){
    //command post konstruktor
    this.repairTimeLeft = 0; //na 0 modul funkcny
    this.repairTime = 3; // cas na nastavenie opravi modulu
    }
    
    public int repairTimeLeft(){
    return this.repairTimeLeft;
    }
    
    public void setRepairTimeLeft(int urichlenieOpravi){
    this.repairTimeLeft = repairTimeLeft - urichlenieOpravi;
    }
}
