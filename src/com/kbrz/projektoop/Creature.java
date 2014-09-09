package com.kbrz.projektoop;


public abstract class Creature
{
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int maxMana;
    protected int mana;
    protected int attack;
    protected int defence;
    protected int speed;
    protected Item equipedWeapon = null;
    protected Item equipedShield = null;

    //--------------------------------------------------------

    public String name() { return name; }

    public int maxHealth() { return maxHealth; }

    public int health() { return health; }

    public int maxMana() { return maxMana; }

    public int mana() { return mana; }

    public int attack() { return attack; }

    public int defence() { return defence; }

    public int speed() { return speed; }

    public Item EquipedWeapon() { return equipedWeapon; }

    public Item EquipedShield() { return equipedShield; }

    //--------------------------------------------------------

    public int performAttack(Creature target)
    {
        int damage = this.attack() - target.defence();
        damage += Chance.generator.nextInt(damage/3) - damage/3;
        return damage;
    }
    public int performDefence()
    {
        return this.defence() * 2;
    }

    public boolean useItem(Item usedItem)
    {
        usedItem.makeEffect(this);
        return true;
    }

    public void changeWeapon(String newWeapon)
    {
        if(equipedWeapon == null)
        {
            equipedWeapon = new Item("Weapon", newWeapon);
            attack += equipedWeapon.points();
            return;
        }

        attack -= equipedWeapon.points();
        equipedWeapon = new Item("Weapon", newWeapon);
        attack += equipedWeapon.points();
    }

    public void changeShield(String newShield)
    {
        if(equipedShield== null)
        {
            equipedShield = new Item("Shield", newShield);
            defence += equipedShield.points();
            return;
        }

        defence -= equipedShield.points();
        equipedShield = new Item("Shield", newShield);
        defence += equipedShield.points();
    }

    public void restoreHealth(int restoreAmount)
    {
        health += restoreAmount;
        if(health > maxHealth)
            health = maxHealth;
    }

    public void restoreMana(int restoreAmount)
    {
        mana += restoreAmount;
        if(mana > maxMana)
            mana = maxMana;
    }

    public String info()
    {
        String infoS = "";
        infoS += "Imie: " + name + "\n";
        infoS += "HP: " + health + "/" + maxHealth + ", ";
        infoS += "MP: " + mana + "/" + maxMana + "\n";
        infoS += "Atak: " + attack + ", Obrona: " + defence + "\n";
        return infoS;
    }
}