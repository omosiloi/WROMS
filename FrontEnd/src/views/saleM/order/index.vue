<template>
  <div class="app-container">
    <el-row>
      <el-col :span="12">
        <el-form 
          :inline="true"
          :model="queryParams" 
          class="demo-form-inline"
        > 
          <el-form-item label="菜品名称">
            <el-input v-model="queryParams.dishName" placeholder="搜索" @keyup.enter.native="handleQuery" />
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
          <el-form-item>
            <el-button
              icon="el-icon-refresh"
              size="mini"
              @click="handleReset"
            >重置
            </el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="12">
        <el-form 
          :inline="true"
          :model="queryParams" 
          class="demo-form-inline"
        >
          <el-form-item label="餐桌名称">
            <el-select v-model="table.tableId" clearable placeholder="选择座位">
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
              @click="handleInsertOrder"
            >下 单</el-button>
          </el-form-item>
          <el-form-item>
            <el-button @click="handleClear">清 空</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div class="container">
          <h2 class="title">菜 品 选 择</h2>
          <el-table
            v-loading="loading"
            :data="dishList"
            height="450"
            border
            style="width: 100%;margin-bottom: 20px;"
            row-key="dishId"
            default-expand-all
          >
            <el-table-column
              label="菜品名称"
              align="center"
              prop="dishName"
            />
            <el-table-column
              label="菜品价格"
              align="center"
              prop="price"
            />
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
                  @click="handleChoose(scope.row)"
                >选择菜品
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="container">
          <h2 class="title">购 物 车</h2>
          <el-table
            v-loading="shopLoding"
            :data="shopDishList"
            border
            height="450"
            style="width: 100%;margin-bottom: 20px;"
            row-key="dishId"
            default-expand-all
          > 
            <el-table-column
              label="菜品名称"
              align="center"
              prop="dishName"
            />
            <el-table-column
              label="价格"
              align="center"
              prop="price"
            />
            <el-table-column
              label="数量"
              align="center"
              prop="quantity"
            />
            <el-table-column
              label="总价格"
              align="center"
              prop="totalPrice"
            />
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
                  @click="handleDelete(scope.row)"
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
    <el-dialog
      :title="title"
      :visible.sync="dishOpen"
      width="500px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="dish"
        :rules="rules"
        label-width="120px"
      >
        <!-- 数据编辑表单组件 -->
        <el-form-item
          label="菜单名称"
          prop="dishName"
        >
          <el-input
            v-model="dish.dishName"
            rows="3"
            readonly
          />
        </el-form-item>
        <el-form-item
          label="菜单类别"
          prop="category"
        >
          <el-input
            v-model="dish.category"
            readonly
          />
        </el-form-item>
        <el-form-item
          label="菜品价格"
          prop="price"
        >
          <el-input
            v-model="dish.price"
            readonly
          />
        </el-form-item>
        <el-form-item
          label="菜品描述"
          prop="description"
        >
          <el-input
            v-model="dish.description"
            readonly
          />
        </el-form-item>
        <el-form-item
          label="数量选择"
          prop="quantity"
        >
          <el-input-number v-model="dish.quantity" :min="1" :step="1" @input="handleInput" />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      > 
        <el-button
          type="primary"
          @click="submitForm"
        >确 定</el-button>
        <el-button @click="handleCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAllDish, listAllTable, listDishByCategory, listDishByDishName, listAllCategory, listDishById, addOrder, addOrderDetail, updateTable } from '@/api/saleM/order'
 export default {
    data() {
      return {
        // 该对象用于存储查询参数
        queryParams: {
          userNumber: 0,
          category: '',
          dishName: null
        },
        // 遮罩层,刷新用
        loading: false,
        shopLoding: false,

        // el-dialog dish
        dishOpen: false,
        title: '',

        // el-dialog order

        // 表单数据校验规则
        rules: {
          quantity: [
            { required: true, message: '数量必须大于等于1', trigger: 'blur' }
          ]
        },

        //
        dishList: [],
        tableList: [],
        orderDetailList: [],
        categoryList: [],
        shopDishList: [],

        // order
        order: {
          orderId: null,
          tableId: null,
          totalPrice: 0,
          status: 'open'
        },
        orderDetail: {
          orderDetailId: null,
          orderId: null,
          dishId: null,
          quantity: 1
        },
        dish: {
          quantity: 1
        },
        table: {
          tableId: null,
          tableCapacity: null,
          status: '待结账'
        }
      }
    },
    created() {
      this.setPage()
    },
    methods: {
      // 获取所有菜品和座位
      setPage() {
        listAllDish().then((response) => {       
          this.dishList = response.data
        })
        listAllTable().then((response) => {
          this.tableList = response.data
          for (let i = 0; i < this.tableList.length; i++) {
            if (this.tableList[i].status === '待结账') {
              this.tableList[i].disabled = true
            } else {
              this.tableList[i].disabled = false
            }
          }
        })
        listAllCategory().then((response) => {
          this.categoryList = response.data
        })
      },
      /** 根据category条件查询dish */
      listDishByCategory(query) {
        this.loading = true
        listDishByCategory(query).then((response) => {
          this.dishList = response.data
          this.loading = false
        })
      },
      /** 根据dishName条件查询dish */
      listDishByDishName(query) {
        this.loading = true
        listDishByDishName(query).then((response) => {
          this.dishList = response.data
          this.loading = false
        })
      },

      /** 搜索按钮操作 */
      handleQuery() {
        this.listDishByDishName(this.queryParams.dishName)
        this.$message.success('查询成功' + this.queryParams.dishName)
      },
      /** 选择按钮操作 */
      handleChoose(row) {
        this.resetDish()
        this.resetOrderDetail()
        this.resetOrder()
        listDishById(row.dishId).then((response) => {
          this.dish = response.data
          this.dish.totalPrice = this.dish.price
          this.dishOpen = true
          this.title = '菜品选择'
        })
      },
      
      /** 取消按钮操作 */
      handleCancel() {
        this.dishOpen = false
        this.resetDish()
        this.resetOrder()
        this.resetOrderDetail()
      },
      handleInput(value) {
        this.dish.quantity = value
        this.dish.totalPrice = this.dish.price * value
      },
      handleInsertOrder() {
          this.order.tableId = this.table.tableId
          if (this.order.tableId != null) {
            if (this.shopDishList.length !== 0) {
              for (let i = 0; i < this.shopDishList.length; i++) {
                this.order.totalPrice = this.order.totalPrice + this.shopDishList[i].totalPrice
              }
              for (let i = 0; i < this.shopDishList.length; i++) {
                  this.orderDetail.quantity = this.shopDishList[i].quantity
                  this.orderDetail.dishId = this.shopDishList[i].dishId
                  this.orderDetailList.push(this.orderDetail)
                  this.resetOrderDetail()
              }
              addOrder(this.order).then((response) => {  
                this.order.orderId = response.data
                for (let i = 0; i < this.orderDetailList.length; i++) {
                  this.orderDetailList[i].orderId = this.order.orderId 
                  addOrderDetail(this.orderDetailList[i])
                }
                this.resetShopDishList()
                this.resetOrderDetailList()
                this.$message.success('下单成功！')
              })
              console.log(this.table)
              if (this.table.status === '待清理') {
                  this.$message.error('请安排员工清理' + this.table.tableName)
              }
              updateTable(this.table).then(() => { 
                this.setPage()
              })
              this.resetOrder()
              this.resetTable()
            } else {
              this.$message.success('请选择菜品！')
            }
          } else {
            this.$message.success('请选择就餐位置！')
          }
      },
      handleClear() {
        this.resetDish()
        this.resetTable()
        this.resetShopDishList()
        this.resetOrderDetailList()
      },
      handleDelete(row) {
        for (let i = 0; i < this.shopDishList.length; i++) {
          if (this.shopDishList[i].dishId === row.dishId) {
            this.shopDishList.splice(i, 1)
          }
        }
      },
      handleUpdate(row) {
        for (let i = 0; i < this.shopDishList.length; i++) {
          if (this.shopDishList[i].dishId === row.dishId) {
            this.shopDishList.splice(i, 1)
          }
        }
      },
      /** 确认按钮操作 */
      submitForm: function() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            if (this.shopDishList.some(obj => obj.dishId === this.dish.dishId)) {
              this.shopLoding = true
              for (let i = 0; i < this.shopDishList.length; i++) {
                if (this.shopDishList[i].dishId === this.dish.dishId) {
                  this.$set(this.shopDishList[i], 'quantity', this.shopDishList[i].quantity + this.dish.quantity)
                  this.$set(this.shopDishList[i], 'totalPrice', this.shopDishList[i].totalPrice + this.dish.totalPrice)
                  this.shopDishList.push({})
                  this.shopDishList.pop()
                  break
                }
              }
              this.shopLoding = false
            } else {
              this.shopDishList.push(this.dish) 
            }
            this.dishOpen = false
            }
        })
      },
      handleReset() {
        this.setPage()
      },
      /** 清空dish */
      resetDish() {
        this.dish = {
          dishId: null,
          dishName: null,
          price: null,
          description: null,
          parentId: 0,
          category: null,
          quantity: 1,
          totalPrice: 0
        }
      },
      resetOrderDetail() {
        this.orderDetail = {
          orderDetailId: null,
          orderId: null,
          dishId: null,
          quantity: 1
        }
      },
      resetOrder() {
        this.order = {
          orderId: null,
          tableId: null,
          totalPrice: 0,
          status: 'open'
        }
      },
      resetTable() {
        this.table = {
          tableId: null,
          tableCapacity: null,
          status: '待结账'
        }
      },
      resetShopDishList() {
        this.shopDishList = []
      },
      resetOrderDetailList() {
        this.orderDetailList = []
      },
      resetQueryParams() {
        this.queryParams = {
          userNumber: 0,
          category: '',
          dishName: null
        }
      }
    }
  }
</script>

<style>
.container {
  text-align: center;
}
.title {
  margin-top: 20px;
}
</style>