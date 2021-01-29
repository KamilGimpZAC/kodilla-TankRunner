package com.kodilla.TankRunner;

public enum Tanks {
    GREEN("tanks_tankGreen3.png","genericItem_color_102.png"),
    GREY("tanks_tankGrey3.png","genericItem_color_102.png"),
    DESERT("tanks_tankDesert3.png","genericItem_color_102.png");

    private String tank;
    private String life;

    private Tanks(String tank, String life){
        this.tank = tank;
        this.life = life;
    }

    public String getTank(){
        return this.tank;
    }

    public String getLife() {
        return life;
    }
}
