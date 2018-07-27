<template>
  <el-dialog
    title="New Todo" :visible ="true"  width="30%" center :show-close="false">
    <el-form :model="todoItem" ref="todoItem" label-width="120px" class="demo-ruleForm">
      <el-form-item label="Title" prop="title">
        <el-input size="mini" v-model="todoItem.title" auto-complete="off" width="10%"></el-input>
      </el-form-item>

      <el-form-item label="Due Date" prop="dueDate">
        <el-date-picker v-model="todoItem.dueDate" type="date" placeholder="Pick a Date" format="yyyy/MM/dd" value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>


      <el-form-item label="Priority" prop="priority">
        <el-select size="mini" v-model="todoItem.priorityId" placeholder="Select">
          <el-option
            v-for="item in priority"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Description" prop="text">
        <el-input size="mini" type="textarea" :rows="3" v-model="todoItem.text" auto-complete="off"></el-input>
      </el-form-item>



    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="$emit('close')">Cancel</el-button>
      <el-button type="primary" @click="updateTodoList">Confirm</el-button>
    </span>
  </el-dialog>
</template>

<script>

  import {todoListAddUrl,getHeader} from '../../../env.js'
  //import ElForm from "../../../../node_modules/element-ui/packages/form/src/form.vue";
  import ls from 'localStorage'
  import moment from 'moment';
  export default {

  //  components: {ElForm},
    props:['todoItem'],

    data() {
      const userId = ls.getItem("userId");
      return {
        //visibility:false,
        priority: [{
          value: '1',
          label: 'High'
        }, {
          value: '2',
          label: 'Medium'
        }, {
          value: '3',
          label: 'Low'
        }]
//
//        todoItem: {
//          id:
//          user:{
//            id: userId
//          },
//          title:'',
//          text:'',
//          dueDate:'',
//          priorityId:''
//        }
      };
    },

    methods: {

      updateTodoList(){


        console.log(this.todoItem);

        console.log(getHeader())

        this.todoItem.dueDate = moment(this.todoItem.dueDate).format('YYYY-MM-DD HH:mm:ss');

        console.log(this.todoItem)
        this.$http.put('todo/'+this.todoItem.id, this.todoItem,{headers: getHeader()}).then(response => {

          if (response.status === 200) {

            this.$emit('close')
            this.$emit('updated')

          } else {
            console.log('toDoList fail!!');
            return false;
          }
        });
      }
    }
  };
</script>
<style scoped>

  .el-textarea{
    width: 70%;

  }
  .el-input{
    width: 225px;
  }
</style>
