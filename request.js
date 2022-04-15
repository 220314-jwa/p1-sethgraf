getRequests();
//getPetsAjax();

async function getRequests() {
    let response = await fetch(requestAppUrl + 'Request');
    // let response = await fetch('http://localhost:8080/pets/8',{method:'PUT', body:JSON.stringify(petObj)});
    
    if (response.status === 200) {
        let requests = await response.json();
        console.log(requests);
        showPets(requests);
    }
}

function getRequestsAjax() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = getData;
    xhr.open('GET', requestAppUrl + 'request');
    xhr.send();

    function getData() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let response = xhr.responseText;
                let pets = JSON.parse(response);
                showPets(pets);
            }
        }
    }
}

function showRequest(requests) {
    let requestsTable = document.getElementById('availablerequests');
    requestsTable.innerHTML=`<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Species</th>
    <th>Description</th>
    <th>Age</th>
    <th></th>
</tr>`;

    // for each pet in the array of pets that we got from the back end
    for (let request of requests) {
        let rowForRequest = document.createElement('tr');

        // for each field in the pet (yes, we can iterate through fields)
        for (let field in request) {
            let column = document.createElement('td');
            if (field!=='status') {
                // pet[field] gets the value of the field
                column.innerText = request[field];
            } else {
                column.innerHTML=`<button id="adopt${request.id}">Adopt</button>`;
            }
            rowForRequest.appendChild(column);
        }
        requestTable.appendChild(rowForRequest);
        document.getElementById('Available' + request.id).onclick = successfulRequest;
    }
}

async function adoptRequest() {
    if (loggedInPerson) {
        let requestId = event.target.id;
        requesttId = requestId.replace('Available', '');
        console.log(requestId);

        let tokenHeader = {"Token":loggedInEmployee.id};
        console.log(tokenHeader);
        let response = await fetch(requestAppUrl + 'requests/available/' + requestId, {
            method:'PUT',
            body:JSON.stringify(loggedInEmployee),
            headers:tokenHeader
        });

        if (response.status===200) {
            let updatedEmployee = await response.json();
            loggedInEmployee = updatedEmployee;
            await getRequests();
        } else {
            let msg = await response.text();
            alert(msg);
        }
    } else {
        alert('You need to be logged in to see available requests.');
    }
}