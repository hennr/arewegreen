import * as React from "react";

export default class Tile extends React.Component {
    render() {
        return (
            <div className="comets">
                <div className="dashbot-col-visual__green">
                    <div data-test-value>{this.props.value}</div>
                    <div data-test-text>{this.props.text}</div>
                </div>
            </div>
        );
    }
}
