import {Button} from "@material-ui/core";
import React, {useCallback, useContext, useEffect, useState} from "react";
import {AnalysisType, RoadMap, RoadMapContent} from "../../api/generated";
import ApiContext from "../../ApiContext";
import {RouteComponentProps} from "@reach/router";
import {JSONComponent} from "../../widgets/JSONComponent";
import cytoscape from "cytoscape";
import {decimalProperty} from "./roadmap/properties";
import AvailableAnalysisDialog from "./roadmap/analysis/AvailableAnalysisDialog";
import AnalysisDialog from "./roadmap/analysis/AnalysisDialog";

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
            refresh();
        });
    }, [roadMap, projectId, projectsApi, refresh]);

    const [showEditor, setShowEditor] = useState(false);
    const [container, setContainer] = useState<HTMLElement>(null);
    const [showAnalysisDialog, setShowAnalysisDialog] = useState(false);

    const [chosenAnalysis, setChosenAnalysis] = useState<AnalysisType>(null);

    const [configurable, setConfigurable] = useState(null);

    const [network, setNetwork] = useState<cytoscape.Core>(null);
    const [smartMap, setSmartMap] = useState(null);

    const refreshNetwork = useCallback(() => {
        if (roadMap && container) {
            try {
                const net = cytoscape({
                    container: container,
                    elements: {
                        nodes: roadMap?.content?.points.map(rp => ({
                            data: {
                                id: rp.id
                            },
                            position: {
                                x: decimalProperty(rp.props, 'x')?.value * container.clientWidth,
                                y: decimalProperty(rp.props, 'y')?.value * container.clientHeight
                            },
                            // locked: true
                        })),
                        edges: roadMap?.content?.lanes.map(lane => ({
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
                            selector: 'edge',
                            style: {
                                // "mid-arrow-color": "#CCC",
                                "mid-target-arrow-shape": "triangle"
                            }
                        }
                    ],
                    layout: {
                        name: 'preset',
                        fit: true
                    }
                });
                net.on('tapend', 'node', evt => {
                    const id = evt.target.id();
                    const newPos = evt.target.position();
                    const newX = newPos.x / container.clientWidth;
                    const newY = newPos.y / container.clientHeight;
                    const points = roadMap.content.points;
                    const pointProps = points.find(p => p.id === id).props;
                    const oldX = decimalProperty(pointProps, 'x');
                    const oldY = decimalProperty(pointProps, 'y');
                    if (oldX.value !== newX && oldY.value !== newY) {
                        oldX.value = newX;
                        oldY.value = newY;
                        editMap(roadMap.content);
                    }
                });
                setNetwork(net);
            } catch (err) {
                console.log("Could not draw network");
                console.error(err);
            }
        }
    }, [editMap, container, roadMap]);

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
                    onClick={() => navigate("analysis")}>
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
                {showAnalysisDialog && (
                    <AvailableAnalysisDialog
                        projectId={projectId}
                        mapId={roadMap?.uuid}
                        onClose={() => setShowAnalysisDialog(false)}
                        onChooseAnalysis={setChosenAnalysis}
                        visible={showAnalysisDialog}
                    />
                )}
                {chosenAnalysis && (
                    <AnalysisDialog
                        visible={!!chosenAnalysis}
                        projectId={projectId}
                        mapId={roadMap?.uuid}
                        analysis={chosenAnalysis}
                        onClose={() => setChosenAnalysis(null)}
                    />
                )}
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
