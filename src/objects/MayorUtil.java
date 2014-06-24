package objects;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Jim
 * Main util service
 */
public class MayorUtil {
	private static final Random random = new SecureRandom();
	
    /**
     * Get random enum from enum class passed in
     * @param clazz
     * @return
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){	
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
