<template>
  <div style="margin: 15px">
    <div v-if="confirmed" style="font-size: small"> <span>{{residencyStr}}</span>
      <el-button size="mini" icon="el-icon-edit" @click="edit"></el-button>
    </div>

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
  import {getHeader} from '../../../../env.js'
  export default {
    props:['countries','candidateId','type'],
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
        jsonStr:null,
        confirmedResidency:[],
        titles: ['Options','Added']
      }
    },
    methods:{
      confirm() {
        if (this.confirmedResidency.length === 0 && this.residency.length !== 0) {
          this.residency.sort();
          this.$http.post("http://localhost:8080/" + this.type + "/create/" + this.candidateId, this.residency,
            {headers: getHeader()}).then(response => {
            if (response.status === 201) {
              this.confirmedResidency = response.data;
              this.jsonStr = JSON.stringify(this.confirmedResidency);
              this.$emit('addResidencyInfo', this.confirmedResidency);
              this.confirmed = true;

            }
          })
        } else if (this.residency.length === 0) {
            this.$http.get("http://localhost:8080/" + this.type + "/delete/" + this.candidateId, {headers: getHeader()}).then(
              res => {
                if (res.status === 200) {
                  this.confirmedResidency = [];
                  this.$emit('editResidencyInfo', this.confirmedResidency);
                  this.confirmed = true;
                }
              });
          }
          else {
          if (this.jsonStr !== JSON.stringify(this.residency.sort())) {
            this.$http.post("http://localhost:8080/" + this.type + "/update/" + this.candidateId, this.residency,
              {headers: getHeader()}).then(response => {
              if (response.status === 201) {
                this.confirmedResidency = response.data;
                this.jsonStr = JSON.stringify(this.confirmedResidency);
                this.$emit('editResidencyInfo', this.confirmedResidency);
                this.confirmed = true;
              }
            })
          }else {
            this.confirmed = true;
          }
        }
      },
      reset(){
        this.residency = [];
      },
      edit(){
        this.confirmed = false;
      },
      deleteResidency(){
        if(this.confirmed) {
          this.$http.get("http://localhost:8080/"+this.type+"/delete/" + this.candidateId, {headers: getHeader()}).then(
            res => {
              if (res.status === 200) {
                this.confirmedResidency = [];
              }
            });
        }
      }
    },
    computed:{
      residencyStr(){
        let str = '';
        if(this.confirmedResidency.length===0) return 'No country selected';
        this.confirmedResidency.forEach(item => {
          str = str+ item.country.name +", ";
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
