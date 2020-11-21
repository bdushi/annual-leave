class Auth {
    constructor() {
        this.authenticated = localStorage.getItem("token") === null || localStorage.getItem("token") === "" ;
    }

    isAuthenticated() {
        return this.authenticated;
    }
}

export default new Auth();