# FlyApp

This is a RESTful web service to manage flights implemented using Play framework for Scala.

Written by Christian Pérez Llamas

## API

<table>
    <tr>
        <td><strong>POST</strong> <em>/flights</em></td>
        <td>Create a flight</td>
    </tr>
    <tr>
        <td><strong>GET</strong> <em>/flights</em></td>
        <td>List flights</td>
    </tr>
    <tr>
        <td><strong>PUT</strong> <em>/flights/&lt;id&gt;</em></td>
        <td>Update flight status</td>
    </tr>
    <tr>
        <td><strong>DELETE</strong> <em>/flights/&lt;id&gt;</em></td>
        <td>Delete a flight</td>
    </tr>
</table>

### Create a flight

Create a new flight.

**POST** */flights*

The body contains the flight details as a *JSON* document with the following fields: *id*, *from*, *to*, *departure*, *arrival* and *status*.
All the fields are strings. *departure* and *arrival* are *DateTime*'s encoded in ISO format. Possible values for *status* are:
*ONTIME*, *DELAYED*, *CANCELLED*, *ONGATE*.

Example:

```json
{
    "id" : "IB001",
    "from" : "BCN",
    "to": "PARIS",
    "departure": "2014-01-28T12:35:00Z",
    "arrival": "2014-01-28T13:30:00Z",
    "status": "ONTIME"
}
```

Return *201 Created* on success or a *400 Bad request* on failure due to wrong input.

When success, the response body is a *JSON* document with the flight *id*.

### List flights

List all the flights within time.

**GET** */flights*

Return *200 Ok*.

The response body is a *JSON* array of flight descriptors.

#### Query parameters

* **from**: filter by departure location. Example: **GET** */flights?from=BCN*
* **to**: filter by destination location. Example: **GET** */flights?to=PARIS*, **GET** */flights?from=BCN\&to=PARIS*

### Update flight status

**Not yet implemented**

**PUT** */flights/<id>*

The body contains the new *status* as plain text.

Return *200 Ok" when success, "404 Not found" when the flight *id* is not found, and *400 Bad request* when the status is not correct.

### Delete a flight

**Not yet implemented**

**DELETE** */flights/<id>*

The response body is empty.

Return *200 Ok* when success, *404 Not found* when the flight *id* is not found.
