package benchmark.ram;

import java.io.IOException;
import java.util.Random;

import benchmark.IBenchmark;
import timing.TimeUnit;
import timing.Timer;

/**
 * Maps a large file into RAM triggering the virtual memory mechanism. Performs
 * reads and writes to the respective file.<br>
 * The access speeds depend on the file size: if the file can fit the available
 * RAM, then we are measuring RAM speeds.<br>
 * Conversely, we are measuring the access speed of virtual memory, implying a
 * mixture of RAM and HDD access speeds (i.e., lower).
 */
public class VirtualMemoryBenchmark implements IBenchmark {
    private MemoryMapper core = null;
    private String result = "";

    @Override
    public void initialize(Object... params) {
    }

    @Override
    public void warmUp() {
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Use run(Object[]) instead");
    }

    @Override
    public void run(Object... options) {
        // expected example: {fileSize, bufferSize}
        Object[] params = (Object[]) options;
        long fileSize = Long.parseLong(params[0].toString()); // e.g. 2-8GB
        int bufferSize = Integer.parseInt(params[1].toString()); // e.g. 4KB


        try {
            core = new MemoryMapper("E:\\JavaExercises\\Writes", fileSize); // change path as needed
            byte[] buffer = new byte[bufferSize];
            Random rand = new Random();
            Timer timer = new Timer();
            // write to VM
            timer.start();
            for (long i = 0; i < fileSize; i += bufferSize) {
                // generate random content (see assignments 9,11)
                rand.nextBytes(buffer);
                core.put(i, buffer);
                // write to memory mapper
            }
            double timeS = TimeUnit.convertTimeDouble(timer.stop(), TimeUnit.SEC);
            double speed = (double)(fileSize / 1024 / 1024L) / timeS; /* fileSize/time MB/s */

            result = "\nWrote " + (fileSize / 1024 / 1024L)
                    + " MB to virtual memory at " + speed + " MB/s";

            // read from VM
            timer.start();
            for (long i = 0; i < fileSize; i += bufferSize) {
                buffer = core.get(i, bufferSize);
            }
            timeS = TimeUnit.convertTimeDouble(timer.stop(), TimeUnit.SEC);
            speed = (double)(fileSize / 1024 / 1024L) / timeS; /* MB/s */

            // append to previous 'result' string
            result += "\nRead " + (fileSize / 1024 / 1024L)
                    + " MB from virtual memory at " + speed +" MB/s";

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (core != null)
                core.purge();
        }
    }

    @Override
    public void clean() {
        if (core != null)
            core.purge();
    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult(int type) {
        return result;
    }

}
