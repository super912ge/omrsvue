<template>
  <el-card>


    <div slot="header" class="clearfix">
      <span>Document</span>
    </div>
    <el-select v-model="doc.name" size="mini" @change="fetchDocumentType">
      <el-option v-for="item in documentsOptions" :value="item.name" :label="item.label" :key="item.name"></el-option>
    </el-select>

    <el-button size="mini" style="padding: 0; border: hidden" :disabled="disableAdd" icon="el-icon-circle-plus" @click="addDocument"></el-button>

    <add-edit-document :doc="doc" :documentType="documentType" :countries="countries" :index="index"
                       :dialogVisible="dialogVisible"
                       v-if="dialogVisible"
                       @confirm="confirm"
                       @cancel="dialogVisible=false;doc={name:null}"
    ></add-edit-document>
    <el-row>
      <p>Added Documents:</p>

      <display-document v-for="(item, index) in addedDocuments"
                        :doc="item" :index="index"
                        @deleteDoc="deleteDoc"
                        @editDoc="editDoc"
                        :key="index"
      ></display-document>
    </el-row>


  </el-card>


</template>

<script>

  import {getHeader} from "../../../../env.js"
  import AddEditDocument from './AddEditDocument.vue'
  import DisplayDocument from './DisplayDocument.vue'
  import _ from 'lodash'
  export default {

    components: {

      DisplayDocument,
      AddEditDocument},
    props:['countries','candidateId'],
    data(){

      return{

        doc:{name:null,type:null,country:null,expiryDate:null,number:null,fitForDuty:false,
          issuingPost:null,issuingDate:null,formDate:null,comment:null, countryItem:{id:null,name:null},
          typeItem:{id:null,name:null},multiplicity:null},
        addedDocuments:[],
        index:null,
        documentsOptions:[{label:'Seaman\'s Book',name:'seamansBook'},{label:'Medical Document',name:'medical'},
          {label:'Passport',name:'passport'},{label:'Visa',name:'visa'},{label:'Certificate',name:'certificate'}],
        documentType:null,
        dialogVisible:false
      }
    },
    methods:{
      addDocument(){

        console.log(this.documentType);
        this.dialogVisible=true;
      },
      fetchDocumentType(){
        let name = this.doc.name;

        if(name==='seamansBook'||name==='passport') {

          return;
        }

        this.$http.get("http://localhost:8080/"+name+"Type/",{headers:getHeader()}).then(res=>{
          if(res.status===200) {
            this.documentType = res.data;
          }
        });
      },

      confirm(document,index){

        console.log('commit', document,index);
        let name = document.name;
        if(document.id){

          this.$http.post('http://localhost:8080/'+document.name+"/update",document,{headers:getHeader()}).then(
            res=>{
              if(res.status===200) {
                document.id = res.data.result;
                this.addedDocuments[index]=document;
                this.dialogVisible = false;
                this.doc = {name:null,type:null,country:null,expiryDate:null,number:null,fitForDuty:false,
                  issuingPost:null,issuingDate:null,formDate:null,comment:null, countryItem:{id:null,name:null},
                  typeItem:{id:null,name:null},multiplicity:null}
              }
            })
        }else {
          this.$http.post('http://localhost:8080/' + name + "/create/"+this.candidateId, document, {headers: getHeader()}).then(
            res => {
              if (res.status === 200) {
                document.id = res.data.result;
                this.addedDocuments.push(document);
                this.doc = {name:null,type:null,country:null,expiryDate:null,number:null,fitForDuty:false,
                  issuingPost:null,issuingDate:null,formDate:null,comment:null,countryItem:{id:null,name:null},
                  typeItem:{id:null,name:null},multiplicity:null};
                this.dialogVisible = false;
              }
            })
        }
      },
      editDoc(index){
        this.doc = this.addedDocuments[index];
        this.index = index;
        this.dialogVisible = true;

      },
      deleteDoc(index){
        let doc = this.addedDocuments[index];
        this.$http.get('http://localhost:8080/'+doc.name+"/delete/"+doc.id,{headers:getHeader()}).then(
          res=>{
            if(res.status===200) {
              this.addedDocuments.splice(index,1);
            }
          })
      }
    },
    computed:{
      disableAdd(){
        if(this.doc.name) return false;
        else return true;
      }
    }
  }

</script>
