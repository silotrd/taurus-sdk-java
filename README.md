# Taurus Java SDK 
<p align="center">
	<a target="_blank" href="https://services.gradle.org/distributions/">
		<img src="https://img.shields.io/badge/license-Apache 2-blue.svg" />
	</a>
	<a target="_blank" href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">
		<img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
	<a target="_blank" href="https://services.gradle.org/distributions/">
		<img src="https://img.shields.io/badge/Gradle-6.3-green.svg" />
	</a>
</p>

## Introduction
This library is the abstraction of Taurus API for access from applications written with Java, which enable you to acces Taurus with quick and easy manner

### Taurus API Document
API Documentation: https://taurus-docs.silot.tech/

## Installation
### With Maven
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
### Without Maven
Please download the jar package through the link below

https://repo1.maven.org/maven2/ai/silot/taurus/taurus-sdk-java/1.0.0/

## How to use
Check [Taurus API Document](https://taurus-docs.silot.tech/), Please obtain ```API Key``` and endpoints of correspoinding environment.

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
For more examples, please check [https://github.com/silotrd/taurus-sdk-java/tree/main/src/test/java/ai/silot/taurus/service](https://github.com/silotrd/taurus-sdk-java/tree/main/src/test/java/ai/silot/taurus/service)
