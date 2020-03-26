package org.fire.common.util;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
public class RSACoder {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    public static byte[] decryptBASE64(String key) {
        return Base64.getDecoder().decode(key);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key)
            throws Exception {
        return decryptByPrivateKey(decryptBASE64(data),key);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap<String, Key>(2);
        
        PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        
        byte[] publicKeyData = publicKey.getEncoded();
       	byte[] privateKeyData = publicKey.getEncoded();
        
       	System.out.println("publicKeyData>>>>>>>>>>>"+publicKeyData);
    	System.out.println("privateKeyData>>>>>>>>>>>"+privateKeyData);
        keyMap.put(PUBLIC_KEY, publicKey);// 公钥
        keyMap.put(PRIVATE_KEY, privateKey);// 私钥
        return keyMap;
    }
    
    
    private static String publicKey="XX";
    private  static String privateKey="CC";
    public static String RSADecode(String inputStr){
   	    System.err.println("公钥加密——私钥解密");
        byte[] encodedData;
        String outputStr="";
		try {
	         privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ7h2vVsqRbW8gWCm/PNpOs3VAGOWFXg0rmYzaLY6vHhbwyIuD62Ds9bAqvWUVxVh02w1h94SnpvC+g/h5yj93wioGobthe2ag2wRnFDlQ4GItI+gIxDBGr3pOF+Eq4BVvAmNd4mnw0HsBIq2z75dm8U9RTotWdMm9kxhj4gWfs1AgMBAAECgYEAjwyO1bWVsCirE2b6/tHCkLzp99Svl/KqB8yaKOrg7hKop0JZkKzePLeWehymeFnRTpPO1dq6Q+3DbMH2WYQPh9mcZuFNTcX7VSyg2ibbZxHCAco4ZZWkWZjum8cdIockkHUeUHekvLNPnYW+7fIF1A9LCEQkfywSKdCHVz/mfMECQQDfZXuKQ/6zKxmn1yRN0T01t48ptsGpxScvNJzdZHAmMTIlSGEcf+AVMJ8MJ6a4HcEQLsmtjbzvCwuysJaRAAv9AkEAthH//jZO1L/R/i/gXhmfqX9i2w8Nf69KCtE9dirqX2PfMeFk9ffmz2gG0Lowz8Xl2c9CW6W2N0bL3q04ZHhlmQJBAJcJUMZBhHF0YWslvQjTv7OSwDsusa7rebujsa7JWj2oI0QrtGSFXGZXMiEQqdpvW3lqeLZbTvK7IBSW6aVtR60CQG6qtDo1kmb4jVB+k1c/JfAl0NWr6Z2R02SN2FjqLvl6QCEWioKDg1TQO0xF0AOKm3Jv6zq/l+kzbBnWmi9fiwkCQGcc9Ybl11Wvl8nW1Qojxg85+UqYyHTDNe46MMh3q3aby0qSVeFIA43D/yaTd8/sh/SJ5eHuNNMEusPz/ynD1qU=";
			 encodedData = RSACoder.encryptByPublicKey(inputStr, publicKey);
			  byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
		                 privateKey);
			   outputStr = new String(decodedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
      
      
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		return outputStr;
   	
   
    	
    	
    }
   

}



