import React from "react";
import ReactDOM from "react-dom";
import "./styl/app.styl";
import Tile from "./components/Tile";

const App = () => (
    <React.Fragment>
        <div className={"row"}>
            <Tile dataSource={"data?source=demo.sh"} text={"foo"}/>
            <Tile dataSource={"data?source=demo.sh"} text={"foo"}/>
        </div>
        <div className={"row"}>
            <Tile dataSource={"data?source=demo.sh"} text={"bar"}/>
        </div>
    </React.Fragment>
);

ReactDOM.render(<App/>, document.getElementById("app"));
