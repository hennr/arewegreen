// @flow

import * as React from 'react';

type Props = {
    errorMessage: string
};

export default function Error(props: Props) {
    return <div>{props.errorMessage}</div>;
}
