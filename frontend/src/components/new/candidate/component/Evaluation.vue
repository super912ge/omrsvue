<template>
  <div>
    <el-dialog title="Evaluation" :visible.sync="visible" width="90%">
      <el-card>
        <div>
          <div>
            <div class="block" style="margin: 5px">
              <span class="demonstration">Recruiter</span>
            <el-select v-model="evaluation.recruiter" placeholder="Select" size="mini">
              <el-option v-for="item in systemUsers" :key="item.id" :label = "item.name" :value="item.id"></el-option>
            </el-select>
            </div>
            <div class="block" style="margin: 5px">
              <span class="demonstration">Instrument</span>
              <el-select v-model="instrument.type" placeholder="Select" size="mini" @change="handleInstrumentSelect">
                <el-option v-for="item in instruments" :key="item.id" :label = "item.label" :value="item.id"></el-option>
              </el-select>
            </div>
            <div class="block" style="margin: 5px">
              <span class="demonstration">Audition Date</span>
              <el-date-picker
                v-model="evaluation.auditionDate"
                type="date"
                size="mini"
                placeholder="Select">
              </el-date-picker>
            </div>
            <div class="block" style="margin: 5px">
              <span class="demonstration">Evaluation Date</span>
              <el-date-picker
                v-model="evaluation.evaluationDate"
                type="date"
                size="mini"
                placeholder="Select">
              </el-date-picker>
            </div>
          </div>

          <div style="margin-top: 50px">

            <el-row style="margin-top: 50px">

              <el-col :span="3" ><span class="demonstration">Overall</span></el-col>
              <el-col :span="21">
                <el-col :span="5" :offset="1">
                  <div>
                    <span class="demonstration">Overall</span>
                  </div>
                  <el-slider
                    v-model="instrument.overall.rating"
                    :format-tooltip="formatRating"
                    :step="16.66">
                  </el-slider>
                </el-col>
              </el-col>
            </el-row>

            <el-row style="margin-top: 50px">

              <el-col :span="3" ><span class="demonstration">Reading</span></el-col>
              <el-col :span="21">
                <el-col :span="5" :offset="1" v-for="item in instrument.reading" v-if="item.display===true" :key="item.id">
                  <div>
                    <span class="demonstration">{{item.label}}</span>
                  </div>
                  <el-slider
                    v-model="item.rating"
                    :format-tooltip="formatRating"
                    :step="16.66">
                  </el-slider>
                </el-col>
              </el-col>
            </el-row>

            <el-row style="margin-top: 50px">
              <el-col :span="3" style="margin-top: 40px">
                <span class="demonstration">Musicianship</span>
              </el-col>
              <el-col :span="21">
                <el-col :span="5" :offset="1" v-for="item in instrument.musicianship"
                        v-if="item.display && item.id!==168" :key="item.id">
                  <div>
                    <span class="demonstration">{{item.label}}</span>
                  </div>
                  <el-slider
                    v-model="item.rating"
                    :format-tooltip="formatRating"
                    :step="16.66">
                  </el-slider>
                </el-col>
                <el-col :span="5" :offset="1" v-for="item in instrument.musicianship"
                        v-if="item.display && item.id===168" :key="item.id">
                  <div>
                    <span class="demonstration">{{item.label}}</span>
                  </div>
                  <el-slider
                    v-model="item.rating"
                    :format-tooltip="formatRatingRange"
                    :step="50">
                  </el-slider>
                </el-col>
              </el-col>
            </el-row>

            <el-row style="margin-top: 50px">
              <el-col :span="3" style="margin-top: 35px">
                <span class="demonstration">Style</span>
              </el-col>
              <el-col :span = 21>
                <el-col :span="5" :offset="1" v-for="(item, index) in instrument.style" v-if="item.display" :key="item.id">
                  <div>
                    <span class="demonstration">{{item.label}}</span>
                  </div>
                  <el-slider
                    v-model="instrument.style[index].rating"
                    :format-tooltip="formatRating"
                    :step="16.66">
                  </el-slider>
                </el-col>
              </el-col>
            </el-row>

            <el-row style="margin-top: 50px">
              <el-col :span="3" style="margin-top: 50px">
                <span class="demonstration">Classification</span>
              </el-col>
              <el-col :span="21">
                <el-row>
                  <el-checkbox-group v-model="selectedClassification" @change="handleClassificationChange">
                    <el-checkbox v-for="item in classificationTypes" :name="item.label" :label="item.id"
                                  :key="item.id">{{item.label}}</el-checkbox>
                  </el-checkbox-group>
                </el-row>

                <el-col :span="5" :offset="1" v-for="(item, index) in classification" :key="item.id">
                  <div>
                    <span class="demonstration">{{item.label}}</span>
                  </div>
                  <el-slider
                    v-model="classification[index].rating"
                    :format-tooltip="formatRatingLan"
                    :step="33.3">
                  </el-slider>
                </el-col>
              </el-col>
            </el-row>
          </div>

          <div>
            <el-row>
              <el-col :span="3" style="margin-top: 50px">
                <span class="demonstration">Language</span>
              </el-col>
              <el-col :span="21">
                <el-row style="margin-top: 30px;margin-bottom: 50px">
                  <span class="demonstration">Add Language</span>
                  <el-select v-model="language" size="mini"  value-key="id">
                    <el-option v-for="item in languageOptions" :key="item.id" :value="item" :label="item.label"
                               ></el-option>
                  </el-select>
                  <el-button size="mini" style="padding: 0; border: hidden; margin-left: 10px" icon="el-icon-circle-plus"
                                         @click="addLanguage"></el-button>
                </el-row>

                <el-col :span="5" :offset="1">
                  <div>
                    <span class="demonstration">English</span>
                  </div>
                  <el-slider v-model="languages[0].rating" :format-tooltip="formatRatingLan"
                             :step="33.3">
                  </el-slider>
                </el-col>
                <el-col :span="5" :offset="1">
                  <div>
                    <span class="demonstration">French</span>
                  </div>
                <el-slider v-model="languages[1].rating" :format-tooltip="formatRatingLan"
                           :step="33.3">
                </el-slider>

                </el-col>
                <div>
                <el-col :span="5" :offset="1" v-for="n in languageNo" :key="n">
                  <div>
                    <span>{{languages[n+1].label}}</span>
                  </div>
                <el-slider  v-model="languages[n+1].rating"
                            :step="33.3"
                           :format-tooltip="formatRatingLan" :key="n">
                </el-slider>
                </el-col>
                </div>
              </el-col>

            </el-row>
          </div>
        </div>
      </el-card>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">Cancel</el-button>
        <el-button type="primary" @click="confirm">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>

  import {getHeader} from "../../../../env.js"
  import _ from "lodash"
  const rating = [1.0,2.0,3.0,3.5,4.0,4.5,5.0];
  const ratingId = [63, 67, 71, 73, 76, 77, 78];
  const lanRating = ['none','poor','fair','good'];
  const rangeRating = ['3rd', '2nd', '1st'];
  const rangeRatingId = [143,144,145];
  const lanRatingId = [85,86,87,88];
  const classificationRatingId = [146,147,148,149];
  export default {

    components: {},
    props:['visible'],
    data(){
      return {
        result:{
          instrument : {type : null, ratings : []},
          language:[],
          evaluation:null,
          classification: []
        },
        systemUsers:null,
        instrument: {
          type: null,
          overall:{id: 81 , rating: null},
          reading: [
            {id: 133, label:'Reading', rating: null, display: false},
            {id: 150, label:'Reading Notes', rating: null, display: false},
            {id: 151, label:'Reading Chords', rating: null, display: false},
            {id: 152, label:'Reading Rhythms', rating: null, display: false}
          ],
          musicianship: [
            {id: 153, label:'Sound',rating: null, display: false},
            {id: 154, label:'Time',rating: null, display: false},
            {id: 155, label:'Feel',rating: null, display: false},
            {id: 168, label:'Range',rating: null, display: false},
            {id: 163, label:'Intonation',rating: null, display: false}
          ],
          style: [
            {id: 132, label: 'Repertoire', rating: null, display: false},
            {id: 156, label: 'Contemporary', rating: null, display: false},
            {id: 157, label: 'Latin', rating: null, display: false},
            {id: 158, label: 'Swing', rating: null, display: false},
            {id: 159, label: 'Rockabilly', rating: null, display: false},
            {id: 160, label: 'Ballads', rating: null, display: false},
            {id: 161, label: 'Classical excerpts', rating: null, display: false},
            {id: 162, label: 'Synth skills', rating: null, display: false},
            {id: 164, label: 'Soprano', rating: null, display: false},
            {id: 165, label: 'Alto', rating: null, display: false},
            {id: 166, label: 'Tenor', rating: null, display: false},
            {id: 167, label: 'Baritone', rating: null, display: false}
          ],
        },
        classification:[],
        selectedClassification:[],
        classificationTypes:[
          {id:179, label: "Blues", rating: null, display: false},
          {id:178, label:"Jazz", rating: null, display: false},
          {id:177, label:"Rock", rating: null, display: false},
          {id:176, label:"Mariachi", rating: null, display: false},
          {id:175, label:"Latin", rating: null, display: false},
          {id:174, label:"Country", rating: null, display: false},
          {id:173, label:"Caribbean", rating: null, display: false},
          {id:172, label:"Flamenco", rating: null, display: false},
          {id:171, label:"Classical", rating: null, display: false},
          {id:170, label:"Lounge", rating: null, display: false},
          {id:169, label:"Party", rating: null, display: false},
          {id:140, label:"Show band", rating: null, display: false},
          {id:139, label:"Strolling", rating: null, display: false},
          {id:138, label:"Piano Bar Entertainer", rating: null, display: false},
          {id:137, label:"One Man Band", rating: null, display: false},
          {id:136, label:"Cocktail", rating: null, display: false},
          {id:135, label:"Group Member", rating: null, display: false},
          {id:134, label:"Atrium", rating: null, display: false}
        ],
        instruments:[],
        ratingTypes:[],
        languageOptions:[],
        language:null,
        languageNumber: 2,
        languages:[{id:61,rating:null},{id:62,rating:null}],
        evaluation:{
          recruiter: null,
          auditionDate:null,
          evaluationDate: null,
          comment:null
        }
      }
    },
    methods:{
      formatRating(val){
        let index = Math.floor(val/16.66);
        return rating[index];
      },
      formatRatingLan(val){
        let index = Math.floor(val/33.3);
        return lanRating[index];
      },
      handleInstrumentSelect(key){
        this.$http.get('http://localhost:8080/evaluation/type/subtypes/'+key,{headers: getHeader()}).then(response=> {
          if (response.status === 200) {
            this.ratingTypes = response.data;
           this.displayRatings();
          }
        })
      },
      addLanguage(){
        let language = {
          id: this.language.id,
          label: this.language.label,
          rating:null
        };

        this.languages.push(language);
        this.languageNumber++;
        this.language = null;
      },

      fetchInstrument(){
        this.$http.get('http://localhost:8080/evaluation/type/subtypes/'+2,{headers: getHeader()}).then(response=> {
          if (response.status === 200) {
            this.instruments = response.data;
          }});

      },
      formatRatingRange(val){
        return rangeRating[val/50];
      },
      handleClassificationChange(){

        this.selectedClassification.forEach(item =>{

          if(!_.find(this.classification,['id',item])) {
            this.classification.push(_.find(this.classificationTypes, ['id', item]));
          }
        });

        this.classification.forEach( (item,index,arr) =>{if(!this.selectedClassification.includes(item.id)){
          _.remove(arr, {id:item.id});
        } })
      },
      fetchLanguage(){
        this.$http.get('http://localhost:8080/evaluation/type/subtypes/'+4,{headers: getHeader()}).then(response=> {
          if (response.status === 200) {
            this.languageOptions = response.data;
          }});
      },
      fetchUsers(){
        this.$http.get('http://localhost:8080/user/all',{headers: getHeader()}).then(response=> {
          if (response.status === 200) {
            this.systemUsers = response.data;
          }});
      },
      displayRatings(){

        this.instrument.reading.forEach((item,index, array)=>{

          if(_.isObject( _.find(this.ratingTypes,['id',item.id]))){
            array[index].display = true;
          }
        });
        this.instrument.musicianship.forEach((item,index, array)=>{
          if(_.isObject( _.find(this.ratingTypes,['id',item.id]))){
            array[index].display = true;
          }
        });
        this.instrument.style.forEach((item,index, array)=>{
          if(_.isObject( _.find(this.ratingTypes,['id',item.id]))){
            array[index].display = true;
          }
        });
      },
      confirm(){

          this.result.instrument = {
            instrumentTypeId : this.instrument.type,
            ratings:[]
          };
          this.result.evaluation = this.evaluation;

          this.result.instrument.ratings.push({ratingTypeId: this.instrument.overall.id,
            ratingId: ratingId[Math.floor(this.instrument.overall.rating/16.66)]});

          this.instrument.reading.forEach(item=>{
            if(item.display===false || item.rating===null){}
            else {
              item.rating = ratingId[Math.floor(item.rating/16.66)];
              this.result.instrument.ratings.push({ratingTypeId: item.id, ratingId: item.rating});
            }
          });

        this.instrument.musicianship.forEach(item=>{
          if(item.display===false || item.rating===null){}
          else if( item.id === 168){
            item.rating = rangeRatingId[item.rating/50];
            this.result.instrument.ratings.push({ratingTypeId: item.id, ratingId: item.rating});
          }
          else {
            item.rating = ratingId[Math.floor(item.rating/16.66)];
            this.result.instrument.ratings.push({ratingTypeId: item.id, ratingId: item.rating});
          }
        });

        this.instrument.style.forEach(item=>{
          if(item.display===false || item.rating===null){}
          else {
            item.rating = ratingId[Math.floor(item.rating/16.66)];
            this.result.instrument.ratings.push({ratingTypeId: item.id, ratingId: item.rating});
          }
        });

        this.languages.forEach(item =>{
          item.rating = lanRatingId[Math.floor(item.rating/33.3)];
          this.result.language.push({id:item.id,ratingId:item.rating});
        });

        this.classification.forEach(item =>{
          if(item.rating === null) {
          }else {
            item.rating = classificationRatingId[Math.floor(item.rating/33.3)];
            this.result.classification.push({id: item.id, ratingId: item.rating});
          }
        });
        this.$emit('submit',this.result);
      },
      cancel(){
        this.$emit('cancel');
      }
    },
    computed:{
      languageNo(){
        return this.languageNumber-2;
      }
    },
    created(){
      this.fetchInstrument();
      this.fetchUsers();
      this.fetchLanguage();
    }
  }
</script>
