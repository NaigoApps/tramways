import {Button, Dialog, DialogActions, DialogContent, PropTypes} from "@material-ui/core";
import React, {ReactNode} from "react";
import Color = PropTypes.Color;

type AlertProps = {
    visible: boolean;
    onClose: () => void;
    children: ReactNode,
    buttonColor?: Color,
}

export default function Alert({ visible, onClose, children, buttonColor = "secondary"}: AlertProps) {
    return (
        <Dialog open={visible} onClose={onClose}>
            <DialogContent>{children}</DialogContent>
            <DialogActions>
                <Button variant="contained" color={buttonColor} onClick={onClose}>
                    Chiudi
                </Button>
            </DialogActions>
        </Dialog>
    );
}
