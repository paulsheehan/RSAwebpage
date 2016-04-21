package com.example.controller;

import java.math.BigInteger;

public class CryptoController {

	char[] alphabet;
	
	CryptoController()
	{
		alphabet = new char[26];
		
		for(char c = 65; c < (65+26); c++)
		{
			alphabet[c-65] = c;
		}
	}
	
	public BigInteger encrypt(BigInteger m, BigInteger n, BigInteger e)
	{
		//c = m^e mod n
		
		BigInteger c;
		
		c = m.pow(e.intValue());
		c = c.mod(n);
		
		return c;
	}
	
	public void decrypt(BigInteger[] c, BigInteger n, BigInteger d)
	{
//		BigInteger m;
//		BigInteger temp;
//		System.out.println(d);
//		for(int i = 0; i < c.length; i++)
//		{
//			temp = c[i].pow(d.intValue());
//			m= temp.mod(n);
//			System.out.println(m);
//		}
		//m = c.pow(d.intValue());
		//m = m.mod(n);
		//return m;
	}
}

