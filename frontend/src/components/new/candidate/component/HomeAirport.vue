<template>
  <div>
    <div v-if="confirmed" style="margin-left: 20px">
      <span style="font-size: small"> {{airportStr}}</span>
    </div>
    <div style="margin-left: 15px; margin-top: 10px">
      <span style="font-size: small">Airport: </span>
      <el-select placeholder="Select" size="mini" v-model="airport.id">
        <el-option v-for="item in airports" label="item.name +'----'+item.code" value="item.id" :key="item.id"></el-option>
      </el-select>
      <span style="font-size: small">Client: </span> <el-input v-model="airport.client" size="mini" style="width: 200px"></el-input>
      <el-button size="mini" icon="el-icon-check" @click="confirm"></el-button>
    </div>
  </div>
</template>
<script>
  import _ from "lodash"
  export default {
    props:['airports'],
    data(){
      return {
        confirmed: false,
        airport:{id:'', client:''}
      }
    },
    computed:{
      airportStr(){
        let airp = _.find(this.airports,['id',this.airport.id]);
        return airp.name+ "("+ airp.code+")  "+this.airport.client;
      }
    },
    methods:{
      confirm(){
        this.confirmed = true;
        this.$emit('addAirport', this.airport);
      }
    }
  }
</script>
