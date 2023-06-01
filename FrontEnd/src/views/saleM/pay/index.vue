<template>
  <div class="app-container">
    <!-- 主展示页面-->
    <el-row>
      <el-col
        :span="24"
        :xs="24"
      >
        <!-- 订单查询 -->  
        <el-form
          ref="queryForm"
          :model="queryParams"
          :inline="true"
          label-width="100px"
        >
          <el-form-item>
            <el-button
              icon="el-icon-refresh"
              size="mini"
              @click="handleReset"
            >重置
            </el-button>
          </el-form-item>
          <el-form-item
            label="订单ID"
            prop="orderId"
          >
            <el-input
              v-model="queryParams.orderId"
              placeholder="请输入订单Id"
              clearable
              size="small"
              @keyup.enter.native="handleQueryByOrderId"
            />
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleQueryByOrderId"
            >搜索
            </el-button>
          </el-form-item>
          <el-form-item 
            label="餐桌名称"
          >
            <el-select v-model="queryParams.tableId" placeholder="选择座位" clearable @change="handleSelectChange">
              <el-option
                v-for="item in tableList"
                :key="item.tableId"
                :value="item.tableId"
                :disabled="item.disabled"
                :label="`${item.tableName} - 容量为：${item.tableCapacity} - ${item.status}`"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handlePay"
            > 支 付 
            </el-button>
            <el-tag 
              v-if="order.status ==='closed'"
              size="small"
              type="success"
            >已付款</el-tag>
            <el-tag 
              v-if="order.status ==='open'"
              size="small"
              type="danger"
            >待支付</el-tag>
            <el-tag 
              v-if="order.status ==='cancelled'"
              size="small"
              type="warning"
            >已取消</el-tag>
          </el-form-item>
          <el-form-item>
            <el-tag 
              size="small"
              type="success"
            >总价：{{ order.totalPrice }}
            </el-tag>
          </el-form-item>
        </el-form>
        <!-- 订单详情表格-->
        <el-table
          v-loading="orderTableLoading "
          :data="orderDetailList "
          height="400"
          style="width: 100%;margin-bottom: 20px;"
        >
          <el-table-column
            label="菜品名称"
            align="center"
            prop="dish.dishName"
            width="100px"
          />
          <el-table-column
            label="菜品价格"
            align="center"
            prop="dish.price"
          />
          <el-table-column
            label="菜品数量"
            align="center"
            prop="quantity"
          />
          <el-table-column
            label="小计"
            align="center"
            prop="totalPrice"
          />
        </el-table>
      </el-col>
    </el-row>
  </div>
</template>
  
  <script>
  /** 导入axios Api */
  import { selectOrderByOrderId, selectAllTable, updateTable, updateOrder, selectOrderByTableIdAndStatus } from '@/api/saleM/pay'
  export default {
    name: 'Pay',
    data() {
      return {
        // 数据查询参数
         queryParams: {
          orderId: null,
          tableId: null,
          status: 'open'
        },
        // 初始化页面
        tableList: [],
        orderTableLoading: false,
        order: {},
        orderDetailList: [],
        table: {},


        // 用于设置el-dialog组件的title属性
        title: '',
        // 是否显示弹出层
        open: false
      }
    },
    created() {
      this.setPage()
    },
    methods: {
      setPage() {
        selectAllTable().then((response) => {
          this.tableList = response.data
          for (let i = 0; i < this.tableList.length; i++) {
            if (this.tableList[i].status !== '待结账') {
              this.tableList[i].disabled = true
            } else {
              this.tableList[i].disabled = false
            }
          }
        })
        this.resetOrder()
      },
      handleQueryByOrderId() {
        if (this.queryParams.orderId == null) {
          this.$message.success('请输入订单Id')
          this.resetQueryParams()
        } else {
          this.orderTableLoading = true
          selectOrderByOrderId(this.queryParams.orderId).then((response) => {
            this.resetOrder()
            this.resetTable()
            this.order = response.data
            if (response.data !== undefined) { 
              this.orderDetailList = response.data.orderDetail
              this.table.tableId = response.data.tableId
              for (let i = 0; i < this.orderDetailList.length; i++) {
                this.orderDetailList[i].totalPrice = this.orderDetailList[i].dish.price * this.orderDetailList[i].quantity
              }
              this.orderTableLoading = false
              this.$message.success('正在对' + this.table.tableName + '的顾客办理付款业务！')
              this.resetQueryParams()
            } else {
              this.$message.error('未查到订单号为' + this.queryParams.orderId + '订单')
              this.resetOrderDetailList()
              this.resetOrder()
              this.orderTableLoading = false
              this.resetQueryParams()
            }
        })
        }
      },
      handlePay() {
        if (this.order.orderId === null) {
          this.$message.error('请先查询相应订单，然后再进行支付操作')
        } else if (this.order.status === 'closed') {
          this.$message.error('该订单已支付，不用重复支付！')
        } else if (this.order.status === 'cancelled') {
          this.$message.error('该订单已取消，不可进行支付！')
        } else {
          this.table.tableId = this.order.tableId
          this.table.tableName = this.order.table.tableName
          this.order.status = 'closed'
          console.log(this.order)
          updateOrder(this.order).then(() => { 
            this.$message.success('已成功支付订单号为' + this.order.orderId + '的订单！')
          })
          updateTable(this.table).then(() => { 
            this.$message.success('请及时清理座位：' + this.table.tableName)
          })
          this.resetOrderDetailList()
        }
      },
      handleSelectChange() {
        if (this.queryParams.tableId !== null && this.queryParams.tableId !== '') {
          selectOrderByTableIdAndStatus(this.queryParams.tableId, this.queryParams.status).then((response) => {
            this.resetOrder()
            this.resetTable()
            this.order = response.data[0]
            this.orderDetailList = response.data[0].orderDetail
            this.table = response.data[0].table
            for (let i = 0; i < this.orderDetailList.length; i++) {
              this.orderDetailList[i].totalPrice = this.orderDetailList[i].dish.price * this.orderDetailList[i].quantity
            }
            this.orderTableLoading = false
            this.$message.success('正在对' + this.table.tableName + '的顾客办理付款业务！')
        })
        }
      },
      handleReset() {
        this.resetQueryParams()
        this.resetOrder()
        this.resetOrderDetailList()
        this.setPage()
      },
      resetQueryParams() {
        this.queryParams = {
          orderId: null,
          tableId: null,
          status: 'open'
        }
      },
      resetOrder() {
        this.order = {
          orderId: null,
          status: 'closed'
        }
      },
      resetTable() {
        this.table = {
          tableId: null,
          tableName: null,
          tableCapacity: null,
          status: '待清理'
        }
      },
      resetOrderDetailList() {
        this.orderDetailList = []
      }
    }
  }
  </script>