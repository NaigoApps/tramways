import {Divider, IconButton, TextField} from "@material-ui/core";
import React, {useContext, useState} from "react";
import {ItemConfiguration, Property} from "../../api/generated";
import ApiContext from "../../ApiContext";
import {OkCancelDialog} from "../../widgets/OkCancelDialog";
import PropertyEditor, {newStringProperty} from "./properties/editors/PropertyEditor";
import useStyles from "../../utils/useStyles";
import AddIcon from "@material-ui/icons/Add"
import DeleteIcon from "@material-ui/icons/Delete"

export type ConfigurationEditorProps = {
    category: string;
    configuration: ItemConfiguration;
    onAbort: () => void;
    onConfirm: () => void;
};

export default function ConfigurationEditor(
    {
        category,
        configuration,
        onAbort,
        onConfirm
    }: ConfigurationEditorProps) {
    const {configurationsApi} = useContext(ApiContext);
    const {formControl} = useStyles();

    const [newName, setNewName] = useState(configuration.name);

    const [newProp, setNewProp] = useState<Property>(newStringProperty());

    const [newProps, setNewProps] = useState<Array<Property>>(configuration.props);

    function confirm() {
        if (configuration.uuid) {
            configurationsApi.editConfiguration(configuration.uuid, {
                name: newName,
                props: newProps
            }).then(onConfirm);
        } else {
            configurationsApi.addConfiguration(category, {
                name: newName,
                props: newProps
            }).then(onConfirm);
        }
    }

    function addNewProp() {
        setNewProps(oldProps => oldProps.concat([newProp]));
        setNewProp(newStringProperty());
    }

    function updateProp(index: number, prop: Property) {
        setNewProps(oldProps => []
            .concat(oldProps.slice(0, index))
            .concat([prop])
            .concat(oldProps.slice(index + 1, oldProps.length)));
    }

    function deleteProp(index: number) {
        setNewProps(oldProps => []
            .concat(oldProps.slice(0, index))
            .concat(oldProps.slice(index + 1, oldProps.length)));
    }

    return (
        <OkCancelDialog onOk={confirm} onCancel={onAbort} visible>
            <TextField
                className={formControl}
                variant={"outlined"}
                label="Configuration name"
                onChange={evt => setNewName(evt.target.value)}
                value={newName}
            />
            <div className={"flex-row justify-space-between"}>
                <PropertyEditor property={newProp} onChange={setNewProp}/>
                <IconButton color={"primary"} onClick={addNewProp}>
                    <AddIcon/>
                </IconButton>
            </div>
            <div className={"spaced-top-10 spaced-bot-10"}>
                <Divider/>
            </div>
            {newProps.map((prop, index) => (
                <div className={"flex-row justify-space-between"}>
                    <PropertyEditor property={prop} onChange={prop => updateProp(index, prop)}/>
                    <IconButton color={"primary"} onClick={() => deleteProp(index)}>
                        <DeleteIcon/>
                    </IconButton>
                </div>
            ))}
        </OkCancelDialog>
    );
}
