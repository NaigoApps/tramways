import {Configurable, ItemConfiguration, Property} from "../../../api/generated";
import React, {useContext, useEffect, useState} from "react";
import {IconButton, Typography} from "@material-ui/core";
import PropertyEditor, {newStringProperty} from "../../configurations/properties/editors/PropertyEditor";
import {Add, Delete, Search} from "@material-ui/icons"
import ApiContext from "../../../ApiContext";
import ConfigurationSelectionDialog from "../../configurations/properties/ConfigurationSelectionDialog";
import Button from "@material-ui/core/Button";
import PropertyInput from "../../configurations/properties/inputs/PropertyInput";

export interface ConfigurableEditorProps<C extends Configurable> {
    element: C;
    onChange: (element: C) => void;
}

export default function ConfigurableEditor<C extends Configurable>({
    element, onChange
}: ConfigurableEditorProps<C>) {

    const {configurationsApi} = useContext(ApiContext);

    const [newProp, setNewProp] = useState<Property>(newStringProperty());

    const [presets, setPresets] = useState<Array<ItemConfiguration>>([]);

    const [showConfigurations, setShowConfigurations] = useState(false);

    useEffect(() => {
        if (element?.category) {
            configurationsApi.getConfigurations(element.category)
                .then(response => setPresets(response.data));
        }
    }, [configurationsApi, element]);

    let suggestedProps = presets
        .flatMap(conf => conf.props);

    suggestedProps = suggestedProps
        .filter((prop, i) => suggestedProps.findIndex(p => p.name === prop.name) === i);

    function addNewProp() {
        onChange({
            ...element,
            props: element.props.concat([newProp])
        });
        setNewProp(newStringProperty());
    }

    function updateProp(index: number, prop: Property) {
        onChange({
            ...element,
            props: []
                .concat(element.props.slice(0, index))
                .concat([prop])
                .concat(element.props.slice(index + 1, element.props.length))
        });
    }

    function deleteProp(index: number) {
        onChange({
            ...element,
            props: []
                .concat(element.props.slice(0, index))
                .concat(element.props.slice(index + 1, element.props.length))
        });
    }

    function applyConfiguration(conf: ItemConfiguration) {
        setShowConfigurations(false);

        let newProps = [...element.props];
        conf.props.forEach(prop => {
            const index = newProps.findIndex(p => p.name === prop.name);
            if (index >= 0) {
                newProps = []
                    .concat(newProps.slice(0, index))
                    .concat([prop])
                    .concat(newProps.slice(index + 1, newProps.length));
            } else {
                newProps = newProps.concat([prop]);
            }
        });

        onChange({
            ...element,
            props: newProps
        });
    }

    return <div>
        <Typography variant={"h6"}>New property</Typography>
        <div className={"flex-row justify-space-between"}>
            <PropertyEditor property={newProp} onChange={setNewProp} suggestions={suggestedProps}/>
            <IconButton color={"primary"} onClick={addNewProp}>
                <Add/>
            </IconButton>
        </div>
        <div className={"flex-row justify-space-between"}>
            <Typography variant={"h6"}>Existing properties</Typography>
            <Button
                color={"primary"}
                variant={"outlined"}
                onClick={() => setShowConfigurations(true)}
                endIcon={<Search/>}>
                Search presets
            </Button>
        </div>
        {element.props.map((prop, index) => (
            <div key={prop.name + index} className={"flex-row justify-space-between"}>
                <PropertyInput property={prop} onChange={prop => updateProp(index, prop)}/>
                <IconButton color={"primary"} onClick={() => deleteProp(index)}>
                    <Delete/>
                </IconButton>
            </div>
        ))}
        {showConfigurations && (
            <ConfigurationSelectionDialog
                configurations={presets}
                onCancel={() => setShowConfigurations(false)}
                onOk={conf => applyConfiguration(conf)}
            />
        )}
    </div>
}
