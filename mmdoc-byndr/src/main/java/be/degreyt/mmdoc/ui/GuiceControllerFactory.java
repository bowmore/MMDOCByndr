package be.degreyt.mmdoc.ui;

import com.google.inject.Injector;
import javafx.util.Callback;

class GuiceControllerFactory implements Callback<Class<?>, Object> {

    private final Injector injector;

    public GuiceControllerFactory(Injector anInjector) {
        injector = anInjector;
    }

    @Override
    public Object call(Class<?> aClass) {
        return injector.getInstance(aClass);
    }
}