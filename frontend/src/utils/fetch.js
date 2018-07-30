import api from 'api'
import {isEmpty} from 'lodash'


 export function fetch( entity, vm, parentIds ){

      let options = JSON.parse(sessionStorage.getItem(entity.name));

      if(!options){

        api.createEntity(entity).getAll().then( response => {

          if (response.status === 200) {

            sessionStorage.setItem(entity.name,JSON.stringify(response.data));

            let filtered = response.data;

            if(!isEmpty(parentIds)){

              filtered = filtered.filter(item => parentIds.includes(item[`${entity.parent}Id`]));
            }

            vm.setData(`${entity.name}s`,filtered);

          }else {
            console.log(`Fetch ${entity.name} failed: ${response.status}`);
          }
        })
      }else {

        vm.setData(`${entity.name}s`, options);
      }
    }


