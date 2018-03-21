<template>
  <el-card :key="doc.id" class="box-card">
    <div slot="header" class="small-header clearfix">
      <span>{{doc.name.toUpperCase()}}</span>

      <el-button size="mini" icon="el-icon-edit" @click="editDoc" style="float: right;"></el-button>
      <el-button size="mini" icon="el-icon-delete" @click="deleteDoc" style="float: right;" ></el-button>
    </div>
    <div>


      <div v-if="edit">
          Issuing Date: <el-date-picker
          v-model="updated.issuingDate" type="date" size="mini" width="120" placeholder="Select">
        </el-date-picker>
      </div>
      <div v-if="">
        <span class="content">Issuing Date: {{doc.issuingDate | formatDate}}</span><br>
      </div>
      <span class="content">Expiry Date: {{doc.expiryDate | formatDate}}</span><br>
      <span class="content">Issuing Post: {{doc.issuingPost}}</span><br>
      <div v-if="doc.country">
      <span class="content" >Country: {{ doc.countryItem.name}}</span>
      </div>
      <div v-if="doc.type">
        <span class="content" >Type: {{ doc.typeItem.name}}</span>
      </div>

      <el-tooltip class="item" effect="light" :content="doc.comment" placement="bottom">
        <el-button size="mini">Show Comment</el-button>
      </el-tooltip>
    </div>
  </el-card>
</template>
<script>

  export default {

    data(){
      return {
        edit: false,
        update: null
      }

    },
    props:['doc','index'],
    methods:{
      editDoc(){
        this.$emit('editDoc',this.index);
      },
      deleteDoc(){
        this.$emit('deleteDoc',this.index);
      }
    }
  }
</script>

<style scoped>
  .el-button--mini, .el-button--mini.is-round{
    padding: 5px;
  }

  .box-card .el-card__header{
    padding:3px 10px;
  }
</style>
