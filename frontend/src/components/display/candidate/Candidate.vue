<template>
  <el-container style="height: auto">
    <el-aside class="left">
      <span>Searching Result</span>

      <ul>
        <li li v-for="candidate in candidates">
          {{candidate.name}}
        </li>
      </ul>



    </el-aside>
    <el-main>

    </el-main>
    <el-aside>Video</el-aside>


  </el-container>

</template>

<script>

  import ElRow from "element-ui/packages/row/src/row";
//  import ElContainer from "element-ui/packages/"

  export default {
    components: {ElRow},
    data() {
      return {
        value3: '',
        value4: '',
        value5: ''
      };
    }

  }
</script>

<style scoped>
  .el-container{
    width: 100%;
  }
  .el-aside .left{
    width: 15%;
    background-color: #122b40;
    text-color:white
  }
  .el-main{
    width:60%
  }
</style>
