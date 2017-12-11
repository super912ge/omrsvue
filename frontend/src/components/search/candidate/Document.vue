<template>
 <div>
<el-row>
  <div style="margin-top: 15px"></div>

  <el-col :span="6">
    <div style="margin-top: 8px"></div>
    <span class="demonstration" style="margin-left: 25px;font-size: 80%">Valid Until</span>
  </el-col>
<el-col :span ="18">


  <el-date-picker v-model="validUntilDate" type="date" placeholder="Pick a Date"
                  format="yyyy/MM/dd" value-format="yyyy-MM-dd" style="width: 180px"></el-date-picker>

</el-col>



</el-row>
   <el-row>
     <div style="margin-top: 15px"></div>
  <el-col :span="6">
    <div style="margin-top: 8px"></div>
    <span class="demonstration" style="margin-left: 25px; font-size: 80%">{{selectType}}</span>

  </el-col>
<el-col :span="18">

  <el-select v-model="selectedOption" filterable placeholder="Select" style="width: 180px">
    <el-option
      v-for="item in options"
      :key="item.id"
      :label="item.name"
      :value="item.id">
    </el-option>
  </el-select>
</el-col>


   </el-row>


   <div style="margin-bottom: 15px"></div>
  </div>
</template>

<script>

  import ElRow from "element-ui/packages/row/src/row";
  import ElCol from "element-ui/packages/col/src/col";

  export default {

    name:'document',


  props: ['selectType' , 'options'],

    data (){
      return {
        selectedOption: null,
        validUntilDate: null
      }

    }
  }


</script>
