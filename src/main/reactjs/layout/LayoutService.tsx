import React, { ReactElement } from "react";
import Tile from "../components/Tile";
import LayoutClient from "./LayoutClient";
import Error from "../components/Error"

export function getComponents(layoutClient: LayoutClient = new LayoutClient()) {
    return layoutClient.fetchLayout()
        .then(response => convertDataToComponents(response.data))
        .catch((error) => {
            returnErrorComponent(error.message)
        });
}

function returnErrorComponent(error: string): ReactElement {
    console.error("failed to load layout.json: " + error);
    return <Error errorMessage={error} />;
}

// FIXME return transformed data based on given layoutJson
export function convertDataToComponents(layoutJson: any): ReactElement {
    const fixTestData =
        <div className={"row"}>
            <Tile dataSource={"data?source=demo.sh"} text={"dummyTile"} />
        </div>;

    return (
        <React.Fragment>
            {fixTestData}
        </React.Fragment>
    )
}
