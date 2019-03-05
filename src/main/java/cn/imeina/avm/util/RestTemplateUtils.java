package cn.imeina.avm.util;

import cn.hutool.core.util.StrUtil;
import cn.imeina.avm.dto.AppInfoDTO;
import cn.imeina.avm.dto.AppVersionDTO;
import cn.imeina.avm.dto.BaseResult;
import cn.imeina.avm.entity.App;
import cn.imeina.avm.entity.AppUpgrade;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author gaopengfei
 * @date 2019-02-27
 * @description 调用远程接口工具类
 */
public class RestTemplateUtils {

    private static final String BASE_URL = "http://192.168.10.117:96";

    private static final String CREATE_APP = "/editAndUpdate";

    private static final String UPGRADE = "/eduAppDetailController/updateOrinsertAppVersion";

    private static HttpEntity createHttpEntity(Object entity) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(entity, headers);
    }

    /**
     * 创建|修改应用
     *
     * @param isCreate 是否为创建应用
     * @param app      应用信息
     * @return
     */
    public static ResponseEntity<BaseResult> editApplication(boolean isCreate, App app) {
        RestTemplate restTemplate = new RestTemplate();
        // 不能使用Map进行数据传递，会导致400错误
        AppInfoDTO appInfoDTO = new AppInfoDTO();
        if (!isCreate) {
            appInfoDTO.setId(app.getId());
        }
        appInfoDTO.setName(app.getAppName());
        if (!StringUtils.isEmpty(app.getPackageName())) {
            appInfoDTO.setPackageName(app.getPackageName());
        }
        if (app.getOs() != null) {
            appInfoDTO.setAppType(app.getOs() == 0 ? (byte) 2 : ((byte) 1));
        }
        appInfoDTO.setCreateTime(app.getCreateTime());
        appInfoDTO.setUpdateTime(app.getUpdateTime());
        return restTemplate.postForEntity(BASE_URL + CREATE_APP, createHttpEntity(appInfoDTO), BaseResult.class);
    }

    /**
     * 升级或修改版本信息
     *
     * @param upgrade 版本更新信息
     * @return
     */
    public static ResponseEntity updateAppVersion(AppUpgrade upgrade) {
        RestTemplate restTemplate = new RestTemplate();
        AppVersionDTO appVersionDTO = new AppVersionDTO();
        appVersionDTO.setAppId(upgrade.getAppId());
        appVersionDTO.setStatus(upgrade.getStatus() ? "0" : "1");
        appVersionDTO.setTitle(upgrade.getTitle());
        appVersionDTO.setContent(upgrade.getChangeLog());
        appVersionDTO.setVersion(upgrade.getVersionName());
        appVersionDTO.setCode(upgrade.getVersionCode().toString());
        appVersionDTO.setBoxDisplayRules(upgrade.getUpgradeCheckPeriod() ? "1" : "2");
        appVersionDTO.setIsForceUpdate(upgrade.getUpgradeIsForce() ? "1" : "0");
        appVersionDTO.setIsBoxDisplay(upgrade.getUpgradeIsSilent() ? "0" : "1");
        // 如果官网下载地址不为空则使用官网下载地址作为更新地址，否则使用OSS下载地址
        if (upgrade.getOs() == 1) {
            appVersionDTO.setDownloadUrl(StrUtil.isEmpty(upgrade.getWebsite()) ? upgrade.getResource().getUrl() : upgrade.getWebsite());
        } else {
            appVersionDTO.setDownloadUrl(upgrade.getWebsite());
        }
        appVersionDTO.setFileMd5(upgrade.getResource().getMd5());
        appVersionDTO.setCreateTime(upgrade.getCreateTime());
        appVersionDTO.setUpdateTime(upgrade.getUpdateTime());
        return restTemplate.postForEntity(BASE_URL + UPGRADE, createHttpEntity(appVersionDTO), BaseResult.class);
    }
}
