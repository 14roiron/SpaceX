package com.roiron.spaceproject.physic;

import com.roiron.spaceproject.graphic.GraphicObject;

class PhysicUtility {
		public static double[] toPolar(double[] vect)
		{
			double radius=Math.sqrt(Math.pow(vect[0], 2)+Math.pow(vect[1], 2));
			double theta = Math.atan2(vect[1], vect[0]);
			return new double[]{radius,theta};
		}
		public static double[] toCarthe(double[] vect)
		{
			double x=vect[0]*Math.cos(vect[1]);
			double y = vect[0]*Math.sin(vect[1]);
			return new double[]{x,y};
		}
		public static double[][] toGlobalPolar(double[][] globalState)
		{
			return new double[][]{toPolar(globalState[0]),toPolar(globalState[1])};
		}
		public static double[][] toGlobalCarther(double[][] globalState)
		{
			return new double[][]{toCarthe(globalState[0]),toCarthe(globalState[1])};
		}

}
		
