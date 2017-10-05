<template>
  <div id="app">
    <router-view></router-view>
  </div>

</template>

<script>
    import AppHeader  from './components/AppHeader.vue'
    import AppNav from './components/AppNav.vue'

    export default {
        name: 'app',
        components: {
            AppHeader,
            AppNav
        }
    }
</script>