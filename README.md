### 应用版本管理后台（App Version Manager）

用于管理移动端（Android & IOS）版本更新

---

#### 框架

- Spring Boot + Thymeleaf + Mybatis

#### 功能

- [x] 登录
- [x] 创建应用
- [x] 修改应用
- [x] 删除应用
- [x] .apk .ipa文件上传及自动解析包信息
- [x] 应用状态（主要针对IOS）、应用升级参数配置
- [ ] 【应用管理】 - 相关文档
- [ ] 【用户管理】 - 账号管理、角色管理、权限管理
- [ ] 【系统管理】 - 菜单管理、操作日志
- [ ] 【当前用户】 - 设置、个人信息

#### 使用

管理员登录-->【应用管理】-->【创建应用】-->【上传应用】

![登录](https://github.com/pengfeigao/app-version-manager/blob/master/screencapture/login.png)

![创建应用](https://github.com/pengfeigao/app-version-manager/blob/master/screencapture/create-app.png)

![应用列表](https://github.com/pengfeigao/app-version-manager/blob/master/screencapture/app-list.pnghttp://images.imeina.cn/images/app-list.png)

![上传文件](https://github.com/pengfeigao/app-version-manager/blob/master/screencapture/upload-app.png)

![404](https://github.com/pengfeigao/app-version-manager/blob/master/screencapture/404.png)

![405](https://github.com/pengfeigao/app-version-manager/blob/master/screencapture/405.png)

***

【客户端登录请求】

- 接口地址：`http://localhost:8081/api/login`
- 请求方式：POST
- 请求参数：{"userName":"admin", "password":"666"}
- 参数说明：userName：用户名; password：密码
- 响应结果：

```
{
    "code": "000000",
    "message": "Success",
    "data": {
        "uid": "ec5f0fdafead48f398c0a530eab8520d",
        "avatar": "http://imeina.cn/images/logo.png",
        "userName": "admin",
        "realName": "高鹏飞",
        "nickName": "管理员",
        "password": null,
        "sex": 0,
        "birthday": "2018-12-14T00:00:00.000+0000",
        "email": "gaopengfedev@gmail.com",
        "telephone": "18800000000",
        "address": "北京市",
        "signature": "我是一个你可有可无的影子",
        "status": null,
        "createTime": "2018-12-14T08:52:58.000+0000",
        "updateTime": "2018-12-14T08:53:00.000+0000",
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGwiLCJuYmYiOjE1NTE4MDY3MTcsImlzcyI6ImRhbnl1YW50ZWNoLmNvbSIsImV4cCI6MTU1MTg5MzExNywiaWF0IjoxNTUxODA2NzE3LCJqdGkiOiJlODg4MjE0NWQzY2E0YmRhOTlmNDg0NDdlMjdkNjVkZiJ9.pZyngzgw9t9OSSJQD0lC3ndUxa8AqxVXDFu4ytO9X5s"
    }
}
```

【客户端检查更新请求】

- 接口地址：`http://localhost:8081/api/checkUpgrade`

- 请求方式：GET

- 请求参数：os=0&versionCode=101&packageName=cn.imeina.demo

- 参数说明：os：应用类型0-Android 1-IOS; versionCode：版本号唯一; packageName：应用包名唯一

- 响应结果：

```
略~
```

> 注：
> - 项目为自己入门SpringBoot，其中许多地方不规范及bug，望多多指正。
> - 项目使用了阿里云OSS云存储，详见OSSManager类，进行AccessKeyId等参数配置。
> - MultipartConfig进行上传文件临时目录配置（为什么这样配置而不用spring.servlet.multipart.location配置详见代码注释）
> - application-xxx.properties配置对应环境的数据源信息
> - logback-spring.xml配置Log日志输出路径及级别等信息