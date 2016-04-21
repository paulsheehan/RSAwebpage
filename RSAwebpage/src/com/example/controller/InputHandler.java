package com.example.controller;

import java.math.BigInteger;

public class InputHandler {

	char[] alphabet;
	int i, j;
	
	InputHandler()
	{
		alphabet = new char[57];
		
		for(char c = 65; c < (65+alphabet.length); c++)
		{
			alphabet[c-65] = c;
		}
	}
	
	public byte[] getByteValue(String m)
	{
		byte[] b = m.getBytes();

		
//		BigInteger numericValue = new BigInteger("0");
//		
//		for(i = 0; i < m.length(); i++)
//		{
//			for(j = 0; j < alphabet.length; j++)
//			{
//				if(m.charAt(i) == alphabet[j])
//				{
//					numericValue = numericValue.add(BigInteger.valueOf(j));
//				}
//			}
//		}
		return b;
	}
}
