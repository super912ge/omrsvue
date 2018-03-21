
import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import {routes} from './router.js'
import store from './store/store.js'
import ls from 'localStorage'
import Element from 'element-ui'

import VueResource from 'vue-resource';
import {header_auth,getHeader} from "./env";
import lang from 'element-ui/lib/locale/lang/en';
import locale from 'element-ui/lib/locale';
import moment from 'moment'


Vue.use(VueResource);
locale.use(lang);
Vue.use(Element);
Vue.use(VueRouter);

Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format('YYYY-MM-DD')
  }
});

const router = new VueRouter({
    mode:'history',
    routes
});
Vue.router = router;

new Vue({
  el: '#app',
    router,
    store,
  template: '<App/>',
  components: { App }
});

Vue.http.options.root = 'http://localhost:8080';
Vue.http.headers.common['Authorization'] = header_auth;


    router.beforeEach((to, from, next) => {

    const user = ls.getItem('authUser');
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
});
