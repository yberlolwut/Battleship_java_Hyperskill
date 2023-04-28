package battleship;

class Field {
    char displayValue;
    boolean isShip;
    boolean isHit;
    boolean isMiss;
    boolean isValid;

    String name;


    public Field() {
        this.displayValue = '~';
        this.isShip = false;
        this.isHit = false;
        this.isMiss = false;
        this.isValid = true;
    }

    public Field(char letter) {
        this.displayValue = letter;
        this.isValid = false;
    }

    public void updateField() {
        if (this.isHit) {
            this.displayValue = 'X';
        } else if (this.isShip) {
            this.displayValue = 'O';
        } else if (this.isMiss) {
            this.displayValue = 'M';
        }
    }

    public void updateHitMiss() {
        if (this.isHit) {
            this.displayValue = 'X';
        } else if (this.isMiss) {
            this.displayValue = 'M';
        }
    }
}