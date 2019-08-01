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
    private List<Skill> skills;
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
        private List<Skill> skills;
        private List<Item> items;
        private List<Weapon> weapons;
        private List<Shield> shields;
        private List<Armor> armors;
        private List<Pet> pets;

        public PlayerBuilder() {
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

        public PlayerBuilder skills(List<Skill> skills) {
            this.skills = skills;
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

    }
}
