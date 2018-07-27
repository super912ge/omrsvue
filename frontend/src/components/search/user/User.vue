<template>
  <div>
  <el-row>

    <el-col style="margin-top: 25px" :span="11" :offset="1">
      <el-row style="margin-top: 25px">
        Username: <el-input size="mini" placeholder="username" v-model="criteria.username" style="width: 50%" @change="handleChange"></el-input>
      </el-row>
      <el-row style="margin-top: 25px">
       Name: <el-input size="mini" placeholder="name" v-model="criteria.name" style="width: 50%"@change="handleChange"></el-input>
      </el-row>
      <el-row style="margin-top: 25px">
        Email: <el-input size="mini" placeholder="email" v-model="criteria.email" style="width: 50%"@change="handleChange"></el-input>
      </el-row>
      <el-row style="margin-top: 25px">
        Role: <el-select size="mini" placeholder="role" v-model="criteria.role" style="width: 50%"@change="handleChange">
        <el-option
          v-for="item in roles"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
      </el-row>
    </el-col>
  </el-row>
    <el-row>
      <div style="margin-top: 150px"></div>
      <el-col :span="4" :offset="8">

        <el-button style="margin: auto" @click="displayUser">Display User({{result.userList.length}})</el-button>
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

  export default {

    component:{

    },
    data(){
      return {
        criteria:{
          name: null,
          email: null,
          username: null,
          role:null
        },
        result:{
          userList:[]
        },
        roles:[]
      }
    },
    method:{
      reset(){},
      displayUser(){},
      handleChange(){},
      searchByCriteria(){}
    }
  }

</script>
