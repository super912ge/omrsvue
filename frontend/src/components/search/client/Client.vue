<template>
<div>



  <el-row>
    <el-col :span="11" :offset="1">

      <el-row style="margin-top: 25px; margin-left: 25px">



      Client Name: <el-input size="mini" v-model="criteria.name" style="width: 70%"></el-input>
      </el-row>
    </el-col>
  </el-row>

  <el-row>
    <div style="margin-top: 150px"></div>
    <el-col :span="4" :offset="8">

      <el-button style="margin: auto" @click="displayClient">Display Client({{result.clientList.length}})</el-button>
    </el-col>
    <el-col :span="2">
      <el-button style="margin: auto" @click="reset">Reset</el-button>
    </el-col>

  </el-row>
  <el-row>
    <div style="margin-top: 20px"></div>
    <!--<display-band v-if="result.showSearchResult" :resultList="result.bandList" :pageNumber="result.totalPage"-->
                  <!--@pageChange="displayBand"></display-band>-->
  </el-row>
</div>
</template>
<script>

  import _ from 'lodash'
  import {getHeader} from '../../../env'
  import localStorage from 'localStorage'
  import ElRow from "element-ui/packages/row/src/row";
  import ElButton from "../../../../node_modules/element-ui/packages/button/src/button.vue";
  //  import Displayband from './Displayband.vue'
  import ElCol from "element-ui/packages/col/src/col";

  export default{
    components: {
      ElCol,
      ElButton,
      ElRow
    },
    data(){
      return {
        criteria: {
          name: null
        },
        result: {
          clientList: []
        }
      }
    },
    method:{
      displayClient(){

      },
      reset(){

      },
      handleNameChange(){

      },
      searchClientByName(){

      }
    }
  }

</script>
