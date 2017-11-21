import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import {routes} from './router.js'
import store from './store/store.js'
import ls from 'local-storage'
import '../theme/index.css'
import Element from 'element-ui'
import VueAuth from '@websanova/vue-auth'
import VueResource from 'vue-resource';
import {header_auth} from "./env";


Vue.use(VueResource);

Vue.use(Element)
Vue.use(VueRouter);

const router = new VueRouter({
    mode:'history',
    routes
});
Vue.router = router;
new Vue({
  el: '#app',
    router,
    store,
  render: h => h(App)
})
Vue.http.options.root = 'http://localhost:8080'
Vue.http.headers.common['Authorization'] = header_auth;


    router.beforeEach((to, from, next) => {

    const user = ls.get('user');
    if (to.matched.some(record => record.meta.requiresAuth)) {

        if (!user) {
            next({
                path: '/login',
                query: { redirect: to.fullPath }
            })
        } else {
            next()
        }
    } else {
        next() // make sure to always call next()!
    }
})

Vue.use(VueAuth, {
    auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
    http: require('@websanova/vue-auth/drivers/http/vue-resource.1.x.js'),
    router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js'),
    // rolesVar: 'type'

});
// Vue.http.interceptors.push(function(request, next) {
//     next(function (res) {
//         if (res.status === 401) {
//             Vue.auth.logout();
//         }
//     });
// });