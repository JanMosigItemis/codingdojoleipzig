package de.itemis.mosig.racecar.tirepressure;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlarmTest {

    @Test
    public void alarmShouldBeOffAfterConstruction() {
        Alarm alarm = new Alarm();
        assertEquals(false, alarm.isAlarmOn());
    }
}
