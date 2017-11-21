import LocalStorage from 'localStorage'
import Vue from 'vue'
export const client_id = 'test'
export const client_secret = 'client_secret'
export const rootUrl = 'http"//localhost:8080/'
export const loginUrl = 'oauth/token'
export const registerUrl = 'user/register'
export const header_auth = 'Basic dGVzdDp0ZXN0LXNlY3JldA=='
export const todoListUrl= 'todo/'
export const todoListAddUrl ='todo/create'
export const getHeader = function () {
    const authUser = JSON.parse(LocalStorage.getItem('authUser'))
    const headers = {
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + authUser.access_token

    }
    return headers;
}
