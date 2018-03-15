<template>
  <el-card>
    <div slot="header" class="clearfix">
      <span>{{name}}</span>
    </div>
    <div style="margin: 10px 5px ">
      <span  class="demonstration" style="font-size: small; margin-right: 10px" >Display Expired Document</span>
      <el-switch v-model="displayExpired">
      </el-switch>
    </div>

    <div>
        <el-card v-for="identity in identities" :key="identity.id" class="box-card"
                 v-show="displayExpired || new Date(identity.expiryDate).getTime() > new Date().getTime()">
          <div slot="header" class="small-header clearfix">
            <span>{{identity.name}}</span>
          </div>
          <div>
            <span class="content">Expiry Date: {{identity.expiryDate}}</span><br>
            <span class="content" >Country: {{ identity.country}}</span>

          </div>

        </el-card>
    </div>
  </el-card>

</template>
<script>

  import ElCard from "../../../../../node_modules/element-ui/packages/card/src/main.vue";

  export default {
    components: {ElCard},
    props:['identities','name'],
    data(){
      return {
        displayExpired:true
      }
    },
    methods:{
      expire(date){

        let ex = date > new Date();
        console.log('ex', ex, new Date(date), new Date())
        return this.displayExpired || new Date(date).getTime() > new Date().getTime();
      }
    }
  }
</script>
<style >

  .box-card{
    width: 200px;
    display: inline-block;
    margin-right: 10px;

  }

  .box-card .el-card__header{
    padding: 10px 15px;
    font-size: 75%;
  }
  .content {

    margin: 10px;
    font-size: 70%;
  }

</style>
