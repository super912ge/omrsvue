<template>
  <div>
    <el-table
      ref="bands"
      :data="resultList"
      style="width: 100%"
      @expand="displayMembers"
    >
      <el-table-column type="expand">
        <template slot-scope="props" >
          <p>{{props.row.members.length}} members in {{props.row.name}}</p>
          <band-member v-for="member in props.row.members" :key = "member.id" :member="member"></band-member>

        </template>
      </el-table-column>
      <el-table-column
        label="Id"
        prop="id"
      >
      </el-table-column>
      <el-table-column
        label="Name"
        prop="name">
      </el-table-column>

      <el-table-column
        label="Type"
        prop="type">
      </el-table-column>

    </el-table>

    <div style="margin-top: 15px"></div>
    <el-pagination layout="prev, pager, next" :total="pageNumber" @current-change="handlePageChange">
    </el-pagination>
  </div>

</template>
<script>
  import _ from "lodash";
  import {getHeader} from '../../../env'
  import BandMember from './BandMember.vue'
  export default {

    components: {BandMember},
    props:['resultList','pageNumber'],
    methods: {
      handleClick() {
        console.log('click');
      },
      handlePageChange(val){
        console.log('handlePageChange',val)
        this.$emit("pageChange",val)
      },
      displayMembers(row, expanded){

        var $vm = this;
        this.$http.get('http://localhost:8080/band/members/'+row.id,{headers: getHeader()}).then(response=> {

          if (response.status === 200) {
            $vm.$set(row, 'members', response.data);

            console.log(row.members);
          }
        })

      }
    },
    data() {
      return { members:[],
        allMembers:[]

      }
    },
    computed:{
      membersMap(){
        return this.members;
      }
    }
  }
</script>
