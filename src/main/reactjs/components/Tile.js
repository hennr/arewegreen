import * as React from "react";

export default class Tile extends React.Component {
    render() {
        return (
                <div className="tile green comets">
                    <div className="value" data-test-value="">{this.props.value}</div>
                    <div className="text" data-test-text="">{this.props.text}</div>
            </div>
        );
    }
}
