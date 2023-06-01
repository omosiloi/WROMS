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
import { getSales, getMonthlySales } from '@/api/dashBoard'
export default {
  name: 'Dashboard',
  data() {
    return {
      pieChartData: [],
      pieChartOption: {
        title: {
          text: '2023年热卖菜品分析报表',
          subtext: '根据2023年订单统计',
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
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      },
      barChartData: [],
      barChartOption: {
        title: {
          text: '2023年月度销售金额统计报表',
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
            data: [],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ]
      }
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
      getSales().then((response) => {
        this.pieChartData = response.data
        this.pieChartOption.series[0].data = this.pieChartData
        var pieChart = this.$echarts.init(this.$refs.left)
        this.pieChartOption && pieChart.setOption(this.pieChartOption)
      })
    },
    /** 初始化柱状图 */
    initBarChart() {
      getMonthlySales().then((response) => {
        this.barChartData = response.data.map(obj => obj.monthlySales)
        this.barChartOption.series[0].data = this.barChartData
        var barChart = this.$echarts.init(this.$refs.right)
        this.barChartOption && barChart.setOption(this.barChartOption)
      }) 
    }
  }
}
</script>