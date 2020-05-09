package benchmark.hdd;

import benchmark.IBenchmark;

import java.io.IOException;

public class HDDWriteSpeed implements IBenchmark {

	@Override
	public void initialize(Object... params) {
	}

	@Override
	public void warmUp() {
	}

	@Override
	public void run() {
		throw new UnsupportedOperationException(
				"Method not implemented. Use run(Object) instead");
	}

	@Override
	public void run(Object... options) {
		FileWriter writer = new FileWriter();
		// either "fs" - fixed size, or "fb" - fixed buffer
		String option = (String) options[0];
		// true/false whether the written files should be deleted at the end
		Boolean clean = (Boolean) options[1];

		String prefix = "E:\\JavaExercises\\Writes\\write-";
		String suffix = ".dat";
		int startIndex = 0;
		int endIndex = 8;
		long fileSize = 512 * 1024 * 1024; // 256 MB
		int bufferSize = 1 * 1024; // 1 KB
		
		try {
			if (option.equals("fs"))
				writer.streamWriteFixedSize(prefix, suffix, startIndex,
						endIndex, fileSize, clean);
			else if (option.equals("fb"))
				writer.streamWriteFixedBuffer(prefix, suffix, startIndex,
						endIndex, bufferSize, clean);
			else
				throw new IllegalArgumentException("Argument "
						+ options[0].toString() + " is undefined");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clean() {
	}

	@Override
	public void cancel() {

	}

	@Override
	public String getResult(int type) {
		return null;
	}
}
