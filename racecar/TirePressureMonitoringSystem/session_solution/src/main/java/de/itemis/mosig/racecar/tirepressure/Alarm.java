package de.itemis.mosig.racecar.tirepressure;

public class Alarm
{
    private static final double LowPressureThreshold = 17;
    private static final double HighPressureThreshold = 21;

    private final Sensor sensor;

    private enum State {
        ACTIVE,
        INACTIVE
    }
    private State state;

    public Alarm(Sensor sensor) {
        this.sensor = sensor;
        this.state = State.INACTIVE;
    }

    public void check()
    {
        double psiPressureValue = sensor.popNextPressurePsiValue();
        state = pressureIsNotInRange(psiPressureValue);
    }

    public boolean isAlarmOn()
    {
        return state == State.ACTIVE;
    }

    private State pressureIsNotInRange(double psiPressureValue) {
        return (psiPressureValue < LowPressureThreshold || HighPressureThreshold < psiPressureValue)?State.ACTIVE:State.INACTIVE;
    }
}
