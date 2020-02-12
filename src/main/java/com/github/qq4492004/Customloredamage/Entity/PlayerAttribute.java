package com.github.qq4492004.Customloredamage.Entity;

public class PlayerAttribute {
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

    private TempAttribute tempAttribute;

    public  PlayerAttribute GetAllAttr(){
        if (this.tempAttribute == null) {
            return this;
        } else {
            PlayerAttribute playerAttribute =new PlayerAttribute(this.MaxDamage,this.MinDamage,this.Defense,this.CritRate,this.CritDouble,this.Health,this.Speed,this.Critfense,this.LifeSpawn,this.SuckhealthRate,this.SuckhealthDouble,this.Damagerebound);
            playerAttribute.MaxDamage = this.tempAttribute.getMaxDamage() + this.MaxDamage;
            playerAttribute.MinDamage = this.tempAttribute.getMinDamage() + this.MinDamage;
            playerAttribute.Defense = this.tempAttribute.getDefense() + this.Defense;
            playerAttribute.CritRate = this.tempAttribute.getCritRate() + this.CritRate;
            playerAttribute.CritDouble = this.tempAttribute.getCritDouble() + this.CritDouble;
            playerAttribute.Health = this.tempAttribute.getHealth() + this.Health;
            playerAttribute.Speed = this.tempAttribute.getSpeed() + this.Speed;
            playerAttribute.Critfense = this.tempAttribute.getCritfense() + this.Critfense;
            playerAttribute.LifeSpawn = this.tempAttribute.getLifeSpawn() + this.LifeSpawn;
            playerAttribute.SuckhealthRate = this.tempAttribute.getSuckhealthRate() + this.SuckhealthRate;
            playerAttribute.SuckhealthDouble = this.tempAttribute.getSuckhealthDouble() + this.SuckhealthDouble;
            playerAttribute.Damagerebound = this.tempAttribute.getDamagerebound() + this.Damagerebound;
            return playerAttribute;
        }
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

    public TempAttribute getTempAttribute() {
        return tempAttribute;
    }

    public void setTempAttribute(TempAttribute tempAttribute) {
        this.tempAttribute = tempAttribute;
    }

    public PlayerAttribute(double maxDamage, double minDamage, double defense, double critRate, double critDouble, double health, double speed, double critfense, double lifeSpawn, double suckhealthRate, double suckhealthDouble, double damagerebound) {
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

}
