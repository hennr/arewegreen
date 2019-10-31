import * as React from "react";
import * as client from "../";

export default class Tile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            value: props.value
        };
    }
    componentDidMount() {
        client.fetchData()
            .then((data) => {
                this.setState({value: data.value});
            })
            .catch(data => console.error("failed to fetch data"));
    }

    render() {
        if (this.state.value === undefined) {
            return <div className={"spinner"}/>
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
