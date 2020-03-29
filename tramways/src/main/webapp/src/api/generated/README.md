# tramways_api

TramwaysApi - JavaScript client for tramways_api
Tramways API
This SDK is automatically generated by the [OpenAPI Generator](https://openapi-generator.tech) project:

- API version: 1.0.0
- Package version: 1.0.0
- Build package: org.openapitools.codegen.languages.JavascriptClientCodegen

## Installation

### For [Node.js](https://nodejs.org/)

#### npm

To publish the library as a [npm](https://www.npmjs.com/), please follow the procedure in ["Publishing npm packages"](https://docs.npmjs.com/getting-started/publishing-npm-packages).

Then install it via:

```shell
npm install tramways_api --save
```

Finally, you need to build the module:

```shell
npm run build
```

##### Local development

To use the library locally without publishing to a remote npm registry, first install the dependencies by changing into the directory containing `package.json` (and this README). Let's call this `JAVASCRIPT_CLIENT_DIR`. Then run:

```shell
npm install
```

Next, [link](https://docs.npmjs.com/cli/link) it globally in npm with the following, also from `JAVASCRIPT_CLIENT_DIR`:

```shell
npm link
```

To use the link you just defined in your project, switch to the directory you want to use your tramways_api from, and run:

```shell
npm link /path/to/<JAVASCRIPT_CLIENT_DIR>
```

Finally, you need to build the module:

```shell
npm run build
```

#### git

If the library is hosted at a git repository, e.g.https://github.com/GIT_USER_ID/GIT_REPO_ID
then install it via:

```shell
    npm install GIT_USER_ID/GIT_REPO_ID --save
```

### For browser

The library also works in the browser environment via npm and [browserify](http://browserify.org/). After following
the above steps with Node.js and installing browserify with `npm install -g browserify`,
perform the following (assuming *main.js* is your entry file):

```shell
browserify main.js > bundle.js
```

Then include *bundle.js* in the HTML pages.

### Webpack Configuration

Using Webpack you may encounter the following error: "Module not found: Error:
Cannot resolve module", most certainly you should disable AMD loader. Add/merge
the following section to your webpack config:

```javascript
module: {
  rules: [
    {
      parser: {
        amd: false
      }
    }
  ]
}
```

## Getting Started

Please follow the [installation](#installation) instruction and execute the following JS code:

```javascript
var TramwaysApi = require('tramways_api');


var api = new TramwaysApi.DefaultApi()
var opts = {
  'userRequest': new TramwaysApi.UserRequest() // {UserRequest} 
};
var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
api.login(opts, callback);

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080/tramways/rest*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*TramwaysApi.DefaultApi* | [**login**](docs/DefaultApi.md#login) | **POST** /users/login | Logs a user in
*TramwaysApi.ProjectsApi* | [**projectsGet**](docs/ProjectsApi.md#projectsGet) | **GET** /projects | Gets user projects
*TramwaysApi.ProjectsApi* | [**projectsPost**](docs/ProjectsApi.md#projectsPost) | **POST** /projects | Creates a new project
*TramwaysApi.UsersApi* | [**createUser**](docs/UsersApi.md#createUser) | **POST** /users | Creates a new user
*TramwaysApi.UsersApi* | [**deleteUser**](docs/UsersApi.md#deleteUser) | **DELETE** /users/{id} | Deletes a user
*TramwaysApi.UsersApi* | [**editRoles**](docs/UsersApi.md#editRoles) | **PUT** /users/{id}/roles | Edit user&#39;s roles
*TramwaysApi.UsersApi* | [**enableUser**](docs/UsersApi.md#enableUser) | **PUT** /users/{id}/enable | Enable or disable a user
*TramwaysApi.UsersApi* | [**getUser**](docs/UsersApi.md#getUser) | **GET** /users/{id} | Gets a user
*TramwaysApi.UsersApi* | [**getUsers**](docs/UsersApi.md#getUsers) | **GET** /users | Gets all users
*TramwaysApi.UsersApi* | [**resetUser**](docs/UsersApi.md#resetUser) | **PUT** /users/{id}/reset | Reset user&#39;s password


## Documentation for Models

 - [TramwaysApi.Action](docs/Action.md)
 - [TramwaysApi.ActionMethod](docs/ActionMethod.md)
 - [TramwaysApi.BooleanWrapper](docs/BooleanWrapper.md)
 - [TramwaysApi.CreateProjectRequest](docs/CreateProjectRequest.md)
 - [TramwaysApi.Error](docs/Error.md)
 - [TramwaysApi.Project](docs/Project.md)
 - [TramwaysApi.ProjectAllOf](docs/ProjectAllOf.md)
 - [TramwaysApi.Resource](docs/Resource.md)
 - [TramwaysApi.StringWrapper](docs/StringWrapper.md)
 - [TramwaysApi.User](docs/User.md)
 - [TramwaysApi.UserAllOf](docs/UserAllOf.md)
 - [TramwaysApi.UserRequest](docs/UserRequest.md)
 - [TramwaysApi.UserRole](docs/UserRole.md)


## Documentation for Authorization



### bearerAuth

- **Type**: Bearer authentication (JWT)
