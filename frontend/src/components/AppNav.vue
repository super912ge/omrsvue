<template>
    <el-col  :span="24">

      <el-row>
        <el-col :span="4" style="height: 60px">OMRS</el-col>

        <el-col :span="18">
          <el-menu :default-active="$route.path" background-color = "rgb(50, 65, 87)"
                   text-color="white" activeTextColor="blue" router  mode="horizontal">
              <template v-for="route in $router.options.routes" v-if="!route.meta.hidden">

                  <el-menu-item v-if="!route.children || route.children.length == 1" :index="route.path" :key="route.path">
                      {{ route.name }}
                  </el-menu-item>
                  <el-submenu v-else :index="route.path">
                      <template slot="title">{{ route.name }}</template>
                      <el-menu-item v-for="child in route.children" v-if="!child.meta.hidden" :index="route.path + '/'
                      + child.path" :key="route.path + '/' + child.path">{{ child.name }}</el-menu-item>
                  </el-submenu>
              </template>
          </el-menu>
        </el-col>


        <el-col :span="2"  style="margin-top: 15px">

          <el-dropdown v-if="username" @command="handleCommand">
            <span id="username">
              {{username}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown" >
              <el-dropdown-item command="account">Account</el-dropdown-item>
              <el-dropdown-item command="logout">Log out</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <div v-else>
            <router-link to="/user/login"><el-button >Log in</el-button></router-link>
          </div>
        </el-col>
      </el-row>
    </el-col>

</template>
<script>
  import ls from 'localStorage'
  import ElDropdown from "../../node_modules/element-ui/packages/dropdown/src/dropdown.vue";
  import ElDropdownItem from "../../node_modules/element-ui/packages/dropdown/src/dropdown-item.vue";
    export default {
      components: {
        ElDropdownItem,
        ElDropdown},
      data() {
            return {
                text: 'logout'
            };
        },
      computed: {
            level1() { //
                return this.$router.options.routes.map(function(route) {
                //  let options = route.path.exclude
                    return route.path;
                });
            },
          username(){
              return JSON.parse(ls.getItem('username'));

          }
        },
      methods:{
        handleCommand(command) {
          console.log(command);
            if(command==='logout'){
              console.log(command);
              ls.removeItem('userId');
              ls.removeItem('username');
              ls.removeItem('authUser');
              this.$router.push('/user/login')
            }else if(command ==='account'){
              this.$router.push('/account')
            }
          }

      }
    };
</script>


<style scoped>
 .el-col {
     background-color: rgb(50, 65, 87) ;
 }

  #username{

    font-size: large;
    color:white;
  }
 .el-menu-item{
   height: 60.99px;
 }
</style>
