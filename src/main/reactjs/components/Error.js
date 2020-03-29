// @flow

import * as React from 'react';

type Props = {
    errorMessage: string
};

export default function Error(props: Props): React$Element<any> {
    return <div>{props.errorMessage}</div>;
}
