<template>
  <div>
    <div style="margin: 10px">
      <div>
        <span style="margin:15px 0px">Contact Info
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
          @click="addContact"></el-button></span>
        </div>
        <template v-for="n in contactComponent">
          <contact-info @addContact="addContactInfo"></contact-info>
        </template>
    </div>
    <div style="margin: 10px">
        <span style="margin:15px 0">Address Info
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                     @click="addAddress"></el-button></span>
    </div>
    <template v-for="n in addressComponent">
      <address-info @addAddress="addAddressInfo" :countries = "countries"></address-info>
    </template>

    <div style="margin: 10px">
      <div>
        <span style="margin:15px 0px">Home Airport
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                     @click="addAirport"></el-button></span>
      </div>
      <template v-for="n in airportComponent">
        <home-airport @addAirport="addHomeAirport"></home-airport>
      </template>
    </div>

    <div style="margin: 10px">
      <div>
        <span style="margin:15px 0px">Residency
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                     @click="addResidency"></el-button></span>
      </div>
      <template v-if="residencyComponent">
        <residency-info @addResidency="addResidencyInfo" :countries="countries"></residency-info>
      </template>
    </div>

    <div style="margin: 10px">
      <div>
        <span style="margin:15px 0px">Citizenship
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                     @click="addCitizenship"></el-button></span>
      </div>
      <template v-if="citizenshipComponent">
        <residency-info @addResidency="addCitizenshipInfo" :countries="countries"></residency-info>
      </template>
    </div>
    <div style="margin: 10px">
      <div>
          <span style="margin:15px 0px">Extra Information
            <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                       @click="addExtraInfoComponent"></el-button></span>
      </div>
      <template v-for="n in extraInfoComponent">
        <extra-info @addExtraInfo="addExtraInfo"></extra-info>
      </template>
    </div>
  </div>
</template>

<script>
  import {getHeader} from "../../../../env.js"
  import ContactInfo from "./ContactInfo.vue"
  import AddressInfo from "./AddressInfo.vue"
  import HomeAirport from "./HomeAirport.vue"
  import ResidencyInfo from "./ResidencyInfo.vue"
  import ExtraInfo from "./ExtraInfo.vue"
  export default {
    components: {ContactInfo,AddressInfo,HomeAirport, ResidencyInfo,ExtraInfo},
    data: function () {
      return {
        result:{contacts:[], address:[], homeAirport:[], residency:null,citizenship:null, info:[]},
        contactComponent:0,
        addressComponent:0,
        airportComponent:0,
        extraInfoComponent:0,
        residencyComponent:false,
        citizenshipComponent:false,
        countries:null,
        airports:null
      }
    },
    methods:{
      addExtraInfo(value){
        this.result.info.push(value);
      },
      addExtraInfoComponent(){
        this.extraInfoComponent++;
      },
      addContact(){
        this.contactComponent++;
      },
      addContactInfo(value){
        this.result.contacts.push(value);
      },
      addAddress(){
        this.addressComponent++;
      },
      addAddressInfo(value){
        this.result.address.push(value);
      },
      addAirport(){
        this.airportComponent++;
      },
      addHomeAirport(value){
        this.result.homeAirport.push(value);
      },
      addResidency(){
        this.residencyComponent = true;
      },
      addResidencyInfo(value){
        this.result.residency = value;
      },
      addCitizenship(){
        this.citizenshipComponent = true;
      },
      addCitizenshipInfo(value){
        this.result.citizenship = value;
      },
      fetchCountry(){
        let countryOptions = JSON.parse(localStorage.getItem('countryOptions'));
        if (!countryOptions){
          this.$http.get('http://localhost:8080/country/',{headers:getHeader()}).then(response=>{
            if(response.status===200){
              localStorage.setItem('countryOptions', JSON.stringify(response.data));
              this.countries = response.data;
            }
          })
        }
        else this.countries = countryOptions;

      },
      fetchAirport(){
        let airportOptions = JSON.parse(localStorage.getItem('airportOptions'));
        if (!airportOptions){
          this.$http.get('http://localhost:8080/airport/',{headers:getHeader()}).then(response=>{
            if(response.status===200){
              localStorage.setItem('airportOptions', JSON.stringify(response.data));
              this.airports = response.data;
            }
          })
        }
        else this.airports = airportOptions;
      }
    },
    created(){
      this.fetchCountry();
      this.fetchAirport();
    }
  }
</script>
<style>

  .el-select .el-input {
    width: 110px;
  }
</style>
