package tramways;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
	public static String readJson(String file) {
		try {
			byte[] raw = Files.readAllBytes(Paths.get("src/test/resources/" + file));
			return new String(raw, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
