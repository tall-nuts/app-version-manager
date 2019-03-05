package cn.imeina.avm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author gaopengfei
 * 文件上传配置（由于配置文件中指定目录可能因为目录不存在导致启动报错，需自定义配置来创建）
 */
@Configuration
public class MultipartConfig {

    private static final Logger logger = LoggerFactory.getLogger(MultipartConfig.class);

    /** 文件上传临时目录 */
    private static final String UPLOAD_TEMP_PATH = "/home/web/app-version-manager/uploadTemp";

    @Bean
    public MultipartConfigElement createMultipartConfig(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        File uploadTempDir = new File(UPLOAD_TEMP_PATH);
        if (!uploadTempDir.exists()){
            logger.debug("上传临时文件夹不存在，创建...");
            uploadTempDir.mkdirs();
        }
        // 上传文件临时主目录
        multipartConfigFactory.setLocation(UPLOAD_TEMP_PATH);
        // 限制单个文件最大大小
        multipartConfigFactory.setMaxFileSize(DataSize.ofMegabytes(2));
        // 限制所有文件最大大小
        multipartConfigFactory.setMaxRequestSize(DataSize.ofMegabytes(5));
        return multipartConfigFactory.createMultipartConfig();
    }
}
