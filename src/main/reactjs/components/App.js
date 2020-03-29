// @flow

import * as React from 'react';
import {getComponents} from "../layout/LayoutService";

type Props = {};

type State = {
    components: React.Node
};

export default class App extends React.Component<Props, State> {

    constructor(props: Props) {
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
