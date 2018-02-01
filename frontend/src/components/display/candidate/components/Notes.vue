<template>
  <div>
    <el-row>
      <el-col>
        <el-input
          type="textarea"
          :rows="3"
          placeholder="Please input"
          v-model="newNote.text">
        </el-input>
        <el-button @click="clear">Clear</el-button>
        <el-button @click="save">Save</el-button>
      </el-col>
      <el-col>
        <el-radio-group v-model="newNote.type">
          <el-radio :label="1">Process Note</el-radio>
          <el-radio :label="2">Availability Note</el-radio>
          <el-radio :label="3">Positive Note</el-radio>
          <el-radio :label="4">Red Flag</el-radio>
        </el-radio-group>

      </el-col>
    </el-row>



    <el-table
      :data="Notes"
      height="250"
      style="width: 100%">
      <el-table-column
        prop="type"
        label="Type"
        width="80"
        :filters="filterTypes" :filter-method="filterType" filter-placement="bottom-end">
      </el-table-column>
      <el-table-column
        prop="note"
        label="Note"
        width="180">
      </el-table-column>

      <el-table-column
        fixed="right"
        label="Operations"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick" type="text" size="small">Edit</el-button>
          <el-button type="text" size="small">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>


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
    props: 'notes',

    data(){
      return {
        newNote:{
          text:null,
          type:null
        },
        filterTypes:[
          {text: 'positive',
            value:1
          },
          {text: 'red flag',
            value:2
          },
          {text: 'processing',
            value:3
          },
          {text: 'avalibility',
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
      watched:{
        Notes(){
          return this.notes;
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
        return row.tag === value;
      }
    }
  }
</script>
