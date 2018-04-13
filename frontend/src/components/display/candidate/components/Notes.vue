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
          <el-radio :label="4" style="margin-left: 10px ">Process Note</el-radio>
          <el-radio :label="3">Availability Note</el-radio>
          <el-radio :label="1">Positive Note</el-radio>
          <el-radio :label="2">Red Flag</el-radio>
        </el-radio-group>
      </el-col>
    </el-row>
    <el-table
      :data="events"
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
          <el-button @click="editNote(scope.$index)" type="text" size="mini"><i class="el-icon-edit"></i></el-button>
          <el-button type="text" size="mini" @click="deleteNoteDialog=true; deleteNoteIndex=scope.$index">
            <i class="el-icon-delete"></i></el-button>

        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="Edit Note"
      style="font-size: x-small"
      :visible.sync="editNoteDialog"
      width="40%">
      <el-row>
      <el-col :span="18" style="margin-bottom: 15px">
        <el-input
          type="textarea"
          :rows="3"
          placeholder="Please input"
          v-model="updateNote.text"
          style="margin-bottom: 5px;">
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-radio-group v-model="updateNote.type">
          <el-radio :label="4" style="margin-left: 10px ">Process Note</el-radio>
          <el-radio :label="3">Availability Note</el-radio>
          <el-radio :label="1">Positive Note</el-radio>
          <el-radio :label="2">Red Flag</el-radio>
        </el-radio-group>
      </el-col>
      </el-row>
<div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="editNoteDialog = false" size="mini">Cancel</el-button>
    <el-button type="primary" size="mini" @click="confirmEditNote">Confirm</el-button>
    </span>
</div>
    </el-dialog>

    <el-dialog
      title="Delete Note"
      :visible.sync="deleteNoteDialog"
      style="font-size: x-small"
      width="30%"
    >
      <span>Are you sure to delete this note?</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="deleteNoteDialog = false" size="mini">Cancel</el-button>
    <el-button type="primary" @click="deleteNote" size="mini">Confirm</el-button>
  </span>
    </el-dialog>


  </el-card>


</template>
<script>

  import {getHeader} from "../../../../env"

  export default {

    props: ['notes','candidateId'],

    data(){
      return {
        newNote:{
          text:null,
          type:null
        },
        events: this.notes,
        editNoteIndex:null,
        deleteNoteIndex:null,
        updateNote:{
          id:null,
          text:null,
          type:null
        },
        editNoteDialog:false,
        deleteNoteDialog:false,
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
          type : null,
        }
      },

      editNote(index){

        this.updateNote.text = this.events[index].text;
        this.updateNote.type = this.events[index].type;
        this.updateNote.id = this.events[index].id;
        this.editNoteIndex = index;
        this.editNoteDialog = true;
      },
      confirmEditNote(){

        this.$emit('noteUpdated',this.updateNote,this.editNoteIndex);

        this.updateNote = {
          id:null,
          text:null,
          type:null
        };
        this.editNoteIndex = null;
        this.editNoteDialog = false;
      },
      save(){
        this.$http.post("http://localhost:8080/participantEvent/create/"+this.candidateId,this.newNote,{headers:getHeader()})
          .then(response=> {
          if(response.status===201) {
            this.$emit('noteAdded',response.data);
            this.newNote = {
              text:null,
              type:null
            }
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

      deleteNote(){
        this.$http.get("http://localhost:8080/participantEvent/delete/"+this.events[this.deleteNoteIndex].id,
          {headers:getHeader()}).then(response=> {
          if(response.status===200) {
            this.$emit('noteDeleted',this.deleteNoteIndex);
            this.deleteNoteDialog = false;
          }
        });
      }
    },
   watch:{
      notes(){
       this.events=this.notes;
      }
    }
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
