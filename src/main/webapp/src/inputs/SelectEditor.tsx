import {FormControl, FormControlProps, InputLabel, MenuItem, Select} from "@material-ui/core";
import React from "react";

type SelectEditorProps<T> = {
    optionId: (option: T) => string;
    optionLabel: (option: T) => string;
    options: T[];
    value: T;
    disabled: boolean;
    label: string;
    multiSelect: boolean;
    onSelectOption: (option: T) => void;
} & FormControlProps;

export default function SelectEditor<T>({
    optionId = o => o as unknown as string,
    optionLabel = o => o as unknown as string,
    options,
    value,
    className,
    label,
    multiSelect,
    onSelectOption,
}: SelectEditorProps<T>) {
    // const inputLabel = useRef<number>(null);
    const [labelWidth, setLabelWidth] = React.useState(0);
    // React.useEffect(() => {
    //     setLabelWidth(inputLabel.current && inputLabel.current.offsetWidth);
    // }, []);

    return (
        <FormControl variant="outlined" className={className}>
            {/*<InputLabel ref={inputLabel}>{label}</InputLabel>*/}
            <InputLabel>{label}</InputLabel>
            <Select
                multiple={multiSelect}
                onChange={evt => onSelectOption(evt.target.value as T)}
                value={value}
                labelWidth={labelWidth}>
                {options.map(option => (
                    <MenuItem key={optionId(option)} value={option as any}>
                        {optionLabel(option)}
                    </MenuItem>
                ))}
            </Select>
        </FormControl>
    );
};
