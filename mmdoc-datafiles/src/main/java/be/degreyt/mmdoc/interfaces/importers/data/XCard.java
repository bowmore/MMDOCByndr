package be.degreyt.mmdoc.interfaces.importers.data;

import java.util.List;

public class XCard {

    private String Rarity;
    private String Type;
    private String SubType;
    private String DisplayName;
    private String Name;
    private String Faction;
    private String Cost;
    private String DestinyLevel;
    private String MagicLevel;
    private String MightLevel;
    private String Attack;
    private String Retaliate;
    private String HP;

    private List<School> schools;
    private String ExtraDescription;
    private String Description;
    private List<XAbility> abilities;
    private Trigger trigger;
    private Ongoing Ongoing;
    private List<Variable> variables;
    private PlayabilityCondition PlayabilityCondition;
    private AttackCondition AttackCondition;
    private AdditionalCost AdditionalCost;

    public String getRarity() {
        return Rarity;
    }

    public void setRarity(String rarity) {
        Rarity = rarity;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSubType() {
        return SubType;
    }

    public void setSubType(String subType) {
        SubType = subType;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFaction() {
        return Faction;
    }

    public void setFaction(String faction) {
        Faction = faction;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getDestinyLevel() {
        return DestinyLevel;
    }

    public void setDestinyLevel(String destinyLevel) {
        DestinyLevel = destinyLevel;
    }

    public String getMagicLevel() {
        return MagicLevel;
    }

    public void setMagicLevel(String magicLevel) {
        MagicLevel = magicLevel;
    }

    public String getMightLevel() {
        return MightLevel;
    }

    public void setMightLevel(String mightLevel) {
        MightLevel = mightLevel;
    }

    public String getAttack() {
        return Attack;
    }

    public void setAttack(String attack) {
        Attack = attack;
    }

    public String getRetaliate() {
        return Retaliate;
    }

    public void setRetaliate(String retaliate) {
        Retaliate = retaliate;
    }

    public String getHP() {
        return HP;
    }

    public void setHP(String HP) {
        this.HP = HP;
    }

    public String getExtraDescription() {
        return ExtraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        ExtraDescription = extraDescription;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}