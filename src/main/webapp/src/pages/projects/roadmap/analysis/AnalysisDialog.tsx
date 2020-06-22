import {Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField, Typography} from "@material-ui/core";
import CheckIcon from "@material-ui/icons/Check";
import React, {useCallback, useContext, useEffect, useState} from "react";
import PropertyInput from "../../../configurations/properties/inputs/PropertyInput";
import {AnalysisType, Property} from "../../../../api/generated";
import ApiContext from "../../../../ApiContext";
import useStyles from "../../../../utils/useStyles";

export interface AnalysisDialogProps {
    visible: boolean;
    onClose: () => void;
    analysis: AnalysisType;
    projectId: string;
    mapId: string;
}

export default function AnalysisDialog({
    visible,
    onClose,
    analysis,
    projectId,
    mapId
}: AnalysisDialogProps) {

    const {formControl} = useStyles();
    const {analysisApi} = useContext(ApiContext);

    const [name, setName] = useState("");
    const [parameters, setParameters] = useState<Array<Property>>([]);
    const [warnings, setWarnings] = useState([]);

    const prepareAnalysis = useCallback(() => {
        analysisApi.prepareAnalysis({
            analysisTypeId: analysis.id,
            projectId: projectId,
            mapId: mapId,
            parameters: parameters
        }).then(response => {
            setParameters(response.data.parameters);
            setWarnings(response.data.warnings);
        })
    }, [analysisApi, analysis.id, projectId, mapId, parameters]);

    function launchAnalysis() {
        analysisApi.prepareAnalysis({
            analysisTypeId: analysis.id,
            projectId: projectId,
            mapId: mapId,
            parameters: parameters
        }).then(response => {
            setParameters(response.data.parameters);
            setWarnings(response.data.warnings);
            if (response.data.ok) {
                analysisApi.launchAnalysis({
                    analysisTypeId: analysis.id,
                    projectId: projectId,
                    mapId: mapId,
                    parameters: parameters
                }).then(response => {
                    if(response.data.)
                });
            }
        });
    }

    function updateParameter(index: number, prop: Property) {
        setParameters(oldParameters => ([]
                .concat(oldParameters.slice(0, index))
                .concat([prop])
                .concat(oldParameters.slice(index + 1, oldParameters.length))
        ));
    }

    return (
        <Dialog onClose={onClose} open={visible}>
            <DialogTitle>Provide following parameters</DialogTitle>
            <DialogContent>
                {warnings.map(warning => (
                    <Typography key={warning} color={"error"}>warning</Typography>
                ))}
                <TextField
                    className={formControl}
                    label="Analysis name"
                    variant="outlined"
                    value={name}
                    onChange={evt => setName(evt.target.value)}
                />
                {parameters?.map((parameter, index) => (
                    <div key={parameter.name}>
                        <PropertyInput
                            property={parameter}
                            onChange={(prop) => {
                                updateParameter(index, prop);
                            }}
                        />
                    </div>
                ))}
            </DialogContent>
            <DialogActions>
                <Button
                    variant="contained"
                    color="primary"
                    startIcon={<CheckIcon/>}
                    onClick={launchAnalysis}>
                    Launch analysis
                </Button>
            </DialogActions>
        </Dialog>
    );
}
