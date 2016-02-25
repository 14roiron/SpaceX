package com.roiron.spaceproject.physic;

import com.roiron.spaceproject.graphic.GraphicObject;

class PhysicUtility {
	/**
	 * Transform the absolute coordinate to a polar one in the reference system
	 * @param vect state vector we want to convert
	 * @param ref reference system where we want to be
	 * @return the polar coordinate in the reference system
	 */
		public static double[] toPolar(double[] vect, double[] ref)
		{
			double radius=Math.sqrt(Math.pow(vect[0]-ref[0], 2)+Math.pow(vect[1]-ref[1], 2));
			double theta = Math.atan2(vect[1]-ref[1], vect[0]-ref[0]);
			return new double[]{radius,theta};
		}
		public static double[] toCarthe(double[] vect,double[] ref)
		{
			double x=vect[0]*Math.cos(vect[1]+ref[1])+ref[0];
			double y = vect[0]*Math.sin(vect[1]+ref[1])+ref[0];
			return new double[]{x,y};
		}
		public static double[][] toGlobalPolar(double[][] globalState, double[][] reference)
		{
			return new double[][]{toPolar(globalState[0],reference[0]),
				toPolar(globalState[1],reference[1])};
		}
		public static double[][] toGlobalCarther(double[][] globalState,double[][] reference)
		{
			return new double[][]{toCarthe(globalState[0],reference[0]),
				toCarthe(globalState[1],reference[1])};
		}

}
		
