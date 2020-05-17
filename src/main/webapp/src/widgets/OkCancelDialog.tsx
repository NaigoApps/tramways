import {
    Dialog,
    DialogActions,
    DialogContent,
    Button
} from "@material-ui/core";
import React, {FunctionComponent} from "react";

type OkCancelDialogProps = {
    valid?: boolean
    large?: boolean;
    visible: boolean;
    onCancel: () => void;
    onOk: () => void;
    cancelText?: string;
    okText?: string;
}

export const OkCancelDialog: FunctionComponent<OkCancelDialogProps> = (
    {
        valid = true,
        children,
        large = false,
        visible,
        onCancel,
        onOk,
        cancelText = "Cancel",
        okText = "Ok"
    }) => {
    return (
        <Dialog open={visible} onClose={onCancel} maxWidth={"lg"}>
            <DialogContent>{children}</DialogContent>
            <DialogActions>
                <Button color="primary" onClick={onCancel}>
                    {cancelText}
                </Button>
                <Button variant="contained" color="primary" onClick={onOk} disabled={!valid}>
                    {okText}
                </Button>
            </DialogActions>
        </Dialog>
    );
};
