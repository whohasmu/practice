<template>
  <div id="app">
    <Header />
    <Footer />
  </div>
</template>

<script>

// header부분import하기
import Header from './components/common/Header.vue'
// footer부분 import하기
import Footer from './components/common/Footer.vue'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
