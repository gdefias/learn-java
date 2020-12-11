package Security.encrypt;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Defias on 2020/08.
 * Description: 非对称加密   --RAS
 */

public class TestRSA {
    public static void main(String[] args) throws Exception {
        // 明文:
        byte[] plain = "Hello, encrypt use RSA".getBytes("UTF-8");

        // 创建公钥／私钥对:
        Person alice = new Person("Alice");
        // 用Alice的公钥加密:
        byte[] pk = alice.getPublicKey();
        System.out.println(String.format("public key: %x", new BigInteger(1, pk)));
        byte[] encrypted = alice.encrypt(plain);
        System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));

        // 用Alice的私钥解密:
        byte[] sk = alice.getPrivateKey();
        System.out.println(String.format("private key: %x", new BigInteger(1, sk)));
        byte[] decrypted = alice.decrypt(encrypted);
        System.out.println(new String(decrypted, "UTF-8"));
    }


    static class Person {
        String name;
        // 私钥:
        PrivateKey sk;
        // 公钥:
        PublicKey pk;

        public Person(String name) throws GeneralSecurityException {
            this.name = name;
            // 生成公钥／私钥对:
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
            kpGen.initialize(1024);
            KeyPair kp = kpGen.generateKeyPair();
            this.sk = kp.getPrivate();
            this.pk = kp.getPublic();
        }

        // 把私钥导出为字节
        public byte[] getPrivateKey() {
            return this.sk.getEncoded();
        }

        // 把公钥导出为字节
        public byte[] getPublicKey() {
            return this.pk.getEncoded();
        }

        // 用公钥加密:
        public byte[] encrypt(byte[] message) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.pk);
            return cipher.doFinal(message);
        }

        // 用私钥解密:
        public byte[] decrypt(byte[] input) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, this.sk);
            return cipher.doFinal(input);
        }

        //从byte[]数组恢复公钥
        public PublicKey revoverpk(byte[] pkData) throws GeneralSecurityException {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            // 恢复公钥:
            X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(pkData);
            PublicKey pk = kf.generatePublic(pkSpec);
            return pk;
        }

        //从byte[]数组恢复私钥
        public PrivateKey revoversk(byte[] skData) throws GeneralSecurityException {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            // 恢复私钥:
            PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(skData);
            PrivateKey sk = kf.generatePrivate(skSpec);
            return sk;
        }

    }

}

