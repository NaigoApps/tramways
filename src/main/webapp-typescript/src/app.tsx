import * as React from "react";

export interface AppParams {
    message: string;
}

export default function App(props: AppParams) {
    return <p>{props.message}</p>;
}
