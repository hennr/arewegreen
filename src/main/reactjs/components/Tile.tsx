import * as React from "react";
import AreWeGreenDataClient from "../data/AreWeGreenDataClient";

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
            value: null
        };
    }

    componentDidMount() {
        const client = new AreWeGreenDataClient();
        client.fetchData(this.props.dataSource)
            .then((response) => {
                this.setState({value: response.data.finalValue});
            })
            .catch((error) => {
                this.setState({value: null});
                return console.error("failed to fetch data: ", error);
            });
    }

    render() {
        if (!this.state.value) {
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
