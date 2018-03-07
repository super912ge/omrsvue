<template>

  <div STYLE="font-size: small; margin: 15px">
    <div v-if="confirmed">{{infoStr}} </div>
    <div v-else>
      <el-input placeholder="Please input" size="mini" style="width:  350px" v-model="info.value">

        <el-select v-model="info.type" slot="prepend"
                   placeholder="Select" size="mini">
          <el-option v-for="item in types"
                     :label="item" :value="item" :key="item"  ></el-option>
        </el-select>

      </el-input>
      <el-button size="mini" icon="el-icon-check" @click="confirm"></el-button>
    </div>
  </div>
</template>
<script>

  export default {
    data(){
      return {
        confirmed:false,
        info:{
          type: null,
          value:null
        },
        types:['BIRTHPLACE', 'SIN', 'HH_PACK_ID', 'HH_CANDIDATE_ID']
      }
    },
    methods:{
      confirm(){
        this.confirmed = true;
        this.$emit('addExtraInfo', this.info);
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
