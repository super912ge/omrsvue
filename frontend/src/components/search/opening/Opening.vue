<template>
  <div>
    <el-row>
      <el-col  :span="11" :offset="1">

        <el-row style="margin-top: 25px">
          <span class="demonstration">Type</span>
          <el-row style="margin-top: 15px">
            <span class="demonstration" style="margin-left: 25px; font-size: 80%">Gig Type</span>
            <el-select v-model="criteria.type.gigTypeId"  @change="handleTypeChange" size="mini"
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
                       @change="handleTypeChange" style="width: 200px;margin-left: 21px" size="mini">
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
          <span class="demonstration" style="margin-left: 25px; font-size: 80%">Grouping</span>
          <el-radio v-model="criteria.type.grouping" label="1" style="margin-left: 15px">Single</el-radio>
          <el-radio v-model="criteria.type.grouping" label="2" style="margin-left: 10px">Group</el-radio>
        </el-row>
        <el-row style="margin-top: 15px">

          <span class="demonstration"style="margin-left: 25px;font-size: 80%;">Requirement</span>
          <div style="margin-top: 5px">
            <el-input
              style="margin-top: 10px;width: 50%; margin-left: 50px"
              size="mini"
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
              :check-strictly="true"
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
          <el-collapse style="width: 50%; margin-left: 25px "  @item-click="fetchClient" >
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
                <el-checkbox v-for="venueBase in venues" :label="venueBase.venueId" :key="venueBase.venueId"
                             style="margin-left: 5px; width: 200px"
                             @change="handleCompanyChange">{{venueBase.name}}  ------  {{venueBase.clientCode}}
                </el-checkbox>
              </el-checkbox-group>
            </el-collapse-item>
          </el-collapse>
          <div style="margin-top: 15px"></div>
          <span class="demonstration" style="margin-left: 25px; font-size: 80%" >Room</span>
          <el-select v-model="criteria.company.room"  @change="handleCompanyChange" size="mini"
                     filterable placeholder="Select" style="width: 200px;margin-left: 15px">
            <el-option
              v-for="item in rooms"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-row>
      </el-col>

      <el-col :span="11">

        <el-row style="margin-top: 25px">

          <el-col :span="24">
            <div class="block">
              <span class="demonstration">Duration</span>
              <div style="margin-top: 15px;"></div>
              <el-date-picker style="width: 50%; margin-left: 25px" size="mini"
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

            <el-row style="margin-top: 10px">
              <span class="demonstration" style="margin-left: 84px; font-size: 80%">Gig Id</span>

              <el-input v-model="criteria.proship.gigId" placeholder="Gig ID" class="shortInput" size="mini"
                        style="width: 37%; margin-left: 15px" @change="handleGigIdChange"></el-input>
            </el-row>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 60px; font-size: 80%">Exclusivity</span>
            <el-radio v-model="criteria.proship.exclusivity" label="1" style="margin-left: 15px">Exclusive positions only</el-radio>
            <el-radio v-model="criteria.proship.exclusivity" label="2" style="margin-left: 137px">Permit non-exclusive positions</el-radio>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 51px; font-size: 80%">Projections</span>
            <el-radio v-model="criteria.proship.projection" label="1" style="margin-left: 15px">Allow projections</el-radio>
            <el-radio v-model="criteria.proship.projection" label="2"style="margin-left: 139px">No projections</el-radio>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 36px; font-size: 80%">Staffing Gaps</span>
            <el-radio v-model="criteria.proship.staffingGap" label="1" style="margin-left: 15px"> Include staffing gaps</el-radio>
            <el-radio v-model="criteria.proship.staffingGap" label="2"style="margin-left: 139px">Exclude staffing gaps</el-radio>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 25px; font-size: 80%">Account Manager</span>
            <el-select v-model="criteria.proship.accountManager"  @change="handleAccountManagerChange" size="mini"
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

        <el-button style="margin: auto" @click="displayOpening">Display Opening({{result.openingIdList.length}})</el-button>
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

  export default {
    data() {
      return {
        systemUsers: [],
        rooms:[],
        openingIdList:[],
        criteria: {
          proship:{
            openingId: '',
            jobId: '',
            gigId: '',
            recruiter: null,
            accountManager: null,
            exclusivity:false,
            projection:false,
            staffingGap:false
          },
          candidateName:'',
          type: {
            gigTypeId: null,
            rank: null,
            requirement:null,
            grouping:null
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
          openingList: [],
          openingIdList:[],
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
      displayOpening(){},
      handleRecruiterChange(){},
      handleAccountManagerChange(){},
      handleGigIdChange(){},
      handleOpeningIdChange(){},
      handleJobIdChange(){},
      handleCompanyChange(){},
      filterNode(value,data){
        if (!value) return true;
        return data.label.indexOf(value) !== -1;

      },
      fetchGigType(){

        let gigTypeOptions = JSON.parse(localStorage.getItem("gigTypeOptions"));
        if(!gigTypeOptions){
          this.$http.get('gigType/',{headers: getHeader()}).then(response=> {

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
        if(! this.clients){
          let clientOptions = JSON.parse(localStorage.getItem("clientOptions"));
          console.log(clientOptions)
          if(!clientOptions){
            this.$http.get('client/',{headers: getHeader()}).then(response=> {
              if (response.status === 200) {
                this.clients = response.data;
                localStorage.setItem('clientOptions',JSON.stringify(response.data))
              }
            })
          }else {
            this.clients = clientOptions;
          }
        }
      },
      fetchVenue(){
        let venueOptions = JSON.parse(localStorage.getItem("venueOptions"));
        if(!venueOptions){
          this.$http.get('venuemainshard/',{headers: getHeader()}).then(response=> {
            if (response.status === 200) {
              venueOptions = response.data;
              console.log('venueOptions from http request',venueOptions);
              localStorage.setItem('venueOptions',JSON.stringify(venueOptions))
            }
          })
        }

        if(!this.criteria.company.clientIds|| this.criteria.company.clientIds.length===0) {
          console.log(this.criteria.company.clientIds);
          this.venues = venueOptions;
        }
        else {
          let filteredVenue = venueOptions.filter(venueBase => this.criteria.company.clientIds.includes(venueBase.clientId));
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
        this.$http.get('evaluation/type/subtypes/'+key.data.id,{headers: getHeader()}).then(response=> {
          if (response.status === 200) {
            resolve(response.data);
          } else resolve([]);
        })
      },
      fetchSystemUser(){

        this.$http.get('user/all',{headers:getHeader()}).then(res=>{
          if(res.status===200)
          this.systemUsers = res.data;
        });
      },
      fetchRoom(){
        this.$http.get('room/all',{headers: getHeader()}).then(res=>{

          if(res.status===200)
          this.rooms = res.data;
        })
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
