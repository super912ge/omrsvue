<template>
  <div>
    <el-row>
      <el-col  :span="11" :offset="1">

        <el-row style="margin-top: 25px">
          <span class="demonstration">Gig</span>
          <el-row style="margin-top: 15px">
            <span class="demonstration" style="margin-left: 25px; font-size: 80%">Gig Type</span>
            <el-select size="mini" v-model="criteria.type.gigTypeId"  @change="handleTypeChange"
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
            <span class="demonstration" style="margin-left: 25px;font-size: 80%;">Gig Name</span>
            <el-input size="mini" placeholder="Gig Name" v-model="criteria.name"
                      style="width: 45%; margin-left: 10px" @change="handleNameChange">
            </el-input>
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
            <el-input size="mini"
              style="margin-top: 10px;width: 50%; margin-left: 50px"
              placeholder="Filter keyword"
              v-model="criteria.filterText">
            </el-input>

            <el-tree
              style="width: 50%;margin-left: 50px"
              class="filter-tree"
              :props="treeProps"
              :load="loadNodeEval"
              nodeKey="id"
              showCheckbox
              checkStrictly="true"
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
          <span class="demonstration">Type</span>
          <div style="margin: 15px 0;"></div>
          <el-collapse style="width: 50%; margin-left: 25px "  @item-click="fetchClient">
            <el-collapse-item title="Client" style="font-size: large" >
              <div style="margin: 15px 0;"></div>
              <el-checkbox-group v-model="criteria.type.clientIds" style="height: 200px; overflow-y: scroll;">
                <el-checkbox v-for="client in clients" :label="client.id" :key="client.id"style="margin-left: 5px; width: 200px"
                             @change="handleTypeChange" >{{client.name}}</el-checkbox>
              </el-checkbox-group>
            </el-collapse-item>
          </el-collapse>
          <div style="margin-top: 15px"></div>
          <el-collapse style="width: 50%; margin-left: 25px "  @item-click="fetchVenue">
            <el-collapse-item title="Venue">
              <div style="margin: 15px 0;"></div>
              <el-checkbox-group v-model="criteria.type.venueIds" style="height: 200px; overflow-y:scroll">
                <el-checkbox v-for="venueBase in venues" :label="venueBase.venueId" :key="venueBase.venueId"
                             style="margin-left: 5px; width: 200px"
                             @change="handleTypeChange">{{venueBase.name}}  ------  {{venueBase.clientCode}}
                </el-checkbox>
              </el-checkbox-group>
            </el-collapse-item>
          </el-collapse>
          <div style="margin-top: 15px"></div>
          <span class="demonstration" style="margin-left: 25px; font-size: 80%">Room</span>
          <el-select size="mini" v-model="criteria.type.roomName"  @change="handleTypeChange"
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

            <el-row style="margin-top: 10px">
              <span class="demonstration" style="margin-left: 84px; font-size: 80%">Gig Id</span>

              <el-input size="mini" v-model="criteria.proship.gigId" placeholder="Gig ID" class="shortInput"
                        style="width: 37%; margin-left: 15px" @change="handleGigIdChange"></el-input>
            </el-row>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 60px; font-size: 80%">Exclusivity</span>
            <el-radio v-model="criteria.proship.exclusivity" label="1" style="margin-left: 15px">Exclusive positions only</el-radio>
            <el-radio v-model="criteria.proship.exclusivity" label="2" style="margin-left: 137px">Permit non-exclusive positions</el-radio>
          </el-row>
          <el-row style="margin-top: 10px">
            <span class="demonstration" style="margin-left: 25px; font-size: 80%">Account Manager</span>
            <el-select size="mini" v-model="criteria.proship.accountManager"  @change="handleAccountManagerChange"
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

        <el-button style="margin: auto" @click="displayGig">Display Gig({{gigIdList.length}})</el-button>
      </el-col>
      <el-col :span="2">
        <el-button style="margin: auto" @click="reset">Reset</el-button>
      </el-col>

    </el-row>
    <el-row>
      <div style="margin-top: 20px"></div>

      <display-gig v-if="result.showSearchResult" :resultList="result.gigList" :pageNumber="result.totalPage"
                   @pageChange="displayGig
"></display-gig>

    </el-row>
  </div>
</template>

<script>
  import _ from 'lodash'
  import {getHeader} from '../../../env'
  import localStorage from 'localStorage'
  import DisplayGig from "./DisplayGig.vue"
  export default {

    components: {
      DisplayGig
    },
    data() {
      return {
        systemUsers: [],
        rooms:[],
        criteria: {
          proship:{
            gigId: '',
            accountManager: null,
            exclusivity:false,
          },
          requirement: {
            requirement:null,
            grouping:null
          },
          type:{
            clientIds: [],
            venueIds: [],
            roomName:null,
            gigTypeId: null
          },
          duration: null,
          name:null
        },
        result: {
          totalGig: 0,
          totalPage: 0,
          nameResultList: null,
          proshipResultList: null,
          typeResultList: null,
          requirementResultList: null,
          idResultList:null,
          gigList: [],
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
      gigIdList: function () {
        let nonNullResultList = [];
        if (this.result.typeResultList) {
          nonNullResultList.push(this.result.typeResultList);
        }
        if (this.result.idResultList) {
          nonNullResultList.push(this.result.idResultList);
        }
        if (this.result.nameResultList) {
          nonNullResultList.push(this.result.nameResultList);
        }

        if (this.result.requirementResultList) {
          nonNullResultList.push(this.result.requirementResultList)
        }if(this.result.proshipResultList){
          nonNullResultList.push(this.result.proshipResultList);
          console.log("000",this.result.proshipResultList)
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
      handleNameChange(){

        _.debounce(this.searchByName,500)();
      },
//      handleReqChange(){},


      reset(){},

      searchByName(){

        if(this.criteria.name && this.criteria.name.trim().length>0){
          let options = { emulateJSON: true};
          this.$http.get("gig/search/name/"+
            this.criteria.name,options,{headers:getHeader()}).then(response=>{

            if (response.status === 200){
              this.result.nameResultList = response.data;
            }
          })
        }else this.result.idResultList = null;
      },

      displayGig(pageNumber){
        if(!_.isNumber(pageNumber))pageNumber = 1;

        this.$http.post("gig/display",{ids: this.gigIdList, page:pageNumber-1,size:20},
          {headers:getHeader()}).then(response=>{
          if(response.status === 200) {
            this.result.gigList = [];
            response.data.resultList.forEach(item => this.result.gigList.push(item));

            this.result.totalPage = response.data.totalPage * 10;
            this.result.showSearchResult = true;
          }
        });

      },
      handleAccountManagerChange(){
        _.debounce(this.searchByAccountManager,500)();

      },

      searchByAccountManager(){
        if(this.criteria.proship.accountManager){
          let options = { emulateJSON: true};
          this.$http.get("gig/search/accountManager/"+
            this.criteria.proship.accountManager,options,{headers:getHeader()}).then(response=>{
            if (response.status === 200){
              this.result.proshipResultList = response.data;
              console.log(this.result.proshipResultList)
            }
          })
        }else this.result.proshipResultList = null;
      },
      handleGigIdChange(){

        _.debounce(this.searchById,1000)();
      },

      searchById(){
        if(this.criteria.proship.gigId && this.criteria.proship.gigId.trim().length>0){
          let options = { emulateJSON: true};
          this.$http.get("gig/search/id/"+
            this.criteria.proship.gigId,options,{headers:getHeader()}).then(response=>{

            if (response.status === 200){
              this.result.idResultList = response.data;
            }
          })
        }else this.result.idResultList = null;
      },

      handleJobIdChange(){

        _.debounce(this.searchByJobId,1000);
      },

      searchByJobId() {

        if(this.criteria.proship.jobId){
          let options = { emulateJSON: true};
          this.$http.get("gig/search/jobId/"+
            this.criteria.proship.jobId,options,{headers:getHeader()}).then(response=>{

            if (response.status === 200){
              this.result.idResultList = response.data;
            }
          })
        }else this.result.idResultList = null;
      },
      handleTypeChange(){

        _.debounce(this.searchByType,1000)();
      },
      searchByType(){
        this.$http.post("gig/search/type",
          this.criteria.type, {headers:getHeader() }).then( response => {
          if (response.status === 200) {
            this.result.typeResultList = response.data;
          }
        })
      },
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
      fetchUser(){
        this.$http.get('user/all',{headers: getHeader()}).then(response=> {
          if (response.status === 200) {
            console.log(response.data);
            this.systemUsers = response.data;
          }
        })
      },
      fetchClient(){

        if(! this.clients){
          let clientOptions = JSON.parse(localStorage.getItem("clientOptions"));
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

              localStorage.setItem('venueOptions',JSON.stringify(venueOptions))
            }
          })
        }

        if(!this.criteria.type.clientIds|| this.criteria.type.clientIds.length===0) {


          this.venues = venueOptions;
        }
        else {
          let filteredVenue = venueOptions.filter(venueBase => this.criteria.type.clientIds.includes(venueBase.clientId));

          this.venues = filteredVenue;
        }
      },
      loadNodeEval(node, resolve){

        if(node.level===0){
          return resolve([{ id:1,label:'Requirement'}]);
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
        this.systemUsers = [{id:1,name:'Adam'},{id:'2',name:'JP'}]
      },
      fetchRoom(){
        let roomOptions = JSON.parse(localStorage.getItem("roomOptions"));
        if(!roomOptions){
          this.$http.get('room/all',{headers: getHeader()}).then(response=> {
            if (response.status === 200) {
              roomOptions = response.data;
              this.gigTypes = response.data;
              localStorage.setItem('roomOptions',JSON.stringify(response.data));
            }
          })
        }else {
          this.rooms = roomOptions;
        }
      }
    },
    created() {
      this.fetchClient();
      this.fetchVenue();
      this.fetchGigType();
//      this.fetchSystemUser();
      this.fetchRoom();
      this.fetchUser();
    }
  }
</script>
