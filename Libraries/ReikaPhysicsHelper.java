/*******************************************************************************
 * @author Reika
 * 
 * This code is the property of and owned and copyrighted by Reika.
 * This code is provided under a modified visible-source license that is as follows:
 * 
 * Any and all users are permitted to use the source for educational purposes, or to create other mods that call
 * parts of this code and use DragonAPI as a dependency.
 * 
 * Unless given explicit written permission - electronic writing is acceptable - no user may redistribute this
 * source code nor any derivative works. These pre-approved works must prominently contain this copyright notice.
 * 
 * Additionally, no attempt may be made to achieve monetary gain from this code by anyone except the original author.
 * In the case of pre-approved derivative works, any monetary gains made will be shared between the original author
 * and the other developer(s), proportional to the ratio of derived to original code.
 * 
 * Finally, any and all displays, duplicates or derivatives of this code must be prominently marked as such, and must
 * contain attribution to the original author, including a link to the original source. Any attempts to claim credit
 * for this code will be treated as intentional theft.
 * 
 * Due to the Mojang and Minecraft Mod Terms of Service and Licensing Restrictions, compiled versions of this code
 * must be provided for free. However, with the exception of pre-approved derivative works, only the original author
 * may distribute compiled binary versions of this code.
 * 
 * Failure to comply with these restrictions is a violation of copyright law and will be dealt with accordingly.
 ******************************************************************************/
package Reika.DragonAPI.Libraries;

import Reika.DragonAPI.DragonAPICore;

public final class ReikaPhysicsHelper extends DragonAPICore {

	public static final double TNTenergy = 12420000000D;
	public static final double g = 9.81D;

	/** Converts 3D polar coordinates into cartesian ones. Use angles in degrees. Args: magnitude, theta, phi */
	public static double[] polarToCartesian(double mag, double theta, double phi) {
		double[] coords = new double[3];
		theta = degToRad(theta);
		phi = degToRad(phi);
		coords[0] = mag*Math.cos(theta)*Math.cos(phi);
		coords[1] = mag*Math.sin(theta);
		coords[2] = mag*Math.cos(theta)*Math.sin(phi);
		return coords;
	}

	/** Converts 3D cartesian coordinates into polar ones. Returns angles in degrees, mapped 0-360. Args: x, y, z */
	public static double[] cartesianToPolar(double x, double y, double z) {
		double[] coords = new double[3];
		boolean is90to270 = false;
		coords[0] = ReikaMathLibrary.py3d(x, y, z); //length
		coords[1] = Math.acos(y/coords[0]);
		coords[2] = Math.atan2(x, z);
		coords[1] = radToDeg(coords[1]);
		coords[2] = 180+radToDeg(coords[2]);
		if (is90to270) {
			coords[2] *= -1;
		}
		return coords;
	}

	/** Converts a degree angle to a radian one. Args: Angle */
	public static double degToRad(double ang) {
		return (ang*Math.PI/180);
	}

	/** Converts a degree angle to a radian one. Args: Angle */
	public static double radToDeg(double ang) {
		return (ang*180/Math.PI);
	}

	/** Calculates the required velocity (in xyz cartesian coordinates) required to travel in
	 * projectile motion from point A to point B. Args: start x,y,z end x,y,z, double g */
	public static double[] targetPosn(double x, double y, double z, double x2, double y2, double z2, double g) {
		double[] v = new double[3];
		//TODO This is still being written, as it depends on a reliable value for g
		return v;
	}

	/** Returns a modified value for the inverse-square law, based on the distance and initial magnitude.
	 * Args: Distance x,y,z, initial magnitude */
	public static double inverseSquare(double dx, double dy, double dz, double mag) {
		return mag/(dx*dx+dy*dy+dz*dz);
	}

	/** Returns a float value for MC-scaled explosion power, based off the input energy in joules. Recall TNT has
	 * a float power of 4F, corresponding to a real-energy value of 12.4 Gigajoules. Args: Energy */
	public static float getExplosionFromEnergy(double energy) {
		double ratio = energy/TNTenergy;
		return (float)(4*ratio);
	}
}