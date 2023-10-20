package andreademasi.entities;

import java.util.Random;

public enum Periodicity {
    SETTIMANALE, MENSILE, SEMESTRALE;

    private static final Random PRNG = new Random();

    public static Periodicity randomPeriodicity() {
        Periodicity[] periodicities = values();
        return periodicities[PRNG.nextInt(periodicities.length)];
    }
}
