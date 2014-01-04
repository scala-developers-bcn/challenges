
## [Monthly challenges](./monthly/README.md)

These challenges are meant to dig deep on a FW, technology or a concept. These are more demanding in time than [microchallenges](../micro) and are aimed to create pet (or even real) applications using state-of-the-art frameworks or technologies. First challenges could be done individually and more complex ones by small teams. Each month a new challenge is going to be proposed and afterwards the experience shared in one (or more) meet ups. 

## APPLICATIONS

Here's a brief list of applications where the challenges might be focused. We won't try to solve an app in a month, instead, we'll try to solve in many ways a part of the app.

### Flyapp

An application that presents arrival/departure information for a given airport to users.

* RESTful API
* Data persistence
* Free text parser
* Data feed from custom sources
* Pushing data to browsers/apps
* Twitter integration

### Retweetservice

A service that listens to a #hashtag and retweets the incoming messages (or performs other operations)

* Twitter integration

### Games

* [Scalatron](http://scalatron.github.io/)


## JANUARY 2014

Your task for Jan 2014 is to complete a REST API for the FlyApp. That is, you don't have to worry about presentation, persistance or any other detail but you have to provide HTTP/JSON endpoints to perform all operations of the FlyApp. To be more specific, you must support:


* POST a flight (id, arrival, departure, from, to, status)
* GET a list of flights to X [within time]
* GET a list of flights from Y [within time]
* PUT an updated status to a flight (i.e: DELAYED, CANCELLED, Gate 42)
* DELETE a flight (maybe)

