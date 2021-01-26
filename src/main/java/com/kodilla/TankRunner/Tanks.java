package com.kodilla.TankRunner;

public enum Tanks {
    GREEN("file:src/main/resources/tanks_tankGreen3.png"),
    GREY("file:src/main/resources/tanks_tankGrey3.png"),
    DESERT("file:src/main/resources/tanks_tankDesert3.png");

    private String tank;

    private Tanks(String tank){
        this.tank = tank;
    }

    public String getTank(){
        return this.tank;
    }
}
