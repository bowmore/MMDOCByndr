package be.degreyt.mmdoc.datamodel;

import java.net.URL;

public interface CreatureBuilder extends CardBuilder {
    CreatureBuilder identification(String identification);

    CreatureBuilder description(String description);

    CreatureBuilder name(String name);

    CreatureBuilder faction(Faction faction);

    CreatureBuilder cost(int cost);

    CreatureBuilder might(int might);

    CreatureBuilder magic(int magic);

    CreatureBuilder destiny(int destiny);

    CreatureBuilder unique();

    CreatureBuilder attack(int attack);

    CreatureBuilder health(int health);

    CreatureBuilder retaliation(int retaliation);

    Creature build();

    CreatureBuilder position(PositionType positionType);

    CreatureBuilder creatureType(CreatureType creatureType);

    CreatureBuilder ability(Ability ability);

    CreatureBuilder setSmallImageUrl(URL smallImageUrl);

    CreatureBuilder setLargeImageUrl(URL largeImageUrl);

    CreatureBuilder expansionInfo(ExpansionInfo expansionInfo);
}
