package de.itemis.mosig.racecar.tirepressure;

public class Alarm
{
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;

    private final Sensor sensor;

    private boolean alarmOn = false;

    public Alarm(Sensor sensor) {
        this.sensor = sensor;
    }

    public void check()
    {
        double psiPressureValue = sensor.popNextPressurePsiValue();

        if (psiPressureValue < LowPressureThreshold || HighPressureThreshold < psiPressureValue)
        {
            alarmOn = true;
        }
    }

    public boolean isAlarmOn()
    {
        return alarmOn; 
    }
}
