package be.degreyt.mmdoc.datamodel;

public interface CardBuilder {

    CardBuilder description(String description);

    CardBuilder setName(String name);

    CardBuilder setFaction(Faction faction);

    CardBuilder setCost(int cost);

    CardBuilder setMight(int might);

    CardBuilder setMagic(int magic);

    CardBuilder setDestiny(int destiny);

    CardBuilder unique();

    CreatureBuilder setAttack(int attack);

    CreatureBuilder setHealth(int health);

    CreatureBuilder setRetaliation(int retaliation);

}
