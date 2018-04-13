<template>

  <el-card  style="margin-top: 15px;height: 467px">
    <div slot="header" class="clearfix">
      <span>Essentials</span>
      <!--<el-button style="float: right; padding: 3px 0" type="text">Operation button</el-button>-->
    </div>
    <div class="text item">

        <ul style=" list-style-type: none; font-size: 90%;">
          <li><span>Main Phone: </span> {{candidate.mainPhone}}</li>
          <li><span>Main Mobile: </span>{{candidate.mainMobile}}</li>
          <li><span>Primary Email: </span>{{candidate.email}}</li>
          <li><span>Main Address: </span><p v-for="item in candidate.mainAddress">{{item}}</p></li>
          <li><span>Residency: </span>{{residency}}</li>
          <li><span>Citizenship: </span>{{citizenship}}</li>
          <li><span>Date of Birth: </span>{{candidate.birthday}}</li>
          <li><span>Home Airport: </span>{{airport}}</li>
          <li><span>Medical Conditions: </span>{{candidate.medicalCondition}}</li>
          <li><span>Criminal Convictions: </span>{{candidate.criminalConviction}}</li>
          <li><span>Employee Number: </span>{{candidate.employeeNumber}}</li>
        </ul>
    </div>
  </el-card>
<!--</div>-->
</template>
<script>

  import ElCol from "element-ui/packages/col/src/col";
  import _ from "lodash"
  export default {
    components: {ElCol},
    props: ['candidate'],
    computed:{
      airport(){
        if(this.candidate.homeAirports)
          return _.map(this.candidate.homeAirports,'name').join(', ')
        return null;
      },
      residency(){
        if (this.candidate.residencys)
        return this.candidate.residencys.join();
        return null;
      },
      citizenship(){
        if(this.candidate.citizenships)
        return this.candidate.citizenships.join();
        return null;
      }
    }

  }
</script>


<style scoped>

  ul li{
    margin-bottom: 10px;
  }
  ul li span {
    width: 40%;
  }
</style>
