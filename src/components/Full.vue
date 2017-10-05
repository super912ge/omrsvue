<template>
<div class="app">
    <AppHeader/>
    <AppNav/>
    <p> full</p>

    <!--<div class="app-body">-->
        <!--<Sidebar :navItems="nav"/>-->

        <!--<main class="main">-->
            <!--<breadcrumb :list="list"/>-->
            <!--<div class="container-fluid">-->
              <router-view></router-view>
            <!--</div>-->
        <!--</main>-->
        <!--<AppAside/>-->
    <!--</div>-->
    <!--<AppFooter/>-->
</div>
</template>
<script>
  //  import { Header as AppHeader, Sidebar, Aside as AppAside, Footer as AppFooter, Breadcrumb } from '../components/'
  import AppHeader  from './AppHeader.vue'
  import AppNav from './AppNav.vue'
    export default {
        name: 'full',
        components: {
            AppHeader,
            AppNav
//            Sidebar,
//            AppAside,
//            AppFooter,
//            Breadcrumb
        }
//        data () {
//            return {
////                nav: nav.items
//            }
//        },
//        computed: {
//            name () {
//                return this.$route.name
//            },
//            list () {
//                return this.$route.matched
//            }
//        }
    }
</script>
