[![Build Status](https://travis-ci.org/hennr/arewegreen.svg?branch=master)](https://travis-ci.org/hennr/arewegreen)
[![codecov.io](https://codecov.io/github/hennr/arewegreen/coverage.svg?branch=master)](https://codecov.io/github/hennr/arewegreen?branch=master)
[![GPLv3](https://img.shields.io/badge/licence-GPLv3-brightgreen.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
[![Flattr](http://api.flattr.com/button/flattr-badge-large.png)](https://flattr.com/submit/auto?user_id=hennr&url=https://github.com/hennr/arewegreen&title=AreWeGreen&language=java&tags=github&category=software)

# AreWeGreen?

Out-of-the-box dashboard

Are all Systems up and running fine?
Are all tests passing?
These are common question developers have to answer several times a day.

AreWeGreen has the goal to empower you giving the right answers as quick as possible.

The result could look like this:

![](screenshot.png)


## Get started

Note: AreWeGreen is currently in alpha state.

Fetch [a release](https://github.com/hennr/arewegreen/releases) or build it from source:

    git clone https://github.com/hennr/arewegreen.git
    cd arewegreen
    mvn spring-boot:run

When run AreWeGreen will create a configuration folder (~/arewegreen) in the home directory of the current user.
Here you can define the layout and place scripts that will provide data for your board.
AreWeGreen will also start a browser automatically which opens the dashboard in a maximized window for you.
This feature can be disabled in the settings.

## Demo

A live demo can be found here: https://arewegreen.herokuapp.com/
Please give heroku a sec or two (30) to start up.

## License

AreWeGreen is licensed under the GPLv3+.

It includes [dashbot](https://github.com/sbstnmsch/dashbot/) (src/main/resources/static) which is licensed under the [MIT License](LICENSE-dashbot).


</br></br>

## Thanks

I want to thank [travis](https://travis-ci.org/) and [codecov.io](https://codecov.io/) for their free open source plans!
AreweGreen is also supported by [jetBrains](https://www.jetbrains.com/?from=arewegreen) with an open source license!

</br></br>
<p align="center">Made with ♥ in Berlin</p>