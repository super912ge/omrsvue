<template>
  <div>
    <el-row>
      <el-col  :span="11" :offset="1">

        <el-row style="margin-top: 25px">
          <span class="demonstration">Type</span>
          <el-row style="margin-top: 15px">
            <span class="demonstration" style="margin-left: 25px; font-size: 80%">Gig Type</span>
            <el-select v-model="criteria.type.gigTypeId"  @change="handleTypeChange"
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
            <el-select v-model="criteria.type.rank" filterable placeholder="Ranking"
                       @change="handleTypeChange" style="width: 200px;margin-left: 21px">
              <el-option
                v-for="item in rankingTypes"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-row>
        </el-row>
        <el-row style="margin-top: 15px">
          <span class="demonstration" style="margin-left: 25px; font-size: 80%">Exact Match</span>
          <el-checkbox v-model="criteria.type.exactMatch" style="margin-left: 15px"></el-checkbox>
        </el-row>
        <el-row style="margin-top: 15px">

          <span class="demonstration"style="margin-left: 25px;font-size: 80%;">Requirement</span>
          <div style="margin-top: 5px">
            <el-input
              style="margin-top: 10px;width: 50%; margin-left: 50px"
              placeholder="Filter keyword"
              v-model="criteria.filterText">
            </el-input>

            <el-tree
              style="width: 50%;margin-left: 50px"
              class="filter-tree"
              :props="treeProps"
              :load="loadNodeEval"
              node-key="id"
              show-checkbox
              check-strictly="true"
              @check-change="handleReqChange"
              lazy
              :filter-node-method="filterNode"
              ref="treeReq"
            >
            </el-tree>
          </div>
        </el-row>
        <!--//Experience-->
        <el-row style="margin-top: 25px">
          <span class="demonstration">Company</span>
          <div style="margin: 15px 0;"></div>
          <el-collapse style="width: 50%; margin-left: 25px "  @item-click="fetchClient">
            <el-collapse-item title="Client" style="font-size: large" >
              <div style="margin: 15px 0;"></div>
              <el-checkbox-group v-model="criteria.company.clientIds" style="height: 200px; overflow-y: scroll;">
                <el-checkbox v-for="client in clients" :label="client.id" :key="client.id"style="margin-left: 5px; width: 200px"
                             @change="handleCompanyChange" >{{client.name}}</el-checkbox>
              </el-checkbox-group>
            </el-collapse-item>
          </el-collapse>
          <div style="margin-top: 15px"></div>
          <el-collapse style="width: 50%; margin-left: 25px "  @item-click="fetchVenue">
            <el-collapse-item title="Venue">
              <div style="margin: 15px 0;"></div>
              <el-checkbox-group v-model="criteria.company.venueIds" style="height: 200px; overflow-y:scroll">
                <el-checkbox v-for="venue in venues" :label="venue.venueId" :key="venue.venueId"
                             style="margin-left: 5px; width: 200px"
                             @change="handleCompanyChange">{{venue.name}}  ------  {{venue.clientCode}}
                </el-checkbox>
              </el-checkbox-group>
            </el-collapse-item>
          </el-collapse>
          <div style="margin-top: 15px"></div>
          <span class="demonstration" style="margin-left: 25px; font-size: 80%">Room</span>
          <el-select v-model="criteria.company.room"  @change="handleCompanyChange"
                     filterable placeholder="Select" style="width: 200px;margin-left: 15px">
            <el-option
              v-for="item in rooms"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-row>
      </el-col>

      <el-col :span="11">
        <el-row>
          <div style="margin-top: 15px;">
            <span class="demonstration">Candidate Name</span>
            <div style="margin-top: 15px;"></div>
            <el-input placeholder="Candidate Name" v-model="criteria.candidateName" class="input-with-select"
                      style="width: 50%; margin-left: 25px" @change="handleNameChange" >
            </el-input>
          </div>
        </el-row>
        <el-row style="margin-top: 25px">

          <el-col :span="24">
            <div class="block">
              <span class="demonstration">Duration</span>
              <div style="margin-top: 15px;"></div>
              <el-date-picker style="width: 50%; margin-left: 25px"
                              v-model="criteria.duration"
                              type="daterange"
                              range-separator="To"
                              start-placeholder="Start Date"
                              end-placeholder="End Date">
              </el-date-picker>
            </div>
          </el-col>
        </el-row>
        <el-row style="margin-top: 25px">
          <span class="demonstration">Proship</span>
          <div style="margin-top: 15px"></div>
          <el-row>
            <el-row>
              <span class="demonstration" style="margin-left: 55px; font-size: 80%">Job Number</span>
              <el-input v-model="criteria.proship.jobId" placeholder="Job Number" class="shortInput"
                        @change="handleJobIdChange" style="width: 37%;margin-left: 15px"></el-input>
            </el-row>
            <el-row style="margin-top: 10px">
             <span class="demonstration" style="margin-left: 59px; font-size: 80%">Contract Id</span>
               <el-input v-model="criteria.proship.contractId" placeholder="Contract ID"
                         class="shortInput" style="margin-left: 15px;width: 37%"
                         @change="handleContractIdChange"></el-input>
            </el-row>
            <el-row style="margin-top: 10px">
             <span class="demonstration" style="margin-left: 84px; font-size: 80%">Gig Id</span>

              <el-input v-model="criteria.proship.gigId" placeholder="Gig ID" class="shortInput"
                        style="width: 37%; margin-left: 15px" @change="handleGigIdChange"></el-input>
            </el-row>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 60px; font-size: 80%">Exclusivity</span>
            <el-radio v-model="criteria.proship.exclusivity" label="1" style="margin-left: 15px">Exclusive positions only</el-radio>
            <el-radio v-model="criteria.proship.exclusivity" label="2" style="margin-left: 137px">Permit non-exclusive positions</el-radio>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 51px; font-size: 80%">Proship Only</span>
            <el-radio v-model="criteria.proship.nonPs" label="1" style="margin-left: 15px">Show Proship contract only</el-radio>
            <el-radio v-model="criteria.proship.nonPs" label="2"style="margin-left: 139px">Show non-Proship contracts</el-radio>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 36px; font-size: 80%">Show Cancelled</span>
            <el-checkbox v-model="criteria.proship.canceled" style="margin-left: 15px"></el-checkbox>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 25px; font-size: 80%">Account Manager</span>
            <el-select v-model="criteria.proship.accountManager"  @change="handleAccountManagerChange"
                       filterable placeholder="Select" style="width: 200px;margin-left: 15px">
              <el-option
                v-for="item in systemUsers"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-row>
          <el-row style="margin-top: 15px">
            <span class="demonstration" style="margin-left: 70px; font-size: 80%">Recruiter</span>
            <el-select v-model="criteria.proship.recruiter"  @change="handleRecruiterChange"
                       filterable placeholder="Select" style="width: 200px;margin-left: 15px">
              <el-option
                v-for="item in systemUsers"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-row>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <div style="margin-top: 20px"></div>
      <el-col :span="4" :offset="8">

        <el-button style="margin: auto" @click="displayContract">Display Contract({{result.contractIdList.length}})</el-button>
      </el-col>
      <el-col :span="2">
        <el-button style="margin: auto" @click="reset">Reset</el-button>
      </el-col>

    </el-row>
    <el-row>
      <div style="margin-top: 20px"></div>
      <!--<display-participant v-if="result.showSearchResult" :resultList="result.candidateList" :pageNumber="result.totalPage"-->
                         <!--@pageChange="displayCandidate"></display-participant>-->
    </el-row>
  </div>
</template>

<script>
  import _ from 'lodash'
  import {getHeader} from '../../../env'
  import localStorage from 'localStorage'
  import ElRow from "element-ui/packages/row/src/row";
  import ElButton from "../../../../node_modules/element-ui/packages/button/src/button.vue";
  import ElCol from "element-ui/packages/col/src/col";
  import ElSelect from "element-ui/packages/select"
  import ElCheckbox from "../../../../node_modules/element-ui/packages/checkbox/src/checkbox.vue";
export default {

  components: {
    ElCheckbox,
    ElCol,
    ElButton,
    ElRow

  },
  data() {
    return {
      systemUsers: [],
      rooms:[],
      contractIdList:[],
      criteria: {
        proship:{
          contractId: '',
          jobId: '',
          gigId: '',
          recruiter: null,
          accountManager: null,
          exclusivity:false,
          nonPs:false,
          canceled:false
        },
        candidateName:'',
        type: {
          gigTypeId: null,
          rank: null,
          requirement:null,
          exactMatch:false
        },
        company:{
          clientIds: [],
          venueIds: [],
        },
        duration: null,
      },
      result: {
        totalCandidate: 0,
        totalPage: 0,
        nameResultList: null,
        proshipResultList: null,
        companyResultList: null,
        requirementResultList: null,
        contractList: [],
        contractIdList:[],
        showSearchResult: false
      },
      clients: null,
      isIndeterminateClient: false,
      venues: null,
      isIndeterminateVenue: false,
      gigTypes: null,
      rankingTypes: null,
      treeProps: {
        children: 'children',
        label: 'label',
        id: 'id',
        disabled: 'isSearchable'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.treeReq.filter(val);
    }
  },
  computed: {
    candidateIdList: function () {
      let nonNullResultList = [];

      if (this.result.documentResultList) {
        nonNullResultList.push(this.result.documentResultList);
      }
      if (this.result.evalResultList) {
        nonNullResultList.push(this.result.evalResultList);
      }
      if (this.result.idResultList) {
        nonNullResultList.push(this.result.idResultList);
      }
      if (this.result.nameResultList) {
        nonNullResultList.push(this.result.nameResultList);
      }
      if (this.result.locationResultList) {
        nonNullResultList.push(this.result.locationResultList);
      }
      if (this.result.experienceResultList) {
        nonNullResultList.push(this.result.experienceResultList)
      }
      if (nonNullResultList.length === 0) return [];
      let resultList = nonNullResultList[0];
      if (nonNullResultList.length > 1) {
        for (let i = 1; i < nonNullResultList.length; i++) {
          resultList = _.intersection(resultList, nonNullResultList[i]);
        }
      }
      return resultList;
    },
  },
  methods: {
    handleNameChange(){},
    handleReqChange(){},
    handleTypeChange(){},
    reset(){},
    displayContract(){},
    handleRecruiterChange(){},
    handleAccountManagerChange(){},
    handleGigIdChange(){},
    handleContractIdChange(){},
    handleJobIdChange(){},
    handleCompanyChange(){},
    filterNode(value,data){
      if (!value) return true;
      return data.label.indexOf(value) !== -1;

    },
    fetchGigType(){
      console.log('fetchgig')
      let gigTypeOptions = JSON.parse(localStorage.getItem("gigTypeOptions"));
      if(!gigTypeOptions){
        this.$http.get('http://localhost:8080/gigType/',{headers: getHeader()}).then(response=> {

          if (response.status === 200) {
            gigTypeOptions = response.data;
            this.gigTypes = response.data;
            localStorage.setItem('gigTypeOptions',JSON.stringify(response.data));
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

      if(!this.criteria.company.clientIds|| this.criteria.company.clientIds.length===0) {
        console.log(this.criteria.company.clientIds);
        this.venues = venueOptions;
      }
      else {
        let filteredVenue = venueOptions.filter(venue => this.criteria.company.clientIds.includes(venue.clientId));
        console.log(filteredVenue);
        this.venues = filteredVenue;
      }
    },
    loadNodeEval(node, resolve){

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
    getChildrenNodes(key, resolve){
      this.$http.get('http://localhost:8080/evaluation/type/subtypes/'+key.data.id,{headers: getHeader()}).then(response=> {
        if (response.status === 200) {
          resolve(response.data);
        } else resolve([]);
      })
    },
    fetchSystemUser(){
      this.systemUsers = [{id:1,name:'Adam'},{id:'2',name:'JP'}]
    },
    fetchRoom(){
      this.rooms = [{id:1,name:'Adam'},{id:'2',name:'JP'}]
    }
  },
  created() {
    this.fetchClient();
    this.fetchVenue();
    this.fetchGigType();
    this.fetchSystemUser();
    this.fetchRoom();
  }
}
</script>
