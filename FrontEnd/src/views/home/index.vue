<template>
  <div class="dashboard-container">
    <div class="dashboard-text">欢迎{{ name }}使用本系统，努力开始今天的工作吧！</div>
    <div class="dashboard-text">roles: {{ roles }}</div>
    <div class="dashboard-text">authorities: {{ authorities }}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Home',
  computed: {
    ...mapGetters([
      'name',
      'roles',
      'authorities'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 20px;
    line-height: 46px;
  }
}
</style>
