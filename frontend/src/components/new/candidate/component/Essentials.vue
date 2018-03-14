<template>
  <div>
    <div style="margin: 10px">
      <div>
        <span style="margin:15px 0px">Contact Info
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                     :disabled="button.disableAddContact"
                     @click="addContact"></el-button></span>
        </div>
        <template v-for="item in result.contacts">
          <contact-info @addContact="addContactInfo" @editContact="editContact"
                        @deleteContact="deleteContact" :candidateId="candidateId"></contact-info>
        </template>
    </div>
    <div style="margin: 10px">
        <span style="margin:15px 0">Address Info
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                     :disabled="button.disableAddAddress"
                     @click="addAddress"></el-button></span>
    </div>
    <template v-for="item in result.address">
      <address-info @addAddress="addAddressInfo" :countries = "countries" :candidateId="candidateId"
                    @editAddress="editAddressInfo"
       @deleteAddress="deleteAddress"></address-info>
    </template>

    <div style="margin: 10px">
      <div>
        <span style="margin:15px 0">Home Airport
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus" :disabled="button.disableAddAirport"
                     @click="addAirport"></el-button></span>
      </div>
      <template v-for="item in result.homeAirport">
        <home-airport @addAirport="addHomeAirport" @deleteAirport="deleteAirport" @editAirport="editAirport"
                      :candidateId="candidateId"></home-airport>
      </template>
    </div>

    <div style="margin: 10px">
      <div>
        <span style="margin:15px 0px">Residency
                  <el-button size="mini" v-if="residencyComponent" style="padding: 0; border: hidden" icon="el-icon-delete"
                             @click="deleteResidency"></el-button>
          <el-button size="mini" v-else style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                     @click="addResidency"></el-button>

        </span>
      </div>
      <template>
        <residency-info @addResidency="addResidencyInfo" @editResidency="editResidency"
                        ref="residency"
                        type="residency"
                        v-if="residencyComponent"
                        :countries="countries" :candidateId="candidateId"></residency-info>
      </template>
    </div>

    <div style="margin: 10px">
      <div>
        <span style="margin:15px 0px">Citizenship
           <el-button size="mini" v-if="citizenshipComponent"
                      style="padding: 0; border: hidden" icon="el-icon-delete"
                      @click="deleteCitizenship"></el-button>
          <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus" v-else
                     @click="addCitizenship"></el-button></span>

      </div>
      <template v-show="citizenshipComponent">
        <residency-info ref="citizenship" @addResidency="addCitizenshipInfo" :countries="countries"
                        type="citizenship" @editResidency="editCitizenship"
                        v-if="citizenshipComponent"
                        :candidateId="candidateId"></residency-info>
      </template>
    </div>
    <div style="margin: 10px">
      <div>
          <span style="margin:15px 0px">Extra Information
            <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus" :disabled="button.disableAddExtraInfo"
                       @click="addExtraInfoComponent"></el-button></span>
      </div>
      <template v-for="item in result.info">
        <extra-info @addExtraInfo="addExtraInfo" :candidateId="candidateId" @editExtraInfo="editExtraInfo" @deleteExtraInfo="deleteExtraInfo"></extra-info>
      </template>
    </div>
  </div>
</template>

<script>
  import {getHeader} from "../../../../env.js"
  import localStorage from "localStorage"
  import ContactInfo from "./ContactInfo.vue"
  import _ from "lodash"
  import AddressInfo from "./AddressInfo.vue"
  import HomeAirport from "./HomeAirport.vue"
  import ResidencyInfo from "./ResidencyInfo.vue"
  import ExtraInfo from "./ExtraInfo.vue"
  export default {
    components: {ContactInfo,AddressInfo,HomeAirport, ResidencyInfo,ExtraInfo},
    props:['candidateId'],
    data: function () {
      return {
        result:{contacts:[], address:[], homeAirport:[], residency:null,citizenship:null, info:[]},
        residencyComponent:false,
        citizenshipComponent:false,
        countries:null,
        airports:null,
        button:{
          disableAddAirport:false,
          disableAddExtraInfo:false,
          disableAddAddress:false,
          disableAddContact:false
        }
      }
    },
    methods:{

      addExtraInfoComponent(){
        this.result.info.push({});
        this.button.disableAddExtraInfo = true;
      },
      addExtraInfo(value){
        this.result.info.pop();
        this.result.info.push(value);
        this.button.disableAddExtraInfo = false;
      },
      editExtraInfo(val){
        let index = _.findIndex(this.result.info, {'id':val.id});
        this.result.info[index] = val;
      },
      deleteExtraInfo(val){
        if(val) _.remove(this.result.info, {id:val});
        else {this.result.info.pop(); this.button.disableAddExtraInfo = false;}
      },

      addContact(){
        this.button.disableAddContact = true;
        console.log(this.candidateId);
        this.result.contacts.push({});
      },
      editContact(val){
        let index = _.findIndex(this.result.contacts, {'id':val.id});
        this.result.contacts[index] = val;
      },
      deleteContact(val){
        if(val)
         _.remove(this.result.contacts, {id:val});
        else {this.result.contacts.pop(); this.button.disableAddContact = false;}
      },
      addContactInfo(value){
        this.result.contacts.pop();
        this.result.contacts.push(value);
        this.button.disableAddContact = false;
      },
      addAddress(){
        this.button.disableAddAddress = true;
        this.result.address.push({});
      },
      addAddressInfo(value){
        this.result.address.pop();
        this.result.address.push(value);
        this.button.disableAddAddress = false;
      },
      editAddressInfo(val){
        let index = _.findIndex(this.result.address, {'id':val.id});
        this.result.address[index] = val;
      },
      deleteAddress(val){
        if(val)
        _.remove(this.result.address, {id:val});
        else {this.result.address.pop(); this.button.disableAddAddress = false;}
      },
      addAirport(){
        this.result.homeAirport.push({});
        this.button.disableAddAirport = true;
      },
      addHomeAirport(value){
        this.result.homeAirport.pop();
        this.result.homeAirport.push(value);
        this.button.disableAddAirport = true;
      },
      editAirport(value){
        let index = _.findIndex(this.result.homeAirport, {'id':value.id});
        this.result.homeAirport[index] = value;
      },
      deleteAirport(val){
        if(val)
        _.remove(this.result.homeAirport, {id:val});
        else {this.result.homeAirport.pop(); this.button.disableAddAirport = false;}
      },
      addResidency(){
        this.residencyComponent = true;
      },
      addResidencyInfo(value){
        this.result.residency = value;
      },
      editResidency(value){
        this.result.residency = value;
      },
      deleteResidency(){
        this.$refs.residency.deleteResidency();
        this.residencyComponent=false;
      },
      addCitizenship(){
        this.citizenshipComponent = true;
      },
      addCitizenshipInfo(value){
        this.result.citizenship = value;
      },
      editCitizenship(value){
        this.result.citizenship = value;
      },
      deleteCitizenship(){
        this.$refs.citizenship.deleteResidency();
        this.citizenshipComponent = false;
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
