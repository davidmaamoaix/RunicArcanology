package cn.davidma.runicarcanology.util;

import java.util.Random;

import net.minecraft.util.math.Vec3i;

public class MathHelper {

	private static Random random;
	
	public static void init() {
		random = new Random();
	}
	
	public static double distance(double fromX, double fromY, double fromZ, double toX, double toY, double toZ) {
		double a = Math.pow(toX - fromX, 2);
		double b = Math.pow(toY - fromY, 2);
		double c = Math.pow(toZ - fromZ, 2);
		
		return Math.sqrt(a + b + c);
	}
	
	public static double oscillate(double time, double min, double max) {
		return min + (Math.sin(Math.toRadians(time)) + 1) / 2 * (max - min);
	}
	
	public static double randomDouble(double min, double max) {
		return min + random.nextDouble() * (max - min);
	}
	
	public static float randomFloat(float min, float max) {
		return min + random.nextFloat() * (max - min);
	}
	
	public static int randomInt(int min, int max) {
		return min + random.nextInt(max - min);
	}
}
