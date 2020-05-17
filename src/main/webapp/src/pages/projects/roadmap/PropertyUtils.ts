import {DecimalProperty} from "../../../api/generated";

export function createDecimal(name: string, value: number): DecimalProperty {
    return {
        propertyType: "DecimalProperty",
        name: name,
        value: value
    } as DecimalProperty;
}
