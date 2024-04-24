document.getElementById('login-form').addEventListener('submit', onLoginHandler);
let user = {}
const errorInput =  document.getElementById('error-input')
const requestSettings = {
    method: 'GET',
    headers: makeHeaders()
}

function onLoginHandler(e) {
    e.preventDefault()
    const form = e.target;
    const userFormData = new FormData(form);
    user = Object.fromEntries(userFormData);
    console.log(user.email)
    const userJson = JSON.stringify(user)

    errorInput.innerHTML = ' ';
    fetch(`/api/users/checkLogin?email=`+user.email+`&password=`+user.password)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Boolean value received:', data);

            if (data === true) {
                console.log('Status is true!');
                localStorage.setItem('user', userJson);
                fetchAuthorized()
            } else {
                console.log('Status is false or not received as expected.');
                return errorInput.textContent = 'incorrect email or password'
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });

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

async function fetchAuthorized() {
    try {
        errorInput.innerHTML = ' '
        await makeRequest('/api/users/login', updateOptions({method: 'post'}))
    } catch (error){
        return errorInput.textContent = 'incorrect email or password try again'
    }
}
