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

    CardBuilder attack(int attack);

    CardBuilder health(int health);

    CardBuilder retaliation(int retaliation);

    CardBuilder type(CardType cardType);

    Card build();

    CardBuilder rarity(Rarity rarity);

    CardBuilder addSchool(MagicSchool magicSchool);

    CardBuilder position(PositionType positionType);

    CardBuilder creatureType(CreatureType creatureType);

    CardBuilder ability(Ability ability);

    CardBuilder playType(PlayType playType);

    CardBuilder expansion(Expansion expansion);
}
