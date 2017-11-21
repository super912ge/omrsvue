<template>

        <div>
            <h1 style="margin-left: 25%">To do List</h1>
            <el-table
                      :align = "alignClass"
                    :data="todoList"
                    style="width: 66%; margin-left: 25%">
                <el-table-column type="expand">
                    <template scope="props">
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
                    <template scope="props">
                        <i v-bind:class="{'el-icon-circle-check' : props.row.done}"></i>
                        <i v-bind:class="{'el-icon-check' : !props.row.done}" @click="handleDone(props.$index, props.row)"></i>
                    </template>

                </el-table-column>
                <el-table-column
                        label="Operations">
                    <template scope="props">
                        <router-link to="/"> <i class="el-icon-edit"
                                                size="small"
                                                @click="handleEdit(props.$index, props.row)" style="margin-right: 10px"></i></router-link>

                        <router-link to="">
                            <i class="el-icon-delete"
                               size="small"
                               type="danger"
                               @click="handleDelete(props.$index, scope.row)"></i>
                        </router-link>
                    </template>
                </el-table-column>
            </el-table>

        </div>


</template>
<script>
    import todoList from '../../../data/todoList'
    export default {
        data() {
//            const finish = info;
            return {
                todoList,
                heightClass: "auto",
                alignClass: "center"
            }
        },
//        computed:{
//            todoList
//        },
        methods: {
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            },
            handleDone(index, row) {
                todoList[index].done = true;
                console.log(index,row);

            }
        }
    }
</script>
<style scoped>
    /*.el-table .complete {*/
        /*background: #c9e5f5;*/
    /*}*/


</style>