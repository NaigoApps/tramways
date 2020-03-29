# TramwaysApi.UsersApi

All URIs are relative to *http://localhost:8080/tramways/rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createUser**](UsersApi.md#createUser) | **POST** /users | Creates a new user
[**deleteUser**](UsersApi.md#deleteUser) | **DELETE** /users/{id} | Deletes a user
[**editRoles**](UsersApi.md#editRoles) | **PUT** /users/{id}/roles | Edit user&#39;s roles
[**enableUser**](UsersApi.md#enableUser) | **PUT** /users/{id}/enable | Enable or disable a user
[**getUser**](UsersApi.md#getUser) | **GET** /users/{id} | Gets a user
[**getUsers**](UsersApi.md#getUsers) | **GET** /users | Gets all users
[**resetUser**](UsersApi.md#resetUser) | **PUT** /users/{id}/reset | Reset user&#39;s password



## createUser

> User createUser(opts)

Creates a new user

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.UsersApi();
let opts = {
  'userRequest': new TramwaysApi.UserRequest() // UserRequest | 
};
apiInstance.createUser(opts, (error, data, response) => {
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

[**User**](User.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## deleteUser

> deleteUser(id)

Deletes a user

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.UsersApi();
let id = "id_example"; // String | 
apiInstance.deleteUser(id, (error, data, response) => {
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
 **id** | **String**|  | 

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


## editRoles

> editRoles(id, opts)

Edit user&#39;s roles

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.UsersApi();
let id = "id_example"; // String | 
let opts = {
  'userRole': [new TramwaysApi.UserRole()] // [UserRole] | 
};
apiInstance.editRoles(id, opts, (error, data, response) => {
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
 **id** | **String**|  | 
 **userRole** | [**[UserRole]**](UserRole.md)|  | [optional] 

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


## enableUser

> enableUser(id, opts)

Enable or disable a user

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.UsersApi();
let id = "id_example"; // String | 
let opts = {
  'booleanWrapper': new TramwaysApi.BooleanWrapper() // BooleanWrapper | 
};
apiInstance.enableUser(id, opts, (error, data, response) => {
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
 **id** | **String**|  | 
 **booleanWrapper** | [**BooleanWrapper**](BooleanWrapper.md)|  | [optional] 

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


## getUser

> User getUser(id)

Gets a user

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.UsersApi();
let id = "id_example"; // String | 
apiInstance.getUser(id, (error, data, response) => {
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
 **id** | **String**|  | 

### Return type

[**User**](User.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## getUsers

> [User] getUsers()

Gets all users

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.UsersApi();
apiInstance.getUsers((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**[User]**](User.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## resetUser

> resetUser(id, opts)

Reset user&#39;s password

### Example

```javascript
import TramwaysApi from 'tramways_api';
let defaultClient = TramwaysApi.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new TramwaysApi.UsersApi();
let id = "id_example"; // String | 
let opts = {
  'stringWrapper': new TramwaysApi.StringWrapper() // StringWrapper | 
};
apiInstance.resetUser(id, opts, (error, data, response) => {
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
 **id** | **String**|  | 
 **stringWrapper** | [**StringWrapper**](StringWrapper.md)|  | [optional] 

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined

