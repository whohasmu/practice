<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<div id="app">
<p>{{ title }}</p>
</div>

<script>
new Vue({
  el: '#app',
  data: {
    title: '안녕 VueJS!'
  }
})
</script>
