package de.itemis.mosig.racecar.tirepressure;


import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class AlarmTest {

    @Test
    public void alarmShouldBeOffAfterConstruction() {
        Alarm alarm = new Alarm(new Sensor());
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void alarmShouldGoOffIfPressureIsBelowInterval() {
        Sensor mockedSensor = Mockito.mock(Sensor.class);
        Mockito.when(mockedSensor.popNextPressurePsiValue()).thenReturn(17d);

        Alarm alarm = new Alarm(mockedSensor);
        alarm.check();
        assertEquals(true, alarm.isAlarmOn());
    }
}
