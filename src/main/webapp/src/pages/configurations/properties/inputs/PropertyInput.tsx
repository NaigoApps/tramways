import React from "react";
import IntegerPropertyInput from "./IntegerPropertyInput";
import {ChoiceProperty, DecimalProperty, IntegerProperty, Property, StringProperty} from "../../../../api/generated";
import StringPropertyInput from "./StringPropertyInput";
import DecimalPropertyInput from "./DecimalPropertyInput";
import ChoicePropertyInput from "./ChoicePropertyInput";

export interface PropertyInputProps {
    property: Property;
    onChange: (prop: Property) => void
}

export default function PropertyInput({property, onChange}: PropertyInputProps) {
    const componentMap = new Map<string, any>();
    componentMap.set(PropertyTypes.INTEGER, IntegerPropertyInput);
    componentMap.set(PropertyTypes.DECIMAL, DecimalPropertyInput);
    componentMap.set(PropertyTypes.STRING, StringPropertyInput);
    componentMap.set(PropertyTypes.CHOICE, ChoicePropertyInput);

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

export const PropertyTypes = {
    INTEGER: "IntegerProperty",
    STRING: "StringProperty",
    DECIMAL: "DecimalProperty",
    CHOICE: "ChoiceProperty",
};
