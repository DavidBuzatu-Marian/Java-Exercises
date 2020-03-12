package logging;

import timing.TimeUnit;

public class DataBaseLogger implements ILogger {
    @Override
    public void write(long time) {

    }

    @Override
    public void write(String text) {

    }

    @Override
    public void write(Object... parameters) {

    }

    @Override
    public void close() {

    }

    @Override
    public void writeTime(long value, TimeUnit unit) {
        String convertedTime = TimeUnit.convertTime(value, unit);
        System.out.println(convertedTime);
    }

    @Override
    public void writeTime(String string, long value, TimeUnit unit) {
        String convertedTime = TimeUnit.convertTime(value, unit);
        System.out.println(string + " " + convertedTime);
    }
}
