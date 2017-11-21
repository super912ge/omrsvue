<template>
    <el-col :span="4">
    <el-menu :default-active="$route.path" theme="dark" router>
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
 .el-col .el-col-4{
     height: 700px;
     background-color: rgb(191, 203, 217);
 }
</style>