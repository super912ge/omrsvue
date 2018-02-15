<template>
  <div>
    <el-table
      border
      :data="resultList"
      style="width: 80%;margin: auto;"
      size="small">
      <el-table-column
        prop="actId"
        label="Act Id"
        width="70"
      ></el-table-column>

      <el-table-column
        prop="name"
        label="Name"
        width="150"
>
      </el-table-column>

      <el-table-column
        prop="nextAvailableDate"
        label="Next Available Date"
      >
      </el-table-column>
      <el-table-column
        label="Skills"
       prop="evaluation">
       <display-candidate-skill slot-scope="scope" :evaluation=scope.row.evaluation></display-candidate-skill>
      </el-table-column>
      <el-table-column
        prop="residency"
        label="Residency"
    >
      </el-table-column>
      <el-table-column
        prop="citizenship"
        label="Citizenship"
 >
      </el-table-column>
      <el-table-column
        prop="birthDay"
        label="Date of Birth"
    >
      </el-table-column>
      <el-table-column
        label="Operations"
>
        <template slot-scope="scope">
          <router-link :to="{ name: 'DisplayCandidate', params: { id: scope.row.id }}">
            <el-button @click="handleClick" type="text" size="small">Detail</el-button></router-link>
          <el-button type="text" size="small">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin-top: 15px"></div>
    <el-pagination layout="prev, pager, next" :total="pageNumber" @current-change="handlePageChange">
    </el-pagination>
  </div>

</template>

<script>
  import ElTree from "../../../../node_modules/element-ui/packages/tree/src/tree.vue";
  import _ from "lodash";
  import DisplayCandidateSkill from './DisplayCandidateSkill.vue'
  export default {

    components: {ElTree,DisplayCandidateSkill},
    props:['resultList','pageNumber'],
    methods: {
      handleClick() {
        console.log('click');
      },
      handlePageChange(val){
        console.log('handlePageChange',val)
        this.$emit("pageChange",val)
      }
    },
    data() {
      return {

      }
    },
    computed: {
      skills(){
          let skills = [];

      }
    }
  }
</script>
