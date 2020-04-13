import {Button, Dialog, DialogActions, DialogContent} from "@material-ui/core";
import React, {ReactNode} from "react";

type AlertProps = {
    visible: boolean;
    onClose: () => void;
    children: ReactNode
}

export default function Alert({ visible, onClose, children}: AlertProps) {
    return (
        <Dialog open={visible} onClose={onClose}>
            <DialogContent>{children}</DialogContent>
            <DialogActions>
                <Button variant="contained" color="secondary" onClick={onClose}>
                    Chiudi
                </Button>
            </DialogActions>
        </Dialog>
    );
}
