<template>
<div>
<el-row>
<el-col  :span="12">
  <span class="demonstration">Candidate Id or Act Id</span>
  <div style="margin-top: 15px"></div>
<el-row>
  <el-col :span="8" >
    <el-input v-model="criteria.candidateId" placeholder="Candidate ID" class="shortInput" @change="handleIdChange"></el-input>
  </el-col>
  <el-col :span="8" style="margin-left: 15px">
    <el-input v-model="criteria.actId" placeholder="Candidate Act ID" class="shortInput" @change="handleActIdChange"></el-input>
  </el-col>

</el-row>
  <el-row>
    <div style="margin-top: 15px;">
      <span class="demonstration">Candidate Name</span>
      <div style="margin-top: 15px;"></div>
      <el-input placeholder="Candidate Name" v-model="criteria.candidateName.name" class="input-with-select"
                style="width: 70%" @change="handleNameChange">
        <el-select v-model="criteria.candidateName.searchType" slot="prepend" placeholder="select">
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
      <div style="margin-top: 15px;"></div>
      <el-date-picker style="width: 300px"
        v-model="criteria.availability"
        type="daterange"
        range-separator="To"
        start-placeholder="Start Date"
        end-placeholder="End Date">
      </el-date-picker>
    </div>
  </el-col>
</el-row>


  <el-row style="margin-top: 15px">
    <span class="demonstration">Interest Level</span>
    <div style="margin-top: 10px">
      <el-radio-group v-model="criteria.interestLevel" size="small">
        <el-radio-button label="4" >Hard Negative</el-radio-button>
        <el-radio-button label="3">Soft Negative</el-radio-button>
        <el-radio-button label="2">Soft Positive</el-radio-button>
        <el-radio-button label="1" >Hard Positive</el-radio-button>
      </el-radio-group>
    </div>
  </el-row>

  <!--//Experience-->
  <el-row style="margin-top: 25px">
    <span class="demonstration">Experience</span>
    <div style="margin: 15px 0;"></div>
    <el-collapse style="width: 70%  "  @item-click="fetchClient">
      <el-collapse-item title="Client" style="font-size: large" >
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="criteria.experience.clientIds" style="height: 200px; overflow-y: scroll;">
          <el-checkbox v-for="client in clients" :label="client.id" :key="client.id"style="margin-left: 5px; width: 200px"
                       @change="handleExperienceChange" >{{client.name}}</el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
    <div style="margin-top: 15px"></div>
      <el-collapse style="width: 70% "  @item-click="fetchVenue">
      <el-collapse-item title="Venue">
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="criteria.experience.venueIds" style="height: 200px; overflow-y:scroll">
          <el-checkbox v-for="venue in venues" :label="venue.venueId" :key="venue.venueId"
                       style="margin-left: 5px; width: 200px"
                       @change="handleExperienceChange">{{venue.name}}  ------  {{venue.clientCode}}
          </el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
  </el-row>
  <el-row style="margin-top: 15px">
    <span class="demonstration" style="margin-left: 25px; font-size: 80%">Gig Type</span>
    <el-select v-model="criteria.experience.gigTypeId"  @change="handleExperienceChange"
               filterable placeholder="Gig Type" style="width: 200px;margin-left: 15px">
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
    <el-select v-model="criteria.experience.rank" filterable placeholder="Ranking"
               @change="handleExperienceChange" style="width: 200px;margin-left: 21px">
      <el-option
        v-for="item in rankingTypes"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>
  </el-row>
  <!--//Location-->
  <el-row style="margin-top: 25px">
    <span class="demonstration">World</span>
    <div style="margin-top: 15px"></div>
    <span class="demonstration" style="margin-left: 25px;font-size: 80%;">Residency</span>
    <el-select v-model="criteria.location.residency" filterable placeholder="Residency" style="width: 200px;margin-left: 21px"
               @change="handleLocationChange">
      <el-option
        v-for="item in countries"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>
    <div style="margin-top: 15px"></div>
    <span class="demonstration" style="margin-left: 25px;font-size: 80%;">Citizenship</span>
    <el-select v-model="criteria.location.citizenship" filterable placeholder="Citizenship" style="width: 200px;margin-left: 16px"
               @change="handleLocationChange">
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
          v-model="criteria.filterText">
        </el-input>

        <el-tree
          class="filter-tree"
          :props="treeProps"
          :load="loadNodeEval"
          node-key="id"
          show-checkbox
          check-strictly="true"
          @check-change="handleEvalChange"
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
          <document selectType="Type" :options="visaType" ref="visa" @documentSelect = 'handleVisaChange'></document>
        </el-tab-pane>
        <el-tab-pane label="Passport">
          <document selectType="Country" :options="countries" ref="passport" @documentSelect = 'handlePassportChange'></document></el-tab-pane>
        <el-tab-pane label="Seamans Book">
          <document selectType="Country" :options="countries" ref="seamansBook" @documentSelect = 'handleSeamansBookChange'></document></el-tab-pane>
        <el-tab-pane label="Certificate">
          <document selectType="Type" :options="certificateType" ref="certificate" @documentSelect = 'handleCertificateChange'></document></el-tab-pane>
        <el-tab-pane label="Medical">
          <document selectType="Type" :options="medicalType" ref="medical" @documentSelect = 'handleMedicalChange'></document></el-tab-pane>
      </el-tabs>
    </el-row>
  </el-col>
</el-row>
  <el-row>
<div style="margin-top: 20px"></div>
    <el-col :span="4" :offset="8">

      <el-button style="margin: auto" @click="displayCandidate">Display Candidate({{candidateIdList.length}})</el-button>
    </el-col>
    <el-col :span="2">
      <el-button style="margin: auto" @click="reset">Reset</el-button>
    </el-col>

  </el-row>
<el-row>
  <div style="margin-top: 20px"></div>
  <display-candidate v-if="result.showSearchResult" :resultList="result.candidateList" :pageNumber="result.totalPage"
  @pageChange="displayCandidate"></display-candidate>
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
  import response from './data/data.js'
  export default {

    components: {
      ElCol,
      ElButton,
      ElRow,
      Document,
      DisplayCandidate},
    data() {
      return {

        criteria:{
          candidateId: '',
          actId:null,
          candidateName:{
            name:null,
            searchType:'Any',
          },
          documentRequestSent: true,
          experience:{
            gigTypeId:null,
            clientIds:[],
            venueIds:[],
            rank:null,
            nonPs:null
          },
          availability:null,
          interestLevel:null,
          document:{},
          location:{
            residency:null,
            citizenship:null
          },
          filterText:'',
          evaluation:null
        },
        result:{
          totalCandidate:0,
          totalPage:0,
          nameResultList:null,
          idResultList:null,
          locationResultList:null,
          documentResultList:null,
          evalResultList:null,
          experienceResultList:null,
          candidateList:[],
          showSearchResult: false
        },
        clients:null,
        isIndeterminateClient: false,
        venues:null,
        isIndeterminateVenue: false,
        gigTypes:null,
        rankingTypes: null,
        countries: null,
        visaType:null,
        certificateType: null,
        medicalType:null,
        treeProps: {
          children: 'children',
          label: 'label',
          id:'id',
          disabled:'isSearchable'
        }
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

        if(this.result.documentResultList){
          nonNullResultList.push(this.result.documentResultList);
        }if(this.result.evalResultList){
          nonNullResultList.push(this.result.evalResultList);
        }if(this.result.idResultList){
          nonNullResultList.push(this.result.idResultList);
        }if(this.result.nameResultList){
          nonNullResultList.push(this.result.nameResultList);
        }if(this.result.locationResultList){
          nonNullResultList.push(this.result.locationResultList);
        }if(this.result.experienceResultList){
          nonNullResultList.push(this.result.experienceResultList)
        }
        if(nonNullResultList.length===0) return [];
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
      displayCandidate(pageNumber){
        if(!_.isNumber(pageNumber))pageNumber = 1;
        console.log(pageNumber);
        this.$http.post("http://localhost:8080/candidate/display",{ids: this.candidateIdList, page:pageNumber-1,size:20},
            {header:getHeader()}).then(response=>{
              if(response.status===200) {
                this.result.candidateList = [];
                response.data.resultList.forEach(item => this.result.candidateList.push(item))
                this.result.totalPage = response.data.totalPage * 10;
                this.result.showSearchResult = true;
              }
        })
      },
      reset(){
        console.log('reset',this.$data,defaultData());
        this.$data.result=defaultData().result;
        this.$data.criteria = defaultData().criteria;
        this.$refs.visa.reset();
        this.$refs.passport.reset();
        this.$refs.seamansBook.reset();
        this.$refs.medical.reset();
        this.$refs.certificate.reset();
      },
      searchByDocument(){
          this.$http.post("http://localhost:8080/candidate/search/document",this.criteria.document,{header:getHeader()}).then(
            response=>{
              if(response.status === 200){
                console.log('document',response.data);
                this.result.documentResultList = response.data;
              }
            });
       this.criteria.documentRequestSent=true;
      },
      handleIdChange(){
        _.debounce(this.searchById,1000)();
      },
      handleActIdChange(){
        _.debounce(this.searchByActId,1000)();
      },
      searchById(){
        if(this.criteria.candidateId && this.criteria.candidateId.trim().length>0){
          let options = { emulateJSON: true};
          this.$http.get("http://localhost:8080/candidate/search/id/"+
            this.criteria.candidateId,options,{header:getHeader()}).then(response=>{

              if (response.status ===200){
                this.result.idResultList = response.data;
            }
          })
        }else this.result.idResultList = null;
      },
      searchByActId(){
        if(this.criteria.actId && this.criteria.actId.trim().length>0){
          let options = { emulateJSON: true};
          this.$http.get("http://localhost:8080/candidate/search/actId/"+
            this.criteria.actId,options,{header:getHeader()}).then(response=>{
            if (response.status ===200){
              this.result.idResultList = response.data;
            };
          })
        }else this.result.idResultList = null;
      },
      handleLocationChange(){
        _.debounce(this.searchByLocation,1500)();
      },
      handlePassportChange(val){
        console.log(val);
        this.criteria.document["passport"]=val;
        if(this.criteria.documentRequestSent===true){
          this.criteria.documentRequestSent = false;
          _.debounce(this.searchByDocument,1500)();
        }
      },
      handleVisaChange(val){
        console.log(val);
        this.criteria.document["visa"] = val;
        if(this.criteria.documentRequestSent===true){
          this.criteria.documentRequestSent = false;
          _.debounce(this.searchByDocument,1500)();
        }
      },
      handleMedicalChange(val){
        console.log(val);
        this.criteria.document["medical"] = val;
        if(this.criteria.documentRequestSent===true){
          this.criteria.documentRequestSent = false;
          _.debounce(this.searchByDocument,1500)();
        }
      },
      handleSeamansBookChange(val){
        console.log(val);
        this.criteria.document['seamansBook'] = val;
        if(this.criteria.documentRequestSent===true){
          this.criteria.documentRequestSent = false;
          _.debounce(this.searchByDocument,1500)();
        }
      },
      handleCertificateChange(val){
        console.log(val);
        this.criteria.document['certificate'] = val;
        if(this.criteria.documentRequestSent===true){
          this.criteria.documentRequestSent = false;
          _.debounce(this.searchByDocument,1500)();
        }
      },
      searchByLocation(){

        console.log(this.criteria.location);
        if(!_.isEmpty(this.criteria.location.citizenship)||!_.isEmpty(this.criteria.location.residency)){
          this.$http.post("http://localhost:8080/candidate/search/residencyAndCitizenship",
           this.criteria.location, {header:getHeader() }).then( response => {
              if (response.status ===200) {

                this.result.locationResultList = response.data;
              }
            })
        }
      },
      handleNameChange(){
        _.debounce(this.searchByName,1500)();
      },
      searchByName(){
        if(this.criteria.candidateName.name && this.criteria.candidateName.searchType
          && this.criteria.candidateName.name.trim().length>0){

          this.criteria.candidateName.name = this.criteria.candidateName.name.replace(/^\s+|\s+$/g, '');
          let options = { emulateJSON: true};
          this.$http.post("http://localhost:8080/candidate/search/name",
            this.criteria.candidateName,{header:getHeader()}).then(response=>{
            if (response.status ===200){
              this.result.idResultList = response.data;
            };
          })
        }
      },
      handleExperienceChange(){
        _.debounce(this.searchByExperience,1500)();
      },
      searchByExperience(){
        if(!_.isEmpty(this.criteria.experience.clientIds)||!_.isEmpty(this.criteria.experience.venueIds)||
        !_.isEmpty(this.criteria.experience.gigTypeId)||!_.isEmpty(this.criteria.experience.rank)){
          this.$http.post("http://localhost:8080/candidate/search/gig",

            this.criteria.experience, {header:getHeader() }).then( response => {
            if (response.status === 200) {
              this.result.experienceResultList = response.data;
            }
          })
        }
      },
      handleEvalChange(){
        console.log('handle eval change');
        let evalTags = this.$refs.treeEval.getCheckedNodes();
        console.log('evaltagnodes',evalTags);

        let instrumentCriteria  = [];
        let nonInstrumentCriteria = [];
        let languageCriteria = [];
        let classificationCriteria = [];
        let presentationCriteria = [];

        let language = null;
        let classification = null;
        let nonInstrument = null;
        let presentation = null;
        let instrument = null;

        let ratingType = null;
        let instrumentCriterion = {instrumentTypeId:null, ratings:null};
        let rating = {ratingTypeId:null,ratingId:null};
        let ratings = [];
        let generalSkillCriterion = {skillTypeId: null, ratingId: null};

        for (let i = 0; i< evalTags.length;i++){
           let item = evalTags[i];

           if(ratingType!== null&&item.parentId===ratingType){
             rating.ratingId = item.id;
             ratings.push(rating);
             rating = {ratingTypeId:null,ratingId:null};
             ratingType = null;
             let instrumentCriterionUpdated = instrumentCriteria.pop();
             instrumentCriterionUpdated.ratings = ratings;
             instrumentCriteria.push(instrumentCriterionUpdated);
             console.log(instrumentCriteria,instrumentCriterion,instrumentCriterionUpdated,ratings,instrument);
             continue;
           }


           if(instrument!== null&&item.parentId===instrument&&item.id!==82){
             ratingType = item.id;
             rating.ratingTypeId = ratingType;
             continue;
           }
           if(nonInstrument!==null&&item.parentId===nonInstrument){
            generalSkillCriterion.ratingId = item.id;
            nonInstrumentCriteria.pop();
            nonInstrumentCriteria.push(generalSkillCriterion);
            nonInstrument = null;
            continue;
           }
          if(presentation!==null&&item.parentId===presentation){
            generalSkillCriterion.ratingId = item.id;
            presentationCriteria.pop();
            presentationCriteria.push(generalSkillCriterion);
            presentation = null;
            continue;
          }
          if(classification!==null&&item.parentId===classification){
            generalSkillCriterion.ratingId = item.id;
            classificationCriteria.pop();
            classificationCriteria.push(generalSkillCriterion);
            classification = null;
            continue;
          }
          if(language!==null&&item.parentId===language){
            generalSkillCriterion.ratingId = item.id;
            languageCriteria.pop();
            languageCriteria.push(generalSkillCriterion);
            language = null ;
            continue;
          }

          if(item.parentId === 2){
            ratings = [];
            instrumentCriterion.instrumentTypeId = item.id;
            instrumentCriterion.ratings = null;
            instrumentCriteria.push(instrumentCriterion);
            instrument = item.id;
          }if(item.parentId=== 3){
            instrument = null;
            generalSkillCriterion.skillTypeId = item.id;
            generalSkillCriterion.ratingId = null;
            nonInstrumentCriteria.push(generalSkillCriterion);
            nonInstrument = item.id;
          }if(item.parentId===4){
            instrument = null;
            generalSkillCriterion.skillTypeId = item.id;
            generalSkillCriterion.ratingId = null;
            languageCriteria.push(generalSkillCriterion);
            language = item.id;
          }if(item.parentId===133){
            instrument = null;
            generalSkillCriterion.skillTypeId = item.id;
            generalSkillCriterion.ratingId = null;
            classificationCriteria.push(generalSkillCriterion);
            classification = item.id;
          }if(item.parentId===180){
            instrument = null;
            generalSkillCriterion.skillTypeId = item.id;
            generalSkillCriterion.ratingId = null;
            presentationCriteria.push(generalSkillCriterion);
            presentation = item.id;
          }
        }
        this.criteria.evaluation = {
          instrumentCriteria,
          nonInstrumentCriteria,
          presentationCriteria,
          classificationCriteria,
          languageCriteria
        };

        let display =  function () {
          console.log(this.criteria.evaluation)
        };
        _.debounce(this.searchByEvaluation,2500)()
      },
      searchByEvaluation(evaluation){
        this.$http.post("http://localhost:8080/candidate/search/evaluation",

          this.criteria.evaluation, {header:getHeader() }).then( response => {
          if (response.status === 200) {
            this.result.evalResultList = response.data;
          }
        })
      },
      handleDocumentClick(){
      },
      getChildrenNodes(key, resolve){
        this.$http.get('http://localhost:8080/evaluation/type/subtypes/'+key.data.id,{headers: getHeader()}).then(response=> {
          if (response.status === 200) {
            resolve(response.data);
          } else resolve([]);
        })
      },
      filterNode(value,data){
        if (!value) return true;
        return data.label.indexOf(value) !== -1;

      },
      loadNodeEval(node, resolve){

        console.log(node.level);
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
          }

      },
      fetchPosition(){
        let positionOptions = JSON.parse(localStorage.getItem("positionOptions"));
        if(!positionOptions){

          this.$http.get('http://localhost:8080/position/',{headers: getHeader()}).then(response=> {

            if (response.status === 200) {
              this.rankingTypes = response.data;
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

            if (response.status === 200) {
              gigTypeOptions = response.data;
              this.gigTypes = response.data;
              localStorage.setItem('gigTypeOptions',JSON.stringify(response.data))
            }
          })
        }else {
          this.gigTypes = gigTypeOptions;
        }
      },
      fetchClient(){
        console.log(this.clients)
        if(! this.clients){
          let clientOptions = JSON.parse(localStorage.getItem("clientOptions"));
          console.log(clientOptions)
          if(!clientOptions){
            this.$http.get('http://localhost:8080/client/',{headers: getHeader()}).then(response=> {
              if (response.status === 200) {
                this.clients = response.data;
                localStorage.setItem('clientOptions',JSON.stringify(response.data))
              }
            })
          }else {
            this.clients = clientOptions;
            console.log(this.clients)
          }
        }
      },
      fetchVenue(){
       let venueOptions = JSON.parse(localStorage.getItem("venueOptions"));
          if(!venueOptions){
            this.$http.get('http://localhost:8080/venuemainshard/',{headers: getHeader()}).then(response=> {
              if (response.status === 200) {
                venueOptions = response.data;
                console.log('venueOptions from http request',venueOptions);
                localStorage.setItem('venueOptions',JSON.stringify(venueOptions))
              }
            })
          }

        console.log(venueOptions);

        if(!this.criteria.experience.clientIds|| this.criteria.experience.clientIds.length===0) {
          console.log(this.criteria.experience.clientIds);
          this.venues = venueOptions;
        }
        else {
          let filteredVenue = venueOptions.filter(venue => this.criteria.experience.clientIds.includes(venue.clientId));
          console.log(filteredVenue);
          this.venues = filteredVenue;
        }
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
      fetchVisaType(){
        let visaTypeOptions =  JSON.parse(localStorage.getItem('visaTypeOptions'));

        if(!visaTypeOptions) this.$http.get('http://localhost:8080/visaType/',{headers: getHeader()}).then(response=> {
          if (response.status === 200) {

            console.log('visa result: ',response.data);
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
          if (response.status === 200) {
            medicalTypeOptions = response.data;
            console.log('medical result: ',response.data);
            localStorage.setItem('medicalTypeOptions',JSON.stringify(medicalTypeOptions));
            this.medicalType = medicalTypeOptions;
          }
        });}
        else this.medicalType = medicalTypeOptions;
      },
      fetchCertificateType(){
        let certificateTypeOptions =  JSON.parse(localStorage.getItem('certificateTypeOptions'));
        if(!certificateTypeOptions) this.$http.get('http://localhost:8080/certificateType/',{headers: getHeader()}).then(response=> {
          if (response.status === 200) {
            certificateTypeOptions = response.data;
            console.log('certificate result: ',response.data);
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

  const defaultData = function(){return { criteria:{
    candidateId: '',
    actId:null,
    candidateName:{
      name:null,
      searchType:'Any',
    },
    documentRequestSent: true,
    experience:{
      gigTypeId:null,
      clientIds:[],
      venueIds:[],
      rank:null,
      nonPs:null
    },
    evaluation:null,
    availability:null,
    interestLevel:null,
    document:{},
    location:{
      residency:null,
      citizenship:null
    },
    filterText:''
  },
    result:{
      totalCandidate:0,
      totalPage:0,
      nameResultList:null,
      idResultList:null,
      locationResultList:null,
      documentResultList:null,
      evalResultList:null,
      experienceResultList:null,
      candidateList:[],
      showSearchResult: false
    }}} ;
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


