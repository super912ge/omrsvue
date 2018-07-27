<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>Work History </span>
      </div>

      <!--({{contracts.length}})-->
      <div style=" margin-bottom: 20px">
      <span class="demonstration" style="font-size: small;">Proship Contracts</span>
      </div>
    <el-table
      :data="psContracts"
      style="width: 95%;margin-left: auto;word-break: keep-all"
      size="small"
      border

    align="center">
      <el-table-column
        label="ID"
        prop="id"
        width="52">
      </el-table-column>
      <el-table-column
        label="Job#"
        prop="jobNumber"
        width="50">
      </el-table-column>

      <el-table-column
        label="Gig"
        width="150"
      >
        <template slot-scope="props" >
          <div style="min-height: 100px; height: auto" v-for="shard in props.row.shards">
          <p><b>Client:</b> {{shard.gig.client.name }}</p>
          <p><b>Venue:</b> {{ shard.gig.venueBase.name }}</p>
          <p><b>Room:</b> {{ shard.gig.room.name }}</p>
          <p><b>Type:</b> {{ shard.gig.gigType.name}}</p>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="Start"
        width="80"
        >
        <template slot-scope="props" >
          <div v-for="shard in props.row.shards" class="shard">
            {{shard.startDate}}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="End"
        width="80"
        >
        <template  slot-scope="props" style="border-bottom: solid black 1px" >
          <div  v-for="shard in props.row.shards" class="shard">
            {{shard.endDate}}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        width="70"
        label="Rank"
      >
        <template  slot-scope="props" >
          <div v-for="shard in props.row.shards" class="shard">
            {{shard.rank}}
          </div>

        </template>
      </el-table-column>
      <el-table-column
        label="Salary"
      width="80">
        <template  slot-scope="props" >
          <div  class="shard"  v-for="shard in props.row.shards">

            {{shard.salary}}
          </div>
          </template>

      </el-table-column>
      <el-table-column
        label="Log">
        <template slot-scope="props">


          <p  v-for="event in props.row.events">
            {{ event.name }}   <span v-if="event.name === 'Embark' || event.name === 'Disembark'">
              {{event.previousStatus}} to {{event.currentStatus}}
          </span>
          </p>

        </template>

      </el-table-column>
    </el-table>
    </el-card>
  </div>
</template>
<script>


  export default {

    props: {
      psContracts: {
        type: Array,
        default: ()=>[]
      }
    }
  }
</script>
<style>

  .cell p{
    margin: 0;
  }

  .shard{
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100px;
    word-break: keep-all;
  }
</style>
