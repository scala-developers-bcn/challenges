#!/bin/bash

curl -X PUT -H "Content-Type: application/json" -d '"modified"' http://localhost:9000/flight/VL1234/status
