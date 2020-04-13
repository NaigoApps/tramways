import React, {useCallback, useContext, useEffect, useState} from "react";
import Page from "../Page";
import RoadMapItem from "./RoadMapItem";
import {Grid} from "@material-ui/core";
import {RouteComponentProps} from "@reach/router";
import {Project} from "../../api/generated";
import ApiContext from "../../ApiContext";

interface ProjectPageProps extends RouteComponentProps {
    projectId?: string;
}

export default function ProjectPage({navigate, projectId}: ProjectPageProps) {

    const {projectsApi} = useContext(ApiContext);

    const [project, setProject] = useState<Project>(null);

    const loadProject = useCallback(() => {
        projectsApi.getProject(projectId).then(response => setProject(response.data));
    }, []);

    useEffect(() => {
        loadProject();
    }, []);

    return (
        <Page title={`Project ${project.name}`}>
            <Grid container spacing={1}>
                {project.roadMaps
                    .map(map => (
                        <Grid item xs={4}>
                            <RoadMapItem
                                project={project}
                                map={map}
                                refresh={loadProject}
                                navigate={navigate}
                            />
                        </Grid>
                    ))}
            </Grid>
        </Page>
    );
}
