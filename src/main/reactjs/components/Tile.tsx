import * as React from "react";
import AreWeGreenDataClient from "../data/AreWeGreenDataClient";

type Props = {
    dataSource: string,
    text: string,
    refreshIntervalInSeconds: number
};

type State = {
    value: any
};

export default class Tile extends React.Component<Props, State> {

    private interval!: NodeJS.Timer;

    constructor(props: Props) {
        super(props);

        this.state = {
            value: null
        };
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    componentDidMount() {
        this.fetchData();
        this.interval = setInterval(() => this.fetchData(), this.props.refreshIntervalInSeconds * 1000);
    }

    private fetchData() {
        const client = new AreWeGreenDataClient();
        client.fetchData(this.props.dataSource)
            .then((response) => {
                this.setState({value: response.data.value});
            })
            .catch((error) => {
                this.setState({value: null});
                return console.error("failed to fetch data: ", error);
            });
    }

    render() {
        if (!this.state.value) {
            console.log("rendering spinner tile")
            return (
                <div>
                    <div className="spinner"/>
                    <div className="text" data-test-text="">{this.props.text}</div>
                </div>
            )
        } else {
            console.log("rendering complete tile")
            return (
                <div className="tile green comets">
                    <div className="value" data-test-value="">{this.state.value}</div>
                    <div className="text" data-test-text="">{this.props.text}</div>
                </div>
            );
        }
    }
}
