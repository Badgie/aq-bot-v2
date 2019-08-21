package util;

import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;

public class CursorUtil {
    private boolean playerHasSubrace = false;

    private MouseMotionFactory m;
    private Robot rob;
    private Util util;

    public CursorUtil() throws AWTException, IOException {
        this.m = new MouseMotionFactory();
        this.rob = new Robot();
        this.util = new Util();
    }

    /**
     * Performs an attack in the game
     * @throws InterruptedException
     */
    public void attack() throws InterruptedException, IOException, AWTException {
        JSONObject attack = util.getDimsAsJSONObject("combat/menu/attack");
        Point p = util.getRandomPoint(attack);
        moveAndClick(p.getX(), p.getY());
    }

    public void allyAssist(String ally) throws InterruptedException, IOException, AWTException {
        JSONObject allyAssist = util.getDimsAsJSONObject("combat/menu/allyassist/allyassist");
        Point p = util.getRandomPoint(allyAssist);
        moveAndClick(p.getX(), p.getY());

        switch (ally) {
            case "artix":
                artixAlly();
                break;
            case "galanoth":
                galanothAlly();
                break;
            case "aquella":
                aquellaAlly();
                break;
            case "zorbak":
                zorbakAlly();
                break;
            case "robina":
                robinaAlly();
                break;
            default:
                cancelAlly();
                break;
        }
    }

    public void spell(int spell) throws InterruptedException, IOException, AWTException {
        switch (spell) {
            case 0:
                quickCastSpell0();
                break;
            case 1:
                quickCastSpell1();
                break;
            case 2:
                quickCastSpell2();
                break;
            case 3:
                quickCastSpell3();
                break;
            case 4:
                quickCastSpell4();
                break;
            case 5:
                quickCastSpell5();
                break;
            case 6:
                quickCastSpell6();
                break;
            case 7:
                quickCastSpell7();
                break;
            default:

                break;
        }
    }

    public void skill() throws InterruptedException, IOException, AWTException {
        JSONObject skill = util.getDimsAsJSONObject("combat/menu/skill");
        Point p = util.getRandomPoint(skill);
        moveAndClick(p.getX(), p.getY());
    }

    public void item(int itemChoice) throws InterruptedException, IOException, AWTException {
        JSONObject item = util.getDimsAsJSONObject("combat/menu/item/item");
        Point p = util.getRandomPoint(item);
        moveAndClick(p.getX(), p.getY());

        switch (itemChoice) {
            case -2:
                healthPot();
                break;
            case -1:
                manaPot();
                break;
            case 0:
                item0();
                break;
            case 1:
                item1();
                break;
            case 2:
                item2();
                break;
            case 3:
                item3();
                break;
            case 4:
                item4();
                break;
            case 5:
                item5();
                break;
            case 6:
                item6();
                break;
            case 7:
                item7();
                break;
            default:

                break;
        }
    }

    public void weapon(int weaponChoice) throws InterruptedException, IOException, AWTException {
        JSONObject weapon = util.getDimsAsJSONObject("combat/menu/weapon/weapon");
        Point p = util.getRandomPoint(weapon);
        moveAndClick(p.getX(), p.getY());

        switch(weaponChoice) {
            case 0:
                weapon0();
                break;
            case 1:
                weapon1();
                break;
            case 2:
                weapon2();
                break;
            case 3:
                weapon3();
                break;
            case 4:
                weapon4();
                break;
            case 5:
                weapon5();
                break;
            case 6:
                weapon6();
                break;
            case 7:
                weapon7();
                break;
            default:

                break;
        }
    }

    public void shield(int shieldChoice) throws InterruptedException, IOException, AWTException {
        JSONObject shield = util.getDimsAsJSONObject("combat/menu/shield/shield");
        Point p = util.getRandomPoint(shield);
        moveAndClick(p.getX(), p.getY());

        switch(shieldChoice) {
            case -1:
                noShield();
                break;
            case 0:
                shield0();
                break;
            case 1:
                shield1();
                break;
            case 2:
                shield2();
                break;
            case 3:
                shield3();
                break;
            case 4:
                shield4();
                break;
            case 5:
                shield5();
                break;
            case 6:
                shield6();
                break;
            case 7:
                shield7();
                break;
            default:

                break;
        }
    }

    public void armor(int armorChoice, boolean hasSubrace) throws InterruptedException, IOException, AWTException {
        JSONObject armor = util.getDimsAsJSONObject("combat/menu/armor/armor");
        Point p = util.getRandomPoint(armor);
        moveAndClick(p.getX(), p.getY());
        playerHasSubrace = hasSubrace;

        switch(armorChoice) {
            case -1:
                race();
                break;
            case 0:
                armor0();
                break;
            case 1:
                armor1();
                break;
            case 2:
                armor2();
                break;
            case 3:
                armor3();
                break;
            case 4:
                armor4();
                break;
            case 5:
                armor5();
                break;
            case 6:
                armor6();
                break;
            case 7:
                armor7();
                break;
            default:

                break;
        }
    }

    public void pet(int petChoice) throws InterruptedException, IOException, AWTException {
        JSONObject pet = util.getDimsAsJSONObject("combat/menu/pet/pet");
        Point p = util.getRandomPoint(pet);
        moveAndClick(p.getX(), p.getY());

        switch(petChoice) {
            case -1:
                hide();
                break;
            case 0:
                pet0();
                break;
            case 1:
                pet1();
                break;
            case 2:
                pet2();
                break;
            case 3:
                pet3();
                break;
            case 4:
                pet4();
                break;
            case 5:
                pet5();
                break;
            case 6:
                pet6();
                break;
            case 7:
                pet7();
                break;
            default:

                break;
        }
    }

    public void flee() throws InterruptedException, IOException, AWTException {
        JSONObject flee = util.getDimsAsJSONObject("combat/menu/flee/flee");
        Point p = util.getRandomPoint(flee);
        moveAndClick(p.getX(), p.getY());

        confirmFlee();

        acceptDefeatFlee();
    }

    public void victory() throws InterruptedException, IOException, AWTException {
        JSONObject victory = util.getDimsAsJSONObject("combat/victory/confirm");
        Point p = util.getRandomPoint(victory);
        moveAndClick(p.getX(), p.getY());
    }

    public void travelMap() throws IOException, InterruptedException, AWTException {
        JSONObject travelMap = util.getDimsAsJSONObject("town/travelmap/travelmap");
        Point p = util.getRandomPoint(travelMap);
        moveAndClick(p.getX(), p.getY());
    }

    public void death() throws InterruptedException, IOException, AWTException {
        JSONObject death = util.getDimsAsJSONObject("combat/death/confirm");
        Point p = util.getRandomPoint(death);
        moveAndClick(p.getX(), p.getY());

        Thread.sleep(5000);

        for (int i = 0; i < 3; i++) {
            deathDialogue();
        }
    }

    public void randomBattle() throws InterruptedException, IOException, AWTException {
        JSONObject randomBattle = util.getDimsAsJSONObject("town/randombattle");
        Point p = util.getRandomPoint(randomBattle);
        moveAndClick(p.getX(), p.getY());
    }

    public void heal() throws InterruptedException, IOException, AWTException {
        JSONObject heal = util.getDimsAsJSONObject("town/heal");
        Point p = util.getRandomPoint(heal);
        moveAndClick(p.getX(), p.getY());
    }

    public void levelUp() throws IOException, InterruptedException, AWTException {
        JSONObject levelUp = util.getDimsAsJSONObject("combat/levelup/next");
        Point p = util.getRandomPoint(levelUp);
        moveAndClick(p.getX(), p.getY());
    }

    public void hoverPlayerAvatar() throws IOException, InterruptedException, AWTException {
        JSONObject playerAvatar = util.getDimsAsJSONObject("town/playeravatar");
        Point p = util.getRandomPoint(playerAvatar);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Moves cursor to a point that doesn't obstruct combat menu
     */
    public void clearAttack() throws InterruptedException, IOException, AWTException {
        JSONObject clearAttack = util.getDimsAsJSONObject("combat/clear/attack");
        Point p = util.getRandomPoint(clearAttack);
        move(p.getX(), p.getY());
    }

    public void clearAvatar() throws InterruptedException, IOException, AWTException {
        JSONObject clearAvatar = util.getDimsAsJSONObject("combat/clear/enemyavatar");
        Point p = util.getRandomPoint(clearAvatar);
        move(p.getX(), p.getY());
    }

    /**
     * Bot trainer helper methods
     */
    public void hoverEnemy() throws InterruptedException, IOException, AWTException {
        JSONObject hoverEnemy = util.getDimsAsJSONObject("enemy/avatar");
        Point p = util.getRandomPoint(hoverEnemy);
        move(p.getX(), p.getY());
    }

    /**
     * Death helper methods
     */

    private void deathDialogue() throws InterruptedException, IOException, AWTException {
        JSONObject deathDialogue = util.getDimsAsJSONObject("combat/death/randomclick");
        Point p = util.getRandomPoint(deathDialogue);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Victory helper methods
     */

    public void zTokens() throws InterruptedException, IOException, AWTException {
        JSONObject zTokens = util.getDimsAsJSONObject("combat/ztoken/confirm");
        Point p = util.getRandomPoint(zTokens);
        moveAndClick(p.getX(), p.getY());

        //TODO: log rewards
    }

    /**
     * Flee helper methods
     */

    private void confirmFlee() throws InterruptedException, IOException, AWTException {
        JSONObject confirmFlee = util.getDimsAsJSONObject("combat/menu/flee/confirm");
        Point p = util.getRandomPoint(confirmFlee);
        moveAndClick(p.getX(), p.getY());
    }

    private void acceptDefeatFlee() throws InterruptedException, IOException, AWTException {
        JSONObject acceptDefeatFlee = util.getDimsAsJSONObject("combat/menu/flee/next");
        Point p = util.getRandomPoint(acceptDefeatFlee);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Pet helper methods
     */

    private void hide() throws InterruptedException, IOException, AWTException {
        JSONObject hide = util.getDimsAsJSONObject("combat/menu/pet/hide");
        Point p = util.getRandomPoint(hide);
        moveAndClick(p.getX(), p.getY());
    }

    private void pet0() throws InterruptedException, IOException, AWTException {
        JSONObject pet0 = util.getDimsAsJSONObject("combat/menu/pet/0");
        Point p = util.getRandomPoint(pet0);
        moveAndClick(p.getX(), p.getY());
    }

    private void pet1() throws InterruptedException, IOException, AWTException {
        JSONObject pet1 = util.getDimsAsJSONObject("combat/menu/pet/1");
        Point p = util.getRandomPoint(pet1);
        moveAndClick(p.getX(), p.getY());
    }

    private void pet2() throws InterruptedException, IOException, AWTException {
        JSONObject pet2 = util.getDimsAsJSONObject("combat/menu/pet/2");
        Point p = util.getRandomPoint(pet2);
        moveAndClick(p.getX(), p.getY());
    }

    private void pet3() throws InterruptedException, IOException, AWTException {
        JSONObject pet3 = util.getDimsAsJSONObject("combat/menu/pet/3");
        Point p = util.getRandomPoint(pet3);
        moveAndClick(p.getX(), p.getY());
    }

    private void pet4() throws InterruptedException, IOException, AWTException {
        JSONObject pet4 = util.getDimsAsJSONObject("combat/menu/pet/4");
        Point p = util.getRandomPoint(pet4);
        moveAndClick(p.getX(), p.getY());
    }

    private void pet5() throws InterruptedException, IOException, AWTException {
        JSONObject pet5 = util.getDimsAsJSONObject("combat/menu/pet/5");
        Point p = util.getRandomPoint(pet5);
        moveAndClick(p.getX(), p.getY());
    }

    private void pet6() throws InterruptedException, IOException, AWTException {
        JSONObject pet6 = util.getDimsAsJSONObject("combat/menu/pet/6");
        Point p = util.getRandomPoint(pet6);
        moveAndClick(p.getX(), p.getY());
    }

    private void pet7() throws InterruptedException, IOException, AWTException {
        JSONObject pet7 = util.getDimsAsJSONObject("combat/menu/pet/7");
        Point p = util.getRandomPoint(pet7);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Armor helper methods
     */

    private void race() throws InterruptedException, IOException, AWTException {
        JSONObject race = util.getDimsAsJSONObject("combat/menu/armor/subrace/subrace");
        Point p = util.getRandomPoint(race);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor0() throws InterruptedException, IOException, AWTException {
        JSONObject armor0;

        if (playerHasSubrace) {
            armor0 = util.getDimsAsJSONObject("combat/menu/armor/subrace/0");
        } else {
            armor0 = util.getDimsAsJSONObject("combat/menu/armor/0");
        }

        Point p = util.getRandomPoint(armor0);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor1() throws InterruptedException, IOException, AWTException {
        JSONObject armor1;

        if (playerHasSubrace) {
            armor1 = util.getDimsAsJSONObject("combat/menu/armor/subrace/1");
        } else {
            armor1 = util.getDimsAsJSONObject("combat/menu/armor/1");
        }

        Point p = util.getRandomPoint(armor1);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor2() throws InterruptedException, IOException, AWTException {
        JSONObject armor2;

        if (playerHasSubrace) {
            armor2 = util.getDimsAsJSONObject("combat/menu/armor/subrace/2");
        } else {
            armor2 = util.getDimsAsJSONObject("combat/menu/armor/2");
        }

        Point p = util.getRandomPoint(armor2);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor3() throws InterruptedException, IOException, AWTException {
        JSONObject armor3;

        if (playerHasSubrace) {
            armor3 = util.getDimsAsJSONObject("combat/menu/armor/subrace/3");
        } else {
            armor3 = util.getDimsAsJSONObject("combat/menu/armor/3");
        }

        Point p = util.getRandomPoint(armor3);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor4() throws InterruptedException, IOException, AWTException {
        JSONObject armor4;

        if (playerHasSubrace) {
            armor4 = util.getDimsAsJSONObject("combat/menu/armor/subrace/4");
        } else {
            armor4 = util.getDimsAsJSONObject("combat/menu/armor/4");
        }

        Point p = util.getRandomPoint(armor4);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor5() throws InterruptedException, IOException, AWTException {
        JSONObject armor5;

        if (playerHasSubrace) {
            armor5 = util.getDimsAsJSONObject("combat/menu/armor/subrace/5");
        } else {
            armor5 = util.getDimsAsJSONObject("combat/menu/armor/5");
        }

        Point p = util.getRandomPoint(armor5);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor6() throws InterruptedException, IOException, AWTException {
        JSONObject armor6;

        if (playerHasSubrace) {
            armor6 = util.getDimsAsJSONObject("combat/menu/armor/subrace/6");
        } else {
            armor6 = util.getDimsAsJSONObject("combat/menu/armor/6");
        }
        Point p = util.getRandomPoint(armor6);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor7() throws InterruptedException, IOException, AWTException {
        JSONObject armor7;

        if (playerHasSubrace) {
            armor7 = util.getDimsAsJSONObject("combat/menu/armor/subrace/7");
        } else {
            armor7 = util.getDimsAsJSONObject("combat/menu/armor/7");
        }
        Point p = util.getRandomPoint(armor7);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Shield helper methods
     */

    private void noShield() throws InterruptedException, IOException, AWTException {
        JSONObject noShield = util.getDimsAsJSONObject("combat/menu/shield/none");
        Point p = util.getRandomPoint(noShield);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield0() throws InterruptedException, IOException, AWTException {
        JSONObject shield0 = util.getDimsAsJSONObject("combat/menu/shield/0");
        Point p = util.getRandomPoint(shield0);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield1() throws InterruptedException, IOException, AWTException {
        JSONObject shield1 = util.getDimsAsJSONObject("combat/menu/shield/1");
        Point p = util.getRandomPoint(shield1);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield2() throws InterruptedException, IOException, AWTException {
        JSONObject shield2 = util.getDimsAsJSONObject("combat/menu/shield/2");
        Point p = util.getRandomPoint(shield2);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield3() throws InterruptedException, IOException, AWTException {
        JSONObject shield3 = util.getDimsAsJSONObject("combat/menu/shield/3");
        Point p = util.getRandomPoint(shield3);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield4() throws InterruptedException, IOException, AWTException {
        JSONObject shield4 = util.getDimsAsJSONObject("combat/menu/shield/4");
        Point p = util.getRandomPoint(shield4);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield5() throws InterruptedException, IOException, AWTException {
        JSONObject shield5 = util.getDimsAsJSONObject("combat/menu/shield/5");
        Point p = util.getRandomPoint(shield5);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield6() throws InterruptedException, IOException, AWTException {
        JSONObject shield6 = util.getDimsAsJSONObject("combat/menu/shield/6");
        Point p = util.getRandomPoint(shield6);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield7() throws InterruptedException, IOException, AWTException {
        JSONObject shield7 = util.getDimsAsJSONObject("combat/menu/shield/7");
        Point p = util.getRandomPoint(shield7);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Weapon helper methods
     */

    private void weapon0() throws InterruptedException, IOException, AWTException {
        JSONObject weapon0 = util.getDimsAsJSONObject("combat/menu/weapon/0");
        Point p = util.getRandomPoint(weapon0);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon1() throws InterruptedException, IOException, AWTException {
        JSONObject weapon1 = util.getDimsAsJSONObject("combat/menu/weapon/1");
        Point p = util.getRandomPoint(weapon1);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon2() throws InterruptedException, IOException, AWTException {
        JSONObject weapon2 = util.getDimsAsJSONObject("combat/menu/weapon/2");
        Point p = util.getRandomPoint(weapon2);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon3() throws InterruptedException, IOException, AWTException {
        JSONObject weapon3 = util.getDimsAsJSONObject("combat/menu/weapon/3");
        Point p = util.getRandomPoint(weapon3);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon4() throws InterruptedException, IOException, AWTException {
        JSONObject weapon4 = util.getDimsAsJSONObject("combat/menu/weapon/4");
        Point p = util.getRandomPoint(weapon4);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon5() throws InterruptedException, IOException, AWTException {
        JSONObject weapon5 = util.getDimsAsJSONObject("combat/menu/weapon/5");
        Point p = util.getRandomPoint(weapon5);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon6() throws InterruptedException, IOException, AWTException {
        JSONObject weapon6 = util.getDimsAsJSONObject("combat/menu/weapon/6");
        Point p = util.getRandomPoint(weapon6);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon7() throws InterruptedException, IOException, AWTException {
        JSONObject weapon7 = util.getDimsAsJSONObject("combat/menu/weapon/7");
        Point p = util.getRandomPoint(weapon7);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Item helper methods
     */

    private void healthPot() throws InterruptedException, IOException, AWTException {
        JSONObject healthPot = util.getDimsAsJSONObject("combat/menu/item/healthpotion/healthpotion");
        Point p = util.getRandomPoint(healthPot);
        moveAndClick(p.getX(), p.getY());
    }

    private void manaPot() throws InterruptedException, IOException, AWTException {
        JSONObject manaPot = util.getDimsAsJSONObject("combat/menu/item/manapotion/manapotion");
        Point p = util.getRandomPoint(manaPot);
        moveAndClick(p.getX(), p.getY());
    }

    private void item0() throws InterruptedException, IOException, AWTException {
        JSONObject item0 = util.getDimsAsJSONObject("combat/menu/item/0");
        Point p = util.getRandomPoint(item0);
        moveAndClick(p.getX(), p.getY());
    }

    private void item1() throws InterruptedException, IOException, AWTException {
        JSONObject item1 = util.getDimsAsJSONObject("combat/menu/item/1");
        Point p = util.getRandomPoint(item1);
        moveAndClick(p.getX(), p.getY());
    }

    private void item2() throws InterruptedException, IOException, AWTException {
        JSONObject item2 = util.getDimsAsJSONObject("combat/menu/item/2");
        Point p = util.getRandomPoint(item2);
        moveAndClick(p.getX(), p.getY());
    }

    private void item3() throws InterruptedException, IOException, AWTException {
        JSONObject item3 = util.getDimsAsJSONObject("combat/menu/item/3");
        Point p = util.getRandomPoint(item3);
        moveAndClick(p.getX(), p.getY());
    }

    private void item4() throws InterruptedException, IOException, AWTException {
        JSONObject item4 = util.getDimsAsJSONObject("combat/menu/item/4");
        Point p = util.getRandomPoint(item4);
        moveAndClick(p.getX(), p.getY());
    }

    private void item5() throws InterruptedException, IOException, AWTException {
        JSONObject item5 = util.getDimsAsJSONObject("combat/menu/item/5");
        Point p = util.getRandomPoint(item5);
        moveAndClick(p.getX(), p.getY());
    }

    private void item6() throws InterruptedException, IOException, AWTException {
        JSONObject item6 = util.getDimsAsJSONObject("combat/menu/item/6");
        Point p = util.getRandomPoint(item6);
        moveAndClick(p.getX(), p.getY());
    }

    private void item7() throws InterruptedException, IOException, AWTException {
        JSONObject item7 = util.getDimsAsJSONObject("combat/menu/item/7");
        Point p = util.getRandomPoint(item7);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Spell helper methods
     */

    private void quickCastSpell0() throws InterruptedException, IOException, AWTException {
        JSONObject quickCastSpell0 = util.getDimsAsJSONObject("combat/menu/spell/quickspell/0");
        Point p = util.getRandomPoint(quickCastSpell0);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell1() throws InterruptedException, IOException, AWTException {
        JSONObject quickCastSpell1 = util.getDimsAsJSONObject("combat/menu/spell/quickspell/1");
        Point p = util.getRandomPoint(quickCastSpell1);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell2() throws InterruptedException, IOException, AWTException {
        JSONObject quickCastSpell2 = util.getDimsAsJSONObject("combat/menu/spell/quickspell/2");
        Point p = util.getRandomPoint(quickCastSpell2);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell3() throws InterruptedException, IOException, AWTException {
        JSONObject quickCastSpell3 = util.getDimsAsJSONObject("combat/menu/spell/quickspell/3");
        Point p = util.getRandomPoint(quickCastSpell3);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell4() throws InterruptedException, IOException, AWTException {
        JSONObject quickCastSpell4 = util.getDimsAsJSONObject("combat/menu/spell/quickspell/4");
        Point p = util.getRandomPoint(quickCastSpell4);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell5() throws InterruptedException, IOException, AWTException {
        JSONObject quickCastSpell5 = util.getDimsAsJSONObject("combat/menu/spell/quickspell/5");
        Point p = util.getRandomPoint(quickCastSpell5);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell6() throws InterruptedException, IOException, AWTException {
        JSONObject quickCastSpell6 = util.getDimsAsJSONObject("combat/menu/spell/quickspell/6");
        Point p = util.getRandomPoint(quickCastSpell6);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell7() throws InterruptedException, IOException, AWTException {
        JSONObject quickCastSpell7 = util.getDimsAsJSONObject("combat/menu/spell/quickspell/7");
        Point p = util.getRandomPoint(quickCastSpell7);
        moveAndClick(p.getX(), p.getY());
    }


    /**
     * Ally assist helper methods
     */

    private void artixAlly() throws InterruptedException, IOException, AWTException {
        JSONObject artixAlly = util.getDimsAsJSONObject("combat/menu/allyassist/artix");
        Point p = util.getRandomPoint(artixAlly);
        moveAndClick(p.getX(), p.getY());
    }

    private void galanothAlly() throws InterruptedException, IOException, AWTException {
        JSONObject galanothAlly = util.getDimsAsJSONObject("combat/menu/allyassist/galanoth");
        Point p = util.getRandomPoint(galanothAlly);
        moveAndClick(p.getX(), p.getY());
    }

    private void aquellaAlly() throws InterruptedException, IOException, AWTException {
        JSONObject aquellaAlly = util.getDimsAsJSONObject("combat/menu/allyassist/aquella");
        Point p = util.getRandomPoint(aquellaAlly);
        moveAndClick(p.getX(), p.getY());
    }

    private void zorbakAlly() throws InterruptedException, IOException, AWTException {
        JSONObject zorbakAlly = util.getDimsAsJSONObject("combat/menu/allyassist/zorbak");
        Point p = util.getRandomPoint(zorbakAlly);
        moveAndClick(p.getX(), p.getY());
    }

    private void robinaAlly() throws InterruptedException, IOException, AWTException {
        JSONObject robinaAlly = util.getDimsAsJSONObject("combat/menu/allyassist/robina");
        Point p = util.getRandomPoint(robinaAlly);
        moveAndClick(p.getX(), p.getY());
    }

    private void cancelAlly() throws InterruptedException, IOException, AWTException {
        JSONObject cancelAlly = util.getDimsAsJSONObject("combat/menu/allyassist/return");
        Point p = util.getRandomPoint(cancelAlly);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Town methods
     */

    public void event() throws IOException, InterruptedException, AWTException {
        JSONObject event = util.getDimsAsJSONObject("town/event/event");
        Point p = util.getRandomPoint(event);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * General helper methods
     */

    /**
     * Moves cursor and simulates a click
     * @param x coordinate of the point to click
     * @param y coordinate of the point to click
     * @throws InterruptedException
     */
    public void moveAndClick(double x, double y) throws InterruptedException {
        move(x, y);
        click();
    }

    /**
     * Simulates mouse movement
     * @param x
     * @param y
     * @throws InterruptedException
     */
    public void move(double x, double y) throws InterruptedException {
        this.m.move(
                (int) x,
                (int) y
        );
    }

    /**
     * Simulates a mouse click
     * @throws InterruptedException
     */
    public void click() throws InterruptedException {
        this.rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(200);
        this.rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(200);
    }
}
