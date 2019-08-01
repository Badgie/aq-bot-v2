package player;

import java.util.List;

public class Player {
    private int allyAssists;
    private int healthPots;
    private int manaPots;
    private int healthPotThreshold;
    private int manaPotThreshold;

    private boolean useSpells;
    private boolean useAllyAssist;
    private boolean dynamicWeapons;
    private boolean dynamicArmors;
    private boolean dynamicPets;

    private Armor armor;
    private Item item;
    private Weapon weapon;
    private Shield shield;
    private Pet pet;

    private List<Spell> spells;
    private List<Item> items;
    private List<Weapon> weapons;
    private List<Shield> shields;
    private List<Armor> armors;
    private List<Pet> pets;

    public static class PlayerBuilder {
        private int allyAssists;
        private int healthPots;
        private int manaPots;
        private int healthPotThreshold;
        private int manaPotThreshold;

        private boolean useSpells;
        private boolean useAllyAssist;
        private boolean dynamicWeapons;
        private boolean dynamicArmors;
        private boolean dynamicPets;

        private Armor armor;
        private Item item;
        private Weapon weapon;
        private Shield shield;
        private Pet pet;

        private List<Spell> spells;
        private List<Item> items;
        private List<Weapon> weapons;
        private List<Shield> shields;
        private List<Armor> armors;
        private List<Pet> pets;

        public PlayerBuilder() {
            this.allyAssists = 0;
            this.healthPots = 0;
            this.manaPots = 0;
            this.healthPotThreshold = 0;
            this.manaPotThreshold = 0;
        }

        public PlayerBuilder useSpells(boolean useSpells) {
            this.useSpells = useSpells;
            return this;
        }

        public PlayerBuilder useAllyAssist(boolean useAllyAssist) {
            this.useAllyAssist = useAllyAssist;
            return this;
        }

        public PlayerBuilder dynamicWeapons(boolean dynamicWeapons) {
            this.dynamicWeapons = dynamicWeapons;
            return this;
        }

        public PlayerBuilder dynamicArmors(boolean dynamicArmors) {
            this.dynamicArmors = dynamicArmors;
            return this;
        }

        public PlayerBuilder dynamicPets(boolean dynamicPets) {
            this.dynamicPets = dynamicPets;
            return this;
        }

        public PlayerBuilder armor(Armor armor) {
            this.armor = armor;
            return this;
        }

        public PlayerBuilder item(Item item) {
            this.item = item;
            return this;
        }

        public PlayerBuilder weapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }

        public PlayerBuilder shield(Shield shield) {
            this.shield = shield;
            return this;
        }

        public PlayerBuilder pet(Pet pet) {
            this.pet = pet;
            return this;
        }

        public PlayerBuilder spells(List<Spell> spells) {
            this.spells = spells;
            return this;
        }

        public PlayerBuilder items(List<Item> items) {
            this.items = items;
            return this;
        }

        public PlayerBuilder weapons(List<Weapon> weapons) {
            this.weapons = weapons;
            return this;
        }

        public PlayerBuilder shields(List<Shield> shields) {
            this.shields = shields;
            return this;
        }

        public PlayerBuilder armors(List<Armor> armors) {
            this.armors = armors;
            return this;
        }

        public PlayerBuilder pets(List<Pet> pets) {
            this.pets = pets;
            return this;
        }

        public Player build() {
            return new Player(this);
        }

    }

    public Player(PlayerBuilder pb) {
        this.allyAssists = pb.allyAssists;
        this.healthPots = pb.healthPots;
        this.manaPots = pb.manaPots;
        this.healthPotThreshold = pb.healthPotThreshold;
        this.manaPotThreshold = pb.manaPotThreshold;
        this.useSpells = pb.useSpells;
        this.useAllyAssist = pb.useAllyAssist;
        this.dynamicWeapons = pb.dynamicWeapons;
        this.dynamicArmors = pb.dynamicArmors;
        this.dynamicPets = pb.dynamicPets;
        this.armor = pb.armor;
        this.item = pb.item;
        this.weapon = pb.weapon;
        this.shield = pb.shield;
        this.pet = pb.pet;
        this.spells = pb.spells;
        this.items = pb.items;
        this.weapons = pb.weapons;
        this.shields = pb.shields;
        this.armors = pb.armors;
        this.pets = pb.pets;
    }

    public int getAllyAssists() {
        return allyAssists;
    }

    public int getHealthPots() {
        return healthPots;
    }

    public int getManaPots() {
        return manaPots;
    }

    public int getHealthPotThreshold() {
        return healthPotThreshold;
    }

    public int getManaPotThreshold() {
        return manaPotThreshold;
    }

    public boolean isUseSpells() {
        return useSpells;
    }

    public boolean isUseAllyAssist() {
        return useAllyAssist;
    }

    public boolean isDynamicWeapons() {
        return dynamicWeapons;
    }

    public boolean isDynamicArmors() {
        return dynamicArmors;
    }

    public boolean isDynamicPets() {
        return dynamicPets;
    }

    public Armor getArmor() {
        return armor;
    }

    public Item getItem() {
        return item;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Shield getShield() {
        return shield;
    }

    public Pet getPet() {
        return pet;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Shield> getShields() {
        return shields;
    }

    public List<Armor> getArmors() {
        return armors;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setAllyAssists(int allyAssists) {
        this.allyAssists = allyAssists;
    }

    public void setHealthPots(int healthPots) {
        this.healthPots = healthPots;
    }

    public void setManaPots(int manaPots) {
        this.manaPots = manaPots;
    }

    public void setHealthPotThreshold(int healthPotThreshold) {
        this.healthPotThreshold = healthPotThreshold;
    }

    public void setManaPotThreshold(int manaPotThreshold) {
        this.manaPotThreshold = manaPotThreshold;
    }

    public void setUseSpells(boolean useSpells) {
        this.useSpells = useSpells;
    }

    public void setUseAllyAssist(boolean useAllyAssist) {
        this.useAllyAssist = useAllyAssist;
    }

    public void setDynamicWeapons(boolean dynamicWeapons) {
        this.dynamicWeapons = dynamicWeapons;
    }

    public void setDynamicArmors(boolean dynamicArmors) {
        this.dynamicArmors = dynamicArmors;
    }

    public void setDynamicPets(boolean dynamicPets) {
        this.dynamicPets = dynamicPets;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setShields(List<Shield> shields) {
        this.shields = shields;
    }

    public void setArmors(List<Armor> armors) {
        this.armors = armors;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
