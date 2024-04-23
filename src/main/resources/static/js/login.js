document.getElementById('login-form').addEventListener('submit', onLoginHandler);
let user = {}
const requestSettings = {
    method: 'GET',
    headers: makeHeaders()
}

function onLoginHandler(e) {
    e.preventDefault()
    const form = e.target;
    const userFormData = new FormData(form);
    user = Object.fromEntries(userFormData);
    const userJson = JSON.stringify(user)
    localStorage.setItem('user', userJson);
    fetchAuthorized()
}

function restoreUser() {
    let userAsJSON = localStorage.getItem('user');
    user = JSON.parse(userAsJSON);
    return user;
}

function makeHeaders() {
    let user = restoreUser()
    let headers = new Headers()
    headers.set('Content-Type', 'application/json')
    console.log(user)
    if (user) {
        headers.set('Authorization', 'Basic ' + btoa(user.email + ':' + user.password))
    }
    return {
        mode: 'cors',
        headers: headers
    }
}

async function makeRequest(url, options) {
    let settings = options || requestSettings
    let response = await fetch(url, settings)

    if (response.ok) {
        return await response.json()
    } else {
        let error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
}

function updateOptions(options) {
    let update = {...options}
    update.mode = 'cors'
    update.headers = makeHeaders()
    return update
}

function fetchAuthorized() {
    makeRequest('/api/users/login',updateOptions({method: 'post'}))
}



