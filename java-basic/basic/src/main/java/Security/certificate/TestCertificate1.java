package Security.certificate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 操作数字证书（x509）
 *
 */

import java.security.*;
import java.io.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class TestCertificate1 {
    public static void main(String[] args) throws FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, IOException, CertificateException {
        //从csr文件中读取证书
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream("out.csr");
        Certificate c = cf.generateCertificate(in);
        String s = c.toString();

        //从密钥库中直接读取证书
        //String pass="123456";
        //FileInputStream in2 = new FileInputStream(".keystore");
        //KeyStore ks = KeyStore.getInstance("JKS");
        //ks.load(in,pass.toCharArray());
        //java.security.cert.Certificate c2 = ks.getCertificate(alias);   //alias为条目的别名
    }

}
