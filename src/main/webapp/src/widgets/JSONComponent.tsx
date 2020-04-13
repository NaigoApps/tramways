import {
    Button,
    Dialog,
    DialogActions,
    DialogContent
} from "@material-ui/core";
import JsonEditor from 'jsoneditor-react';
import "jsoneditor-react/es/editor.min.css";
import React, {FunctionComponent, useState} from "react";
import Alert from "./Alert";

type JSONComponentProps = {
    initialJSON: string;
    onChange: (json: string) => void;
    onClose: () => void;
    visible: boolean;
}

export const JSONComponent: FunctionComponent<JSONComponentProps> = ({
    initialJSON,
    onChange,
    onClose,
    visible
}) => {
    const [options, setOptions] = useState({
        mode: "code"
    });

    const [json, setJson] = useState(initialJSON);

    const [error, setError] = useState("");

    return (
        <Dialog
            onEntered={() => setOptions({mode: "code"})}
            onClose={onClose}
            open={visible}
            fullScreen>
            <DialogContent>
                <JsonEditor
                    htmlElementProps={{style: {height: "100%"}}}
                    {...options}
                    value={json}
                    onChange={setJson}
                />
            </DialogContent>
            <DialogActions>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => {
                        try {
                            onChange(json);
                        } catch (err) {
                            setError("Map not valid");
                        }
                    }}>
                    Save
                </Button>
                <Button onClick={onClose}>Close</Button>
            </DialogActions>
            <Alert onClose={() => setError("")} visible={!!error}>
                <p>{error}</p>
            </Alert>
        </Dialog>
    );
};
