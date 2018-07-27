
import {getHeader} from '../../env'
const state = {
  searchResult:[],
  savedCandidate:[]

};

const getters = {

}

const mutations={
  'SAVE_CANDIDATE'(state,{id, name}){
    state.savedCandidate.push({id, name});
  },
  'SET_SEARCH_RESULT'(state,{list}){
    this.$http.post("candidate/names",list,{header:getHeader}).then(
      response=>{
        if(response.status===200){
          state.searchResult = response.data;
        }
      }
    );
  }
};

const actions = {
  setSearchResult({commit},list){
    commit('SET_SEARCH_RESULT',list);
  }
};

export default {
  state,
  getters,
  actions,
  mutations
}
