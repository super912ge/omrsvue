<template>

  <el-tree
    style="font-size: 50%"
    :data="skills"
    :props="defaultProps"
  >
  </el-tree>
</template>

<script>
  import _ from "lodash"
  export default {
    props:['evaluation'],
    methods: {
      traverse(obj,result) {

        _.forOwn(obj, (val, key) => {
          if (_.isArray(val)) {
            val.forEach(el => {
              this.traverse(el,result);
            });
          } else if (_.isObject(val)) {
            this.traverse(val,result);
          } else {
            if (key === 'discriminatorId' && val === 1 && obj['label']!==
              'No instrument') result.push(obj);
          }
        });
      },
    },
    computed:{
      skills(){
        let skills = [];
        this.traverse(this.evaluation,skills);

        console.log('skill', skills);
        return skills
      }
    },
    data(){
      return {
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    }


  }
</script>
<style scoped="">
  .el-tree{
    border: hidden;
    font-size: 6px;
  }
  .el-tree-node__label {
    font-size: small;
    height: 20px;
  }

</style>
