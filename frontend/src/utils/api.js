import axios from 'axios'
import {getHeader} from "../env";

class API {

  constructor({ url }){
    this.url = url? 'http//localhost:8080/api':url;
    this.endpoints = {}
  }
  /**
   * Create and store a single entity's endpoints
   * @param {A entity Object} entity
   */
  createEntity(entity) {
    this.endpoints[entity.name] = this.createBasicCRUDEndpoints(entity)
  }

  createEntities(arrayOfEntity) {
    arrayOfEntity.forEach(this.createEntity.bind(this))
  }


  /**
   * Create the basic endpoints handlers for CRUD operations
   * @param {A entity Object} entity
   */
  createBasicCRUDEndpoints( {url} ) {

    let endpoints = {};

    const resourceURL = `${this.url}/${url}`;

    endpoints.getAll = () => axios.get(resourceURL+'/all', { headers:getHeader()});

    endpoints.getOne = ({ id }) =>  axios.get(`${resourceURL}/${id}`,{ headers:getHeader()});

    endpoints.create = (toCreate,id) =>  axios.post(`${resourceURL}/${id}`, toCreate,{ headers:getHeader()});

    endpoints.update = (toUpdate) => axios.post(`${resourceURL}/update/${toUpdate.id}`, toUpdate,{ headers:getHeader()});

    endpoints.delete = ({ id }) => axios.get(`${resourceURL}/delete/${id}`,{ headers:getHeader()});

    endpoints.search = (condition) => axios.post(`${resourceURL}/search`,condition,{headers:getHeader()});

    endpoints.display = (ids) => axios.post(`${resourceURL}/display`,ids,{headers:getHeader()});

    return endpoints

  }

}

export default API
