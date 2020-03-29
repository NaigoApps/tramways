# TramwaysApi.ProjectsApi

All URIs are relative to *http://localhost:8080/tramways/rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**projectsGet**](ProjectsApi.md#projectsGet) | **GET** /projects | Gets user projects
[**projectsPost**](ProjectsApi.md#projectsPost) | **POST** /projects | Creates a new project



## projectsGet

> projectsGet(user)

Gets user projects

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.ProjectsApi();
let user = "user_example"; // String | 
apiInstance.projectsGet(user, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user** | **String**|  | 

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


## projectsPost

> projectsPost(opts)

Creates a new project

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.ProjectsApi();
let opts = {
  'createProjectRequest': new TramwaysApi.CreateProjectRequest() // CreateProjectRequest | 
};
apiInstance.projectsPost(opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **createProjectRequest** | [**CreateProjectRequest**](CreateProjectRequest.md)|  | [optional] 

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined

