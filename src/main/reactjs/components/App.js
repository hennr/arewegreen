import React from 'react';
import {getComponents} from "../layout/LayoutService";

export default class App extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            components: null
        };
    }

    componentDidMount() {
        getComponents()
            .then(comps => {
                this.setState({components: comps});
            })
            .catch(comps => {
                this.setState({components: comps})
            });
    }

    render() {
        return this.state.components;
    }
}
