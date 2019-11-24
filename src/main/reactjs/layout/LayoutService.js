import React from "react";
import Tile from "../components/Tile";

export function getLayout() {
    return (
        <React.Fragment>
            <div className={"row"}>
                <Tile dataSource={"data?source=demo.sh"} text={"foo"}/>
                <Tile dataSource={"data?source=demo.sh"} text={"bar"}/>
            </div>
            <div className={"row"}>
                <Tile dataSource={"data?source=demo.sh"} text={"baz"}/>
            </div>
        </React.Fragment>
    )
}
