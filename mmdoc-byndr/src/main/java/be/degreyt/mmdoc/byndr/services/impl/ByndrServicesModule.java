package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import com.google.inject.AbstractModule;

public class ByndrServicesModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(ByndrService.class).to(ByndrServiceImpl.class);
    }
}
