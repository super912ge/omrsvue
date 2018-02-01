<template>
  <div>


    <el-container style="height: auto" v-if="candidate">
      <el-aside class="left">
        <search-result></search-result>

      </el-aside>
      <el-main>
        <el-header>

          <el-card>

            <el-col span="3" :offset="1">
              <image src=""></image>
            </el-col>

            <el-col span="14">

              <span>{{candidate.essentials.name}}</span>
              <span>{{primarySkill}}</span>
              <span>{{candidate.essentials.employeeNumber}}</span>
              <span>{{candidate.essentials.availableDate}}</span>
            </el-col>
          </el-card>
        </el-header>
        <el-row>
          <el-col span="10">
            <essentials :candidate="candidate.essentials"></essentials>
          </el-col>
          <el-col span="14">
            <notes :notes="candidate.notes"></notes>
          </el-col>
        </el-row>
        <el-row>
          <availability></availability>
        </el-row>
        <el-row>
          <work-history></work-history>
        </el-row>
        <el-row>
          <documents></documents>
        </el-row>
        <el-row>
          <documents></documents>
        </el-row>
        <el-row>
          <association></association>
        </el-row>
        <el-row>
          <band></band>
        </el-row>
      </el-main>
      <el-aside>
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
  import {fetchCandidate} from "./api.js"
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
  export default {
    components: {
      ElCard,
      ElCol, ElRow, Association, Availability, WorkHistory, Notes, Band,
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
          this.candidate = candidate
        }
      }
    },

    beforeRouteEnter(to, from, next) {


      fetchCandidate(to.params.id, (err, candidate) => {

        next(vm => vm.setData(err, candidate));
      })
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
