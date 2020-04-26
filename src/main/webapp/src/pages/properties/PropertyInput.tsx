import React from "react";
import IntegerPropertyInput from "./IntegerPropertyInput";
import {Property} from "../../api/generated";
import StringPropertyInput from "./StringPropertyInput";
import DecimalPropertyInput from "./DecimalPropertyInput";

export interface PropertyInputProps {
    property: Property;
    onChange: (prop: Property) => void
}

export default function PropertyInput({property, onChange}: PropertyInputProps) {
    const componentMap = new Map<string, any>();
    componentMap.set("IntegerProperty", IntegerPropertyInput);
    componentMap.set("DecimalProperty", DecimalPropertyInput);
    componentMap.set("StringProperty", StringPropertyInput);

    let Component = componentMap.get(property.propertyType);
    if (!Component) {
        Component = () => <p>Unsupported</p>;
    }
    return <Component property={property} onChange={onChange}/>;
}
