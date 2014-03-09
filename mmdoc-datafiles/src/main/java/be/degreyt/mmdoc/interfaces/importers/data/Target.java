package be.degreyt.mmdoc.interfaces.importers.data;

import java.util.List;

public class Target {
    private String Zone;
    private String Side;
    private String Amount;
    private String Cancelable;
    private String CantTargetSelf;

    private List<Effect> effects;
    private CardType CardType;
    private List<Variable> variables;
    private Subgroup Subgroup;
    private Option Option;
    private Condition Condition;
    private CardFilter CardFilter;
    private BattlegroundFilter BattlegroundFilter;
}
