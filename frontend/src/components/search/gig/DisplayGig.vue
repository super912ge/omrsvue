<template>
<div>
  <el-table
    border
    :data="resultList"
    style="width: 100%; font-size: 80%">

    <el-table-column
      width="70"
      prop="id"
      label="Id">
    </el-table-column>

    <el-table-column
      prop="manager.name"
      label="Manager"
    ></el-table-column>
    <el-table-column
      prop="client.code"
      label="Client">
    </el-table-column>
    <el-table-column
      prop="venue.name"
      label="Venue">
    </el-table-column>
    <el-table-column
      prop="room"
      label="Room">
    </el-table-column>
    <el-table-column
      prop="chair"
      label="Chair">
    </el-table-column>
    <el-table-column
      prop="type"
      label="Type">
    </el-table-column>
    <el-table-column
      prop="startDate"
      label="Start Date">
    </el-table-column>
    <!--<el-table-column-->
      <!--prop="endDate"-->
      <!--label="End Date">-->
    <!--</el-table-column>-->
    <el-table-column
      prop="minSal"
      label="Minimum Salary">
    </el-table-column>
    <el-table-column
      prop="territory.name"
      label="Territory">
    </el-table-column>
    <el-table-column
      label="Operations">
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


  export default {
//    components: {ElTree,DisplayCandidateSkill},
    props:['resultList','pageNumber'],
    methods: {
      handleClick() {

      },
      handlePageChange(val) {
        this.$emit("pageChange", val)
      }
    }
  }
</script>

<style scoped>

  .el-table .cell{
    padding: 5px;
  }
</style>
