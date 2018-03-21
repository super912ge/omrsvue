<template>
  <div>
    <div v-if="confirmed" style="margin-left: 20px" v-show="!deleted">
      <span style="font-size: small"> {{airportStr}}</span>
      <el-button size="mini" icon="el-icon-edit" @click="edit"></el-button>
      <el-button size="mini" icon="el-icon-delete" @click="deleteAirport" ></el-button>
    </div>
    <div style="margin-left: 15px; margin-top: 10px" v-else="">
      <span style="font-size: small">Airport: </span>
      <el-select placeholder="Select" size="mini" v-model="airport.airportId">
        <el-option v-for="item in airports" :label="item.name +'-------'+item.code" :value="item.id" :key="item.id"></el-option>
      </el-select>
      <span style="font-size: small">Client: </span> <el-input v-model="airport.client" size="mini" style="width: 200px"></el-input>
      <el-button size="mini" icon="el-icon-check" @click="confirm"></el-button>
      <el-button size="mini" icon="el-icon-delete" @click="deleteAirport" ></el-button>
    </div>
  </div>
</template>
<script>
  import _ from "lodash"
  import {getHeader} from "../../../../env.js"
  export default {
    props:['airports','candidateId'],
    data(){
      return {
        confirmed: false,
        airport:{id:null, airportId:'', client:''},
        deleted: false,
        jsonStr: null
      }
    },
    computed:{
      airportStr(){
        let airp = _.find(this.airports,['id',this.airport.airportId]);
        return airp.name+ "("+ airp.code+")  "+this.airport.client;
      }
    },
    methods:{
      confirm(){
        if(this.airport.id) {
          if(this.jsonStr!==JSON.stringify(this.airport)) {
            this.$http.post("http://localhost:8080/homeAirport/update", this.airport,
              {headers: getHeader()}).then(res => {
              if (res.status === 200) {
                this.airport.id = res.data.result;
                this.jsonStr = JSON.stringify(this.airport);
                this.$emit('addAirport', this.airport);
                this.confirmed = true;
              }
            });
          }else this.confirmed = true;
        }else {
          this.$http.post("http://localhost:8080/homeAirport/create/"+this.candidateId, this.airport,
            {headers: getHeader()}).then(res => {
            if (res.status === 200) {
              this.airport.id = res.data.result;
              this.jsonStr = JSON.stringify(this.airport);
              this.$emit('editAirport', this.airport);
              this.confirmed = true;
            }
          })
        }

      },
      edit(){
        this.confirmed = false;
      },
      deleteAirport(){
        if(this.confirmed) {
          this.$http.get("http://localhost:8080/homeAirport/delete/" + this.airport.id,
            {headers: getHeader()}).then(
              res => {
                if (res.status === 200) {
                  this.deleted = true;
                }
            });
        }else this.$emit('deleteAirport');
      }
    }
  }
</script>
