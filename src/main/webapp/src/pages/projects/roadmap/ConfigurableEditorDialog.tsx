import {Configurable} from "../../../api/generated";
import React, {useEffect, useState} from "react";
import {Typography} from "@material-ui/core";
import {OkCancelDialog} from "../../../widgets/OkCancelDialog";
import ConfigurableEditor from "./ConfigurableEditor";

export interface ConfigurableEditorDialogProps<C extends Configurable> {
    visible: boolean;
    element: C;
    onOk: (element: C) => void;
    onCancel: () => void;
}

export default function ConfigurableEditorDialog<C extends Configurable>({
    visible, element, onOk, onCancel
}: ConfigurableEditorDialogProps<C>) {

    const [currentElement, setCurrentElement] = useState(element);

    useEffect(() => setCurrentElement(element), [element]);

    return <OkCancelDialog visible={visible}
                           onOk={() => onOk(currentElement)}
                           onCancel={() => onCancel()}>
        <Typography variant={"h5"}>{element.id} configuration</Typography>
        <ConfigurableEditor element={currentElement} onChange={e => setCurrentElement(e)}/>
    </OkCancelDialog>
}
