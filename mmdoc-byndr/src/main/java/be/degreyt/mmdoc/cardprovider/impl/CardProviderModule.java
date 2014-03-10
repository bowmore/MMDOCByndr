package be.degreyt.mmdoc.cardprovider.impl;

import be.degreyt.mmdoc.cardprovider.CardLoader;
import be.degreyt.mmdoc.cardprovider.CardProvider;
import com.google.inject.AbstractModule;

public class CardProviderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CardLoader.class).to(CardLoaderImpl.class);
    }
}
