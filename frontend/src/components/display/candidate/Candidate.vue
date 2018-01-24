<template>
  <el-container>
    <el-aside>Aside</el-aside>
    <el-main><div class="block">
      <span class="demonstration">Week</span>
      <el-date-picker
        v-model="value3"
        type="week"
        format="Week WW"
        placeholder="Pick a week">
      </el-date-picker>
    </div>
      <div class="block">
        <span class="demonstration">Month</span>
        <el-date-picker
          v-model="value4"
          type="month"
          placeholder="Pick a month">
        </el-date-picker>
      </div>
      <div class="block">
        <span class="demonstration">Year</span>
        <el-date-picker
          v-model="value5"
          type="year"
          placeholder="Pick a year">
        </el-date-picker>
      </div>
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
  .el-main{
    width:60%
  }
</style>
