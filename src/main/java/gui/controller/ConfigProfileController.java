package gui.controller;

import armor.Armor;
import item.Item;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import pet.Pet;
import shield.Shield;
import spell.Spell;
import util.GUIUtil;
import util.SetupUtil;
import util.Util;
import weapon.Weapon;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConfigProfileController implements Initializable {
    public TextField PROFILE_NAME;
    public Button SAVE_BUTTON;
    public Button CANCEL_BUTTON;
    
    public TextField MANA_POT_THRESHOLD;
    public TextField HEALTH_POT_THRESHOLD;
    
    public CheckBox USE_SPELLS;
    public CheckBox USE_ALLY_ASSIST;
    public CheckBox DYNAMIC_WEAPONS;
    public CheckBox DYNAMIC_PETS;
    public CheckBox IS_GUARDIAN;
    public CheckBox HAS_SUBRACE;

    public ChoiceBox<String> PET;
    public ChoiceBox<String> ITEM;
    public ChoiceBox<String> SHIELD;
    public ChoiceBox<String> ARMOR;
    public ChoiceBox<String> WEAPON;

    public TextField SPELL_ZERO_NAME;
    public ChoiceBox<String> SPELL_ZERO_TYPE;
    public TextField SPELL_ONE_NAME;
    public ChoiceBox<String> SPELL_ONE_TYPE;
    public TextField SPELL_TWO_NAME;
    public ChoiceBox<String> SPELL_TWO_TYPE;
    public TextField SPELL_THREE_NAME;
    public ChoiceBox<String> SPELL_THREE_TYPE;
    public TextField SPELL_FOUR_NAME;
    public ChoiceBox<String> SPELL_FOUR_TYPE;
    public TextField SPELL_FIVE_NAME;
    public ChoiceBox<String> SPELL_FIVE_TYPE;
    public TextField SPELL_SIX_NAME;
    public ChoiceBox<String> SPELL_SIX_TYPE;
    public TextField SPELL_SEVEN_NAME;
    public ChoiceBox<String> SPELL_SEVEN_TYPE;

    public TextField ITEM_ZERO_NAME;
    public TextField ITEM_ONE_NAME;
    public TextField ITEM_TWO_NAME;
    public TextField ITEM_THREE_NAME;
    public TextField ITEM_FOUR_NAME;
    public TextField ITEM_FIVE_NAME;
    public TextField ITEM_SIX_NAME;
    public TextField ITEM_SEVEN_NAME;

    public TextField WEAP_ZERO_NAME;
    public ChoiceBox<String> WEAP_ZERO_TYPE1;
    public ChoiceBox<String> WEAP_ZERO_TYPE2;
    public TextField WEAP_ONE_NAME;
    public ChoiceBox<String> WEAP_ONE_TYPE1;
    public ChoiceBox<String> WEAP_ONE_TYPE2;
    public TextField WEAP_TWO_NAME;
    public ChoiceBox<String> WEAP_TWO_TYPE1;
    public ChoiceBox<String> WEAP_TWO_TYPE2;
    public TextField WEAP_THREE_NAME;
    public ChoiceBox<String> WEAP_THREE_TYPE1;
    public ChoiceBox<String> WEAP_THREE_TYPE2;
    public TextField WEAP_FOUR_NAME;
    public ChoiceBox<String> WEAP_FOUR_TYPE1;
    public ChoiceBox<String> WEAP_FOUR_TYPE2;
    public TextField WEAP_FIVE_NAME;
    public ChoiceBox<String> WEAP_FIVE_TYPE1;
    public ChoiceBox<String> WEAP_FIVE_TYPE2;
    public TextField WEAP_SIX_NAME;
    public ChoiceBox<String> WEAP_SIX_TYPE1;
    public ChoiceBox<String> WEAP_SIX_TYPE2;
    public TextField WEAP_SEVEN_NAME;
    public ChoiceBox<String> WEAP_SEVEN_TYPE1;
    public ChoiceBox<String> WEAP_SEVEN_TYPE2;

    public TextField SHIELD_ZERO_NAME;
    public TextField SHIELD_ONE_NAME;
    public TextField SHIELD_TWO_NAME;
    public TextField SHIELD_THREE_NAME;
    public TextField SHIELD_FOUR_NAME;
    public TextField SHIELD_FIVE_NAME;
    public TextField SHIELD_SIX_NAME;
    public TextField SHIELD_SEVEN_NAME;

    public TextField ARMOR_ZERO_NAME;
    public TextField ARMOR_ONE_NAME;
    public TextField ARMOR_TWO_NAME;
    public TextField ARMOR_THREE_NAME;
    public TextField ARMOR_FOUR_NAME;
    public TextField ARMOR_FIVE_NAME;
    public TextField ARMOR_SIX_NAME;
    public TextField ARMOR_SEVEN_NAME;

    public TextField PET_ZERO_NAME;
    public ChoiceBox<String> PET_ZERO_TYPE;
    public TextField PET_ONE_NAME;
    public ChoiceBox<String> PET_ONE_TYPE;
    public TextField PET_TWO_NAME;
    public ChoiceBox<String> PET_TWO_TYPE;
    public TextField PET_THREE_NAME;
    public ChoiceBox<String> PET_THREE_TYPE;
    public TextField PET_FOUR_NAME;
    public ChoiceBox<String> PET_FOUR_TYPE;
    public TextField PET_FIVE_NAME;
    public ChoiceBox<String> PET_FIVE_TYPE;
    public TextField PET_SIX_NAME;
    public ChoiceBox<String> PET_SIX_TYPE;
    public TextField PET_SEVEN_NAME;
    public ChoiceBox<String> PET_SEVEN_TYPE;
    public ScrollPane SCROLL_PANE;

    private String[] EQUIP_INDEX = {" ", "1", "2", "3", "4", "5", "6", "7", "8"};
    private String[] PET_INDEX = {" ", "Hide", "1", "2", "3", "4", "5", "6", "7", "8"};
    private String[] SHIELD_ITEM_INDEX = {" ", "None", "1", "2", "3", "4", "5", "6", "7", "8"};

    private String[] SPELL_AFFINITY_INDEX = {" ", "Fire", "Water", "Wind", "Ice", "Earth", "Energy", "Light", "Darkness",
            "Void", "Heal", "Summon"};
    private String[] EQUIP_AFFINITY_INDEX = {" ", "Fire", "Water", "Wind", "Ice", "Earth", "Energy", "Light", "Darkness",
            "Void"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setButtons();
            choiceBoxes();
            SCROLL_PANE.setFitToWidth(true);
            if (!IndexController.IS_NEW_PROFILE) {
                loadProfile();
            }
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    private void setButtons() throws IOException {
        save();
        cancel();
    }

    private void save() throws IOException {
        SAVE_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String profileFileName = PROFILE_NAME.getText().toLowerCase().replace(" ", "-");
                try {
                    FileWriter writer = new FileWriter(new File(Util.getUsrDir() + "/profile/" + profileFileName));
                    StringBuilder builder = new StringBuilder();
                    builder.append("##########################################\n")
                            .append("#######          General           #######\n")
                            .append("##########################################\n")
                            .append("PROFILE=")
                            .append(parseString(PROFILE_NAME.getText())).append("\n\n")
                            .append("USE_SPELLS=")
                            .append(parseBool(USE_SPELLS.isSelected())).append("\n")
                            .append("USE_ALLY_ASSIST=")
                            .append(parseBool(USE_ALLY_ASSIST.isSelected())).append("\n")
                            .append("DYNAMIC_WEAPONS=")
                            .append(parseBool(DYNAMIC_WEAPONS.isSelected())).append("\n")
                            .append("DYNAMIC_PETS=")
                            .append(parseBool(DYNAMIC_PETS.isSelected())).append("\n")
                            .append("GUARDIAN=")
                            .append(parseBool(IS_GUARDIAN.isSelected())).append("\n")
                            .append("SUBRACE=")
                            .append(parseBool(HAS_SUBRACE.isSelected())).append("\n\n")
                            .append("HEALTH_POT_THRESHOLD=")
                            .append(parseString(HEALTH_POT_THRESHOLD.getText())).append("\n")
                            .append("MANA_POT_THRESHOLD=")
                            .append(parseString(MANA_POT_THRESHOLD.getText())).append("\n\n")
                            .append("ARMOR=")
                            .append(ARMOR.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("ITEM=")
                            .append(ITEM.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("WEAPON=")
                            .append(WEAPON.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("SHIELD=")
                            .append(SHIELD.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("PET=")
                            .append(PET.getSelectionModel().getSelectedItem().toString()).append("\n\n")
                            .append("##########################################\n")
                            .append("#######           Spells           #######\n")
                            .append("##########################################\n")
                            .append("SPELL_ZERO=")
                            .append(parseString(SPELL_ZERO_NAME.getText())).append(";")
                            .append(SPELL_ZERO_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("SPELL_ONE=")
                            .append(parseString(SPELL_ONE_NAME.getText())).append(";")
                            .append(SPELL_ONE_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("SPELL_TWO=")
                            .append(parseString(SPELL_TWO_NAME.getText())).append(";")
                            .append(SPELL_TWO_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("SPELL_THREE=")
                            .append(parseString(SPELL_THREE_NAME.getText())).append(";")
                            .append(SPELL_THREE_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("SPELL_FOUR=")
                            .append(parseString(SPELL_FOUR_NAME.getText())).append(";")
                            .append(SPELL_FOUR_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("SPELL_FIVE=")
                            .append(parseString(SPELL_FIVE_NAME.getText())).append(";")
                            .append(SPELL_FIVE_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("SPELL_SIX=")
                            .append(parseString(SPELL_SIX_NAME.getText())).append(";")
                            .append(SPELL_SIX_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("SPELL_SEVEN=")
                            .append(parseString(SPELL_SEVEN_NAME.getText())).append(";")
                            .append(SPELL_SEVEN_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n\n")
                            .append("##########################################\n")
                            .append("#######           Items            #######\n")
                            .append("##########################################\n")
                            .append("ITEM_ZERO=")
                            .append(parseString(ITEM_ZERO_NAME.getText())).append("\n")
                            .append("ITEM_ONE=")
                            .append(parseString(ITEM_ONE_NAME.getText())).append("\n")
                            .append("ITEM_TWO=")
                            .append(parseString(ITEM_TWO_NAME.getText())).append("\n")
                            .append("ITEM_THREE=")
                            .append(parseString(ITEM_THREE_NAME.getText())).append("\n")
                            .append("ITEM_FOUR=")
                            .append(parseString(ITEM_FOUR_NAME.getText())).append("\n")
                            .append("ITEM_FIVE=")
                            .append(parseString(ITEM_FIVE_NAME.getText())).append("\n")
                            .append("ITEM_SIX=")
                            .append(parseString(ITEM_SIX_NAME.getText())).append("\n")
                            .append("ITEM_SEVEN=")
                            .append(parseString(ITEM_SEVEN_NAME.getText())).append("\n\n")
                            .append("##########################################\n")
                            .append("#######          Weapons           #######\n")
                            .append("##########################################\n")
                            .append("WEAP_ZERO=")
                            .append(parseString(WEAP_ZERO_NAME.getText())).append(";")
                            .append(WEAP_ZERO_TYPE1.getSelectionModel().getSelectedItem().toString()).append(";")
                            .append(WEAP_ZERO_TYPE2.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("WEAP_ONE=")
                            .append(parseString(WEAP_ONE_NAME.getText())).append(";")
                            .append(WEAP_ONE_TYPE1.getSelectionModel().getSelectedItem().toString()).append(";")
                            .append(WEAP_ONE_TYPE2.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("WEAP_TWO=")
                            .append(parseString(WEAP_TWO_NAME.getText())).append(";")
                            .append(WEAP_TWO_TYPE1.getSelectionModel().getSelectedItem().toString()).append(";")
                            .append(WEAP_TWO_TYPE2.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("WEAP_THREE=")
                            .append(parseString(WEAP_THREE_NAME.getText())).append(";")
                            .append(WEAP_THREE_TYPE1.getSelectionModel().getSelectedItem().toString()).append(";")
                            .append(WEAP_THREE_TYPE2.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("WEAP_FOUR=")
                            .append(parseString(WEAP_FOUR_NAME.getText())).append(";")
                            .append(WEAP_FOUR_TYPE1.getSelectionModel().getSelectedItem().toString()).append(";")
                            .append(WEAP_FOUR_TYPE2.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("WEAP_FIVE=")
                            .append(parseString(WEAP_FIVE_NAME.getText())).append(";")
                            .append(WEAP_FIVE_TYPE1.getSelectionModel().getSelectedItem().toString()).append(";")
                            .append(WEAP_FIVE_TYPE2.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("WEAP_SIX=")
                            .append(parseString(WEAP_SIX_NAME.getText())).append(";")
                            .append(WEAP_SIX_TYPE1.getSelectionModel().getSelectedItem().toString()).append(";")
                            .append(WEAP_SIX_TYPE2.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("WEAP_SEVEN=")
                            .append(parseString(WEAP_SEVEN_NAME.getText())).append(";")
                            .append(WEAP_SEVEN_TYPE1.getSelectionModel().getSelectedItem().toString()).append(";")
                            .append(WEAP_SEVEN_TYPE2.getSelectionModel().getSelectedItem().toString()).append("\n\n")
                            .append("##########################################\n")
                            .append("#######           Shield           #######\n")
                            .append("##########################################\n")
                            .append("SHIELD_ZERO=")
                            .append(parseString(SHIELD_ZERO_NAME.getText())).append("\n")
                            .append("SHIELD_ONE=")
                            .append(parseString(SHIELD_ONE_NAME.getText())).append("\n")
                            .append("SHIELD_TWO=")
                            .append(parseString(SHIELD_TWO_NAME.getText())).append("\n")
                            .append("SHIELD_THREE=")
                            .append(parseString(SHIELD_THREE_NAME.getText())).append("\n")
                            .append("SHIELD_FOUR=")
                            .append(parseString(SHIELD_FOUR_NAME.getText())).append("\n")
                            .append("SHIELD_FIVE=")
                            .append(parseString(SHIELD_FIVE_NAME.getText())).append("\n")
                            .append("SHIELD_SIX=")
                            .append(parseString(SHIELD_SIX_NAME.getText())).append("\n")
                            .append("SHIELD_SEVEN=")
                            .append(parseString(SHIELD_SEVEN_NAME.getText())).append("\n\n")
                            .append("##########################################\n")
                            .append("#######           Armors           #######\n")
                            .append("##########################################\n")
                            .append("ARMOR_ZERO=")
                            .append(parseString(ARMOR_ZERO_NAME.getText())).append("\n")
                            .append("ARMOR_ONE=")
                            .append(parseString(ARMOR_ONE_NAME.getText())).append("\n")
                            .append("ARMOR_TWO=")
                            .append(parseString(ARMOR_TWO_NAME.getText())).append("\n")
                            .append("ARMOR_THREE=")
                            .append(parseString(ARMOR_THREE_NAME.getText())).append("\n")
                            .append("ARMOR_FOUR=")
                            .append(parseString(ARMOR_FOUR_NAME.getText())).append("\n")
                            .append("ARMOR_FIVE=")
                            .append(parseString(ARMOR_FIVE_NAME.getText())).append("\n")
                            .append("ARMOR_SIX=")
                            .append(parseString(ARMOR_SIX_NAME.getText())).append("\n")
                            .append("ARMOR_SEVEN=")
                            .append(parseString(ARMOR_SEVEN_NAME.getText())).append("\n\n")
                            .append("##########################################\n")
                            .append("#######            Pets            #######\n")
                            .append("##########################################\n")
                            .append("PET_ZERO=")
                            .append(parseString(PET_ZERO_NAME.getText())).append(";")
                            .append(PET_ZERO_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("PET_ONE=")
                            .append(parseString(PET_ONE_NAME.getText())).append(";")
                            .append(PET_ONE_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("PET_TWO=")
                            .append(parseString(PET_TWO_NAME.getText())).append(";")
                            .append(PET_TWO_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("PET_THREE=")
                            .append(parseString(PET_THREE_NAME.getText())).append(";")
                            .append(PET_THREE_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("PET_FOUR=")
                            .append(parseString(PET_FOUR_NAME.getText())).append(";")
                            .append(PET_FOUR_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("PET_FIVE=")
                            .append(parseString(PET_FIVE_NAME.getText())).append(";")
                            .append(PET_FIVE_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("PET_SIX=")
                            .append(parseString(PET_SIX_NAME.getText())).append(";")
                            .append(PET_SIX_TYPE.getSelectionModel().getSelectedItem().toString()).append("\n")
                            .append("PET_SEVEN=")
                            .append(parseString(PET_SEVEN_NAME.getText())).append(";")
                            .append(PET_SEVEN_TYPE.getSelectionModel().getSelectedItem().toString());
                    writer.write(builder.toString());
                    writer.close();
                    SAVE_BUTTON.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GUIUtil.getIndexController().updateProfileBox();
            }
        });
    }

    private void cancel() {
        CANCEL_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CANCEL_BUTTON.getScene().getWindow().hide();
            }
        });
    }

    private void choiceBoxes() {
        PET.setItems(FXCollections.observableArrayList(PET_INDEX));
        PET.getSelectionModel().select(0);
        ARMOR.setItems(FXCollections.observableArrayList(EQUIP_INDEX));
        ARMOR.getSelectionModel().select(0);
        SHIELD.setItems(FXCollections.observableArrayList(SHIELD_ITEM_INDEX));
        SHIELD.getSelectionModel().select(0);
        WEAPON.setItems(FXCollections.observableArrayList(EQUIP_INDEX));
        WEAPON.getSelectionModel().select(0);
        ITEM.setItems(FXCollections.observableArrayList(SHIELD_ITEM_INDEX));
        ITEM.getSelectionModel().select(0);

        SPELL_ZERO_TYPE.setItems(FXCollections.observableArrayList(SPELL_AFFINITY_INDEX));
        SPELL_ZERO_TYPE.getSelectionModel().select(0);
        SPELL_ONE_TYPE.setItems(FXCollections.observableArrayList(SPELL_AFFINITY_INDEX));
        SPELL_ONE_TYPE.getSelectionModel().select(0);
        SPELL_TWO_TYPE.setItems(FXCollections.observableArrayList(SPELL_AFFINITY_INDEX));
        SPELL_TWO_TYPE.getSelectionModel().select(0);
        SPELL_THREE_TYPE.setItems(FXCollections.observableArrayList(SPELL_AFFINITY_INDEX));
        SPELL_THREE_TYPE.getSelectionModel().select(0);
        SPELL_FOUR_TYPE.setItems(FXCollections.observableArrayList(SPELL_AFFINITY_INDEX));
        SPELL_FOUR_TYPE.getSelectionModel().select(0);
        SPELL_FIVE_TYPE.setItems(FXCollections.observableArrayList(SPELL_AFFINITY_INDEX));
        SPELL_FIVE_TYPE.getSelectionModel().select(0);
        SPELL_SIX_TYPE.setItems(FXCollections.observableArrayList(SPELL_AFFINITY_INDEX));
        SPELL_SIX_TYPE.getSelectionModel().select(0);
        SPELL_SEVEN_TYPE.setItems(FXCollections.observableArrayList(SPELL_AFFINITY_INDEX));
        SPELL_SEVEN_TYPE.getSelectionModel().select(0);

        WEAP_ZERO_TYPE1.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_ZERO_TYPE1.getSelectionModel().select(0);
        WEAP_ZERO_TYPE2.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_ZERO_TYPE2.getSelectionModel().select(0);
        WEAP_ONE_TYPE1.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_ONE_TYPE1.getSelectionModel().select(0);
        WEAP_ONE_TYPE2.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_ONE_TYPE2.getSelectionModel().select(0);
        WEAP_TWO_TYPE1.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_TWO_TYPE1.getSelectionModel().select(0);
        WEAP_TWO_TYPE2.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_TWO_TYPE2.getSelectionModel().select(0);
        WEAP_THREE_TYPE1.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_THREE_TYPE1.getSelectionModel().select(0);
        WEAP_THREE_TYPE2.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_THREE_TYPE2.getSelectionModel().select(0);
        WEAP_FOUR_TYPE1.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_FOUR_TYPE1.getSelectionModel().select(0);
        WEAP_FOUR_TYPE2.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_FOUR_TYPE2.getSelectionModel().select(0);
        WEAP_FIVE_TYPE1.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_FIVE_TYPE1.getSelectionModel().select(0);
        WEAP_FIVE_TYPE2.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_FIVE_TYPE2.getSelectionModel().select(0);
        WEAP_SIX_TYPE1.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_SIX_TYPE1.getSelectionModel().select(0);
        WEAP_SIX_TYPE2.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_SIX_TYPE2.getSelectionModel().select(0);
        WEAP_SEVEN_TYPE1.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_SEVEN_TYPE1.getSelectionModel().select(0);
        WEAP_SEVEN_TYPE2.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        WEAP_SEVEN_TYPE2.getSelectionModel().select(0);

        PET_ZERO_TYPE.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        PET_ZERO_TYPE.getSelectionModel().select(0);
        PET_ONE_TYPE.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        PET_ONE_TYPE.getSelectionModel().select(0);
        PET_TWO_TYPE.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        PET_TWO_TYPE.getSelectionModel().select(0);
        PET_THREE_TYPE.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        PET_THREE_TYPE.getSelectionModel().select(0);
        PET_FOUR_TYPE.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        PET_FOUR_TYPE.getSelectionModel().select(0);
        PET_FIVE_TYPE.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        PET_FIVE_TYPE.getSelectionModel().select(0);
        PET_SIX_TYPE.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        PET_SIX_TYPE.getSelectionModel().select(0);
        PET_SEVEN_TYPE.setItems(FXCollections.observableArrayList(EQUIP_AFFINITY_INDEX));
        PET_SEVEN_TYPE.getSelectionModel().select(0);
    }

    private void loadProfile() throws IOException, AWTException {
        SetupUtil setup = new SetupUtil();
        String profileFileName = IndexController.PROFILE_NAME.toLowerCase().replace(" ", "-");
        JSONObject profile = setup.getProfileAsJSON(profileFileName);

        List<Spell> spells = setup.createSpells(profile);
        List<Item> items = setup.createItems(profile);
        List<Weapon> weapons = setup.createWeapons(profile);
        List<Shield> shields = setup.createShields(profile);
        List<Armor> armors = setup.createArmors(profile);
        List<Pet> pets = setup.createPets(profile);

        PROFILE_NAME.setText(parseString(profile.getString(SetupUtil.PROFILE)));

        if (parseBool(profile.getInt(SetupUtil.CONFIG_USE_SPELLS))) USE_SPELLS.fire();
        if (parseBool(profile.getInt(SetupUtil.CONFIG_USE_ALLY_ASSIST))) USE_ALLY_ASSIST.fire();
        if (parseBool(profile.getInt(SetupUtil.CONFIG_DYNAMIC_WEAPONS))) DYNAMIC_WEAPONS.fire();
        if (parseBool(profile.getInt(SetupUtil.CONFIG_DYNAMIC_PETS))) DYNAMIC_PETS.fire();
        if (parseBool(profile.getInt(SetupUtil.CONFIG_GUARDIAN))) IS_GUARDIAN.fire();
        if (parseBool(profile.getInt(SetupUtil.CONFIG_SUBRACE))) HAS_SUBRACE.fire();

        HEALTH_POT_THRESHOLD.setText(parseString(profile.getString(SetupUtil.CONFIG_HEALTH_POT_THRESHOLD)));
        MANA_POT_THRESHOLD.setText(parseString(profile.getString(SetupUtil.CONFIG_MANA_POT_THRESHOLD)));

        // equipment - consult index arrays in this class
        if (!profile.getString(SetupUtil.CONFIG_ARMOR).equals("") && !profile.getString(SetupUtil.CONFIG_ARMOR).equals(" "))
            ARMOR.getSelectionModel().select(Integer.parseInt(profile.getString(SetupUtil.CONFIG_ARMOR)) + 1);
        if (!profile.getString(SetupUtil.CONFIG_ITEM).equals("") && !profile.getString(SetupUtil.CONFIG_ITEM).equals(" "))
            ITEM.getSelectionModel().select(Integer.parseInt(profile.getString(SetupUtil.CONFIG_ITEM)) + 1);
        if (!profile.getString(SetupUtil.CONFIG_WEAPON).equals("") && !profile.getString(SetupUtil.CONFIG_WEAPON).equals(" "))
            WEAPON.getSelectionModel().select(Integer.parseInt(profile.getString(SetupUtil.CONFIG_WEAPON)) + 1);
        if (profile.getString(SetupUtil.CONFIG_SHIELD).equals("None"))
            SHIELD.getSelectionModel().select(1);
        else if (!profile.getString(SetupUtil.CONFIG_SHIELD).equals("") && !profile.getString(SetupUtil.CONFIG_SHIELD).equals(" "))
            SHIELD.getSelectionModel().select(Integer.parseInt(profile.getString(SetupUtil.CONFIG_SHIELD)) + 2);
        if (profile.getString(SetupUtil.CONFIG_PET).equals("Hide"))
            PET.getSelectionModel().select(1);
        else if (!profile.getString(SetupUtil.CONFIG_PET).equals("") && !profile.getString(SetupUtil.CONFIG_PET).equals(" "))
            PET.getSelectionModel().select(Integer.parseInt(profile.getString(SetupUtil.CONFIG_PET)) + 2);

        SPELL_ZERO_NAME.setText(parseString(spells.get(0).getName()));
        SPELL_ZERO_TYPE.getSelectionModel().select(spells.get(0).getAffinity().getAffinity());
        SPELL_ONE_NAME.setText(parseString(spells.get(1).getName()));
        SPELL_ONE_TYPE.getSelectionModel().select(spells.get(1).getAffinity().getAffinity());
        SPELL_TWO_NAME.setText(parseString(spells.get(2).getName()));
        SPELL_TWO_TYPE.getSelectionModel().select(spells.get(2).getAffinity().getAffinity());
        SPELL_THREE_NAME.setText(parseString(spells.get(3).getName()));
        SPELL_THREE_TYPE.getSelectionModel().select(spells.get(3).getAffinity().getAffinity());
        SPELL_FOUR_NAME.setText(parseString(spells.get(4).getName()));
        SPELL_FOUR_TYPE.getSelectionModel().select(spells.get(4).getAffinity().getAffinity());
        SPELL_FIVE_NAME.setText(parseString(spells.get(5).getName()));
        SPELL_FIVE_TYPE.getSelectionModel().select(spells.get(5).getAffinity().getAffinity());
        SPELL_SIX_NAME.setText(parseString(spells.get(6).getName()));
        SPELL_SIX_TYPE.getSelectionModel().select(spells.get(6).getAffinity().getAffinity());
        SPELL_SEVEN_NAME.setText(parseString(spells.get(7).getName()));
        SPELL_SEVEN_TYPE.getSelectionModel().select(spells.get(7).getAffinity().getAffinity());

        ITEM_ZERO_NAME.setText(parseString(items.get(0).getName()));
        ITEM_ONE_NAME.setText(parseString(items.get(1).getName()));
        ITEM_TWO_NAME.setText(parseString(items.get(2).getName()));
        ITEM_THREE_NAME.setText(parseString(items.get(3).getName()));
        ITEM_FOUR_NAME.setText(parseString(items.get(4).getName()));
        ITEM_FIVE_NAME.setText(parseString(items.get(5).getName()));
        ITEM_SIX_NAME.setText(parseString(items.get(6).getName()));
        ITEM_SEVEN_NAME.setText(parseString(items.get(7).getName()));

        WEAP_ZERO_NAME.setText(parseString(weapons.get(0).getName()));
        WEAP_ZERO_TYPE1.getSelectionModel().select(weapons.get(0).getAffinity().get(0));
        WEAP_ZERO_TYPE2.getSelectionModel().select(weapons.get(0).getAffinity().get(1));
        WEAP_ONE_NAME.setText(parseString(weapons.get(1).getName()));
        WEAP_ONE_TYPE1.getSelectionModel().select(weapons.get(1).getAffinity().get(0));
        WEAP_ONE_TYPE2.getSelectionModel().select(weapons.get(1).getAffinity().get(1));
        WEAP_TWO_NAME.setText(parseString(weapons.get(2).getName()));
        WEAP_TWO_TYPE1.getSelectionModel().select(weapons.get(2).getAffinity().get(0));
        WEAP_TWO_TYPE2.getSelectionModel().select(weapons.get(2).getAffinity().get(1));
        WEAP_THREE_NAME.setText(parseString(weapons.get(3).getName()));
        WEAP_THREE_TYPE1.getSelectionModel().select(weapons.get(3).getAffinity().get(0));
        WEAP_THREE_TYPE2.getSelectionModel().select(weapons.get(3).getAffinity().get(1));
        WEAP_FOUR_NAME.setText(parseString(weapons.get(4).getName()));
        WEAP_FOUR_TYPE1.getSelectionModel().select(weapons.get(4).getAffinity().get(0));
        WEAP_FOUR_TYPE2.getSelectionModel().select(weapons.get(4).getAffinity().get(1));
        WEAP_FIVE_NAME.setText(parseString(weapons.get(5).getName()));
        WEAP_FIVE_TYPE1.getSelectionModel().select(weapons.get(5).getAffinity().get(0));
        WEAP_FIVE_TYPE2.getSelectionModel().select(weapons.get(5).getAffinity().get(1));
        WEAP_SIX_NAME.setText(parseString(weapons.get(6).getName()));
        WEAP_SIX_TYPE1.getSelectionModel().select(weapons.get(6).getAffinity().get(0));
        WEAP_SIX_TYPE2.getSelectionModel().select(weapons.get(6).getAffinity().get(1));
        WEAP_SEVEN_NAME.setText(parseString(weapons.get(7).getName()));
        WEAP_SEVEN_TYPE1.getSelectionModel().select(weapons.get(7).getAffinity().get(0));
        WEAP_SEVEN_TYPE2.getSelectionModel().select(weapons.get(7).getAffinity().get(1));

        SHIELD_ZERO_NAME.setText(parseString(shields.get(0).getName()));
        SHIELD_ONE_NAME.setText(parseString(shields.get(1).getName()));
        SHIELD_TWO_NAME.setText(parseString(shields.get(2).getName()));
        SHIELD_THREE_NAME.setText(parseString(shields.get(3).getName()));
        SHIELD_FOUR_NAME.setText(parseString(shields.get(4).getName()));
        SHIELD_FIVE_NAME.setText(parseString(shields.get(5).getName()));
        SHIELD_SIX_NAME.setText(parseString(shields.get(6).getName()));
        SHIELD_SEVEN_NAME.setText(parseString(shields.get(7).getName()));

        ARMOR_ZERO_NAME.setText(parseString(armors.get(0).getName()));
        ARMOR_ONE_NAME.setText(parseString(armors.get(1).getName()));
        ARMOR_TWO_NAME.setText(parseString(armors.get(2).getName()));
        ARMOR_THREE_NAME.setText(parseString(armors.get(3).getName()));
        ARMOR_FOUR_NAME.setText(parseString(armors.get(4).getName()));
        ARMOR_FIVE_NAME.setText(parseString(armors.get(5).getName()));
        ARMOR_SIX_NAME.setText(parseString(armors.get(6).getName()));
        ARMOR_SEVEN_NAME.setText(parseString(armors.get(7).getName()));

        PET_ZERO_NAME.setText(parseString(pets.get(0).getName()));
        PET_ZERO_TYPE.getSelectionModel().select(pets.get(0).getAffinity().getAffinity());
        PET_ONE_NAME.setText(parseString(pets.get(1).getName()));
        PET_ONE_TYPE.getSelectionModel().select(pets.get(1).getAffinity().getAffinity());
        PET_TWO_NAME.setText(parseString(pets.get(2).getName()));
        PET_TWO_TYPE.getSelectionModel().select(pets.get(2).getAffinity().getAffinity());
        PET_THREE_NAME.setText(parseString(pets.get(3).getName()));
        PET_THREE_TYPE.getSelectionModel().select(pets.get(3).getAffinity().getAffinity());
        PET_FOUR_NAME.setText(parseString(pets.get(4).getName()));
        PET_FOUR_TYPE.getSelectionModel().select(pets.get(4).getAffinity().getAffinity());
        PET_FIVE_NAME.setText(parseString(pets.get(5).getName()));
        PET_FIVE_TYPE.getSelectionModel().select(pets.get(5).getAffinity().getAffinity());
        PET_SIX_NAME.setText(parseString(pets.get(6).getName()));
        PET_SIX_TYPE.getSelectionModel().select(pets.get(6).getAffinity().getAffinity());
        PET_SEVEN_NAME.setText(parseString(pets.get(7).getName()));
        PET_SEVEN_TYPE.getSelectionModel().select(pets.get(7).getAffinity().getAffinity());
    }

    private int parseBool(boolean bool) {
        if (bool) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean parseBool(int bool) {
        return bool == 1;
    }

    private String parseString(String s) {
        if (s != null && !s.equals("") && !s.equals(" ")) {
            return s;
        } else {
            return "";
        }
    }
}
