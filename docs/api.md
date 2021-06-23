# Access log filter's API

## AccessLogFilterConfigurer

### 예시
```java
public class Example {
    
    public static void main() {
        AccessLogFilterConfigurer configurer = AccessLogFilterConfigurer.builder()
                .enableContentLogging(true)
                .maxContentLength(1024)
                .whiteList(Arrays.asList("/favicon.ico"))
                .build();
    }
}
```

### 속성
| key | value | default |
| --- | --- | --- |
| enableContentLogging | Request body & response body 를 로깅할지 여부 | false |
| maxContentLength | 로깅할 request body & response body 의 최대 길이. | 1024 |
| whiteList | 로그를 남기지 않고 pass 처리할 uri 의 prefix | empty list |

## AccessLog

### 속성
| key | value | nullable |
| --- | --- | --- |
| requestAt | 요청 시각 | false
| responseAt | 응답 시각 | false
| userAgent | 요청자의 user agent 속성 | false 
| hostName | 서버의 hostname | false
| ip | 요청자의 ip | false
| uri | 요청 URI | false
| query | 요청 query parameter | true
| method | HTTP method | false
| requestHeaders | 요청 헤더 | false
| requestBody | 요청 contents | true
| status | HTTP status | false
| responseHeaders | 응답 헤더 | false
| responseBody | 응답 contents | true
| elapsed | 응답 소요 시간 | false
