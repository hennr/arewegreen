import React from "react";
import ReactDOM from "react-dom";
import "./styl/app.styl";
import Tile from "./components/Tile";

const App = () => (
    <React.Fragment>
        <div className={"row"}>
            <Tile value={"42"} text={"foo"}/>
            <Tile value={"42"} text={"foo"}/>
        </div>
        <div className={"row"}>
            <Tile value={"666"} text={"bar"}/>
        </div>
    </React.Fragment>
);

ReactDOM.render(<App/>, document.getElementById("app"));
