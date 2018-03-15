<template>
<div>

  <span>Searching Result</span>

  <ul>
    <li li v-for="candidate in candidates">
      {{candidate.name}}
    </li>
  </ul>
</div>

</template>

<script>
  import {mapGetters} from 'vuex';

  export default {

    computed:{
      ...mapGetters({ candidates:'searchResults'})
    }

  }
</script>
