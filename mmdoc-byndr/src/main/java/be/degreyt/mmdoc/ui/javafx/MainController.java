package be.degreyt.mmdoc.ui.javafx;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import be.degreyt.mmdoc.byndr.services.CardCollection;
import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.byndr.services.NameMatcher;
import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.exceptions.UnderlyingIOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

public class MainController {

    private final ByndrService byndrService;

    @FXML private FlowPane resultPane;

    private Predicate<CardOwnership> cardTypes = new OrPredicate();

    private final Map<CardOwnership, Node> screens = new HashMap<>();
    private final Map<Node, Predicate<CardOwnership>> predicates = new HashMap<>();
    private final OrPredicate cardTypeGroup = new OrPredicate();
    private final OrPredicate expansionGroup = new OrPredicate();
    private final OrPredicate rarityGroup = new OrPredicate();
    private final OrPredicate factionGroup = new OrPredicate();
    private final OrPredicate schoolGroup = new OrPredicate();
    private final AndPredicate textGroup = new AndPredicate(Collections.<Predicate<CardOwnership>>emptyList());
    private final AndPredicate mainFilter = new AndPredicate(asList(cardTypeGroup, textGroup, expansionGroup, rarityGroup, factionGroup, schoolGroup));
    private CardCollection cardCollection;

    @Inject
    public MainController(ByndrService byndrService) {
        this.byndrService = byndrService;
    }

    public void initScreen() {
        cardCollection = byndrService.load();
        cardCollection.ownerships().stream().sorted().forEach(this::addGlyph);
    }

    private void addGlyph(CardOwnership ownership) {
        try {
            if (screens.containsKey(ownership)) {
                resultPane.getChildren().add(screens.get(ownership));
                return;
            }
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/be/degreyt/mmdoc/ui/CardGlyph.fxml"));
            Parent loadScreen = myLoader.load();
            resultPane.getChildren().add(loadScreen);
            GlyphController contentController = myLoader.getController();
            contentController.set(ownership);
            screens.put(ownership, loadScreen);
        } catch (IOException e) {
            throw new UnderlyingIOException(e);
        }
    }

    public void heroCardTypeSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, cardTypeGroup, node -> byndrService.getFilterProvider().hasType(CardType.HERO));
    }

    public void eventCardTypeSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, cardTypeGroup, node -> byndrService.getFilterProvider().hasType(CardType.EVENT));
    }

    public void creatureCardTypeSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, cardTypeGroup, node -> byndrService.getFilterProvider().hasType(CardType.CREATURE));
    }

    public void spellCardTypeSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, cardTypeGroup, node -> byndrService.getFilterProvider().hasType(CardType.SPELL));
    }

    public void buildingCardTypeSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, cardTypeGroup, node -> byndrService.getFilterProvider().hasType(CardType.BUILDING));
    }

    public void fortuneCardTypeSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, cardTypeGroup, node -> byndrService.getFilterProvider().hasType(CardType.FORTUNE));
    }

    public void cardNameFilterChanged(KeyEvent keyEvent) {
        TextField textField = (TextField) keyEvent.getSource();
        NameMatcher nameMatcher = (NameMatcher) predicates.computeIfAbsent(textField, t -> byndrService.getFilterProvider().nameMatches(((TextField) t).getText()));
        nameMatcher.setText(textField.getText());
        System.out.println(textField.getText());
        if (textField.getText().isEmpty()) {
            textGroup.remove(nameMatcher);
        } else {
            textGroup.add(nameMatcher);
        }
        applyFilter();
    }

    private void handleCheckBoxFilterEvent(ActionEvent actionEvent, OrPredicate group, Function<? super Node, ? extends Predicate<CardOwnership>> mappingFunction) {
        CheckBox checkBox = (CheckBox) actionEvent.getSource();
        Predicate<CardOwnership> filter = predicates.computeIfAbsent(checkBox, mappingFunction);
        if (checkBox.isSelected()) {
            group.add(filter);
        } else {
            group.remove(filter);
        }
        applyFilter();
    }

    private void applyFilter() {
        resultPane.getChildren().clear();
        cardCollection.ownerships(mainFilter).stream().sorted().forEach(this::addGlyph);
    }

    public void baseSet1SelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasExpansion(Expansion.BASE_SET));
    }

    public void baseSet2SelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasExpansion(Expansion.BASE_SET_2));
    }

    public void heraldOfTheVoidSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasExpansion(Expansion.HERALD_OF_THE_VOID));
    }

    public void voidRisingSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasExpansion(Expansion.VOID_RISING));
    }

    public void forgottenWarsSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasExpansion(Expansion.FORGOTTEN_WARS));
    }

    public void fiveTowersSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasExpansion(Expansion.FIVE_TOWERS));
    }

    public void rewardsSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasExpansion(Expansion.SPECIAL));
    }

    public void commonSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasRarity(Rarity.COMMON));
    }

    public void uncommonSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasRarity(Rarity.UNCOMMON));
    }

    public void rareSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasRarity(Rarity.RARE));
    }

    public void epicSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasRarity(Rarity.EPIC));
    }

    public void heroicSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, expansionGroup, node -> byndrService.getFilterProvider().hasRarity(Rarity.HEROIC));
    }

    public void academySelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, factionGroup, node -> byndrService.getFilterProvider().hasFaction(Faction.ACADEMY));
    }

    public void havenSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, factionGroup, node -> byndrService.getFilterProvider().hasFaction(Faction.HAVEN));
    }

    public void infernoSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, factionGroup, node -> byndrService.getFilterProvider().hasFaction(Faction.INFERNO));
    }

    public void necropolisSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, factionGroup, node -> byndrService.getFilterProvider().hasFaction(Faction.NECROPOLIS));
    }

    public void sanctuarySelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, factionGroup, node -> byndrService.getFilterProvider().hasFaction(Faction.SANCTUARY));
    }

    public void strongholdSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, factionGroup, node -> byndrService.getFilterProvider().hasFaction(Faction.STRONGHOLD));
    }

    public void neutralSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, factionGroup, node -> byndrService.getFilterProvider().hasFaction(Faction.NEUTRAL));
    }

    public void airSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, schoolGroup, node -> byndrService.getFilterProvider().hasMagicSchool(MagicSchool.AIR));
    }

    public void earthSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, schoolGroup, node -> byndrService.getFilterProvider().hasMagicSchool(MagicSchool.EARTH));
    }

    public void darkSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, schoolGroup, node -> byndrService.getFilterProvider().hasMagicSchool(MagicSchool.DARK));
    }

    public void fireSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, schoolGroup, node -> byndrService.getFilterProvider().hasMagicSchool(MagicSchool.FIRE));
    }

    public void lightSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, schoolGroup, node -> byndrService.getFilterProvider().hasMagicSchool(MagicSchool.LIGHT));
    }

    public void primeSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, schoolGroup, node -> byndrService.getFilterProvider().hasMagicSchool(MagicSchool.PRIMAL));
    }

    public void waterSelectionChanged(ActionEvent actionEvent) {
        handleCheckBoxFilterEvent(actionEvent, schoolGroup, node -> byndrService.getFilterProvider().hasMagicSchool(MagicSchool.WATER));
    }
}