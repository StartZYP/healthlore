package com.github.qq4492004.Customloredamage.Entity;

public class TempAttribute {
    private double MaxDamage;
    private double MinDamage;
    private double Defense;
    private double CritRate;
    private double CritDouble;
    private double Health;
    private double Speed;
    private double Critfense;
    private double LifeSpawn;
    private double SuckhealthRate;
    private double SuckhealthDouble;
    private double Damagerebound;

    public TempAttribute(double maxDamage, double minDamage, double defense, double critRate, double critDouble, double health, double speed, double critfense, double lifeSpawn, double suckhealthRate, double suckhealthDouble, double damagerebound) {
        MaxDamage = maxDamage;
        MinDamage = minDamage;
        Defense = defense;
        CritRate = critRate;
        CritDouble = critDouble;
        Health = health;
        Speed = speed;
        Critfense = critfense;
        LifeSpawn = lifeSpawn;
        SuckhealthRate = suckhealthRate;
        SuckhealthDouble = suckhealthDouble;
        Damagerebound = damagerebound;
    }

    @Override
    public String toString() {
        return "TempAttribute{" +
                "MaxDamage=" + MaxDamage +
                ", MinDamage=" + MinDamage +
                ", Defense=" + Defense +
                ", CritRate=" + CritRate +
                ", CritDouble=" + CritDouble +
                ", Health=" + Health +
                ", Speed=" + Speed +
                ", Critfense=" + Critfense +
                ", LifeSpawn=" + LifeSpawn +
                ", SuckhealthRate=" + SuckhealthRate +
                ", SuckhealthDouble=" + SuckhealthDouble +
                ", Damagerebound=" + Damagerebound +
                '}';
    }

    public double getMaxDamage() {
        return MaxDamage;
    }

    public void setMaxDamage(double maxDamage) {
        MaxDamage = maxDamage;
    }

    public double getMinDamage() {
        return MinDamage;
    }

    public void setMinDamage(double minDamage) {
        MinDamage = minDamage;
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double speed) {
        Speed = speed;
    }

    public double getCritfense() {
        return Critfense;
    }

    public void setCritfense(double critfense) {
        Critfense = critfense;
    }

    public double getLifeSpawn() {
        return LifeSpawn;
    }

    public void setLifeSpawn(double lifeSpawn) {
        LifeSpawn = lifeSpawn;
    }

    public double getSuckhealthRate() {
        return SuckhealthRate;
    }

    public void setSuckhealthRate(double suckhealthRate) {
        SuckhealthRate = suckhealthRate;
    }

    public double getSuckhealthDouble() {
        return SuckhealthDouble;
    }

    public void setSuckhealthDouble(double suckhealthDouble) {
        SuckhealthDouble = suckhealthDouble;
    }

    public double getDamagerebound() {
        return Damagerebound;
    }

    public void setDamagerebound(double damagerebound) {
        Damagerebound = damagerebound;
    }


    public double getDefense() {
        return Defense;
    }

    public void setDefense(double defense) {
        Defense = defense;
    }

    public double getCritRate() {
        return CritRate;
    }

    public void setCritRate(double critRate) {
        CritRate = critRate;
    }

    public double getCritDouble() {
        return CritDouble;
    }

    public void setCritDouble(double critDouble) {
        CritDouble = critDouble;
    }

    public double getHealth() {
        return Health;
    }

    public void setHealth(double health) {
        Health = health;
    }
}
