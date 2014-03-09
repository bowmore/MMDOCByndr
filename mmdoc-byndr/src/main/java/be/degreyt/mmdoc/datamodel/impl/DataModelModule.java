package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.CardBuilder;
import com.google.inject.AbstractModule;

public class DataModelModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CardBuilder.class).to(CardBuilderImpl.class);
    }
}
