import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static BigInteger multiplied(int p, int q) {
		int result=p*q;
		BigInteger res = BigInteger.valueOf(result);
		return res;
		
	}
	public static int phi(int p, int q) {
		int result = (p - 1) * (q - 1);
		return result;
		
	}
	public static ArrayList<Integer> getFactor(int x) {
		ArrayList<Integer> factors=new ArrayList<Integer>();
		for(int i=1; i<=x; i++) {
			if(x%i==0) {
				factors.add(i);
			}
		}
		return factors;
		
	}
	
	public static boolean coPrime(ArrayList<Integer> x ,ArrayList<Integer> y){		
		int max=0;
		ArrayList <Integer> commonFactor = new ArrayList<Integer>();
	    for(int i=0; i<x.size(); i++) {
	    	for(int j=0; j<y.size(); j++) {
	    		if(x.get(i)==y.get(j)) {
	    			commonFactor.add(x.get(i));
	    		}
	    	}
	    }
	    for(int i=0; i<commonFactor.size(); i++) {
	    	if(commonFactor.get(i)>max) {
	    		max=commonFactor.get(i);
	    	}
	    }
		if(max!=1) {
			return false;	
		}
		else {
			return true;	
		}
		
	}
	
	public static int preCoPrime(int phi, BigInteger n) {
		ArrayList<Integer> y=getFactor(phi);
		Random r = new Random();
		while(true) {	
		int low = 2;
		int high = phi;
		int e = r.nextInt(high-low) + low;
		ArrayList<Integer> x=getFactor(e);		
		if(coPrime(x,y)==true) {
			 return e;
		 }
		}
	}
	public static int computeD(int e, int phi) {
		int counterD=0;
		while(true) {
			if((counterD*e)%phi==1) {
				return counterD;
			}
				counterD++;
		}
		
	}

	public static BigInteger encryption(BigInteger message,int e, BigInteger n) {
	    BigInteger encrypted= (message.pow(e)).mod(n);  
	    return encrypted;
	}

	public static BigInteger decryption(BigInteger encrypted,int d, BigInteger n) {   
		 int IntValue1 = encrypted.intValue();
		 int IntValue2 = n.intValue();
	     BigInteger decrypted;
	     BigInteger N = BigInteger.valueOf(IntValue2);
	     BigInteger C = BigDecimal.valueOf(IntValue1).toBigInteger();
	     decrypted = (C.pow(d)).mod(N);      
		 return decrypted;
	}
	
	public static void main(String[] args)  {
		while(true) {
		Scanner s= new Scanner(System.in);
		System.out.println();
		System.out.println("Enter the message to be encrypted:");
		int p= 97;
		int q=89;
		BigInteger n= multiplied(p,q);
		BigInteger message=s.nextBigInteger();
		int M= message.intValue();
		int N=n.intValue();
		if(M>N) {
			System.out.println("Please Try Again ");	
			System.out.println("For correct Encryption and Decryption, the message to be encrypted should be less than N" +"("+n+")");	
			s.close();
			return;
		}
		System.out.println("N: "+n);
		int phi= phi(p,q);
		System.out.println("PHI: "+phi);
		int e=preCoPrime(phi,n);
		System.out.println("E: "+e);
		int d=computeD(e,phi);
		System.out.println("D: "+d);
		BigInteger encrypted=encryption(message,e,n);
		BigInteger decrypted= decryption(encrypted,d,n);
		System.out.println("Message after Encryption: "+ encrypted);
		System.out.println("Message after Decryption: "+ decrypted);
		
		}
	}

	
}
