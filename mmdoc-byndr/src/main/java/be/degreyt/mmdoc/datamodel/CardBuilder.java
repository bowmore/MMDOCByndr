package be.degreyt.mmdoc.datamodel;

public interface CardBuilder {

    CardBuilder identification(String identification);

    CardBuilder description(String description);

    CardBuilder name(String name);

    CardBuilder faction(Faction faction);

    CardBuilder cost(int cost);

    CardBuilder might(int might);

    CardBuilder magic(int magic);

    CardBuilder destiny(int destiny);

    CardBuilder unique();

    CreatureBuilder attack(int attack);

    CreatureBuilder health(int health);

    CreatureBuilder retaliation(int retaliation);

}
