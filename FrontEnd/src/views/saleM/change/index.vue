<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24" :xs="24"> 
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
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleQuery"
            >搜索
            </el-button>
          </el-form-item>
          <el-form-item 
            label="餐桌名称"
          >
            <el-select v-model="queryParams.tableId" placeholder="选择座位" clearable @change="handleQuery">
              <el-option
                v-for="item in tableSet"
                :key="item.tableId"
                :value="item.tableId"
                :disabled="item.disabled"
                :label="`${item.tableName} - 容量为：${item.tableCapacity} - ${item.status}`"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-tag 
              size="small"
              type="success"
            >总价：{{ order.totalPrice }}
            </el-tag>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-row>
          <el-col>
            <el-table
              ref="dishSetTable"
              v-loading="dishSetTableLoading"
              :data="dishSet"
              border
              height="340"
              style="width: 100%;margin-bottom: 20px;"
              row-key="dishId"
              default-expand-all
            >
              <el-table-column
                label="菜品名称"
                align="center"
              >
                <template slot-scope="scope">
                  <el-popover trigger="hover" placement="top">
                    <p>名称: {{ scope.row.dishName }}</p>
                    <p>描述: {{ scope.row.description }}</p>
                    <div slot="reference" class="name-wrapper">
                      <el-tag size="medium">{{ scope.row.dishName }}</el-tag>
                    </div>
                  </el-popover>
                </template>
              </el-table-column>
              <el-table-column
                label="菜品价格"
                align="center"
                prop="price"
                sortable
              />
              <el-table-column
                label="菜品类型"
                align="center"
                prop="category"
                :filters="categoryFilter"
                :filter-method="handleFilter"
              />
              <el-table-column
                label="加菜操作"
                align="center"
                class-name="small-padding fixed-width"
                width="200px"
              >
                <template slot-scope="scope">
                  <el-input-number v-model="scope.row.quantity" size="mini" :min="0" :step="1" />
                  <el-button
                    size="small"
                    type="text"
                    icon="el-icon-edit"
                    @click="handleAdd(scope.row)"
                  >加菜
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
          <el-col>
            <el-form :inline="true">
              <el-form-item>
                <el-button
                  icon="el-icon-refresh"
                  size="mini"
                  @click="handleResetDishSetTable"
                >重置
                </el-button>
              </el-form-item>
              <el-form-item label="菜品名称">
                <el-input v-model="queryParams.dishName" placeholder="搜索" @keyup.enter.native="handleQueryByDishName" />
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  icon="el-icon-search"
                  size="mini"
                  @click="handleQueryByDishName"
                >搜索
                </el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="12">
        <el-table
          v-loading="orderDetailTableLoading "
          border
          :data="orderDetailSet"
          height="340"
          style="width: 100%;margin-bottom: 20px;"
        >
          <el-table-column prop="dish.dishName" label="菜品名称" />
          <el-table-column prop="dish.price" label="单价" />
          <el-table-column prop="quantity" label="数量" />
          <el-table-column prop="totalPrice" label="小计（元）" sortable />
        </el-table>
        <el-form :inline="true">
          <el-form-item>
            <el-select v-model="order.tableId" clearable placeholder="选择座位" @change="changeTable">
              <el-option
                v-for="item in tableSet"
                :key="item.tableId"
                :value="item.tableId"
                :disabled="!item.disabled"
                :label="`${item.tableName} - 容量为：${item.tableCapacity} - ${item.status}`"
              />
            </el-select>
          </el-form-item>
          <el-form-item 
            label="操作"
          >
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="handleChange"
            > 确认更改
            </el-button>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleCancel"
            > 取消订单 
            </el-button>
          </el-form-item>
          <el-form-item>
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
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
  import { addOrderDetail, selectOrderByOrderId, selectAllTable, updateTable, updateOrder, selectOrderByTableIdAndStatus, updateOrderDetail, selectDishByDishName, selectAllDish, selectAllCategory, selectTableByTableId, deleteOrderDetail } from '@/api/saleM/change'
  export default {
    name: 'Change',
    data() {
      return {
        // 数据查询参数
         queryParams: {
          orderId: null,
          tableId: null,
          status: 'open'
        },
        changeFlag: {
          table: false,
          addDish: false,
          returnDish: false
        },
        categoryFilter: [],
        filter: {},
        // 数据
        dishSet: [],
        categorySet: [],
        tableSet: [],
        orderDetailSet: [],
        table: {},
        newTable: {},
        dish: {},
        order: {},
        orderDetail: {
          orderDetailId: null,
          orderId: null,
          dishId: null,
          quantity: null,
          dish: null
        },
        
        dialogVisible: false,
        orderDetailTableLoading: false,
        dishSetTableLoading: false,
        min: 0
      }
    },
    created() {
      this.setPage()
    },
    methods: {
      setPage() {
        selectAllTable().then((response) => {
          this.tableSet = response.data
          for (let i = 0; i < this.tableSet.length; i++) {
            if (this.tableSet[i].status !== '待结账') {
              this.tableSet[i].disabled = true
            } else {
              this.tableSet[i].disabled = false
            }
          }
        })
        // 对dishSetTable进行渲染
        this.dishSetLoading = true
        selectAllDish().then((response) => {       
          this.dishSet = response.data
          selectAllCategory().then((response) => {       
            this.categorySet = response.data
            for (let i = 0; i < this.categorySet.length; i++) {
              this.filter.text = this.categorySet[i]
              this.filter.value = this.categorySet[i]
              this.categoryFilter.push(this.filter)
              this.resetFilter()
            }
          })
        })
        this.dishSetLoading = false
      },
      handleQueryByDishName() {
        this.dishSetLoading = true
        selectDishByDishName(this.queryParams.dishName).then((response) => {       
          this.dishSet = response.data
          selectAllCategory().then((response) => {       
            this.categorySet = response.data
            for (let i = 0; i < this.categorySet.length; i++) {
              this.filter.text = this.categorySet[i]
              this.filter.value = this.categorySet[i]
              this.categoryFilter.push(this.filter)
              this.resetFilter()
            }
        })
        })
        this.dishSetLoading = false
      },
      changeTable() {
        this.table.status = '待清理'
      },
      handleFilter(value, row, column) {
        const property = column['property']
        return row[property] === value
      },
      handleAdd(row) {
        if ('tableId' in this.table) {
          if (row.quantity > 0) {
            const array = []
            for (let i = 0; i < this.orderDetailSet.length; i++) { 
              array.push(this.orderDetailSet[i].dishId)
            }
            for (let i = 0; i < this.orderDetailSet.length; i++) {  
              if (row.dishId === this.orderDetailSet[i].dishId) {
                this.orderDetailSet[i].quantity = this.orderDetailSet[i].quantity + row.quantity
                this.orderDetailSet[i].totalPrice = this.orderDetailSet[i].quantity * this.orderDetailSet[i].dish.price
                this.orderDetailSet[i].flag = 'update'
              }
            }
            if (!array.includes(row.dishId)) {
                this.orderDetail.dishId = row.dishId
                this.orderDetail.orderId = this.order.orderId
                this.orderDetail.quantity = row.quantity
                this.orderDetail.flag = 'insert'
                this.orderDetail.dish = row
                this.orderDetail.totalPrice = this.orderDetail.quantity * row.price
                this.orderDetailSet.push(this.orderDetail)  
                this.resetOrderDetail()
            } 
            row.quantity = 0
          } else {
            this.$message.error('请选择需要添加的数量！')
          }
        } else {
            this.$message.error('请选择需要更改的就餐座位！')
        }
      },
      handleQuery() {
        this.orderDetailTableLoading = true
        if (this.queryParams.orderId == null || this.queryParams.orderId === '') {
          if (this.queryParams.tableId !== null && this.queryParams.tableId !== '') {
            selectOrderByTableIdAndStatus(this.queryParams.tableId, this.queryParams.status).then((response) => {
              // 生成具体的order对象
              this.order = response.data[0]
              // 生成具体的orderDetailSet,里面放置多个orderDetail对象
              this.orderDetailSet = response.data[0].orderDetail
              // 生成具体的table对象
              this.table = response.data[0].table
              for (let i = 0; i < this.orderDetailSet.length; i++) {
                // 给每个orderDetail对象添加totalPrice属性，并设置属性值
                this.orderDetailSet[i].totalPrice = this.orderDetailSet[i].dish.price * this.orderDetailSet[i].quantity
                this.orderDetailSet[i].canReturn = this.orderDetailSet[i].quantity
                this.orderDetailSet[i].returnNum = 0
              }
              this.$message.success('请对' + this.table.tableName + '进行更改操作')
            })
          } else {
            this.$message.success('请输入订单Id或选择座位名称')
          }
          this.resetQueryParams()
        } else {
          selectOrderByOrderId(this.queryParams.orderId).then((response) => {
            //  生成具体的order对象
            this.order = response.data
            // 生成具体的table对象
            this.table = response.data.table
            if (response.data !== undefined) { 
              // 生成具体的orderDetailSet,里面放置多个orderDetail对象
              this.orderDetailSet = response.data.orderDetail
              for (let i = 0; i < this.orderDetailSet.length; i++) {
                // 给每个orderDetail对象添加totalPrice属性，并设置属性值
                this.orderDetailSet[i].totalPrice = this.orderDetailSet[i].dish.price * this.orderDetailSet[i].quantity
                this.orderDetailSet[i].canReturn = this.orderDetailSet[i].quantity
                this.orderDetailSet[i].returnNum = 0
              }
              this.$message.success('查询成功')
            } else {
              this.$message.error('未查到订单号为' + this.queryParams.orderId + '订单')
            }
          })
        }
        this.orderDetailTableLoading = false
      },
      handleCancel() {
        if (this.order.orderId === null) {
          this.$message.error('请先查询相应订单，然后再进行取消操作')
        } else if (this.order.status === 'closed') {
          this.$message.error('该订单已支付，不可进行取消！')
        } else if (this.order.status === 'cancelled') {
          this.$message.error('该订单已取消，不可进行再次取消！')
        } else {
          this.order.status = 'cancelled'
          this.table.status = '待清理'
          updateOrder(this.order).then(() => { 
            this.$message.success('已成功取消订单号为' + this.order.orderId + '的订单！')
          })
          updateTable(this.table).then(() => { 
            this.$message.success('请及时清理座位：' + this.table.tableName)
          })
        }
      },
      handleChange() {
        // 更换座位
        if (this.table.status === '待清理') {
          updateTable(this.table).then(() => { 
            this.$message.success('请及时清理座位：' + this.table.tableName)
            updateOrder(this.order).then(() => { 
                selectTableByTableId(this.order.tableId).then((response) => { 
                  this.newTable = response.data
                  this.newTable.status = '待结账'
                  this.changeFlag.table = true
                  updateTable(this.newTable).then(() => {
                    this.$message.success('带顾客到新座位：' + this.newTable.tableName)
                  })
                })
            })
          })
        }
        // 添菜
        for (let i = 0; i < this.orderDetailSet.length; i++) {  
          if (this.orderDetailSet[i].flag === 'update') {
            updateOrderDetail(this.orderDetailSet[i])
            this.changeFlag.addDish = true
          }
        }
        for (let i = 0; i < this.orderDetailSet.length; i++) {  
          if (this.orderDetailSet[i].flag === 'insert') {
            addOrderDetail(this.orderDetailSet[i])
            this.changeFlag.addDish = true
          }
        }
        // // 退菜
        // for (let i = 0; i < this.orderDetailSet.length; i++) {  
        //   // 退一部分菜
        //   if (this.orderDetailSet[i].quantity > 0 && this.orderDetailSet[i].quantity < this.orderDetailSet[i].canReturn) {
        //     updateOrderDetail(this.orderDetailSet[i])
        //   }
        //   // 退全部菜
        //   if (this.orderDetailSet[i].quantity === 0) {
        //     this.changeFlag.returnDish = true
        //     deleteOrderDetail(this.orderDetailSet[i].orderDetailId)
        //   }
        // }
        this.order.totalPrice = 0
        for (let i = 0; i < this.orderDetailSet.length; i++) {
          this.order.totalPrice = this.order.totalPrice + this.orderDetailSet[i].totalPrice
        }
        // 输出更改结果
        if (this.changeFlag.addDish) {
          this.$message.success('成功加菜')
        }
        this.resetChangeFlag()
        updateOrder(this.order)
      },
      handleReset() {
        this.resetFilter()
        this.resetCategoryFilter()
        this.resetQueryParams()
        this.resetOrder()
        this.resetTable()
        this.resetTableSet()
        this.resetOrderDetailSet()
        this.setPage()
      },
      handleResetDishSetTable() {
        this.resetQueryParams()
         // 对dishSetTable进行渲染
         this.dishSetLoading = true
        selectAllDish().then((response) => {       
          this.dishSet = response.data
          selectAllCategory().then((response) => {       
            this.categorySet = response.data
            for (let i = 0; i < this.categorySet.length; i++) {
              this.filter.text = this.categorySet[i]
              this.filter.value = this.categorySet[i]
              this.categoryFilter.push(this.filter)
              this.resetFilter()
            }
          })
        })
        this.dishSetLoading = false
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
          status: null
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
      resetOrderDetailSet() {
        this.orderDetailSet = []
      },
      resetTableSet() {
        this.tableSet = []
      },
      resetCategoryFilter() {
        this.categoryFilter = []
      },
      resetFilter() {
        this.filter = {}
      },
      resetOrderDetail() {
        this.orderDetail = {
          orderDetailId: null,
          orderId: null,
          dishId: null,
          quantity: null,
          dish: null
        }
      },
      resetChangeFlag() {
        this.changeFlag = {
          table: false,
          addDish: false,
          returnDish: false
        }
      }
    }
  }
  </script>