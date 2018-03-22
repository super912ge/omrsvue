<template>
<div class="app">


    <!--<AppHeader/>-->

  <el-row>


      <AppNav/>



    <el-col  :span="24">



      <div style="margin: 15px">
        <router-view></router-view>
      </div>
    </el-col>

  </el-row>



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
