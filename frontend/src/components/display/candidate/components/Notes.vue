<template>
  <el-card style="margin-top: 15px">
    <div slot="header" class="clearfix">
      <span>Notes</span>
    </div>
    <el-row>
      <el-col :span="18" style="margin-bottom: 15px">
        <el-input
          type="textarea"
          :rows="3"
          placeholder="Please input"
          v-model="newNote.text"
        style="margin-bottom: 5px;">
        </el-input>
        <el-button @click="clear" size="mini">Clear</el-button>
        <el-button @click="save" size="mini">Save</el-button>
      </el-col>
      <el-col :span="6">
        <el-radio-group v-model="newNote.type">
          <el-radio :label="1" style="margin-left: 10px ">Process Note</el-radio>
          <el-radio :label="2">Availability Note</el-radio>
          <el-radio :label="3">Positive Note</el-radio>
          <el-radio :label="4">Red Flag</el-radio>
        </el-radio-group>

      </el-col>
    </el-row>
    <el-table
      :data="Notes"
      :height="250"
      size="small"
      style="width: 100%;">
      <el-table-column
        prop="type"
        label="Type"
        width="60"
        :filters="filterTypes" :filter-method="filterHandler" filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <i v-if="scope.row.type===3" class="el-icon-date"></i>
          <i v-if="scope.row.type===4" class="el-icon-info"></i>
          <i v-if="scope.row.type===2" class="el-icon-warning"></i>
          <i v-if="scope.row.type===1" class="el-icon-success"></i>
        </template>
      </el-table-column>
      <el-table-column
        prop="text"
        label="Note"
        width="320">
      </el-table-column>

      <el-table-column
        fixed="right"
        label="Operations"
        width="80">
        <template slot-scope="scope">
          <el-button @click="handleClick" type="text" size="small"><i class="el-icon-edit"></i></el-button>
          <el-button type="text" size="small"><i class="el-icon-delete"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>


</template>
<script>

  import ElCol from "element-ui/packages/col/src/col";
  import ElRadio from "../../../../../node_modules/element-ui/packages/radio/src/radio.vue";
  import ElButton from "../../../../../node_modules/element-ui/packages/button/src/button.vue";
  import {getHeader} from "../../../../env"
  export default {
    components: {
      ElButton,
      ElRadio,
      ElCol},
    props: ['notes'],

    data(){
      return {
        newNote:{
          text:null,
          type:null
        },
        filterTypes:[
          {
            text: 'positive',
            value:1
          },
          {

            text: 'red flag',
            value:2
          },
          {
            text: 'availability',
            value:3
          },
          {
            text: 'process',
            value:4
          }
        ]
      }
    },
    methods:{
      clear(){
        this.newNote = {
          text : null,
          type : null
        }
      },

      save(){
        this.$resource.post("candidate/note/add",this.newNote,{header:getHeader()}).then(response=> {
          if(response.status===200) {
            this.notes.push(this.newNote);
          }
        })
      },
      filterType(value, row) {
        return row.type === value;
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      handleClick(){

      }
    },
    computed:{

      Notes(){

        console.log(this.notes)
        return this.notes;
      }
    },
  }
</script>
<style scoped>

  .el-radio+.el-radio {
    margin-left: 10px;
  }


.el-table th{
    padding: 2px 0px;
    font-size: small;
  }
  .el-table th div, .el-table th>.cell {
    line-height:15px;
  }

  .cell{
    padding: 0px;
    font-size: small;
  }

  .el-table_1_column_2 .is-leaf{
    padding: 0;
  }
</style>
