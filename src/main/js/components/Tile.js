import * as React from "react";

export default class Tile extends React.Component {
    render() {
        return (
            <div className="comets">
                <div className="dashbot-col-visual__green">
                    <div>{this.props.value}</div>
                    <div>{this.props.text}</div>
                </div>
            </div>
        );
    }
}
