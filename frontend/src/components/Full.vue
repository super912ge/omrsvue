<template>
<div class="app">


    <!--<AppHeader/>-->

  <el-row>


      <AppNav/>



    <el-col :offset="1" :span="22">



      <div style="margin: 15px">
        <router-view></router-view>
      </div>
    </el-col>

  </el-row>


  <user-info/>


    <!--<div class="app-body">-->
        <!--<Sidebar :navItems="nav"/>-->

        <!--<main class="main">-->
            <!--<breadcrumb :list="list"/>-->
            <!--<div class="container-fluid">-->



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
  import UserInfo from './UserInfo.vue'
  import ElRow from "element-ui/packages/row/src/row";
  import ElCol from "element-ui/packages/col/src/col";
    export default {
        name: 'full',
        components: {
          ElCol,
          ElRow,
          AppHeader,
            AppNav,
            UserInfo
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
