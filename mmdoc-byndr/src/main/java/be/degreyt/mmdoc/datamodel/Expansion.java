package be.degreyt.mmdoc.datamodel;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by WDH on 26/02/14.
 */
public enum Expansion {
    BASE_SET("b01"), VOID_RISING("s01"), HERALD_OF_THE_VOID("s02"), FORGOTTEN_WARS("s03"), FIVE_TOWERS("s04"), BASE_SET_2("b02"), SPECIAL("rew");

    private final String expansionCode;

    Expansion(String expansionCode) {
        this.expansionCode = expansionCode;
    }

    public static Optional<Expansion> forCode(final String code) {
        return Arrays.stream(values()).filter((e) -> e.expansionCode.equals(code)).findAny();
    }

    public String getExpansionCode() {
        return expansionCode;
    }
}
