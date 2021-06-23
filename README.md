# Access log filter for spring

Request body 와 response body 를 포함해서 access log 를 남길 수 있게 해주는 spring boot filter. 

## 의존성 설치 방법

### Gradle

* ```repositories``` 에 jitpack repository 추가
```groovy
repositories {
    // ... 
    maven {
        url 'https://jitpack.io'
    }
}
```

* ```dependencies``` 에 의존성 추가
```groovy
dependencies {
    // ...
    implementation 'com.github.omnipede:spring-access-log-filter:0.1.0'
}
```

### Maven
* ```repositories``` 에 jitpack repository 추가
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

* ```dependencies``` 에 의존성 추가
```xml
<dependency>
    <groupId>com.github.omnipede</groupId>
    <artifactId>spring-access-log-filter</artifactId>
    <version>0.1.0</version>
</dependency>
```

## 사용 방법

* 먼저 ```AccessLogger``` 인터페이스에 대한 구현체를 생성한다. ```AccessLog``` 객체에 대한 설명은 [문서](./docs/api.md) 참조.
```AccessLog``` 객체를 콘솔에 출력해도 되고, DB 에 저장해도 된다. 
```java
@Configuration
public class SampleConfig {
    
    // ...
    
    @Bean
    public AccessLogger accessLogger() {
        private ObjectMapper objectMapper = new ObjectMapper();
        return new AccessLogger() {
            @Override
            public void log(AccessLog accessLog) {
                try {
                    String log = objectMapper.writeValueAsString(accessLog);
                    System.out.println(log);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
```

* ```AccessLogFilterConfigurer``` 객체를 생성한다.

```java
@Configuration
public class SampleConfig {
    
    // ...

    @Bean
    public AccessLogFilterConfigurer accessLogFilterConfigurer() {

        return AccessLogFilterConfigurer.builder()
                .enableContentLogging(true)
                .maxContentLength(1024)
                .whiteList(Arrays.asList("/favicon.ico"))
                .build();
    }
}
```

* ```AccessLogFilterConfigurer``` 와 ```AccessLogger``` 구현체를 이용해서 ```AccessLogFilter``` 를 생성한다

```java
@Configuration
public class SampleConfig {
    
    // ...

    @Bean
    public AccessLogFilter accessLogFilter(AccessLogFilterConfigurer configurer, AccessLogger accessLogger) {

        return new AccessLogFilter(configurer, accessLogger);
    }
}
```

* 생성한 필터를 등록한다
```java
@Configuration
public class SampleConfig {

    // ...
    
    @Bean
    public FilterRegistrationBean<AccessLogFilter> filterRegistrationBean(AccessLogFilter accessLogFilter) {
        
        FilterRegistrationBean<AccessLogFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(accessLogFilter);
        return filterRegistrationBean;
    }
}
```

## API

[API 문서](./docs/api.md) 참조
