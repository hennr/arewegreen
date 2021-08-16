import React, {ReactElement} from "react";
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

export interface Layout {
    visual: string,
    image: string,
    refreshIntervalInSeconds: number,
    title: string,
    dataSource: string
}

export function convertDataToComponents(layoutJson: Layout): ReactElement {

    const singleTile =
        <div className={"row"}>
            <Tile dataSource={layoutJson.dataSource} text={layoutJson.title} refreshIntervalInSeconds={layoutJson.refreshIntervalInSeconds} />
        </div>;

    return (
        <React.Fragment>
            {singleTile}
        </React.Fragment>
    )
}
