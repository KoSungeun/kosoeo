package com.study.block02;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class EcdsaTest2 {

	public static void main(String[] args) {
		// 비대칭키 생성 하기
		PublicKey publicKey = null;
		PrivateKey privateKey = null;
		
		Security.addProvider(new BouncyCastleProvider());
		
		
		try {
			// 바운시 캐슬의 타원 곡선 표준 알고리즘(ECDSA)을 사용한다, / ("EC", "SunEC")
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			// 타원 곡선의 세부 알고리즘으로 prime192v1을 사용한다. / sect163k1
			ECGenParameterSpec ecSpec= new ECGenParameterSpec("prime192v1");
			
			// Initialize the key generaotr
			keyGen.initialize(ecSpec, random);
			// Generate a KeyPair
			// - 256 bytes provides an acceptable security level
			KeyPair keyPair = keyGen.generateKeyPair();
			
			// Get the public and private keys from the keyPair
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		// Test public and private keys
		System.out.println(privateKey);
		System.out.println(publicKey);
		System.out.println("Private key : " + BlockUtil.getStringFromKey(privateKey));
		System.out.println("Public key : " + BlockUtil.getStringFromKey(publicKey));
		

	}
	
}
