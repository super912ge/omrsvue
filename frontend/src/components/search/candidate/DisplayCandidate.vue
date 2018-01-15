<template>
  <div>
    <el-table
      border
      :data="resultList"
      style="width: 100%; font-size: 80%">
      <el-table-column
        prop="actId"
        label="Act Id"
        width ="100"
      ></el-table-column>

      <el-table-column
        prop="name"
        label="Name"
        width="120">
      </el-table-column>

      <el-table-column
        prop="nextAvailableDate"
        label="Next Available Date"
        width="150">
      </el-table-column>
      <el-table-column
        label="Skills"
        width="200" prop="evaluation">
       <display-candidate-skill slot-scope="scope" :evaluation=scope.row.evaluation></display-candidate-skill>
      </el-table-column>
      <el-table-column
        prop="residency"
        label="Residency"
        width="100">
      </el-table-column>
      <el-table-column
        prop="citizenship"
        label="Citizenship"
        width="100">
      </el-table-column>
      <el-table-column
        prop="birthDay"
        label="Date of Birth"
        width="150">
      </el-table-column>
      <el-table-column
        label="Operations"
        width="107">
        <template slot-scope="scope">
          <el-button @click="handleClick" type="text" size="small">Detail</el-button>
          <el-button type="text" size="small">Edit</el-button>
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
