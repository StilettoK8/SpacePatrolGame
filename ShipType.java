public enum ShipType {
    PLAYER("Imperia_playerShip.txt"),
    ENEMY_FIGHTER("enemy_fighter.txt"),
    ENEMY_CRUISER("enemy_cruiser.txt"),
    ENEMY_DESTROYER("enemy_destroyer.txt");

    private String fileLocation;

    ShipType(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return this.fileLocation;
    }
}
