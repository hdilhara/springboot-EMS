export function getToken() {
    return localStorage.getItem('token');
}
export function getUser() {
    return localStorage.getItem('user');
}
export function getAuthorities() {
    let userDetails = JSON.parse(localStorage.getItem('userDetails'));
    return userDetails.authorities.map(a => a['authority']);
}
export function isLogedIn() {
    if (getToken())
        return true;
    return false;
}
