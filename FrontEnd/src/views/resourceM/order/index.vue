<template>
  <div class="app-container">
    <!-- 主展示页面-->
    <el-row :gutter="10">
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
          <el-form-item>
            <el-button
              icon="el-icon-refresh"
              size="mini"
              @click="resetQuery"
            >重置
            </el-button>
          </el-form-item>
        </el-form>
        <!-- order表格 -->
        <el-table
          v-loading="orderTableLoading "
          :data="orderList "
          height="400"
          style="width: 100%;margin-bottom: 20px;"
        >
          <el-table-column
            label="订单ID"
            align="center"
            prop="orderId"
            width="100px"
          />
          <el-table-column
            label="座位名称"
            align="center"
            prop="table.tableName"
          />
          <el-table-column
            label="订单时间"
            align="center"
            prop="orderTime"
          />
          <el-table-column
            label="总价格"
            align="center"
            prop="totalPrice"
          />
          <el-table-column
            label="状态"
            align="center"
            prop="status"
            width="100px"
          ><template slot-scope="scope">
            <el-tag 
              v-if="scope.row.status ==='closed'"
              size="small"
              type="success"
            >已付款</el-tag>
            <el-tag 
              v-if="scope.row.status ==='open'"
              size="small"
              type="warning"
            >待支付</el-tag>
            <el-tag 
              v-if="scope.row.status ==='cancelled'"
              size="small"
              type="danger"
            >已取消</el-tag>
          </template>
          </el-table-column>
          <!-- 数据行操作按钮 -->
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
            width="200px"
          >
            <template slot-scope="scope">
              <el-button
                size="small"
                type="text"
                icon="el-icon-edit"
                @click="handleSeeDetail(scope.row)"
              >订单详情
              </el-button>
              <el-button
                size="small"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除订单
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <!-- 订单详情表格-->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="800px"
      append-to-body
    >
      <el-table
        v-loading="orderDetailTableLoading"
        :data="orderDetailTable"
        :inline="true"
        height="500"
        style="width: 100%;margin-bottom: 20px;"
      >
        <el-table-column
          label="订单明细id"
          align="center"
          prop="orderDetailId"
          width="100px"
        />
        <el-table-column
          label="菜品名称"
          align="center"
          prop="dish.dishName"
        />
        <el-table-column
          label="菜品类别"
          align="center"
          prop="dish.category"
        />
        <el-table-column
          label="菜品购买数量"
          align="center"
          prop="quantity"
        />
        <el-table-column
          label="菜品单价"
          align="center"
          prop="dish.price"
        />
      </el-table>
      
    </el-dialog>
  </div>
</template>
  
  <script>
  /** 导入axios Api */
  import { selectAllOrder, deleteOrder, selectOrderByOrderId } from '@/api/resourceM/order'
  export default {
    name: 'Order',
    data() {
      return {
        // 遮罩层
        orderTableLoading: false,
        orderDetailTableLoading: false,

        // 清单，存放一堆对象
        orderList: [],
        orderDetailList: [],
        dishList: [],
        tableList: [],

        // 对象,存放单个对象
        order: {},
        dish: {},
        orderDetail: {
          dishName: null,
          tableName: null,
          dishQuantity: null
        },
        table: {},

        // el-table所使用
        orderTable: [],
        orderDetailTable: [],

        // 用于设置el-dialog组件的title属性
        title: '',
        // 是否显示弹出层
        open: false,
        // 数据查询参数
        queryParams: {
          orderId: null
        }
      }
    },
    created() {
      this.makeOrderTable()
    },
    methods: {
      // 制作orerTable
      makeOrderTable(query) {
        this.orderTableLoading = true
        if (query == null) {
          this.getOrderListAll()
        } else { 
          this.getOrderListByOrderId(query)
        }
        this.orderTableLoading = false
      },
      // 制作orderDetailTable
      makeOrderDetailTable(row) {
        this.getOrderListByOrderId(row.orderId)
        this.getOrderListAll()
      },
      /** 查询所有order，并放入orderList数组中 */
      getOrderListAll() {
        selectAllOrder().then((response) => {
          this.orderList = response.data
        })
      },
      // 根据OrderId查询order，并放入orderList数组中
      getOrderListByOrderId(orderId) {
        selectOrderByOrderId(orderId).then((response) => {
          this.orderList = []
          if (response.data !== undefined) {
            this.orderList[0] = response.data
            this.orderDetailTable = response.data.orderDetail
          }
        })
      },
      
      /** queryParams重置 */
      resetQuery() {
        this.queryParams = {
          orderId: null
        }
        this.makeOrderTable()
      },
      handleQueryByOrderId() {
        if (this.queryParams.orderId == null) {
          this.$message.success('请输入订单Id')
        } else {
                this.makeOrderTable(this.queryParams.orderId)
                this.$message.success('查询成功')
        }
      },
      /** 查看详情页面 */
      handleSeeDetail(row) {
        this.orderDetailTableLoading = true
        this.makeOrderDetailTable(row)
        this.orderDetailTableLoading = false
        this.open = true
        this.title = '订单详情'
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm(
          '是否确认删除名称为"' + row.orderName + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(function() {
            return deleteOrder(row.orderId)
          })
          .then(() => {
            this.getOrderListAll()
            this.$message.success('删除成功')
          })
          .catch(() => { })
      }
    }
  }
  </script>
  