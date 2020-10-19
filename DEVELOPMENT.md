
Some notes on the development process

# hot reload

Thanks to the spring-boot-devtools and parcel's watch mode areWeGreen is able to reload many code changes dynamically.

## Back-End

Starting the application via the spring-boot maven plugin supports hot reload of back-end code automatically:

    mvn spring-boot:run

## Front-End

When the application is already running, you can start the parcel watcher (afterwards!) to have hot reload support and sources maps for the front-end code:

    cd src/main/reactjs
    node/yarn/dist/bin/yarn watch


# updating front-end depdendencies

This project has npm-check-updates installed. To run it, change into the reactjs dir:
    cd src/main/reactjs/

from here you can run ncu for all dependencies:

    node/yarn/dist/bin/yarn run ncu -u

or with a filter, e.g. react:

    node/yarn/dist/bin/yarn run ncu -u -f "/.*react.*/"

after the update you need to install the new dependencies and run the tests:

    node/yarn/dist/bin/yarn
    node/yarn/dist/bin/yarn test


# a word on the jest tests

This project uses jest-environment-enzyme which helps to avoid imports and boilerplate code in the test.
Namely you don't have to import React in every test, as well as you don't have to import shallow, mount and render.
Furthermore you don't need to configure the Adapter. For more information see: https://github.com/FormidableLabs/enzyme-matchers/tree/master/packages/jest-environment-enzyme#readme