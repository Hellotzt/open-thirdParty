package com.codeLife.openThirdParty.infrastructure.common.util;

import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
public class PrivateKeyUtil {
    /**
     * 获取私钥
     * @param filePath 私钥文件路径  (required)
     * @return 私钥对象
     */
    public static PrivateKey getPrivateKey(String filePath) throws IOException {
        InputStream inputStream = new ClassPathResource(filePath).getInputStream();
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            // 将缓冲区中的数据写入到 ByteArrayOutputStream 中
            resultStream.write(buffer, 0, length);
        }
        // 将 ByteArrayOutputStream 中的字节转换为字符串
        String content = resultStream.toString("UTF-8");
        // 关闭 ByteArrayOutputStream
        resultStream.close();

        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }
}
