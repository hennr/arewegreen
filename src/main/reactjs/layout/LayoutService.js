// @flow

import React from "react";
import Tile from "../components/Tile";
import LayoutClient from "./LayoutClient";
import Error from "../components/Error"
import type {$AxiosXHR} from "axios";

export function getComponents(): Promise<$AxiosXHR<*,*>> {
    return new LayoutClient().fetchLayout()
        .then(response => convertDataToComponents(response.data))
        .catch((error) => {
            returnErrorComponent(error.message)
        });
}

function returnErrorComponent(error: string): React$Element<any> {
    console.error("failed to load layout.json: " + error);
    return <Error errorMessage={error}/>;
}

// FIXME return transformed data based on given layoutJson
export function convertDataToComponents(layoutJson: any): React$Element<any> {
    const fixTestData =
        <div className={"row"}>
            <Tile dataSource={"data?source=demo.sh"} text={"dummyTile"}/>
        </div>;

    return (
        <React.Fragment>
            {fixTestData}
        </React.Fragment>
    )
}
