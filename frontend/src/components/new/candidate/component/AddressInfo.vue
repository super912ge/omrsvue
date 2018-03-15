<template>
  <div>
  <div v-if="confirmed" style="margin-left: 20px" v-show="!deleted">
    <span style="font-size: small"> {{addressStr}}</span>
    <el-button size="mini" icon="el-icon-edit" @click="edit"></el-button>
    <el-button size="mini" icon="el-icon-delete" @click="deleteAddress" ></el-button>
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
      <el-button size="mini" icon="el-icon-delete" @click="deleteAddress"></el-button>
    </div>
  </div>
  </div>
</template>

<script>
import _ from 'lodash'
import {getHeader} from "../../../../env.js"
  export default {
    props:['countries','candidateId'],
    data(){
      return {
        address:{
          id:null,
          line1:'',
          line2:'',
          city: '',
          region:'',
          postalCode:'',
          country: null
        },
        deleted:false,
        confirmed: false
      }
    },
    methods:{
      confirm(){
        if(this.address.id){
          this.$http.post("http://localhost:8080/address/update",this.address,{headers:getHeader()}).then(
            res=>{
              if(res.status===200){
                this.address.id = res.data.result;
                this.confirmed = true;
                this.$emit('editAddress', this.address);
              }}
          );
        }
        else {
          this.$http.post("http://localhost:8080/address/create/" + this.candidateId, this.address, {headers: getHeader()}).then(
            res => {
              if (res.status === 200) {
                this.address.id = res.data.result;
                this.confirmed = true;
                this.$emit('addAddress', this.address.id);

              }
            }
          );
        }
      },
      edit(){
        this.confirmed = false;
      },
      deleteAddress(){
        if(this.address.id)
        this.$http.get("http://localhost:8080/address/delete/" + this.address.id,  {headers: getHeader()}).then(
          res => {
            if (res.status === 200) {
              this.deleted = true;
            }
          });
        else  this.$emit('deleteAddress');
      }
    },
    computed:{
      addressStr(){

        if(this.address.line2===null || ''===this.address.line2.trim())
          return this.address.line1 + ", "+ this.address.city + ", "
            + this.address.region+ ", "+ _.find(this.countries,['id',this.address.country]).name+", "
            +this.address.postalCode;
        else
        return this.address.line1 + ", "+ this.address.line2 + ", "+ this.address.city + ", "
          + this.address.region+ ", "+ _.find(this.countries,['id',this.address.country]).name+", "
          +this.address.postalCode;
      }
    }

  }
</script>
