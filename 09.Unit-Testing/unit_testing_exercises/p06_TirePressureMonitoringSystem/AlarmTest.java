package p06_TirePressureMonitoringSystem;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AlarmTest {

    @Test
    public void testAlarmShouldBeOnWhenPressureIsMoreThanHIGH_PRESSURE_THRESHOLD(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(21.4);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmShouldBeOnWhenPressureIsLessThanLOW_PRESSURE_THRESHOLD(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(16.4);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmShouldBeOffWhenPressureIsInBounds(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(17.1);
        alarm.check();
        assertFalse(alarm.getAlarmOn());
    }
}