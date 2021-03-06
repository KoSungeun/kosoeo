package com.study.blockchain05;

import java.util.Date;

public class Block {
	public String hash;
	public String previousHash;
	public String data;
	public long timeStemp;
	public int nonce;
	
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStemp = new Date().getTime();
		this.hash = calculateHash();
	}
	public String calculateHash() {
		String calculatedhash = BlockUtil.applySha256(previousHash + Long.toString(timeStemp) + Integer.toString(nonce) + data);
		return calculatedhash;
	}
	
	// 난이도를 적용하여 채굴
	public void mineBlock(int difficulty) {
		String target = BlockUtil.getDifficultyString(difficulty);
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
			//System.out.printf("%8d : %s\n", nonce, hash);
		}
		System.out.printf("Block MIned!!! : %8d : %s\n", nonce, hash);
		

	}
}
