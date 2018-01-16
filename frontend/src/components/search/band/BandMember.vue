<template>

  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>{{member.name}}</span>
      <el-button style="float: right; padding: 2px 0" type="text">Edit</el-button>
    </div>
    <div class="text item">
      <p style="margin: 3px"> {{skills}}</p>

    </div>



  </el-card>

</template>

<script>
import DisplayCandidateSkill from '../candidate/DisplayCandidateSkill.vue'
import ElTree from "../../../../node_modules/element-ui/packages/tree/src/tree.vue";
import _ from 'lodash'
  export default {

    components: {ElTree},
    props: ['member'],
    component:{
      DisplayCandidateSkill
    },
    methods:{
      traverse(obj,result) {

        console.log('obj', obj);
        _.forOwn(obj, (val, key) => {
          if(_.isArray(val)) {
            val.forEach(el => {
              this.traverse(el,result);
            });
          } else if (_.isObject(val)) {
            this.traverse(val,result);
          } else {
            console.log('result2', result);
            if (key === 'discriminatorId' && val === 1 && obj['label']!=='No instrument')
            {
              result.skills += obj['label'] ;
              console.log('result', result);
              obj['children'].forEach(el => {
                if(el['discriminatorId']===5){
                  result.skills += ' P ';
                }
              })
              result.skills += ' * ';
            }
          }
        });
      },
    },
    computed:{
      skills(){
        let str = {skills : ''};
        this.traverse(this.member.evaluation,str );

        return str.skills;
      }
    }
  }
</script>
<style scoped>
  .text {
    font-size: 12px;
  }

  .item {
    margin-bottom: 3px;
  }

  .el-card header {
    padding: 10px;
    border-bottom: 1px solid #d1dbe5;
    box-sizing: border-box;
  }
  .el-card body {
    padding: 10px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 40%;
  }
</style>
