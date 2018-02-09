package de.itemis.mosig.racecar.tirepressure;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/*
 * wir laufen aus dem Interval raus (oben) -> alarm an
 * wir laufen aus dem Interval raus (unten) -> alarm an
 * wir laufen in das Interval hinein (von oben) -> alarm aus
 * wir laufen in das Interval hinein (von unten) -> alarm aus
 *
 */
public class AlarmTest {
    private Alarm alarm;
    private Sensor sensorMock;

    @Before
    public void instatiateVars(){
        sensorMock = Mockito.mock(Sensor.class);
        alarm = new Alarm(sensorMock);
    }

    private void setNextSensorValue(double value) {
        Mockito.when(sensorMock.popNextPressurePsiValue()).thenReturn(value);
    }

    @Test
    public void initiallyAlarmShouldBeOff() {
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void alarmGoesOffIfPressureExceedsUpperBound() {
        setNextSensorValue(22d);
        alarm.check();
        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void alarmGoesOffIfPressureExceedsLowerBound() {
        setNextSensorValue(16d);
        alarm.check();
        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void alarmIsOffForValueBetweenBounds() {
        setNextSensorValue(18d);
        alarm.check();
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void alarmTurnsOffAfterValueWasBelowLowerBound() {
        setNextSensorValue(16d);
        alarm.check();
        assertEquals(true, alarm.isAlarmOn());

        setNextSensorValue(18d);
        alarm.check();
        assertEquals(false, alarm.isAlarmOn());
    }
}
