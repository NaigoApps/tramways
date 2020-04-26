import React from "react";
import {AnalysisApi, DefaultApi, ProjectsApi, UsersApi} from "./api/generated";

type ApiContext = {
    defaultApi: DefaultApi;
    usersApi: UsersApi;
    projectsApi: ProjectsApi;
    analysisApi: AnalysisApi;

    updateToken: (token: string) => void
}

const ApiContext = React.createContext({} as ApiContext);

export default ApiContext;
