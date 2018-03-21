<template>

  <el-dialog
    :title="methodName+' '+document.name"
    :visible.sync="dialogVisible"
    width="30%"
    @close="cancel"
   >
    <template v-if="typeDisplay">Type:
    <el-select v-model="document.typeItem" placeholder="Select" size="mini" value-key="id">
      <el-option v-for="item in documentTypeOptions" :key="item.id" :label = "item.name" :value="item" ></el-option>
    </el-select>
      <div v-if="document.name==='medical'">
        <el-checkbox v-model="document.fitForDuty" > Fit for duty</el-checkbox>
      </div>
    </template>

    <template v-if= "countryDisplay">Country:
      <el-select v-model="document.countryItem" size="mini" placeholder="Country" value-key="id">
        <el-option v-for="item in countryOptions" :key="item.id" :label = "item.name" :value="item"></el-option>
      </el-select>
    </template>
    <div>
    Expiry Date:   <el-date-picker
    v-model="document.expiryDate" type="date" size="mini" width="120" placeholder="Select">
  </el-date-picker>
    </div><div>
    Issuing Date:   <el-date-picker
    v-model="document.issuingDate" type="date" size="mini" width="120" placeholder="Select">
  </el-date-picker>
  </div>
    <div>
      Form Date:   <el-date-picker
      v-model="document.formDate" type="date" size="mini" width="120" placeholder="Select">
      </el-date-picker>
    </div>

    <span>Document Number:</span><el-input v-model="document.number" size="mini"></el-input>

    <div v-if="document.name==='visa'"><span>Multiplicity:</span><el-input v-model="document.multiplicity"
                                                                           size="mini"></el-input></div>
    <span>Issuing Post: </span><el-input v-model="document.issuingPost" size="mini"></el-input>
    <div>Comment:
    <el-input type="textarea" :rows="2" placeholder="Please input" v-model="document.comment"></el-input>
    </div>
    <span slot="footer" class="dialog-footer">
        <el-button @click="cancel" size="mini">Cancel</el-button>
        <el-button type="primary" @click="confirm" size="mini">Confirm</el-button>
      </span>
  </el-dialog>

</template>

<script>
  import ElCheckbox from "../../../../../node_modules/element-ui/packages/checkbox/src/checkbox.vue";

  const showCountry = ['passport','seamansBook','certificate'];
  const showType = ['medical','visa','certificate'];
  import _ from "lodash"
  export default {
    components: {ElCheckbox},
    props:['dialogVisible', 'doc','documentType','index','countries'],
    data(){
      return{
        document: this.doc,
        documentTypeOptions: _.isEmpty(this.documentType)? []:this.documentType,
        countryOptions: _.isEmpty(this.countries)?[]: this.countries
      }
    },
    methods:{
      confirm(){
        console.log(this.index, this.document);
        if(this.document.countryItem.id||this.document.typeItem.id) {
          if (this.document.countryItem.id) {
            this.document.country = this.document.countryItem.id;
          }
          if (this.document.typeItem.id) {
            this.document.type = this.document.typeItem.id;
          }
          this.documentTypeOptions = [];
          this.$emit('confirm', this.document, this.index);
        }
      },
      cancel(){
        this.country = {id:null, name:null};
        this.type = {id:null, name:null};
        this.documentTypeOptions = [];
        this.$emit('cancel');
      }
    },
    computed:{
      methodName(){
        if(this.document.id){
          return 'Edit';
        }else return 'Add';
      },
      countryDisplay(){
        return (showCountry.indexOf(this.document.name)!==-1)
      },
      typeDisplay(){

        return (showType.indexOf(this.document.name)!==-1)
      }
    }
  }

</script>
