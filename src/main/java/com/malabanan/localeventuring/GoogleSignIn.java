package com.malabanan.localeventuring;

public class GoogleSignIn {

	private static String tokenId;

	public static String getTokenId() {
		return tokenId;
	}

	public static void setTokenId(String tokenId) {
		GoogleSignIn.tokenId = tokenId;
	}
}
