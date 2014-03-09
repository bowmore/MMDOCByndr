package be.degreyt.mmdoc.ui;

import com.google.inject.AbstractModule;

public class UIModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MainController.class).to(MainController.class);
    }
}
