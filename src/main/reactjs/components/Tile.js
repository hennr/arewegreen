import * as React from "react";
import * as client from "../data/AreWeGreenClient";

export default class Tile extends React.Component {

    constructor(props) {
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
            .catch(data => console.error("failed to fetch data"));
    }

    render() {
        if (this.state.value === undefined) {
            return (
                <div>
                    <div className={"spinner"}/>
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
