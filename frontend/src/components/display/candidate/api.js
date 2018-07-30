

import Vue from 'vue'
import VueResource from 'vue-resource'
import participant from "./candidate.js"
export function fetchCandidate(id,cb) {


  console.log(id);
  Vue.http.get("candidate/display/"+id)
    .then(response=>{
      if(response.status === 200){
        cb(null,response.data);
      }else
      cb(new Error("Can't load candidate"+response.status));
    });

  // cb(null,participant);
}


export function fetchNotes(id,cb) {


  Vue.http.get("participantEvent/candidate/"+id)
    .then(response=>{
      if(response.status === 200){
        cb(null,response.data);
      }else
        cb(new Error("Can't load candidate's notes"+response.status));
    });

  // cb(null,participant);
}
