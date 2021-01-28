package com.kodilla.TankRunner;

public enum Tanks {
    GREEN("tanks_tankGreen3.png"),
    GREY("tanks_tankGrey3.png"),
    DESERT("tanks_tankDesert3.png");

    private String tank;

    private Tanks(String tank){
        this.tank = tank;
    }

    public String getTank(){
        return this.tank;
    }
}
