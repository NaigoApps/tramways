# TramwaysApi.DefaultApi

All URIs are relative to *http://localhost:8080/tramways/rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**login**](DefaultApi.md#login) | **POST** /users/login | Logs a user in



## login

> StringWrapper login(opts)

Logs a user in

### Example

```javascript
import TramwaysApi from 'tramways_api';

let apiInstance = new TramwaysApi.DefaultApi();
let opts = {
  'userRequest': new TramwaysApi.UserRequest() // UserRequest | 
};
apiInstance.login(opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userRequest** | [**UserRequest**](UserRequest.md)|  | [optional] 

### Return type

[**StringWrapper**](StringWrapper.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

