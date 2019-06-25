import React from "react";
import ReactDOM from "react-dom";
import "./app/stylesheets/styl/app.styl";
import Tile from "./components/Tile";

const App = () => (
    <React.Fragment>
          <Tile value={"42"} text={"foo"}/>
          <Tile value={"666"} text={"bar"}/>
    </React.Fragment>
);

ReactDOM.render(<App/>, document.getElementById("app"));
