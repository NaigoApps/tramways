import {Button} from "@material-ui/core";
import React, {useCallback, useContext, useEffect, useState} from "react";
import {RoadMap, RoadMapContent} from "../../api/generated";
import ApiContext from "../../ApiContext";
import {RouteComponentProps} from "@reach/router";
import {JSONComponent} from "../../widgets/JSONComponent";
import cytoscape from "cytoscape";
import {intProperty} from "./roadmap/properties";

interface RoadMapComponentProps extends RouteComponentProps {
    projectId: string;
    roadMap: RoadMap;
    refresh: () => void;
}

export default function RoadMapComponent({
    projectId,
    roadMap,
    refresh,
    navigate
}: RoadMapComponentProps) {

    const {projectsApi} = useContext(ApiContext);

    const editMap = useCallback((mapContent: RoadMapContent) => {
        projectsApi.updateMap(projectId, roadMap.uuid, {
            map: {
                ...roadMap,
                content: mapContent
            }
        }).then(() => {
            setShowEditor(false);
            refresh();
        });
    }, [roadMap, projectId, projectsApi, refresh]);

    const [showEditor, setShowEditor] = useState(false);
    const [container, setContainer] = useState(null);
    const [showAnalysisDialog, setShowAnalysisDialog] = useState(false);

    const [chosenAnalysis, setChosenAnalysis] = useState(null);

    const [configurable, setConfigurable] = useState(null);

    const [network, setNetwork] = useState<cytoscape.Core>(null);
    const [smartMap, setSmartMap] = useState(null);

    const refreshNetwork = useCallback(() => {
        if (roadMap && container) {
            const net = cytoscape({
                container: container,
                elements: {
                    nodes: roadMap.content.points.map(rp => ({
                        data: {
                            id: rp.id
                        },
                        position: {
                            x: intProperty(rp.props, 'x')?.value,
                            y: intProperty(rp.props, 'y')?.value
                        }
                    })),
                    edges: roadMap.content.lanes.map(lane => ({
                        data: {
                            source: lane.sourceId,
                            target: lane.destinationId
                        }
                    }))
                },
                style: [
                    {
                        selector: 'node',
                        style: {
                            label: 'data(id)'
                        }
                    }, {
                        selector: 'edges',
                        style: {
                            // "mid-arrow-color": "#CCC",
                            "mid-target-arrow-shape": "triangle"
                        }
                    }
                ]
            });
            net.fit(undefined, 50);
            setNetwork(net);
        }
    }, [container, roadMap]);

    useEffect(() => {
        refreshNetwork();
    }, [refreshNetwork]);

    return (<>
            <div className={"button-row"}>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => setShowAnalysisDialog(true)}>
                    Analyze
                </Button>
                <Button
                    variant="outlined"
                    color="primary"
                    onClick={() => navigate("./analysis")}>
                    Analysis
                </Button>
                <Button
                    variant="outlined"
                    color="primary"
                    onClick={() => {
                        setShowEditor(true);
                        setTimeout(refreshNetwork, 0);
                    }}>
                    Edit
                </Button>
                <Button
                    color="primary"
                    onClick={() => {
                        navigate("..");
                    }}>
                    Back
                </Button>
            </div>
            <div className={"flex-row"}>
                {roadMap && (
                    <div className={"flex-grid network-container"} ref={(element: HTMLElement) => {
                        setContainer(element);
                    }}/>
                )}
                {showEditor && (
                    <div className={"flex-grid"}>
                        <JSONComponent
                            onClose={() => {
                                setShowEditor(false);
                                setTimeout(refreshNetwork, 0);
                            }}
                            visible={showEditor}
                            initialJSON={roadMap?.content || {}}
                            onChange={editMap}
                        />
                    </div>
                )}
                {/*<AvailableAnalysisDialog*/}
                {/*    onClose={() => setShowAnalysisDialog(false)}*/}
                {/*    onChooseAnalysis={setChosenAnalysis}*/}
                {/*    visible={showAnalysisDialog}*/}
                {/*/>*/}
                {/*{chosenAnalysis && (*/}
                {/*    <AnalysisDialog*/}
                {/*        visible={chosenAnalysis}*/}
                {/*        projectId={projectId}*/}
                {/*        mapId={mapId}*/}
                {/*        analysis={chosenAnalysis}*/}
                {/*        onClose={() => setChosenAnalysis(null)}*/}
                {/*    />*/}
                {/*)}*/}
                {/*{configurable && (*/}
                {/*    <ConfigurableDialog*/}
                {/*        visible={!!configurable}*/}
                {/*        onClose={() => setConfigurable(null)}*/}
                {/*        configurable={configurable}*/}
                {/*        properties={smartMap.properties[configurable]}*/}
                {/*        onConfirm={props => {*/}
                {/*            console.log(props);*/}
                {/*            smartMap.addProperties(configurable, props);*/}
                {/*            smartMap.sendUpdates();*/}
                {/*        }}*/}
                {/*    />*/}
                {/*)}*/}
            </div>
        </>
    );
}
