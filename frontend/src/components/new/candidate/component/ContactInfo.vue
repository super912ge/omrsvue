<template>

  <div style="margin: 10px 10px" v-show="!deleted">
    <div v-if="confirmed" style="font-size: small">

      <span>{{contactStr}}</span>
      <el-button size="mini"  icon="el-icon-edit" @click="edit"></el-button>
      <el-button size="mini" icon="el-icon-delete" @click="deleteContact" ></el-button>
    </div>
    <div v-else>
      <el-select v-model="contact.type"  placeholder="Select" size="mini">
        <el-option v-for="item in contactFieldLabelOptions" :label="item.label" :value="item.id" :key="item.label"></el-option>
      </el-select>
      <el-input placeholder="Please input" size="mini" style="width:  350px" v-model="contact.text">

        <el-select v-model="contact.method" slot="prepend"
                   placeholder="Select" size="mini">
          <el-option v-for="item in contactFieldTypeOptions"
                     :label="item.label" :value="item.id" :key="item.label"></el-option>
        </el-select>

      </el-input>

      <el-button size="mini" icon="el-icon-check" @click="confirm"></el-button>
      <el-button size="mini" icon="el-icon-delete" @click="deleteContact" ></el-button>
    </div>
  </div>
</template>
<script>
import _ from 'lodash'
import {getHeader} from "../../../../env.js"

  export default {

    props:['candidateId'],

    data(){
      return {
        confirmed: false,
        contact: {
          id: null,
          type: '',
          method: '',
          text: ''
        },
        deleted:false,
        contactType:null,
        contactLabel:null,
        contactFieldTypeOptions: [
          {id:1, label:"Email"},
          {id:2, label: "Phone"},
          {id:3, label: "Mobile"},
          {id:4, label: "URL"},
          {id:5, label: "Skype"},
          {id:6, label: "Fax"}
        ],
        contactFieldLabelOptions: [
          {id: 1, label: "Personal"},
          {id: 2, label: "Parents"},
          {id: 3, label: "Spouse"},
          {id: 4, label: "GF/BF"},
          {id: 5, label: "Friend"},
          {id: 6, label: "Work"},
          {id: 7, label: "Brother"},
          {id: 8, label: "Sister"},
          {id: 9, label: "Child"},
          {id:10, label: "Ship"},
          {id:11, label: "Father"},
          {id:12, label: "Mother"}]
      };
    },
    methods:{
      confirm(){
        if(this.contact.id){
          this.$http.post("http://localhost:8080/contact/update",this.contact,{headers:getHeader()}).then(
            res=>{
              if(res.status===200){

                this.contact.id = res.data.result;
                this.confirmed = true;
                this.$emit('editContact', this.contact);

              }}
          );
        }
        else {
          this.$http.post("http://localhost:8080/contact/create/" + this.candidateId, this.contact, {headers: getHeader()}).then(
            res => {
              if (res.status === 200) {
                this.contact.id = res.data.result;
                this.confirmed = true;
                this.$emit('addContactInfo', this.contact);
              }
            }
          );
        }
      },
      edit(){
        this.confirmed = false;
      },
      deleteContact(){
        if(this.contact.id) {
          this.$http.get("http://localhost:8080/contact/delete/" + this.contact.id, {headers: getHeader()}).then(
            res => {
              if (res.status === 200) this.deleted = true;
            });
        }else this.$emit('deleteContact');
      }
    },
    computed:{
      contactStr(){
        return _.find(this.contactFieldLabelOptions,['id',this.contact.type]).label+
          " "+_.find(this.contactFieldTypeOptions,['id',this.contact.method]).label+": "+this.contact.text;

      }
    }
  }
</script>
