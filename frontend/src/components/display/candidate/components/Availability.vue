<template>
  <el-card>
    <div slot="header" class="clearfix">
      <span>Availability</span>
    </div>
    <el-row style="margin-top: 5px">
      <el-col :span="8">
        <div class="block">
          <span class="demonstration" style="font-size: small">Add Availability</span>
          <el-date-picker
            v-model="dateRange"
            type="daterange"
            size="mini"
            range-separator="To"
            start-placeholder="Start date"
            end-placeholder="End date"
            style="margin-top:10px; width: 80%">
          </el-date-picker>
        </div>
        <el-input size="mini"
          type="textarea"
          :rows="3"
          placeholder="Please input"
          v-model="comment"
          style="margin-bottom: 5px; width: 80%;font-size: small">
        </el-input>
        <el-radio-group v-model="interest" size="mini" style="font-size:10%;margin-left: 1px">
          <el-radio-button :label="1" style="border-radius:4px;border: solid thin;border-color: dodgerblue ">Available</el-radio-button>
          <el-radio-button :label="3" style="border-radius:4px;border: solid thin;border-color: dodgerblue ">Maybe Unavailable</el-radio-button><br/>
          <el-radio-button :label="4" style="border-radius:4px;border: solid thin;border-color: dodgerblue ">Unavailable</el-radio-button>
          <el-radio-button :label="2" style="border-radius:4px;border: solid thin;border-color: dodgerblue " >Maybe Available</el-radio-button>


        </el-radio-group>


      </el-col>
      <el-col :span="16">
        <div class="block">
          <span class="demonstration" style="font-size: small">Upcoming Availability</span>

          {{availability}}
        </div>

      </el-col>

    </el-row>


  </el-card>

</template>
<script>
  import ElCard from "../../../../../node_modules/element-ui/packages/card/src/main.vue";
  import ElCol from "element-ui/packages/col/src/col";
  import ElRadioButton from "../../../../../node_modules/element-ui/packages/radio/src/radio-button.vue";

  export default {
    components: {
      ElRadioButton,
      ElCol,
      ElCard},
    props:['availability'],
    data(){
      return {
        dateRange:null,
        comment:null,
        interest:null
      }
    }
  }
</script>
<style>
  .el-radio-button {
    border-radius: 0 4px 4px 0;
  }
</style>
