package de.itemis.mosig.racecar.tirepressure;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class AlarmTest {

    private static final double PRESSURE_INTERVAL_LOWER_BOUND = 17d;
    private static final double PRESSURE_INTERVAL_UPPER_BOUND = 21d;

    private Sensor mockedSensor;
    private Alarm underTest;

    @Before
    public void setUp() {
        mockedSensor = Mockito.mock(Sensor.class);
        Mockito.when(mockedSensor.popNextPressurePsiValue()).thenCallRealMethod();
        underTest = createUnderTest(mockedSensor);
    }

    @Test
    public void alarmShouldBeOffAfterConstruction() {
        assertEquals(false, underTest.isAlarmOn());
    }

    @Test
    public void alarmShouldGoOffIfPressureIsBelowInterval() {
        setupNextPressureValue(PRESSURE_INTERVAL_LOWER_BOUND - 1);

        underTest.check();
        assertEquals(true, underTest.isAlarmOn());
    }

    @Test
    public void alarmShouldGoOffIfPressureIsAboveInterval() {
        setupNextPressureValue(PRESSURE_INTERVAL_UPPER_BOUND + 1);

        underTest.check();
        assertEquals(true, underTest.isAlarmOn());
    }

    @Test
    public void alarmShouldStopIfPressureIsBackWithinInterval() {
        setupNextPressureValue(PRESSURE_INTERVAL_UPPER_BOUND + 1);
        underTest.check();
        setupNextPressureValue(PRESSURE_INTERVAL_UPPER_BOUND - 1);
        underTest.check();

        assertEquals(false, underTest.isAlarmOn());
    }

    /*
     * #### start of private helper code ####
     */

    private Alarm createUnderTest(Sensor sensor) {
        return new Alarm(sensor);
    }

    private void setupNextPressureValue(double nextValue) {
        Mockito.when(mockedSensor.popNextPressurePsiValue()).thenReturn(nextValue);
    }
}
