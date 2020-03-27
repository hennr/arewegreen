import React from "react";
import Tile from "../components/Tile";
import LayoutClient from "./LayoutClient";
import Error from "../components/Error"

// FIXME return component in favour of promise here
export function getComponents() {
    return new LayoutClient().fetchLayout()
        .then(response => convertDataToComponents(response.data))
        .catch(error => {
            returnErrorComponent(error)
        });
}

function returnErrorComponent(error) {
    console.error("failed to load layout.json: " + error);
    return <Error error={error}/>;
}

// FIXME return transformed data based on given layoutJson
export function convertDataToComponents(layoutJson) {
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
