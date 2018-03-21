<template>

  <div STYLE="font-size: small; margin: 15px" v-show="!deleted">
    <div v-if="confirmed">{{infoStr}}
      <el-button size="mini" icon="el-icon-edit" @click="edit"></el-button>
    <el-button size="mini" icon="el-icon-delete" @click="deleteExtraInfo" ></el-button></div>
    <div v-else>
      <el-input placeholder="Please input" size="mini" style="width:  350px" v-model="info.value">

        <el-select v-model="info.type" slot="prepend"
                   placeholder="Select" size="mini">
          <el-option v-for="item in types"
                     :label="item" :value="item" :key="item"  ></el-option>
        </el-select>

      </el-input>

      <el-button size="mini" icon="el-icon-check" @click="confirm"></el-button>
      <el-button size="mini" icon="el-icon-delete" @click="deleteExtraInfo" ></el-button>
    </div>
  </div>
</template>
<script>
import {getHeader} from '../../../../env.js'
  export default {
  props: ['candidateId'],
    data(){
      return {
        confirmed:false,
        info:{
          id:null,
          type: null,
          value:null
        },
        types:['BIRTHPLACE', 'SIN', 'HH_PACK_ID', 'HH_CANDIDATE_ID'],
        deleted:false,
        jsonStr: null
      }
    },
    methods:{
      confirm(){

        if (!this.info.id) {

          if(JSON.stringify(this.info)!==this.jsonStr) {
            this.$http.post("http://localhost:8080/info/create/" + this.candidateId, this.info, {headers: getHeader()}).then(
              res => {
                if (res.status === 200) {
                  this.info.id = res.data.result;
                  this.jsonStr = JSON.stringify(this.info);
                  this.confirmed = true;
                  this.$emit('addExtraInfo', this.info);
                }
              })
          }else this.confirmed = true;
        }else {
          this.$http.post("http://localhost:8080/info/update",this.info,{headers:getHeader()}).then(
            res=>{
              if(res.status===200){
                this.info.id = res.data.result;
                this.jsonStr = JSON.stringify(this.info);
                this.confirmed = true;
                this.$emit('editExtraInfo', this.info);
              }}
          );
        }
      },

      edit(){
        this.confirmed = false;

      },


      deleteExtraInfo(){

        if(this.confirmed) {
          this.$http.get("http://localhost:8080/info/delete/" + this.info.id, {headers: getHeader()}).then(
            res => {
              if (res.status === 200) this.deleted = true;
            });
        }else this.$emit('deleteExtraInfo');
      }
    },
    computed:{
      infoStr(){
        return this.info.type + ": " +this.info.value;
      }
    }
  }
</script>

<style scoped>
  li.el-select-dropdown__item{
    height: 20px;
    font-size: 10px;
  }
  li.el-select-dropdown__item span{
    font-size: 10px;
    /*line-height: 20px;*/
  }

</style>
