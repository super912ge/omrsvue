<template>
  <div>


    <el-container style="height: auto" v-if="candidate">
      <el-aside class="left" style="width: 14%">
        <search-result></search-result>

      </el-aside>
      <el-main style="padding: 0;">

        <el-row>

          <el-card>
            <el-col :span="3" :offset="1">
              <img src='./snoop.jpg' style="width: 80px; height: 80px;border-radius: 50%"/>
            </el-col>

            <el-col :span="13" >
              <ul style=" list-style-type: none; line-height: 130%">

                <li><span>Name: {{candidate.essentials.name}}</span></li>
                <li><span>Primary Skill: {{candidate.essentials.primarySkill}}</span></li>
                <li><span>Employee Number: {{candidate.essentials.employeeNumber}}</span></li>
                <li><span>Available From: 2017-05-08</span></li>

              </ul>
            </el-col>
            <el-col :span="3">

              <!--<span style="margin: auto">red flag</span>-->
            </el-col>
          </el-card>
        </el-row>

        <el-row>
          <el-col :span="10">
            <essentials :candidate="candidate.essentials"></essentials>
          </el-col>

          <el-col :span="14">
            <notes :notes="candidate.notes" :candidateId="candidate.essentials.employeeNumber" ref="note"
             @noteAdded="addNote" @noteUpdated="updateNote" @noteDeleted="deleteNote"
            ></notes>
          </el-col>
        </el-row>


        <el-row>
          <availability></availability>
        </el-row>
        <el-row>
          <work-history :psContracts="candidate.psContracts"></work-history>
        </el-row>
        <el-row>
          <documents :identities="candidate.identifications" name="Identification Documents"></documents>
        </el-row>
        <el-row>
          <documents :identities="candidate.medicals" name="Medical Documents"></documents>
        </el-row>
        <el-row>
          <association :associations="candidate.associations"></association>
        </el-row>
        <el-row>
          <band :band="candidate.bands"></band>
        </el-row>
      </el-main>
      <el-aside style="width: 16%">
        <el-row>
          <actions></actions>
        </el-row>
        <el-row>
          <evaluation></evaluation>
        </el-row>

      </el-aside>


    </el-container>

  </div>

</template>

<script>

  import ElRow from "element-ui/packages/row/src/row";
  import {fetchCandidate,fetchNotes} from "./api.js"
  import ElCol from "element-ui/packages/col/src/col";
  import Association from "./components/Association.vue"
  import Availability from "./components/Availability.vue";
  import WorkHistory from "./components/WorkHistory.vue";
  import Notes from "./components/Notes.vue";
  import Band from "./components/Band.vue";
  import Documents from "./components/Documents.vue";
  import SearchResult from "./components/SearchResult.vue";
  import Essentials from "./components/Essentials.vue";
  import Actions from "./components/Actions.vue";
  import Evaluation from "./components/Evaluation.vue";
  import ElCard from "../../../../node_modules/element-ui/packages/card/src/main.vue";
  import AppNav from "../../AppNav.vue"
  import {getHeader} from "../../../env.js"
  import candidate from './candidate.js'

  export default {
    components: {
  Association, Availability, WorkHistory, Notes, Band,
      Documents, SearchResult, Essentials, Actions, Evaluation
    },
    data() {

      return {

        candidate: null,
        error: null
      };
    },
    computed:{
      primarySkill(){
        return 'primary skill'
      }
    },

    methods: {
      setData(err, candidate) {
        if (err) {
          this.error = err.toString()
        } else {
          this.candidate = candidate;

        }
      },
      addNote(val){
        this.candidate.notes.unshift(val);
      },
      updateNote(val,index){

        this.$http.post("participantEvent/update",val,
          {headers:getHeader()}).then(response=> {
          if(response.status===201) {

            fetchNotes(this.candidate.essentials.employeeNumber,(err, res)=>{
              this.candidate.notes = res;
             // setData(err,this.candidate);
            });
          }
        });

      },
      deleteNote(index){
        this.candidate.notes.splice(index,1);
      }
    },

    beforeRouteEnter(to, from, next) {

      fetchCandidate(to.params.id, (err, candidate) => {
        next(vm => vm.setData(err, candidate));
      });

    },
    beforeRouteUpdate(to, from, next) {
      this.candidate = null;
      fetchCandidate(to.params.id, (err, candidate) => {
        this.setData(err, candidate);
        next();
      });
    }

  }
</script>

<style scoped>
  .el-container{
    width: 100%;
  }
  .el-aside .left{
    width: 15%;
    background-color: #122b40;
    text-color:white
  }
  .el-main{
    width:60%
  }
</style>
