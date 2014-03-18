package be.degreyt.mmdoc.cardprovider.impl;

import be.degreyt.mmdoc.cardprovider.CardLoader;
import be.degreyt.mmdoc.cardprovider.CardParser;
import be.degreyt.mmdoc.cardprovider.CardProvider;
import be.degreyt.mmdoc.cardprovider.DataFileEnumerator;
import com.google.inject.AbstractModule;

public class CardProviderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CardLoader.class).to(CardLoaderImpl.class);
        bind(CardParser.class).to(StaxCardParser.class);
        bind(DataFileEnumerator.class).to(DataFileEnumeratorImpl.class);
    }
}
