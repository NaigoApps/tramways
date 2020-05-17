import React from "react";
import IntegerPropertyInput from "../inputs/IntegerPropertyInput";
import {ChoiceProperty, DecimalProperty, IntegerProperty, Property, StringProperty} from "../../../../api/generated";
import StringPropertyInput from "../inputs/StringPropertyInput";
import DecimalPropertyInput from "../inputs/DecimalPropertyInput";
import ChoicePropertyContentEditor from "./ChoicePropertyEditor";
import {PropertyTypes} from "../inputs/PropertyInput";

export interface PropertyValueEditorProps {
    property: Property;
    onChange: (prop: Property) => void
}

export default function PropertyContentEditor({property, onChange}: PropertyValueEditorProps) {
    const componentMap = new Map<string, any>();
    componentMap.set(PropertyTypes.INTEGER, IntegerPropertyInput);
    componentMap.set(PropertyTypes.DECIMAL, DecimalPropertyInput);
    componentMap.set(PropertyTypes.STRING, StringPropertyInput);
    componentMap.set(PropertyTypes.CHOICE, ChoicePropertyContentEditor);

    let Component = componentMap.get(property.propertyType);
    if (!Component) {
        Component = () => <p>Unsupported</p>;
    }
    return <Component property={property} onChange={onChange}/>;
}

export function renderProperty(prop : Property){
    switch (prop.propertyType) {
        case PropertyTypes.INTEGER:
            return (prop as IntegerProperty).value;
        case PropertyTypes.DECIMAL:
            return (prop as DecimalProperty).value;
        case PropertyTypes.STRING:
            return (prop as StringProperty).value;
        case PropertyTypes.CHOICE:
            return (prop as ChoiceProperty).value;
    }
}
