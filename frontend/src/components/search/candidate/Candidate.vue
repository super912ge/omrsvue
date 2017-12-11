<template>
<div>

<el-row>
<el-col  :span="12">
<el-row>
  <el-col :span="8" >
    <el-input v-model="candidateId" placeholder="Candidate ID" class="shortInput" @change='handleIdChange'></el-input>
  </el-col>
  <el-col :span="8" style="margin-left: 15px">
    <el-input v-model="actId" placeholder="Candidate Act ID" class="shortInput"></el-input>
  </el-col>

</el-row>
  <el-row>
    <div style="margin-top: 15px;">
      <el-input placeholder="Candidate Name" v-model="candidateName.name" class="input-with-select"
                style="width: 70%" @change="handleNameChange">
        <el-select v-model="candidateName.searchType" slot="prepend" placeholder="select">
          <el-option label="First Name" value="FirstName"></el-option>
          <el-option label="Last Name" value="LastName"></el-option>
          <el-option label="Any Match" value="Any"></el-option>
        </el-select>
      </el-input>
    </div>
  </el-row>
<el-row style="margin-top: 25px">

  <el-col :span="24">

    <div class="block">
      <span class="demonstration">Availability</span>
      <el-date-picker style="width: 300px"
        v-model="availability"
        type="daterange"
        range-separator="To"
        start-placeholder="Start date"
        end-placeholder="End date">
      </el-date-picker>
    </div>
  </el-col>
</el-row>


  <el-row style="margin-top: 15px">
    <span class="demonstration">Interest Level</span>
    <div style="margin-top: 10px">
      <el-radio-group v-model="interestLevel" size="small">
        <el-radio-button label="4" >Hard Negative</el-radio-button>
        <el-radio-button label="3">Soft Negative</el-radio-button>
        <el-radio-button label="2">Soft Positive</el-radio-button>
        <el-radio-button label="1" >Hard Positive</el-radio-button>
      </el-radio-group>
    </div>

  </el-row>
  <el-row style="margin-top: 25px">
    <span class="demonstration">Experience</span>
    <div style="margin: 15px 0;"></div>
    <el-collapse style="width: 70%  "  @item-click="fetchClient">
      <el-collapse-item title="Client" style="font-size: large" >

        <!--<el-checkbox :indeterminate="isIndeterminateClient" v-model="checkAllClient"-->
                     <!--@change="handleCheckAllChange">Check all</el-checkbox>-->
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="checkedClients" style="height: 200px; overflow-y: scroll;">
          <el-checkbox v-for="client in clients" :label="client.id" :key="client.id"style="margin-left: 5px; width: 200px" @change="handleClientsChange" >{{client.name}}</el-checkbox>
        </el-checkbox-group>

      </el-collapse-item>

    </el-collapse>

    <div style="margin-top: 15px"></div>
      <el-collapse style="width: 70%  "  @item-click="fetchVenue">

      <el-collapse-item title="Venue">

        <!--<el-checkbox :indeterminate="isIndeterminateVenue" v-model="checkAllVenue" @change="handleCheckAllChange">Check all</el-checkbox>-->
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="checkedVenues" style="height: 200px; overflow-y:scroll">
          <el-checkbox v-for="venue in venues" :label="venue.id" :key="venue.id"style="margin-left: 5px; width: 200px">{{venue.name}}  ------  {{venue.clientCode}}</el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
  </el-row>

  <el-row style="margin-top: 15px">
    <span class="demonstration" style="margin-left: 25px; font-size: 80%">Gig Type</span>
    <el-select v-model="selectedGigTypes" filterable placeholder="Gig Type" style="width: 200px;margin-left: 15px">
      <el-option
        v-for="item in gigTypes"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>

  </el-row>

  <el-row style="margin-top: 15px">
    <span class="demonstration" style="margin-left: 25px;font-size: 80%;">Ranking</span>
    <el-select v-model="selectedRanking" filterable placeholder="Ranking" style="width: 200px;margin-left: 21px">
      <el-option
        v-for="item in rankingTypes"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>

  </el-row>
  <el-row style="margin-top: 25px">
    <span class="demonstration">World</span>
    <div style="margin-top: 15px"></div>
    <span class="demonstration" style="margin-left: 25px;font-size: 80%;">Residency</span>
    <el-select v-model="location.residency" filterable placeholder="Residency" style="width: 200px;margin-left: 21px" @change="handleLocationChange">
      <el-option
        v-for="item in countries"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>
    <div style="margin-top: 15px"></div>
    <span class="demonstration" style="margin-left: 25px;font-size: 80%;">Citizenship</span>
    <el-select v-model="location.citizenship" filterable placeholder="Citizenship" style="width: 200px;margin-left: 16px" @change="handleLocationChange">
      <el-option
        v-for="item in countries"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>

  </el-row>

</el-col>

  <el-col :span="12">
    <el-row>
      <span class="demonstration">Evaluation</span>
      <div style="margin-top: 5px">
        <el-input
          style="margin-top: 15px"
          placeholder="Filter keyword"
          v-model="filterText">
        </el-input>

        <el-tree

          class="filter-tree"
          :props="treeProps"
        :load="loadNodeEval"
          node-key="id"
          highlight-current
          show-checkbox
          lazy
          :filter-node-method="filterNode"
          ref="treeEval"
        >
        </el-tree>
      </div>

    </el-row>
    <el-row style="margin-top: 25px">
      <span class="demonstration">Documents</span>

      <el-tabs type="card" @tab-click="handleDocumentClick">
        <el-tab-pane label="Visa">
          <document selectType="Type" :options="visaType"></document>

        </el-tab-pane>
        <el-tab-pane label="Passport">
          <document selectType="Country" :options="countries"></document></el-tab-pane>
        <el-tab-pane label="Seamans Book">
          <document selectType="Country" :options="countries"></document></el-tab-pane>
        <el-tab-pane label="Certificate">
          <document selectType="Type" :options="certificateType"></document></el-tab-pane>
        <el-tab-pane label="Medical">
          <document selectType="Type" :options="medicalType"></document></el-tab-pane>
      </el-tabs>

    </el-row>


  </el-col>

</el-row>
  <el-row>
<div style="margin-top: 20px"></div>
    <el-col :span="4" :offset="10">

      <el-button style="margin: auto" @click="displayCandidate">Display Candidate({{candidateIdList.length}})</el-button>
    </el-col>

  </el-row>
<el-row>
  <div style="margin-top: 20px"></div>
  <display-candidate v-if="showSearchResult" :resultList="candidateList" :pageNumber="totalPage"
  @on=""></display-candidate>

</el-row>

</div>

</template>


<script>

  import Document from './Document.vue'
  import _ from 'lodash'
  import {getHeader} from '../../../env'
  import localStorage from 'localStorage'
  import ElRow from "element-ui/packages/row/src/row";
  import ElButton from "../../../../node_modules/element-ui/packages/button/src/button.vue";
  import DisplayCandidate from './DisplayCandidate.vue'
  import ElCol from "element-ui/packages/col/src/col";


  export default {

    components: {
      ElCol,
      ElButton,
      ElRow,
      Document,
      DisplayCandidate},
    data() {

      return {
        totalCandidate:0,
        totalPage:0,
        nameResultList:null,
        idResultList:null,
        locationResultList:null,
        documentResultList:null,
        evalResultList:null,
        experienceResultList:null,
        candidateList:[],
        candidateId: '',
        candidateName:{
          name:null,
          searchType:'Any',
        },
        actId:null,
        availability:null,
        interestLevel:null,
        checkedClients:[],
        clients:null,
        isIndeterminateClient: true,
        checkedVenues:[],
        venues:null,
        isIndeterminateVenue: true,
        gigTypes:null,
        selectedGigTypes:null,
        rankingTypes: null,
        selectedRanking:null,
        countries: null,
        visaType:null,
        certificateType: null,
        medicalType:null,
        location:{
          residency:null,
          citizenship:null,
        },

        filterText:'',
        treeProps: {
          children: 'children',
          label: 'label',
          id:'id',
          disabled:'isSearchable'
        },
        activeName: 'first',
        showSearchResult: false,
        candidateListReset:true
      }
    },
    watch:{
      filterText(val) {
        this.$refs.treeEval.filter(val);
      }
    },
    computed:{

      candidateIdList:function () {
        let nonNullResultList = [];

        if(this.documentResultList){
          nonNullResultList.push(this.documentResultList);
        }if(this.evalResultList){
          nonNullResultList.push(this.evalResultList);
        }if(this.idResultList){
          nonNullResultList.push(this.idResultList);
        }if(this.nameResultList){
          nonNullResultList.push(this.nameResultList);
        }if(this.locationResultList){
          nonNullResultList.push(this.locationResultList);
        }
        if(nonNullResultList.length==0) return [];
        let resultList = nonNullResultList[0];
        if(nonNullResultList.length>1){
            for(let i=1;i<nonNullResultList.length; i++){
                resultList = _.intersection(resultList,nonNullResultList[i]);
          }
        }
        return resultList;
      },
    },
    methods:{
      displayCandidate(){
        this.$http.post("http://localhost:8080/candidate/display",{ids: this.candidateIdList, page:1,size:20},
            {header:getHeader()}).then(response=>{

              console.log(response.data)
              response.data.resultList.forEach(item=> this.candidateList.push(item))
            //this.candidateList.push(response.data);
          this.totalPage = response.data.totalPage *10;
            this.showSearchResult = true;
        })
      },

      handleIdChange(){
        _.debounce(this.searchById,3000)();
      },
      searchById(){

        if(this.candidateId && this.candidateId.trim().length>0){
          let options = { emulateJSON: true};
          this.$http.post("http://localhost:8080/candidate/search/id",

            [this.candidateId],options,{header:getHeader()}).then(response=>{

              if (response.status ==200){
                this.idResultList = response.data;
            };
          })
        }
      },
      handleLocationChange(){
        console.log('handle location change')
        _.debounce(this.searchByLocation,3000)();
      },
      searchByLocation(){

        console.log(this.location)
        if(this.location.citizenship!=null||this.location.residency!=null){
          this.$http.post("http://localhost:8080/candidate/search/residencyAndCitizenship",
           this.location, {header:getHeader() }).then( response => {
              if (response.status ==200) {

                this.locationResultList = response.data;
              }
            })
        }
      },
      handleNameChange(){
        _.debounce(this.searchByName,2000)();
      },
      searchByName(){
        if(this.candidateName.name && this.candidateName.searchType && this.candidateName.name.trim().length>0){

          this.candidateName.name = this.candidateName.name.replace(/^\s+|\s+$/g, '');
          let options = { emulateJSON: true};
          this.$http.post("http://localhost:8080/candidate/search/name",

            this.candidateName,{header:getHeader()}).then(response=>{

            if (response.status ==200){
              this.idResultList = response.data;
            };
          })
        }
      },

      handleClientsChange(){
        console.log(this.checkedClients);
      },

      getChildrenNodes(key, resolve){

        console.log(key)

        this.$http.get('http://localhost:8080/evaluation/type/subtypes/'+key.data.id,{headers: getHeader()}).then(response=> {
          if (response.status == 200) {

            console.log('Eval result: ',response.data)
            resolve(response.data);
          } else resolve([]);
        })

      },

      filterNode(value,data){
        if (!value) return true;
        return data.label.indexOf(value) !== -1;

      },
      loadNodeEval(node, resolve){

        console.log(node.level)
          if(node.level===0){
            return resolve([{ id:1,label:'Evaluation'}]);
          }
          if(node.level===1){
            return resolve([
              {id: 2, label: 'Instrument'},
              {id: 3, label: 'Non Instrument'},
              {id: 133, label: 'Classification'},
              {id: 4, label: 'Language'},
            {id: 180, label: 'Presentation'}
            ])
          }
          if(node.level>1)
          {
           this.getChildrenNodes(node, resolve);

            console.log('eval data',data)

            resolve(data)
          }

      },


      fetchPosition(){
        let positionOptions = JSON.parse(localStorage.getItem("positionOptions"));
        if(!positionOptions){

          this.$http.get('http://localhost:8080/position/',{headers: getHeader()}).then(response=> {

            if (response.status == 200) {
              this.rankingTypes = response.data
              positionOptions = response.data;
              localStorage.setItem('positionOptions',JSON.stringify(response.data))
            }
          })
        }else {
          this.rankingTypes = positionOptions;
        }
      },

      fetchGigType(){
        let gigTypeOptions = JSON.parse(localStorage.getItem("gigTypeOptions"));
        if(!gigTypeOptions){

          this.$http.get('http://localhost:8080/gigType/',{headers: getHeader()}).then(response=> {

            if (response.status == 200) {
              gigTypeOptions = response.data;
              this.gigTypes = response.data
              localStorage.setItem('gigTypeOptions',JSON.stringify(response.data))
            }
          })
        }else {
          this.gigTypes = gigTypeOptions;
        }
      },


      fetchClient(){

        console.log('fetchClient')
        if(! this.clients){
          let clientOptions = JSON.parse(localStorage.getItem("clientOptions"));
          if(!clientOptions){

            this.$http.get('http://localhost:8080/client/',{headers: getHeader()}).then(response=> {

              if (response.status == 200) {
                this.clients = response.data
                localStorage.setItem('clientOptions',JSON.stringify(response.data))
              }
            })
          }else {
            this.clients = clientOptions;
          }
        }
      },
      handleDocumentClick(){

      },

      fetchVenue(){

        console.log(this.checkedClients);
       let venueOptions = JSON.parse(localStorage.getItem("venueOptions"));

          console.log('venueOptions from local storage',venueOptions)

          if(!venueOptions){
            this.$http.get('http://localhost:8080/venuemainshard/',{headers: getHeader()}).then(response=> {
              if (response.status == 200) {
                venueOptions = response.data;
                console.log('venueOptions from http request',venueOptions)
                localStorage.setItem('venueOptions',JSON.stringify(venueOptions))
              }
            })
          }

        console.log(venueOptions);

        if(!this.checkedClients|| this.checkedClients.length===0) {
          console.log(this.checkedClients);
          this.venues = venueOptions;
        }
        else {
          let filteredVenue = venueOptions.filter(venue => this.checkedClients.includes(venue.clientId));
          console.log(filteredVenue);
          this.venues = filteredVenue;
        }
      },


      fetchCountry(){
        let countryOptions = JSON.parse(localStorage.getItem('countryOptions'));
        console.log('country',countryOptions)
        if (!countryOptions){
          this.$http.get('http://localhost:8080/country/',{headers:getHeader()}).then(response=>{
            if(response.status===200){
              localStorage.setItem('countryOptions', JSON.stringify(countryOptions));
              this.countries = response.data;
            }
          })
        }
        else this.countries = countryOptions;
      },
      fetchVisaType(){
        let visaTypeOptions =  JSON.parse(localStorage.getItem('visaTypeOptions'));

        if(!visaTypeOptions) this.$http.get('http://localhost:8080/visaType/',{headers: getHeader()}).then(response=> {
          if (response.status == 200) {

            console.log('visa result: ',response.data)
            visaTypeOptions = response.data;
            localStorage.setItem('visaTypeOptions',JSON.stringify(visaTypeOptions));
            this.visaType = visaTypeOptions;
          }
        })
        else this.visaType = visaTypeOptions;
      },
      fetchMedicalType(){
        let medicalTypeOptions =  JSON.parse(localStorage.getItem('medicalTypeOptions'));
        if(!medicalTypeOptions) {

          this.$http.get('http://localhost:8080/medicalType/',{headers: getHeader()}).then(response=> {
          if (response.status == 200) {
            medicalTypeOptions = response.data;
            console.log('medical result: ',response.data)
            localStorage.setItem('medicalTypeOptions',JSON.stringify(medicalTypeOptions));
            this.medicalType = medicalTypeOptions;
          }
        });}


        else this.medicalType = medicalTypeOptions;
      },


      fetchCertificateType(){
        let certificateTypeOptions =  JSON.parse(localStorage.getItem('certificateTypeOptions'));
        if(!certificateTypeOptions) this.$http.get('http://localhost:8080/certificateType/',{headers: getHeader()}).then(response=> {
          if (response.status == 200) {
            certificateTypeOptions = response.data;
            console.log('certificate result: ',response.data)
            localStorage.setItem('certificateTypeOptions',JSON.stringify(certificateTypeOptions));
            this.certificateType = certificateTypeOptions;
          }
        })
        else this.certificateType = certificateTypeOptions;
      }

    },
    created(){
      this.fetchGigType();
      this.fetchPosition();
      this.fetchCountry();
      this.fetchVisaType();
      this.fetchMedicalType();
      this.fetchCertificateType();
    }
  }

</script>

<style scoped>
  .el-select  {
    width: 110px;
  }

  /*.shortInput{*/
    /*width: 180px;*/
  /*}*/
  /*.el-input{*/
    /*width: 300px;*/
  /*}*/
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
    width: 70%;

  }
 .input-with-select  .el-input-group  {

    width: 70%;
  }

  .el-tab-pane{
    height: auto;
  }

</style>


