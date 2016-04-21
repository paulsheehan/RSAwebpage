package com.example.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EuclideanController {
	
//	BigInteger lastX;
//	BigInteger firstX;
//	BigInteger lastY;
//	BigInteger firstY;
//	BigInteger temp;
	
//	BigInteger a;
//	BigInteger b;
	
	EuclideanController(BigInteger x, BigInteger y)
	{
//		firstX = x;
//		firstY = y;
	}
	
	public BigInteger calculateGCD(BigInteger x, BigInteger y, BigInteger r)
	{	

		BigInteger quotient = calculateQuotient(x, y);
		
		r = x.subtract(y.multiply(quotient));
		
		if(r.compareTo(BigInteger.valueOf(1)) < 0)	//r is the remainder which is the base case
		{
			BigInteger GCD = y;	//gcd is the last y in our alg because variables have not been shifted yet
			return GCD;	//return answer
		}
		else
		{
			x = y;	//shifting of variables
			y = r;	//moving onto next step in the alg
			//System.out.println("x:" + x + "y:" + y + "q:" + quotient);
			return calculateGCD(x, y, r);	//function calls itself
		}
	}
	
	public BigInteger calculateQuotient(BigInteger x, BigInteger y)
	{
		BigInteger quotient = x.divide(y); //round down number

		return quotient;
	}
	
public BigInteger extendedEuch(BigInteger a, BigInteger b)
{
	// I referenced this site http://www.sanfoundry.com/java-program-extended-euclid-algorithm/
	BigInteger x = BigInteger.valueOf(0), y = BigInteger.valueOf(1), lastx = BigInteger.valueOf(1), lasty = BigInteger.valueOf(0), temp;
	BigInteger t = a;
	while (b.compareTo(BigInteger.valueOf(0)) != 0)
    {
    	BigInteger q = a.divide(b);
    	BigInteger r = a.mod(b);
        
        a = b;
        b = r;
        
        temp = x;
        x = lastx.subtract(q.multiply(x));
        lastx = temp;

        temp = y;
        y = lasty.subtract(q.multiply(y));
        lasty = temp;            
    }
    
    if(lasty.compareTo(BigInteger.valueOf(0)) != 1)
    {
    	lasty = t.add(lasty);
    }
    
    return lasty;
}
}