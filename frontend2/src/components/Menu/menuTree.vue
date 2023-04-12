
<template>
  <v-app>
    <v-btn color="success">Success</v-btn>
    <v-btn color="error">Error</v-btn>
    <v-btn color="warning">Warning</v-btn>
    <v-btn color="info" :to="'/childRoute'" link>Info</v-btn>

    <router-view/>

  </v-app>
</template>

<script>
export default {
  methods:{
    testMethod1() {

    }
  }
}
</script>
