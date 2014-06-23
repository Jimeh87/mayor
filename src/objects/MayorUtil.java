package objects;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Jim
 * Get random enum from enum class passed in
 */
public class MayorUtil {
	private static final Random random = new SecureRandom();
	
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){	
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
