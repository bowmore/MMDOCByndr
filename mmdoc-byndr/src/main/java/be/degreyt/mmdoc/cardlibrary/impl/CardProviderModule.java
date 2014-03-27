package be.degreyt.mmdoc.cardlibrary.impl;

import be.degreyt.mmdoc.cardlibrary.CardLoader;
import be.degreyt.mmdoc.cardlibrary.CardParser;
import be.degreyt.mmdoc.cardlibrary.DataFileEnumerator;
import com.google.inject.AbstractModule;

public class CardProviderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CardLoader.class).to(CardLoaderImpl.class);
        bind(CardParser.class).to(StaxCardParser.class);
        bind(DataFileEnumerator.class).to(DataFileEnumeratorImpl.class);
    }
}
