import React from 'react';
import {configure, shallow} from 'enzyme';
import Tile from "./Tile";
import Adapter from 'enzyme-adapter-react-16';

configure({ adapter: new Adapter() });

it('Tile renders given values', () => {
    const rendered = shallow(<Tile value={"42"} text={"foo"}/>);
    expect(rendered.text()).toEqual('42foo');
});
