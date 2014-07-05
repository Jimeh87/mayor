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
    
    /**
     * Solves a^2 + b^2 = c^2
     * Must fill in all but one result. Will return null if all three parms are filled in. NPE if less than two are filled in.
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static Double pythagorean(Double a, Double b, Double c) {
    	Double result = null;
    	
    	if (a == null) {
    		result = Math.sqrt((c * c) - (b * b));
    	} else if (b == null) {
    		result = Math.sqrt((c * c) - (a * a));
    	} else if (c == null) {
    		result = Math.sqrt((a * a) + (b * b));
    	}
    	
    	return result;
    }
    
    public static Double calculateDistance(Tile tile1, Tile tile2) {
    	double a = Math.abs(tile1.getXLocation() - tile2.getXLocation());
    	double b = Math.abs(tile1.getYLocation() - tile2.getYLocation());
    	return pythagorean(a, b, null);
    }
}
