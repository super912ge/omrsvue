import LocalStorage from 'localStorage'

export const client_id = 'test';
export const client_secret = 'client_secret';
export const rootUrl = '/api/';


export const loginUrl = '/api/oauth/token';
export const registerUrl = 'user/register';
export const header_auth = 'Basic dGVzdDp0ZXN0LXNlY3JldA==';
export const todoListUrl= 'todo/';
export const todoListAddUrl ='todo/create';
export const getHeader = function () {
    const authUser = JSON.parse(LocalStorage.getItem('authUser'));

    return {
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + authUser.access_token

    };
};
