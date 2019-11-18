package com.study.block05;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;



public class EcdsaSignatureTest {

	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		
		try {
			// 지갑별 비대칭키 생성
			Wallet walletA = new Wallet();
			
			// walletA의 개인키와 공개키를 특정한 파일 이름으로 저장
			BlockUtil.writePemFile(walletA.privateKey, "ECDSA PRIVATE KEY", "privateECDSA.pem");
			BlockUtil.writePemFile(walletA.publicKey, "ECDSA PUBLIC KEY", "publicECDSA.pem");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		// =============================================================================
		
		try {
			// walletA의 비대칭키 파일로부터 생성 하기
			PublicKey publicKey = BlockUtil.readPublicKeyFromPemFile("ECDSA", "publicECDSA.pem");
			PrivateKey privateKey = BlockUtil.readPrivateKeyFromPemFile("ECDSA", "privateECDSA.pem");
			
			// 지갑별 비대칭키 생성
			Wallet walletB = new Wallet();
			
			String data = "A가 B에게 100코인을 전송";
			
			// 서명
			byte[] signature = BlockUtil.applyECDSASig(privateKey, data);
			
			// 서명한 데이터를 검증 - A가 서명한게 맞니?
			boolean result1 = BlockUtil.verifyECDSASig(publicKey, data, signature);
			System.out.println(result1);
			
			// 서명한 데이터를 검증 - B가 서명한게 맞니?
			boolean result2 = BlockUtil.verifyECDSASig(walletB.publicKey, data, signature);
			System.out.println(result2);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
