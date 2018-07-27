<template>
  <div>
    <el-row>
      <el-col  :span="11" :offset="1" style="margin-top: 25px">
        <span class="demonstration">Band</span>
        <div style="margin-top: 15px"></div>
        <el-row>
          <el-row style="font-size: 80%">

            <span class="demonstration" style="margin-left: 25px;margin-right: 20px">Band Size</span>



            min: <el-input size="mini" style="margin: 10px; width: 15%" v-model.number="criteria.band.size.min" placeholder="1" class="shortInput" @change="handleSizeChange"></el-input>


            max: <el-input size="mini" v-model.number="criteria.band.size.max" placeholder="10" style="width: 15%" class="shortInput" @change="handleSizeChange"></el-input>

          </el-row>


          <el-row>
            <div style="margin-top: 15px;">
              <span class="demonstration"style="margin-left: 25px; font-size: 80%">Band Name</span>
              <el-input size="mini" placeholder="band Name" v-model="criteria.band.name"
                        style="width: 45%; margin-left: 10px" @change="handleNameChange">
              </el-input>
            </div>
          </el-row>



        </el-row>
        <el-row style="margin-top: 25px">

          <el-col :span="24">
            <div class="block">
              <span class="demonstration">Duration</span>
              <div style="margin-top: 15px;"></div>
              <el-date-picker style="width: 300px"
                              v-model="criteria.duration"
                              type="daterange"
                              range-separator="To"
                              start-placeholder="Start Date"
                              end-placeholder="End Date">
              </el-date-picker>
            </div>
          </el-col>
        </el-row>

        <!--//Experience-->

        <!--//Location-->
      </el-col>

      <el-col :span="12">

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
                <el-checkbox v-for="venueBase in venues" :label="venueBase.venueId" :key="venueBase.venueId"
                             style="margin-left: 5px; width: 200px"
                             @change="handleExperienceChange">{{venueBase.name}}  ------  {{venueBase.clientCode}}
                </el-checkbox>
              </el-checkbox-group>
            </el-collapse-item>
          </el-collapse>
        </el-row>
        <el-row style="margin-top: 15px">
          <span class="demonstration" style="margin-left: 25px; font-size: 80%">Gig Type</span>
          <el-select size="mini" v-model="criteria.experience.gigTypeId"  @change="handleExperienceChange"
                     filterable placeholder="Gig Type" style="width: 200px;margin-left: 15px">
            <el-option
              v-for="item in gigTypes"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <div style="margin-top: 20px"></div>
      <el-col :span="4" :offset="8">

        <el-button style="margin: auto" @click="displayBand">Display Band({{bandIdList.length}})</el-button>
      </el-col>
      <el-col :span="2">
        <el-button style="margin: auto" @click="reset">Reset</el-button>
      </el-col>

    </el-row>
    <el-row>
      <div style="margin-top: 20px"></div>
      <display-band v-if="result.showSearchResult" :resultList="result.bandList" :pageNumber="result.totalPage"
                    @pageChange="displayBand"></display-band>
    </el-row>
  </div>
</template>
<script>
  import _ from 'lodash'
  import {getHeader} from '../../../env'
  import localStorage from 'localStorage'
  import ElRow from "element-ui/packages/row/src/row";
  import ElButton from "../../../../node_modules/element-ui/packages/button/src/button.vue";
  import DisplayBand from './DisplayBand.vue'
  import ElCol from "element-ui/packages/col/src/col";
  export default {

    components: {
      ElCol,
      ElButton,
      ElRow,
      DisplayBand
    },
    data() {
      return {

        criteria:{
          band:{ name:null, size:{
            min:null,
            max:null
          } },
          experience:{
            gigTypeId:null,
            clientIds:[],
            venueIds:[],
            nonPs:null
          },
          availability:null,
        },
        result:{
          totalBand:0,
          totalPage:0,
          nameResultList:null,
          experienceResultList:null,
          sizeResultList:null,
          bandList:[],
          showSearchResult: false
        },
        clients:null,
        isIndeterminateClient: false,
        venues:null,
        isIndeterminateVenue: false,
        gigTypes:null

      }
    },

    computed:{
      bandIdList:function () {
        let nonNullResultList = [];

        if(this.result.nameResultList){
          nonNullResultList.push(this.result.nameResultList);
        }
        if(this.result.sizeResultList){
          nonNullResultList.push(this.result.sizeResultList)
        }
        if(this.result.experienceResultList){

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
      displayBand(pageNumber){
        if(!_.isNumber(pageNumber))pageNumber = 1;
        console.log(pageNumber);
        this.$http.post("band/display",{ids: this.bandIdList, page:pageNumber-1,size:20},
          {headers:getHeader()}).then(response=>{
          if(response.status===200) {
            this.result.bandList = [];
            response.data.resultList.forEach(item => this.result.bandList.push(item));
            this.result.totalPage = response.data.totalPage * 10;
            this.result.showSearchResult = true;
          }
        })
      },
      reset(){
        console.log('reset',this.$data,defaultData());
        this.$data.result=defaultData().result;
        this.$data.criteria = defaultData().criteria;
      },
      handleNameChange(){
        console.log('band name changed');
        _.debounce(this.searchByName,500)();
      },

      searchByName(){

        if(this.criteria.band.name
          && this.criteria.band.name.trim().length>0){
          this.criteria.band.name = this.criteria.band.name.replace(/^\s+|\s+$/g, '');
          let options = { emulateJSON: true};
          this.$http.post("band/search/name",
            this.criteria.band.name,{headers:getHeader()}).then(response=>{
            if (response.status ===200){
              this.result.nameResultList = response.data;
            };
          })
        }
      },
      handleSizeChange(){
        _.debounce(this.searchBySize,500)();
      },
      searchBySize(){
        let options = { emulateJSON: true};
        this.$http.post("band/search/size",
          this.criteria.band.size,{headers:getHeader()}).then(response=>{
          if (response.status ===200){
            this.result.sizeResultList = response.data;
          }
        })
      },
      handleExperienceChange(){
        _.debounce(this.searchByExperience,500)();
      },
      searchByExperience(){
        if(!_.isEmpty(this.criteria.experience.clientIds)||!_.isEmpty(this.criteria.experience.venueIds)||
          !_.isEmpty(this.criteria.experience.gigTypeId)){
          this.$http.post("band/search/gig",

            this.criteria.experience, {headers:getHeader() }).then( response => {

              if (response.status === 200) {

                this.result.experienceResultList = response.data;
            }
          })
        }
      },
      displayBand(pageNumber){
        if(!_.isNumber(pageNumber))pageNumber = 1;
//        let data = {"resultList":[{"id":3757,"name":"Galaxy Duo","type":"Lounge Band"},{"id":2652,"name":"Jean Mac Trio","type":"Lounge Band"},{"id":2884,"name":"Apassionado","type":"Lounge Band"},{"id":2930,"name":"Digital Hitch","type":"Lounge Band"},{"id":2948,"name":"Band Alaska","type":"Lounge Band"},{"id":4054,"name":"Apollo Duo","type":"Lounge Band"},{"id":4057,"name":"Domino Duo","type":"Party Band"},{"id":4089,"name":"The Great Escape","type":"Lounge Band"},{"id":4379,"name":"Jerry Yuzon","type":"One Man Band"}],"totalPage":1};
//        this.result.bandList = [];
//        data.resultList.forEach(item => this.result.bandList.push(item));
//        this.result.totalPage = data.totalPage * 10;
//        this.result.showSearchResult = true;
        this.$http.post("band/display",{ids: this.bandIdList, page:pageNumber-1,size:20},
          {headers:getHeader()}).then(response=>{
          if(response.status===200) {
            this.result.bandList = [];
            response.data.resultList.forEach(item => this.result.bandList.push(item));
            this.result.totalPage = response.data.totalPage * 10;
            this.result.showSearchResult = true;
          }
        })
      },
      fetchGigType(){
        let gigTypeOptions = JSON.parse(localStorage.getItem("gigTypeOptions"));
        if(!gigTypeOptions){

          this.$http.get('gigType/',{headers: getHeader()}).then(response=> {

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
              console.log('venueOptions from http request',venueOptions);
              localStorage.setItem('venueOptions',JSON.stringify(venueOptions))
            }
          })
        }
        if(!this.criteria.experience.clientIds|| this.criteria.experience.clientIds.length===0) {
          console.log(this.criteria.experience.clientIds);
          this.venues = venueOptions;
        }
        else {
          let filteredVenue = venueOptions.filter(venueBase => this.criteria.experience.clientIds.includes(venueBase.clientId));
          console.log(filteredVenue);
          this.venues = filteredVenue;
        }
      },
    },
    created(){
      this.fetchGigType();
      this.fetchVenue();
      this.fetchClient();
    }
  }

  const defaultData = function(){return {
    criteria:{
    band:{
      name:null,
      size:{
        min: null,
        max: null
      }
    },
    experience:{
      gigTypeId:null,
      clientIds:[],
      venueIds:[],
    },
    duration:null,
  },
    result:{
      totalBand:0,
      totalPage:0,
      nameResultList:null,
      experienceResultList:null,
      bandList:[],
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
