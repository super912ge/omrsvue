<template>
  <div>
    <el-row >
      <el-col :span="2":offset="11">

        <p>OMRS</p>
      </el-col>

      <el-col :span="2" :offset="9" style="margin-top: 6px">
        <router-link to="/user/login"><el-button >Login</el-button></router-link>
      </el-col>

    </el-row>



  </div>

</template>

<script>
    export default {

      name: 'header'
    }
</script>
<style scoped>
  div{
    background-color: rgb(191, 203, 217);
  }

</style>
