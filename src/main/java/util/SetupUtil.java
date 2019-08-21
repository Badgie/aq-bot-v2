package util;

import armor.Armor;
import common.Affinity;
import common.RectDimension;
import item.Item;
import org.json.JSONObject;
import pet.Pet;
import player.*;
import shield.Shield;
import spell.Spell;
import weapon.Weapon;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetupUtil {
    public final int CONFIG_LINE_KEY = 0;
    public final int CONFIG_LINE_VAL = 1;

    public final String EPSILON = "";
    public final String POUND = "#";
    public final String EQUALS = "=";

    public final static String PROFILE = "PROFILE";
    public final static String CONFIG_USE_SPELLS = "USE_SPELLS";
    public final static String CONFIG_USE_ALLY_ASSIST = "USE_ALLY_ASSIST";
    public final static String CONFIG_DYNAMIC_WEAPONS = "DYNAMIC_WEAPONS";
    public final static String CONFIG_DYNAMIC_ARMORS = "DYNAMIC_ARMORS";
    public final static String CONFIG_DYNAMIC_PETS = "DYNAMIC_PETS";
    public final static String CONFIG_GUARDIAN = "GUARDIAN";
    public final static String CONFIG_SUBRACE = "SUBRACE";
    public final static String CONFIG_HEALTH_POT_THRESHOLD = "HEALTH_POT_THRESHOLD";
    public final static String CONFIG_MANA_POT_THRESHOLD = "MANA_POT_THRESHOLD";
    public final static String CONFIG_ARMOR = "ARMOR";
    public final static String CONFIG_ITEM = "ITEM";
    public final static String CONFIG_WEAPON = "WEAPON";
    public final static String CONFIG_SHIELD = "SHIELD";
    public final static String CONFIG_PET = "PET";

    final static String[] CONFIG_SPELL_ARRAY = {
            "SPELL_ZERO",
            "SPELL_ONE",
            "SPELL_TWO",
            "SPELL_THREE",
            "SPELL_FOUR",
            "SPELL_FIVE",
            "SPELL_SIX",
            "SPELL_SEVEN"
    };

    final static String[] CONFIG_ITEM_ARRAY = {
            "ITEM_ZERO",
            "ITEM_ONE",
            "ITEM_TWO",
            "ITEM_THREE",
            "ITEM_FOUR",
            "ITEM_FIVE",
            "ITEM_SIX",
            "ITEM_SEVEN"
    };

    final static String[] CONFIG_WEAP_ARRAY = {
            "WEAP_ZERO",
            "WEAP_ONE",
            "WEAP_TWO",
            "WEAP_THREE",
            "WEAP_FOUR",
            "WEAP_FIVE",
            "WEAP_SIX",
            "WEAP_SEVEN"
    };

    final static String[] CONFIG_SHIELD_ARRAY = {
            "SHIELD_ZERO",
            "SHIELD_ONE",
            "SHIELD_TWO",
            "SHIELD_THREE",
            "SHIELD_FOUR",
            "SHIELD_FIVE",
            "SHIELD_SIX",
            "SHIELD_SEVEN"
    };

    final static String[] CONFIG_ARMOR_ARRAY = {
            "ARMOR_ZERO",
            "ARMOR_ONE",
            "ARMOR_TWO",
            "ARMOR_THREE",
            "ARMOR_FOUR",
            "ARMOR_FIVE",
            "ARMOR_SIX",
            "ARMOR_SEVEN"
    };

    final static String[] CONFIG_PET_ARRAY = {
            "PET_ZERO",
            "PET_ONE",
            "PET_TWO",
            "PET_THREE",
            "PET_FOUR",
            "PET_FIVE",
            "PET_SIX",
            "PET_SEVEN"
    };

    /**
     * Creates player object from profile
     */
    public Player createPlayer(String path) throws IOException, AWTException, AWTException {
        JSONObject profile = getProfileAsJSON(path);
        List<Spell> spells = createSpells(profile);
        List<Item> items = createItems(profile);
        List<Weapon> weapons = createWeapons(profile);
        List<Shield> shields = createShields(profile);
        List<Armor> armors = createArmors(profile);
        List<Pet> pets = createPets(profile);
        System.out.println(armors.get(5).toString());

        Armor armor = Pattern.matches("[0-9]", profile.getString(CONFIG_ARMOR)) ?
                        armors.get(profile.getInt(CONFIG_ARMOR) - 1) : null;
        Item item = Pattern.matches("[0-9]", profile.getString(CONFIG_ITEM)) ?
                        items.get(profile.getInt(CONFIG_ITEM) - 1) : null;
        Weapon weapon = Pattern.matches("[0-9]", profile.getString(CONFIG_WEAPON)) ?
                        weapons.get(profile.getInt(CONFIG_WEAPON) - 1) : null;
        Shield shield = Pattern.matches("[0-9]", profile.getString(CONFIG_SHIELD)) ?
                        shields.get(profile.getInt(CONFIG_SHIELD) - 1) : null;
        Pet pet = Pattern.matches("[0-9]", profile.getString(CONFIG_PET)) ?
                        pets.get(profile.getInt(CONFIG_PET) - 1) : null;

        return new Player.PlayerBuilder()
                .useSpells(parseBoolean(profile.getString(CONFIG_USE_SPELLS)))
                .useAllyAssist(parseBoolean(profile.getString(CONFIG_USE_ALLY_ASSIST)))
                .dynamicWeapons(parseBoolean(profile.getString(CONFIG_DYNAMIC_WEAPONS)))
                .dynamicPets(parseBoolean(profile.getString(CONFIG_DYNAMIC_PETS)))
                .guardian(parseBoolean(profile.getString(CONFIG_GUARDIAN)))
                .subrace(parseBoolean(profile.getString(CONFIG_SUBRACE)))
                .healthPotThreshold(profile.getInt(CONFIG_HEALTH_POT_THRESHOLD))
                .manaPotThreshold(profile.getInt(CONFIG_MANA_POT_THRESHOLD))
                .armor(armor)
                .item(item)
                .weapon(weapon)
                .shield(shield)
                .pet(pet)
                .spells(spells)
                .items(items)
                .weapons(weapons)
                .shields(shields)
                .armors(armors)
                .pets(pets)
                .build();
    }

    public JSONObject getProfileAsJSON(String path) throws IOException {
        String fileContents = new String(Files.readAllBytes(Paths.get(Util.getUsrDir() + "/profile/" + path)));
        Map<String, String> profileMap = parseProfileFile(fileContents);
        return new JSONObject(profileMap);
    }

    /**
     * Create player helpers
     */
    private Map<String, String> parseProfileFile(String fileContents) {
        Map<String, String> profileMap = new HashMap<>();
        String[] fileArray = fileContents.split("\n");
        String[] temp;
        for (String s : fileArray) {
            if (!(s.equals(EPSILON) || s.contains(POUND))) {
                temp = s.split(EQUALS);
                if (temp.length == 1) {
                    profileMap.put(temp[CONFIG_LINE_KEY], EPSILON);
                } else {
                    profileMap.put(temp[CONFIG_LINE_KEY], temp[CONFIG_LINE_VAL]);
                }
            }
        }
        return profileMap;
    }

    public List<Spell> createSpells(JSONObject obj) {
        List<Spell> spells = new ArrayList<>();
        String config;
        String[] configSplit;
        for (int i = 0; i < CONFIG_SPELL_ARRAY.length; i++) {
            config = obj.getString(CONFIG_SPELL_ARRAY[i]);
            if (!config.equals(EPSILON)) {
                configSplit = config.split(";");
                spells.add(Spell.create(
                        configSplit[0],
                        new Affinity(configSplit[1])
                ));
            } else {
                spells.add(Spell.create(
                        " ",
                        new Affinity(" ")
                ));
            }
        }
        return spells;
    }

    public List<Item> createItems(JSONObject obj) {
        List<Item> items = new ArrayList<>();
        String config;
        for (int i = 0; i < CONFIG_ITEM_ARRAY.length; i++) {
            config = obj.getString(CONFIG_ITEM_ARRAY[i]);
            if (!config.equals(EPSILON)) {
                items.add(Item.create(config));
            } else {
                items.add(Item.create(" "));
            }
        }

        return items;
    }

    public List<Weapon> createWeapons(JSONObject obj) {
        List<Weapon> weapons = new ArrayList<>();
        String config;
        String[] configSplit;
        for (int i = 0; i < CONFIG_WEAP_ARRAY.length; i++) {
            config = obj.getString(CONFIG_WEAP_ARRAY[i]);
            List<Affinity> affinities = new ArrayList<>();
            if (!config.equals(EPSILON)) {
                configSplit = config.split(";");

                for (int j = 1; j < configSplit.length; j++) {
                    if (configSplit[j] != null && !configSplit[j].equals("")) {
                        affinities.add(new Affinity(configSplit[j]));
                    } else {
                        affinities.add(new Affinity(" "));
                    }
                }

                weapons.add(Weapon.create(
                        configSplit[0],
                        affinities
                ));
            } else {
                affinities.add(new Affinity(" "));
                affinities.add(new Affinity(" "));
                weapons.add(Weapon.create(
                        " ",
                        affinities
                ));
            }
        }
        return weapons;
    }

    public List<Shield> createShields(JSONObject obj) {
        List<Shield> shields = new ArrayList<>();
        String config;
        for (int i = 0; i < CONFIG_SHIELD_ARRAY.length; i++) {
            config = obj.getString(CONFIG_SHIELD_ARRAY[i]);
            if (!config.equals(EPSILON)) {
                shields.add(Shield.create(config));
            } else {
                shields.add(Shield.create(" "));
            }
        }
        return shields;
    }

    public List<Armor> createArmors(JSONObject obj) throws IOException, AWTException {
        List<Armor> armors = new ArrayList<>();
        String config;
        for (int i = 0; i < CONFIG_ARMOR_ARRAY.length; i++) {
            config = obj.getString(CONFIG_ARMOR_ARRAY[i]);
            if (!config.equals(EPSILON)) {
                armors.add(Armor.create(config));
            } else {
                armors.add(Armor.create(" "));
            }
        }
        return armors;
    }

    public List<Pet> createPets(JSONObject obj) {
        List<Pet> pets = new ArrayList<>();
        String config;
        String[] configSplit;
        for (int i = 0; i < CONFIG_PET_ARRAY.length; i++) {
            config = obj.getString(CONFIG_PET_ARRAY[i]);
            if (!config.equals(EPSILON)) {
                configSplit = config.split(";");
                pets.add(Pet.create(
                        configSplit[0],
                        new Affinity(configSplit[1])
                ));
            } else {
                pets.add(Pet.create(" ", new Affinity(" ")));
            }
        }
        return pets;
    }

    public boolean parseBoolean(String s) {
        if (s.equals("1")) {
            return true;
        } else {
            return false;
        }
    }
}
