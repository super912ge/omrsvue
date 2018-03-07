<template>
  <div style="margin: 15px">
    <div v-if="confirmed" style="font-size: small"> {{residencyStr}}</div>

    <el-transfer v-else
      v-model="residency"
      :titles="titles"
      :data="options"
      >
      <el-button class="transfer-footer" slot="left-footer" @click="reset" size="small" >Reset</el-button>
      <el-button class="transfer-footer" @click="confirm" slot="right-footer" size="small">Confirm</el-button>
    </el-transfer>
  </div>

</template>
<script>
  import _ from 'lodash'
  export default {
    props:['countries'],
    data() {
      const generateData = _ => {
        const data = [];

        this.countries.forEach((country) => {
          data.push({
            label: country.name,
            key: country.id,
            code: country.iso_alpha3
          });
        });
        return data;
      };
      return {
        confirmed:false,
        options: generateData(),
        residency: [],
        titles: ['Options','Added']
      }
    },
    methods:{
      confirm(){
        this.confirmed = true;
        this.$emit('addResidencyInfo',this.residency);
      },
      reset(){
        this.residency = [];
      }
    },
    computed:{
      residencyStr(){
        let str = '';
        this.residency.forEach(id => {
          str = str+ _.find(this.countries, ['id', id]).name +", ";
        });
        return str.slice(0,-2);
      }
    }
  };
</script>
<style>
  .el-transfer-panel p.el-transfer-panel__header {
    font-size: small;
    height: 30px;
    line-height: 30px;
  }
  .el-transfer-panel__header label.el-checkbox {
    line-height: 30px;
  }
  .el-transfer-panel .el-transfer-panel__header .el-checkbox{
    line-height: 30px;
    font-size: small;
  }
  .el-transfer-panel .el-transfer-panel__header .el-checkbox .el-checkbox__label{
    font-size: small;
  }
  .el-transfer-panel__item{
    height: 20px;
    line-height: 20px;
  }
  .el-checkbox__label{
    font-size: 12px;
  }

</style>
