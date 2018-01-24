<template>

        <div>
          <el-col :span="17"><h1 style="margin-left: 50%">To do List</h1>
          </el-col>
           <el-col :span="3" style="margin-top: 25px"><el-button @click="showDialogNew = true">add</el-button></el-col>

          <new-todo-list v-if="showDialogNew" @close = "showDialogNew = false" v-on:updated="fetchData"></new-todo-list>
          <edit-todo-list v-if="showDialogEdit" @close = "showDialogEdit = false" v-on:updated="fetchData" :todoItem="todoItem"></edit-todo-list>
            <el-table :align = "alignClass" :data="todoList" style="width: 66%; margin-left: 25%" >
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <p>Title: {{ props.row.title }}</p>
                        <p>Priority: {{ props.row.priorityId }}</p>
                        <p>Due Date: {{ props.row.dueDate }}</p>
                        <p>Create Date: {{ props.row.createDate }}</p>
                        <p>Description: {{ props.row.text }}</p>
                        <p>Done: <i v-bind:class="{ 'el-icon-circle-check' : props.row.done}"></i></p>
                    </template>
                </el-table-column>
                <el-table-column
                        label="Title"
                        prop="title">
                </el-table-column>
                <el-table-column
                        label="Due Date"
                        prop="dueDate"
                        sortable>
                </el-table-column>
                <el-table-column
                        label="Priority"
                        prop="priorityId"
                        sortable>
                </el-table-column>
                <el-table-column
                        label="Done"
                        prop="done">
                    <template slot-scope="props">
                        <i v-bind:class="{'el-icon-circle-check' : props.row.done}"></i>
                        <i v-bind:class="{'el-icon-check' : !props.row.done}" @click="handleDone(props.$index, props.row)"></i>
                    </template>

                </el-table-column>
                <el-table-column
                        label="Operations">
                    <template slot-scope="props">

                          <i class="el-icon-edit" size="small"
                                                @click="handleEdit(props.$index, props.row)" style="margin-right: 10px"></i>



                            <i class="el-icon-delete"
                               size="small"
                               type="danger"
                               @click="handleDelete(props.$index, props.row)"></i>

                    </template>
                </el-table-column>
            </el-table>

        </div>


</template>
<script>
   import {todoListUrl,getHeader} from '../../../env'
   import NewTodoList from './NewTodoList.vue'
    import EditTodoList from './EditTodoList.vue'
    export default {
      components: {
        NewTodoList,
        EditTodoList
      },
      data() {
//            const finish = info;
            return {
                todoList:[],
                heightClass: "auto",
                alignClass: "center",
                todoItem:{},
              showDialogNew: false,
              showDialogEdit:false
            }
        },
        created () {
            this.fetchData()
        },
//        computed:{
//            todoList
//        },
        methods: {
          handleEdit(index, row) {
                console.log(index, row);
                this.todoItem = row;
            this.showDialogEdit = true;
            console.log(this.showDialogEdit);

            },
            handleDelete(index, row) {
                this.$http.delete('http://localhost:8080/todo/'+row.id,{headers: getHeader()}).then(response=>{
                  this.todoList.splice(index,1);
                })
            },
            handleDone(index, row) {

                row.done = true;
                this.$http.put('http://localhost:8080/todo/'+row.id, row,{headers: getHeader()}).then(response=>{
                  if (response.status == 200) {
                    this.todoList[index].done = true;
                  }
                })
                console.log(index,row);

            },

            fetchData(){
                this.$http.get('http://localhost:8080/todo/',{headers: getHeader()}).then(response=> {
                        if (response.status == 200) {
                            this.todoList = response.data
                        }
                    })
            }

        }
    }
</script>
<style scoped>
    /*.el-table .complete {*/
        /*background: #c9e5f5;*/
    /*}*/


</style>
