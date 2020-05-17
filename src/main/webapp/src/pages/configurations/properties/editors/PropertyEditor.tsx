import React, {useState} from "react";
import {ChoiceProperty, DecimalProperty, IntegerProperty, Property, StringProperty} from "../../../../api/generated";
import {PropertyTypes} from "../inputs/PropertyInput";
import SelectEditor from "../../../../inputs/SelectEditor";
import {IconButton, InputAdornment, TextField} from "@material-ui/core";
import useStyles from "../../../../utils/useStyles";
import {Search, Settings} from "@material-ui/icons";
import PropertySelectionDialog from "../PropertySelectionDialog";
import PropertyContentEditor from "./PropertyContentEditor";
import Alert from "../../../../widgets/Alert";

export interface PropertyEditorProps {
    property: Property;
    onChange: (prop: Property) => void,
    suggestions?: Property[]
}

export default function PropertyEditor({property, onChange, suggestions = []}: PropertyEditorProps) {

    const [editValue, setEditValue] = useState(false);

    const {formControl} = useStyles();

    const [showSuggestions, setShowSuggestions] = useState(false);

    function changePropertyType(type: string) {
        switch (type) {
            case PropertyTypes.INTEGER:
                onChange(newIntegerProperty());
                break;
            case PropertyTypes.DECIMAL:
                onChange(newDecimalProperty());
                break;
            case PropertyTypes.CHOICE:
                onChange(newChoiceProperty());
                break;
            case PropertyTypes.STRING:
            default:
                onChange(newStringProperty());
                break;
        }
    }

    function selectSuggestion(prop: Property) {
        if (prop) {
            setShowSuggestions(false);
            onChange({
                name: prop.name,
                propertyType: prop.propertyType
            });
        }
    }

    return <div className={"flex-row flex-grow justify-space-between"}>
        <SelectEditor<string>
            className={"flex-grow"}
            options={Object.values(PropertyTypes)}
            value={property && property.propertyType}
            label={"Property type"}
            onSelectOption={(type) => changePropertyType(type)}
        />
        <TextField
            className={formControl}
            label={"Name"}
            variant="outlined"
            value={property?.name || ''}
            onChange={evt => onChange({
                ...property,
                name: evt.target.value
            })}
            InputProps={{
                endAdornment: <InputAdornment position="end">
                    <IconButton onClick={() => setShowSuggestions(true)}>
                        <Search/>
                    </IconButton>
                </InputAdornment>
            }}
        />
        <IconButton onClick={() => setEditValue(true)}>
            <Settings/>
        </IconButton>
        <Alert visible={editValue} onClose={() => setEditValue(false)} buttonColor={"default"}>
            <PropertyContentEditor property={property} onChange={onChange}/>
        </Alert>
        {showSuggestions && (
            <PropertySelectionDialog
                properties={suggestions}
                onOk={selectSuggestion}
                onCancel={() => setShowSuggestions(false)}
            />
        )}
    </div>;
}


export function newStringProperty(): StringProperty {
    return {
        name: "",
        value: "",
        propertyType: PropertyTypes.STRING
    };
}

export function newIntegerProperty(): IntegerProperty {
    return {
        name: "",
        value: 0,
        propertyType: PropertyTypes.INTEGER
    };
}

export function newDecimalProperty(): DecimalProperty {
    return {
        name: "",
        value: 0,
        propertyType: PropertyTypes.DECIMAL
    };
}

export function newChoiceProperty(): ChoiceProperty {
    return {
        name: "",
        value: null,
        choices: [],
        propertyType: PropertyTypes.CHOICE
    };
}
