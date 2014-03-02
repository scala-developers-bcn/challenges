#!/bin/bash

curl -X POST -H "Content-Type: application/json" -d '{"id":"VL1234","to":"BCN", "from":"MEN", "arrival": 1000, "departure": 2000, "status": "new"}' http://localhost:9000/flight/new
