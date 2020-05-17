import {Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@material-ui/core";
import CheckIcon from "@material-ui/icons/Check";
import React, {useContext, useEffect, useState} from "react";
import PropertyInput from "../../../configurations/properties/inputs/PropertyInput";
import {AnalysisType, Property} from "../../../../api/generated";
import ApiContext from "../../../../ApiContext";

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

    const {analysisApi} = useContext(ApiContext);

    const [name, setName] = useState("");
    const [parameters, setParameters] = useState<Array<Property>>([]);

    useEffect(() => {
        setParameters([...analysis?.parameters]);
    }, [analysis]);

    function launchAnalysis() {
        // const result = await post(
        //     `analysis/${analysis}/launch/${projectId}/${mapId}?name=${name}`,
        //     []
        // );
        // if (result !== null) {
        //     onClose();
        // }
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
                <TextField
                    label="Name"
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
                    onClick={launchAnalysis}
                >
                    Launch analysis
                </Button>
            </DialogActions>
        </Dialog>
    );
}
