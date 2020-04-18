import {IntegerProperty, Property} from "../../../api/generated";

export function intProperty(props: Array<Property>, name: string): IntegerProperty {
    return props.find(p => p.name === name) as IntegerProperty;
}
