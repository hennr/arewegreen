import * as React from "react";

export default class Tile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            value: props.value
        };
    }

    render() {
        return (
                <div className="tile green comets">
                    <div className="value" data-test-value="">{this.state.value}</div>
                    <div className="text" data-test-text="">{this.props.text}</div>
            </div>
        );
    }
}
