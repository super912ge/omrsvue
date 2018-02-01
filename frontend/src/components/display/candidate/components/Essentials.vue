<template>

  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>Essentials</span>
      <!--<el-button style="float: right; padding: 3px 0" type="text">Operation button</el-button>-->
    </div>
    <div class="text item">

        <ul>
          <li><span>Main Phone: </span> {{candidate.phone}}</li>
          <li><span>Main Mobile: </span>{{candidate.mobile}}</li>
          <li><span>Primary Email: </span>{{candidate.email}}</li>
          <li><span>Main Address</span>{{candidate.address}}</li>
          <li><span>Residency</span>{{candidate.residency}}</li>
          <li><span>Citizenship</span>{{candidate.citizenship}}</li>
          <li><span>Date of Birth</span>{{candidate.birthday}}</li>
          <li><span>Home Airport</span>{{candidate.airport}}</li>
          <li><span>Medical Conditions</span>{{candidate.medicalCondition}}</li>
          <li><span>Criminal Convictions</span>{{candidate.criminalConviction}}</li>
          <li><span>Employee Number</span>{{candidate.employeeNumber}}</li>
        </ul>
    </div>
  </el-card>

</template>
<script>

  import ElCol from "element-ui/packages/col/src/col";

  export default {
    components: {ElCol},
    props: 'candidate'

  }
</script>
<style scoped>
  ul li span {
    width: 40%;
  }
</style>
