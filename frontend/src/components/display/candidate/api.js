

import Vue from 'vue'
import VueResource from 'vue-resource'
import participant from "./candidate.js"
Vue.use(VueResource);
export function fetchCandidate(id,cb) {


  console.log(id);
  Vue.http.get("http://localhost:8080/candidate/display/"+id)
    .then(response=>{
      if(response.status === 200){
        cb(null,response.data);
      }else
      cb(new Error("Can't load candidate"+response.status));
    });

  // cb(null,participant);
}
