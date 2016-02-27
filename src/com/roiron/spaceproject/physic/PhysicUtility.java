package com.roiron.spaceproject.physic;

//This file is use to have some maths functions, to be able to performate operations on vectors of size
// 2 and 3 (sum2 or sum3)

public class PhysicUtility {
	/**
	 * Measure distance between a and b
	 * @param Point A double[x,y]
	 * @param Point B double[x,y]
	 * @return distance between A and B
	 */
	public static double distance(double[] a,double[] b)
	{
		return Math.sqrt(Math.pow(a[0]-b[0], 2)+Math.pow(a[1]-b[1], 2));
	}
	/**
	 * Normalize a vector a
	 * @param vector A double[x,y]
	 * @return Vector A normalized
	 */
	public static double[] unityVector(double[] a)
	{
		double norm=distance(a,new double[]{0,0});
		double x=a[0]/norm;
		double y=a[1]/norm;
		return new double[]{x,y};
	}
	/**
	 * Get the norm of a 2 dimension vector
	 * @param Vector A double[x,y]
	 * @return the norm
	 */
	public static double norm2(double[] vector)
	{
		return distance(vector,new double[]{0,0});
	}
	/**
	 * get the sum of two 2-dimension vector
	 * @param Vector A double[x,y]
	 * @param Vector B double[x,y]
	 * @return the vector which represent the sum
	 */
	public static double[] sum2(double[] a, double[] b)
	{
		return new double[]{a[0]+b[0],a[1]+b[1]};
	}
	/**
	 * get the difference of two 2-dimension vector
	 * @param Vector A double[x,y]
	 * @param Vector B double[x,y]
	 * @return the vector which represent the difference A-B
	 */
	public static double[] substraction2(double[] a, double[] b)
	{
		return new double[]{a[0]-b[0],a[1]-b[1]};
	}
	/**
	 * get the difference of two 2-dimension vector
	 * @param scalar a
	 * @param Vector B double[x,y]
	 * @return the vector which represent the product [a*x,a*y]
	 */
	public static double[] scalarProd(double lambda, double[] vect)
	{
		return new double[]{lambda*vect[0],lambda*vect[1]};
	}
	/**
	 * Copy a vector of size 2
	 * @param a Vector of size 2
	 * @return a copy of this vector
	 */
	public static double[] copy(double[] a)
	{
		return new double[]{a[0],a[1]};
		
	}

}
		
