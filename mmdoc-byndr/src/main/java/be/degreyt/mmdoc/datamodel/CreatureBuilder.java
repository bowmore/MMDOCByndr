package be.degreyt.mmdoc.datamodel;

public interface CreatureBuilder extends CardBuilder {

    CreatureBuilder description(String description);

    CreatureBuilder setName(String name);

    CreatureBuilder setFaction(Faction faction);

    CreatureBuilder setCost(int cost);

    CreatureBuilder setMight(int might);

    CreatureBuilder setMagic(int magic);

    CreatureBuilder setDestiny(int destiny);

    CreatureBuilder unique();

    CreatureBuilder setAttack(int attack);

    CreatureBuilder setHealth(int health);

    CreatureBuilder setRetaliation(int retaliation);

    Creature build();
}
