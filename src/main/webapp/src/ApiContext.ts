import React from "react";
import {DefaultApi, ProjectsApi, UsersApi} from "./api/generated";

type ApiContext = {
    defaultApi: DefaultApi;
    usersApi: UsersApi;
    projectsApi: ProjectsApi

    updateToken: (token: string) => void
}

const ApiContext = React.createContext({} as ApiContext);

export default ApiContext;
