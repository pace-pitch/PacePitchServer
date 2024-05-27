# DefaultApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**sessionSessionIdRecordsNewPost**](DefaultApi.md#sessionSessionIdRecordsNewPost) | **POST** /session/{sessionId}/records/new | Upload a video file |


<a name="sessionSessionIdRecordsNewPost"></a>
# **sessionSessionIdRecordsNewPost**
> String sessionSessionIdRecordsNewPost(sessionId, _file)

Upload a video file

Upload a video file to MinIO and save the URL in the database

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UUID sessionId = UUID.randomUUID(); // UUID | The ID of the session
    File _file = new File("/path/to/file"); // File | The video file to upload
    try {
      String result = apiInstance.sessionSessionIdRecordsNewPost(sessionId, _file);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#sessionSessionIdRecordsNewPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **sessionId** | **UUID**| The ID of the session | |
| **_file** | **File**| The video file to upload | [optional] |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | File uploaded successfully |  -  |
| **500** | File upload failed |  -  |

