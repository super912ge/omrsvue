<template>
  <div>
  <div v-if="confirmed" style="margin-left: 20px">
    <span style="font-size: small"> {{addressStr}}</span>
  </div>
  <div v-else style="padding: 10px; font-size: small" >
    <div style="margin: 5px">
      <span>Line 1: </span>
      <el-input style="width: 350px" size="mini" v-model="address.line1" ></el-input>
    </div>
    <div style="margin: 5px ">
      <span>Line 2: </span>
      <el-input size="mini" style="width: 350px" v-model="address.line2"></el-input>

    </div>
    <div style="margin: 5px 15px">
      <span>City: </span>
      <el-input style="width: 150px" size="mini" v-model="address.city"></el-input>
      <span style="margin-left: 15px">State: </span>
      <el-input size="mini" style="width: 150px" v-model="address.region"></el-input>
    </div>

    <div style="margin: 5px">
      <span>Country: </span>
      <el-select v-model="address.country" placeholder="Select" size="mini" style="width: 130px">
        <el-option v-for="item in countries" :label="item.name" :value="item.id" :key="item.id"></el-option>
      </el-select>
      <span>Postal Code: </span>
      <el-input size="mini" v-model="address.postalCode" style="width: 80px"></el-input>
      <el-button size="mini" icon="el-icon-check" @click="confirm"></el-button>
    </div>
  </div>
  </div>
</template>

<script>
import _ from 'lodash'
  export default {
    props:['countries'],
    data(){
      return {
        address:{
          line1:'',
          line2:'',
          city: '',
          region:'',
          postalCode:'',
          country: null
        },
        options:[{id:1,name:'b'},{id:2, name:'e'}],
        confirmed: false
      }
    },
    methods:{
      confirm(){
        this.confirmed = true;
        this.$emit('addAddress', this.address);
      }
    },
    computed:{
      addressStr(){
        return this.address.line1 + ", "+ this.address.line2 + ", "+ this.address.city + ", "
          + this.address.region+ ", "+ _.find(this.countries,['id',this.address.country]).name+", "+this.address.postalCode;
      }
    }

  }
</script>
