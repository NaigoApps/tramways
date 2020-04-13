import {
    Dialog,
    DialogActions,
    DialogContent,
    Button
} from "@material-ui/core";
import React, {FunctionComponent} from "react";

type OkCancelDialogProps = {
    large?: boolean;
    visible: boolean;
    onCancel: () => void;
    onOk: () => void;
    cancelText?: string;
    okText?: string;
}

export const OkCancelDialog: FunctionComponent<OkCancelDialogProps> = (
    {
        children,
        large = false,
        visible,
        onCancel,
        onOk,
        cancelText = "Cancel",
        okText = "Ok"
    }) => {
    return (
        <Dialog open={visible} onClose={onCancel}>
            <DialogContent>{children}</DialogContent>
            <DialogActions>
                <Button color="primary" onClick={onCancel}>
                    {cancelText}
                </Button>
                <Button variant="contained" color="primary" onClick={onOk}>
                    {okText}
                </Button>
            </DialogActions>
        </Dialog>
    );
};
