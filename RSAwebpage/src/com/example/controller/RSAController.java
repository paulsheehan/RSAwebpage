package com.example.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RSAController {
	
	private static RSAController RSASingleton = new RSAController();
	private BigInteger p, q, totient, m;
	private BigInteger n, e, d;
	CryptoController crypt;
	
	public RSAController()
	{
		generateRandomBigIntegers();
	}
	
	public void onWelcome()
	{
		crypt = new CryptoController();
		setN();
		calculateTotient();
		setE();
		setD();
	}
	
	public static RSAController getInstance()
	{
		return RSASingleton;
	}
	
	public void onStringInput(HttpServletRequest request, String message)
	{
		int i;
		InputHandler inputHandler = new InputHandler();
		
		byte[] b;
		b = inputHandler.getByteValue(message);
		BigInteger[] arrayOfBigInts = encryptByteArray(b);
		String msg[] = new String[arrayOfBigInts.length];
		
		HttpSession session = request.getSession();
		
		session.setAttribute("encryptedMessage", arrayOfBigInts);
	}
	
	public void onStringDecrypt(HttpServletRequest request)
	{
		System.out.println(d);
		HttpSession session = request.getSession();
		BigInteger[] arrayOfBigInts = (BigInteger[]) session.getAttribute("encryptedMessage");
		//BigInteger[] arrayOfSmallInts = 
		decryptMessage(arrayOfBigInts);
		for(int i = 0; i < arrayOfBigInts.length; i++)
		{
			System.out.println(arrayOfBigInts[i]);
		}
		session.setAttribute("decryptedMessage", arrayOfBigInts);
	}
	
	public void onFileInput(HttpServletRequest request, byte[] b)
	{
		BigInteger[] arrayOfBigFileInts = encryptByteArray(b);
		HttpSession session = request.getSession();
		
		session.setAttribute("encryptedFile", arrayOfBigFileInts);
	}
	
	public void generateRandomBigIntegers()
	{
		SecureRandom rand = new SecureRandom();
		p = new BigInteger(128, 2, rand);
		rand.nextInt();
		q = new BigInteger(128, 2, rand);
		
		while(!primeChecker())
		{
			p.nextProbablePrime();
			q.nextProbablePrime();
		}
	}
	
	public boolean primeChecker()
	{
		boolean itsPrime = false; 
		if(p.isProbablePrime(2) && q.isProbablePrime(2))
		{
			itsPrime = true;
		}
		
		return itsPrime;
	}
	
	public void setN()
	{
		n = p.multiply(q);
	}
	
	public void calculateTotient()
	{
		
		totient = new BigInteger(p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1))).toString());	//totient = (p-1)*(q-1)
		
	}

	public void setE()
	{
		e = BigInteger.valueOf(127);	//I chose 127 as my public key
		
		EuclideanController euchll = new EuclideanController(n, e);
		
		//If the public key is not coprime with with n and the totient then the public key will be incremented until it is
		while((euchll.calculateGCD(n, e, BigInteger.valueOf(0)).compareTo(BigInteger.valueOf(1)) != 0) && (euchll.calculateGCD(totient, e, BigInteger.valueOf(0)).compareTo(BigInteger.valueOf(1)) != 0));
		{
			e =e.add(BigInteger.valueOf(1));
		}
	}
	
	public void setD()
	{
		EuclideanController euch = new EuclideanController(totient, e);
		d = euch.extendedEuch(totient, e);
	}
	
	
	public BigInteger[] encryptByteArray(byte[] b)
	{
		CryptoController crypth = new CryptoController();
		int i;
		BigInteger[] accum = new BigInteger[b.length];
		
		for(i = 0; i < b.length; i++)
		{
			accum[i] = crypth.encrypt(BigInteger.valueOf(b[i]), n, e);
		}
		
		return accum;
		
	}
	
	public void decryptMessage(BigInteger[] m)
	{
		//CryptoController decrypt = new CryptoController();
		//decrypt.decrypt(m, n, d);
		
		
	}
}
