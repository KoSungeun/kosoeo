package com.study.block05;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class Wallet {

	public PrivateKey privateKey;	// 개인키
	public PublicKey publicKey;		// 공개키
	
	public Wallet() {
		// 지갑이 생성될 때 개인키와 공개키를 생성한다.
		generateKeyPair();
	}
	
	public void generateKeyPair() {
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
	}
}
