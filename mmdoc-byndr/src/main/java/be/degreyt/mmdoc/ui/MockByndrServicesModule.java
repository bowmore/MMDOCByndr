package be.degreyt.mmdoc.ui;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import be.degreyt.mmdoc.byndr.services.CardCollection;
import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.*;
import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

import java.util.Set;

public class MockByndrServicesModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ByndrService.class).to(MockByndrService.class);
    }

    private static class MockByndrService implements ByndrService {
        @Override
        public CardCollection load() {
            return new MockCardCollection();
        }
    }

    private static class MockCardCollection implements CardCollection {
        @Override
        public Set<CardOwnership> ownerships() {
            ImmutableSet.Builder<CardOwnership> builder = ImmutableSet.builder();
            for (int i = 0; i < 100; i++) {
                builder.add(new CardOwnershipImpl("Card " + i));
            }
            return builder.build();
        }

        @Override
        public int totalWildCardCost() {
            return 0;
        }

        @Override
        public int totalWildcardCost(Rarity rarity) {
            return 0;
        }
    }

    private static class CardOwnershipImpl implements CardOwnership {
        private final String name;

        private CardOwnershipImpl(String name) {
            this.name = name;
        }

        @Override
        public Card getCard() {
            return new CardImpl(name);
        }

        @Override
        public int ownedCopies() {
            return 0;
        }

        @Override
        public int wantedCopies() {
            return 0;
        }

        @Override
        public int required() {
            return 0;
        }
    }

    private static class CardImpl implements Card {
        private final String name;

        private CardImpl(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Faction getFaction() {
            return null;
        }

        @Override
        public Set<ExpansionInfo> getExpansionInfo() {
            return null;
        }

        @Override
        public int getWildCardCost() {
            return 0;
        }

        @Override
        public Set<Format> getAllowedFormats() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }
    }
}
