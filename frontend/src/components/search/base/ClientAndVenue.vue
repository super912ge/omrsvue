<template>
  <div>
    <div style="margin: 15px 0;"></div>
    <el-collapse style="width:60%  ;margin-left: 25px"  @item-click="fetchClient">
      <el-collapse-item title="Client" style="font-size: large" >
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="criteria.experience.clientIds" style="height: 200px; overflow-y: scroll;">
          <el-checkbox v-for="client in clients" :label="client.id" :key="client.id"style="margin-left: 5px; width: 200px"
                       @change="handleExperienceChange" >{{client.name}}</el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
    <div style="margin-top: 15px"></div>
    <el-collapse style="width: 60%;margin-left: 25px "  @item-click="fetchVenue">
      <el-collapse-item title="Venue">
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="criteria.experience.venueIds" style="height: 200px; overflow-y:scroll">
          <el-checkbox v-for="venue in venues" :label="venue.id" :key="venue.id"
                       style="margin-left: 5px; width: 200px"
                       @change="handleExperienceChange">{{venue.name}}  ------  {{venue.clientCode}}
          </el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>

  import {venue,client} from "../../../data/entity";

  import localStorage from 'localStorage'

  export default {

    data(){
      return {

        clients:[],
        isIndeterminateClient: false,
        venues:[],
        isIndeterminateVenue: false
      }
    },

    methods:{

      fetchClient(){

        if(! this.clients){

          let clientOptions = JSON.parse(localStorage.getItem("clientOptions"));

          if(!clientOptions){
            this.$http.get('http://localhost:8088/client/',{headers: getHeader()}).then(response=> {

              if (response.status === 200) {

                this.clients = response.data;

                localStorage.setItem('clientOptions',JSON.stringify(response.data))
              }
            })
          }else {

            this.clients = clientOptions;
          }
        }
      },

      fetchVenue(){

        let venueOptions = JSON.parse(localStorage.getItem("venueOptions"));

        if(!venueOptions){

          this.$http.get('http://localhost:8088/venue/',{headers: getHeader()}).then(response=> {

            if (response.status === 200) {

              venueOptions = response.data;

              localStorage.setItem('venueOptions',JSON.stringify(venueOptions))
            }
          })
        }

        if(!this.criteria.experience.clientIds|| this.criteria.experience.clientIds.length===0) {

          this.venues = venueOptions;
        }
        else {

          this.venues = venueOptions.filter(venue => this.criteria.experience.clientIds.includes(venue.clientId));
        }
      }
    }
  }
</script>
