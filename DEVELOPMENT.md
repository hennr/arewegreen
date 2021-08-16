
Some notes on the development process

# hot reload

Thanks to the spring-boot-devtools and parcel's watch mode areWeGreen is able to reload many code changes dynamically.

## Back-End

Starting the application via the spring-boot maven plugin supports hot reload of back-end code automatically:

    mvn spring-boot:run

## Front-End

When the application is already running, you can start the parcel watcher (afterwards!) to have hot reload support and sources maps for the front-end code:

    ./yarn watch

# updating front-end dependencies

This project has npm-check-updates installed.

from here you can run ncu for all dependencies:

    ./yarn run update-dependencies # this will install deps and test the app

or by hand with a filter, e.g. react:

    ./yarn run ncu -u -f "/.*react.*/"

in which case you need to install the new dependencies and run the tests:

    ./yarn
    ./yarn test
