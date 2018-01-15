<template>

  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>{{member.name}}</span>
      <el-button style="float: right; padding: 2px 0" type="text">Edit</el-button>
    </div>
    <div class="text item" is="display-candidate-skill">
      <!--<display-candidate-skill slot-scope="scope" :evaluation=member.evaluation></display-candidate-skill>-->

    </div>



  </el-card>

</template>

<script>
import DisplayCandidateSkill from '../candidate/DisplayCandidateSkill.vue'
  export default {

    props: ['member'],
    component:{
      DisplayCandidateSkill
    }
  }
</script>
<style scoped>
  .text {
    font-size: 8px;
  }

  .item {
    margin-bottom: 3px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 40%;
  }
</style>
