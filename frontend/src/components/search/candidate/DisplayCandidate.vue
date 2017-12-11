<template>
  <div>
    <el-table
      :data="resultList"
      style="width: 100%; font-size: 50%">
      <el-table-column
        prop="actId"
        label="Act Id"
        width ="100"
      ></el-table-column>
      <!--<el-table-column-->
      <!--prop="firstName"-->
      <!--label="First/Middle Name"-->
      <!--width="150">-->
      <!--</el-table-column>-->
      <el-table-column
        prop="name"
        label="Name"
        width="150">
      </el-table-column>

      <el-table-column
        fixed
        prop="nextAvailableDate"
        label="Next Available Date"
        width="160">
      </el-table-column>
      <el-table-column
        label="Skills"
        width="120">
        <template scope = "scope">
          <!--<el-tree>-->

          <!--</el-tree>-->
          <div></div>
        </template>
      </el-table-column>
      <el-table-column
        prop="residency"
        label="Residency"
        width="120">
      </el-table-column>
      <el-table-column
        prop="citizenship"
        label="Citizenship"
        width="120">
      </el-table-column>
      <el-table-column
        prop="birthday"
        label="Date of Birth"
        width="120">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="Operations"
        width="120">
        <template slot-scope="scope">
          <el-button @click="handleClick" type="text" size="small">Detail</el-button>
          <el-button type="text" size="small">Edit</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin-top: 15px"></div>
    <el-pagination layout="prev, pager, next" :total="pageNumber" @current-page="handlePageChange">
    </el-pagination>
  </div>

</template>

<script>
  import ElTree from "../../../../node_modules/element-ui/packages/tree/src/tree.vue";

  export default {

    components: {ElTree},
    props:['resultList','pageNumber'],
    methods: {
      handleClick() {
        console.log('click');
      },
      handlePageChange(val){
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
         // resultList.
      }
    }
  }
</script>
