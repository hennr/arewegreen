// @flow

import * as React from "react";
import * as client from "../data/AreWeGreenDataClient";

type Props = {
    dataSource: string,
    text: string
};

type State = {
    value: any
};

export default class Tile extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);

        this.state = {
            value: undefined
        };
    }

    componentDidMount() {
        client.fetchData(this.props.dataSource)
            .then((response) => {
                this.setState({value: response.data.value});
            })
            .catch((error) => console.error("failed to fetch data: ", error));
    }

    render(): React.Node {
        if (this.state.value === undefined) {
            return (
                <div>
                    <div className="spinner"/>
                    <div className="text" data-test-text="">{this.props.text}</div>
                </div>
            )
        } else {
            return (
                <div className="tile green comets">
                    <div className="value" data-test-value="">{this.state.value}</div>
                    <div className="text" data-test-text="">{this.props.text}</div>
                </div>
            );
        }
    }
}
