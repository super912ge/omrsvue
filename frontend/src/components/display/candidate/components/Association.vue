<template>

  <el-card>

    <div slot="header" class="clearfix">
      <span>Known Associations</span>
    </div>

    <el-table
      align="center"
      :data="associations"
      style="width: 78%; margin-left: 8%"
    size="small">
      <el-table-column type="expand">
        <template slot-scope="props">
          <p>Job Number: {{ props.row.jobNumber }}</p>
          <p>ContractId: {{ props.row.contractId}}</p>
          <p>Venue: {{ props.row.contractId }}</p>
          <p>Room: {{ props.row.language}}</p>
          <p>Rank: {{ props.row.id }}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="Name"
        prop="name"
        sortable>
      </el-table-column>
      <el-table-column
      label = "Primary Skill"
      prop="primarySkill">

      </el-table-column>
      <el-table-column
        label = "Language">
        <template slot-scope="props">
          <ul style="list-style-type: none;padding: 0;margin: 0;">
            <li v-for="item in props.row.language" :key="item">{{item}}</li>
          </ul>
        </template>

      </el-table-column>
      <el-table-column label="Last Worked Together">
        <el-table-column label = "Start Date" prop="startDate" sortable>
        </el-table-column>
        <el-table-column label = "End Date" prop="endDate" sortable>
        </el-table-column>
      </el-table-column>

    </el-table>

    <!--<span class="demonstration" v-else style="font-size: small">No associations has been found.</span>-->
  </el-card>

</template>
<script>
  import ElTable from "../../../../../node_modules/element-ui/packages/table/src/table.vue";

  export default {

    components: {ElTable},
    props:['associations']
  }
</script>
