<template>
  <div class="app-container">
    <el-row>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>产品销量饼状图</span>
          </div>
          <div ref="left" style="height: 480px; margin: 0 auto;" />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>月度销售额柱状图</span>
          </div>
          <div ref="right" style="height: 480px; margin: 0 auto;" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Dashboard',
  data() {
    return {
    }
  },
  created() {},
  mounted() {
    this.initPieChart()
    this.initBarChart()
  },
  methods: {
    /** 初始化饼状图 */
    initPieChart() {
      var pieChart = this.$echarts.init(this.$refs.left)
      var option = {
        title: {
          text: '热卖商品分析报表',
          subtext: '按产品类型统计',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '占所有产品的',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 48, name: '钢笔' },
              { value: 60, name: '铅笔盒' },
              { value: 73, name: '日记本' },
              { value: 58, name: '毛巾' },
              { value: 48, name: '香波' },
              { value: 30, name: '拖鞋' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      option && pieChart.setOption(option)
    },
    /** 初始化柱状图 */
    initBarChart() {
      var pieChart = this.$echarts.init(this.$refs.right)
      var option = {
        title: {
          text: '月度销售额统计报表',
          subtext: '按月份统计',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [120, 200, 150, 80, 70, 110, 150, 180, 90, 100, 140, 160],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ]
      }
      option && pieChart.setOption(option)
    }
  }
}
</script>