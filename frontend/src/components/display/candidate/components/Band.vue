<template>
  <el-card>
    <div slot="header" class="clearfix">
      <span>Band</span>
    </div>
    <el-table
      ref="bands"
      :data="band"
      size="small"
      style="width: 70%"
      @expand="displayMembers"
    >
      <el-table-column type="expand">
        <template slot-scope="props" >
          <p>{{props.row.members.length}} members in {{props.row.name}}</p>
          <band-member v-for="member in props.row.members" :key = "member.id" :member="member"></band-member>

        </template>
      </el-table-column>
      <!--<el-table-column-->
        <!--label="Id"-->
        <!--prop="id"-->
      <!--&gt;-->
      <!--</el-table-column>-->
      <el-table-column
        label="Name"
        prop="name">
      </el-table-column>

      <el-table-column
        label="Type"
        prop="type">
      </el-table-column>

    </el-table>


  </el-card>
</template>
<script>
export default {
  props:['band'],
  methods:{
    displayMembers(row, expanded){

      var $vm = this;
      this.$http.get('http://localhost:8080/band/members/'+row.id,{headers: getHeader()}).then(response=> {

        if (response.status === 200) {
          $vm.$set(row, 'members', response.data);

          console.log(row.members);
        }
      })
    }
  }
}

</script>
