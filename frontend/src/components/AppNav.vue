<template>
    <el-col  :span="24">

      <el-row>
        <el-col :span="4" style="height: 60px">OMRS</el-col>

        <el-col :span="18">
          <el-menu :default-active="$route.path" theme="dark" router class="el-menu-demo" mode="horizontal">
              <template v-for="route in $router.options.routes" v-if="!route.meta.hidden">
                  <el-menu-item v-if="!route.children || route.children.length == 1" :index="route.path" :key="route.path">
                      {{ route.name }}
                  </el-menu-item>
                  <el-submenu v-else :index="route.path">
                      <template slot="title">{{ route.name }}</template>
                      <el-menu-item v-for="child in route.children" v-if="!child.meta.hidden" :index="route.path + '/' + child.path" :key="route.path + '/' + child.path">{{ child.name }}</el-menu-item>
                  </el-submenu>
              </template>
          </el-menu>
        </el-col>
        <el-col :span="2"  style="margin-top: 6px">
          <router-link to="/user/login"><el-button >Login</el-button></router-link>
        </el-col>
      </el-row>
    </el-col>

</template>
<script>
    export default {
        data() {
            return {
            };
        },
        computed: {
            level1() { // 默认打开所有一级菜单
                return this.$router.options.routes.map(function(route) {
                    return route.path;
                });
            }
        }
    };
</script>


<style scoped>
 .el-col {
     background-color: rgb(50, 65, 87) ;
 }
</style>
