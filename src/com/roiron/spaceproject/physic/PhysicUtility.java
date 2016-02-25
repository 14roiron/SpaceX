package com.roiron.spaceproject.physic;

//This file is use to have some maths functions, to be able to performate operations on vectors of size
// 2 and 3 (sum2 or sum3)

class PhysicUtility {

	public static double distance(double[] a,double[] b)
	{
		return Math.sqrt(Math.pow(a[0]-b[0], 2)+Math.pow(a[1]-b[1], 2));
	}
	public static double[] unityVector(double[] a)
	{
		double norm=distance(a,new double[]{0,0});
		double x=a[0]/norm;
		double y=a[1]/norm;
		return new double[]{x,y};
	}
	public static double[] sum2(double[] a, double[] b)
	{
		return new double[]{a[0]+b[0],a[1]+b[1]};
	}
	public static double[] sum3(double[] a, double[] b)
	{
		return new double[]{a[0]+b[0],a[1]+b[1],a[2]+b[2]};
	}
	public static double[] substraction2(double[] a, double[] b)
	{
		return new double[]{a[0]-b[0],a[1]-b[1]};
	}
	public static double[] scalarProd(double lambda, double[] vect)
	{
		return new double[]{lambda*vect[0],lambda*vect[1]};
	}
	public static double[] scalarProd3(double lambda, double[] vect)
	{
		return new double[]{lambda*vect[0],lambda*vect[1],lambda*vect[2]};
	}
	public static double[] copy(double[] a)
	{
		return new double[]{a[0],a[1]};
		
	}

}
		
