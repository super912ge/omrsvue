<template>
  <div>

    <el-container style="height: auto">
      <el-aside class="left" style="width: 14%">
        <div>
          <header>
            Quick Links
          </header>
          <ul>
            <li>Essentials</li>
            <li>Availability</li>
            <li>Evaluation</li>
            <li>Identification Documents</li>
            <li>Medical Documents</li>

          </ul>
        </div>

      </el-aside>
      <el-main style="padding: 0;">

        <el-row>

          <el-card style="height: 280px" body-style="height: inherit">

            <el-col :span="3" :offset="1" style="margin-top: 60px; margin-right: 25px">
              <!--<img src='./snoop.jpg' style="width: 80px; height: 80px;border-radius: 50%"/>-->

              <el-upload
                class="avatar-uploader profile "
                action="https://jsonplaceholder.typicode.com/posts/"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="profilePic" :src="profilePic" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon "></i>
              </el-upload>
            </el-col>

            <el-col :span="13" style="margin-top: 20px">

              <el-row style="margin:5px; height: 27.86px; display: flex;align-items: center">
                Name:
                <el-input v-if="editMode" size="mini" placeholder="First Name" v-model="name.firstName"></el-input>
                <el-input v-if="editMode" size="mini" placeholder="Middle Name" v-model="name.otherName"></el-input>
                <el-input v-if="editMode" size="mini" placeholder="Last Name" v-model="name.lastName"></el-input>
                <span v-else>{{fullName}}</span>

              </el-row>
              <el-row style="margin:5px;height: 27.86px; display: flex;align-items: center">
                Pronounce as: <el-input size="mini" placeholder="Pronunciation" v-if="editMode"
                                        v-model="name.pronunciation"  class="pronunciation"></el-input>
                <span v-else>{{name.pronunciation}}</span>
              </el-row>
              <el-row style="margin:5px;height: 27.86px; display: flex;align-items: center">
                Act Name: <el-input size="mini" placeholder="Act Name" v-if="editMode"
                                       v-model="name.actName"  class="pronunciation"></el-input>
                <span v-else>{{name.actName}}</span>
              </el-row>
              <el-row style="margin:5px;height: 27.86px; display: flex;align-items: center">
                Birthday:   <el-date-picker
                v-model="name.birthday"
                v-if = "editMode"
                type="date"
                size="mini"
                style="width: 30%"
                placeholder="Select">
              </el-date-picker>
                <span v-else>{{name.birthday|formatDate}}</span>
              </el-row>

              <el-row style="margin:5px;height: 27.86px; display: flex;align-items: center">
                Gender: <el-radio v-model="name.gender" label="MALE" v-if="editMode">Male</el-radio>
                <el-radio v-model="name.gender" label="FEMALE" v-if="editMode">Female</el-radio>

                <span v-else>{{name.gender}}</span>
              </el-row>
              <el-row style="margin:5px;height: 27.86px; display: flex;align-items: center" v-if="candidateCreated">
                Employee Number: {{candidateId}}
                <div style="margin-left: 10%">

                  <span style="margin:15px 0px;">Evaluate Candidate
                  <el-button size="mini" style="padding: 0; border: hidden" icon="el-icon-circle-plus"
                         @click="evaluationVisible = true"></el-button></span>
                </div>
              </el-row>
            </el-col>
            <el-col :span="4" style="height: 170px; display: flex;align-items: center">
              <el-button size="mini" icon="el-icon-check" v-if="editMode" @click="createCandidate">Save Candidate</el-button>
              <el-button size="mini" icon="el-icon-edit" v-else @click="editCandidate">Edit Candidate</el-button>

            </el-col>
          </el-card>
        </el-row>
        <el-row>
          <div style="margin:5px">
            <evaluation :visible = evaluationVisible @submit = "submitEvaluation" @cancel="evaluationVisible=false"></evaluation>
          </div>
        </el-row>

        <el-row>

            <essentials :candidateId="candidateId" :countries="countries" v-if="candidateId"></essentials>

          <el-col>

            <document :candidateId="candidateId" :countries="countries" v-if="candidateId"></document>
          </el-col>

        </el-row>
      </el-main>
      <el-aside style="width: 16%">
        <div v-if="evaluationRoot">
          <el-tree  style="margin-left: 25px; width: 70%"
                    :props="defaultProps"
                    :data="skills"
                    node-key="id"
                    ref="treeEval">

          </el-tree>
        </div>
      </el-aside>
    </el-container>
  </div>

</template>

<script>
  import Evaluation from './component/Evaluation.vue'
  import Essentials from './component/Essentials.vue'
  import localStorage from "localStorage"
  import {getHeader} from './../../../env.js'
  import ElTree from "../../../../node_modules/element-ui/packages/tree/src/tree.vue";
  import ElRow from "element-ui/packages/row/src/row";
  import ElCol from "element-ui/packages/col/src/col";
  import Document from "../../new/candidate/component/Document.vue";
  export default {
    components: {
      Document,
      Essentials,
      Evaluation},
    data(){
      return{
        profilePic: '',
        evaluationVisible:false,
        name:{
          firstName:'',
          otherName:'',
          lastName: '',
          pronunciation:'',
          actName: '',
          birthday:null,
          gender: 'MALE'
        },
        countries:[],
        evaluationRoot: null,
        candidateCreated: false,
        editMode:true,
        candidateId: null,
        actId:null,
        evaluationId:null,

      }
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.profilePic = URL.createObjectURL(file.raw);
      },
      fetchCountry(){
        let countryOptions = JSON.parse(localStorage.getItem('countryOptions'));
        if (!countryOptions){
          this.$http.get('country/',{headers:getHeader()}).then(response=>{
            if(response.status===200){
              localStorage.setItem('countryOptions', JSON.stringify(response.data));
              this.countries = response.data;
            }
          })
        }
        else this.countries = countryOptions;

      },

      createCandidate(){

        if(!this.candidateCreated) {
          this.$http.post("candidate/create-by-name",
            this.name, {headers: getHeader()}).then(response => {
            if (response.status === 201) {
              this.candidateCreated = true;
              this.editMode = false;
              this.candidateId = response.data;
              this.actId = response.data.actId;
              this.evaluationId = response.data.evaluationId;
            }
          });
        }else {
          this.$http.post("candidate/updateName/"+this.candidateId,
          this.name,{headers: getHeader()}).then(response => {
            if (response.status===201){
              this.editMode = false;
            }
          })
        }
      },

      editCandidate(){
        this.editMode = true;
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('Avatar picture must be JPG format!');
        }
        if (!isLt2M) {
          this.$message.error('Avatar picture size can not exceed 2MB!');
        }
        return isJPG && isLt2M;
      },
      submitEvaluation(val){
        this.$http.post("evaluation/create/"+this.evaluationId,
          val, {headers: getHeader()}).then(response => {
          if (response.status === 201) {
            this.evaluationRoot = response.data;
            this.evaluationVisible = false;
          }
        });
      }
    },
    computed:{
      fullName(){
        return this.name.firstName.trim()+" "+this.name.otherName.trim()+ " "+ this.name.lastName.trim();
      },
      skills(){
        return this.evaluationRoot.children;
      }
    },
    created(){
      this.fetchCountry();
    }
  }
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 50%;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .el-input__inner{
    padding: 0 5px;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 100px;
    margin-top: auto;
    text-align: center;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
  }
  .el-select-dropdown__item{
    font-size: small;
    padding: 0 10px;
    line-height: 14px;
  }

  </style>
<style scoped>


  .el-input{
    width: 20%;
    margin-left: 5px;
  }
  .pronunciation  {
    width: 30%;
  }
  .el-radio{
    margin-left: 10px;
  }
  </style>
