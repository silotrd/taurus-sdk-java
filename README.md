# Taurus Java SDK 

## 简介
这个SDK是对Taurus接口的一个封装, 只需要简单的配置, 就可以调用Taurus的接口,让你更快捷的接入Taurus.

### Taurus接口文档
接口文档: http://k8s-azure.silot.tech:40015/

## 安装
### Maven
```
<dependency>
  <groupId>ai.silot.taurus</groupId>
  <artifactId>taurus-sdk-java</artifactId>
  <version>1.0.0</version>
</dependency>
```
### Gradle
```
implementation 'ai.silot.taurus:taurus-sdk-java:1.0.0'
```
### 非Maven项目
自行下载jar包

https://repo1.maven.org/maven2/ai/silot/taurus/taurus-sdk-java/1.0.0/

## 使用
查看 [Taurus接口文档](http://k8s-azure.silot.tech:40015/), 拿到你的API Key以及对应环境的服务器域名.
```java
public class Example {
    public static void main(String[] args) {
        Taurus.apiKey = "[Your API Key]";
        Taurus.serverUrl = "[Taurus Server URL,UAT or PROD]";

        //Create invoice example
        String externalId = "23142134231343";
        BigDecimal amount = new BigDecimal(20000);
        String description = "test create";
        Integer invoiceDuration = 86400;
        String successRedirectUrl = "http://example.com/success/callback";

        TaurusBaseVo<InvoiceVo> responseVo = InvoiceService.create(
                externalId, amount, description, invoiceDuration, successRedirectUrl);
    }
}
```
更多示例请查看 [https://github.com/silotrd/taurus-sdk-java-example/tree/main/src/main/java/ai/silot/taurus/example](https://github.com/silotrd/taurus-sdk-java-example/tree/main/src/main/java/ai/silot/taurus/example)

