package de.itemis.mosig.racecar.tirepressure;

import java.util.Random;

public class Sensor {
    private static final double OFFSET = 16;

    private final Random basicRandomNumbersGenerator;

    public Sensor(Random rnd) {
        this.basicRandomNumbersGenerator = rnd;
    }


    public double popNextPressurePsiValue() {
        return OFFSET + samplePressure();
    }

    private double samplePressure() {
        return 6 * basicRandomNumbersGenerator.nextDouble() * basicRandomNumbersGenerator.nextDouble();
    }
}
