<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col
        :span="24"
        :xs="24"
      >
        <!-- 数据筛选表单 -->
        <el-form
          ref="queryForm"
          :model="queryParams"
          :inline="true"
          label-width="100px"
        >
          <el-form-item
            label="餐桌名"
            prop="tableName"
          > 
            <el-input
              v-model="queryParams.tableName"
              placeholder="请输入餐桌名"
              clearable
              size="small"
              @keyup.enter.native="handleQueryByTableName"
            />
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleQueryByTableName"
            >搜索
            </el-button>
          </el-form-item>
          <el-form-item
            label="餐桌状态"
            prop="status"
          >
            <el-select
              v-model="queryParams.status"
              placeholder="请选择"
            >
              <el-option
                v-for="item in statusOptions"
                :key="item.status"
                :label="item.label"
                :value="item.status"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleQueryByStatus"
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
        <!-- 数据操作按钮 -->
        <el-row :gutter="20">
          <el-col :span="3">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="small"
              @click="handleAdd"
            >新增餐桌
            </el-button>
          </el-col>
        </el-row>
        <!-- 数据表格 -->
        <el-table
          v-loading="loading"
          :data="tableList"
          style="width: 100%;margin-bottom: 20px;"
          row-key="tableId"
        >
          <el-table-column
            label="餐桌Id"
            align="center"
            prop="tableId"
          />
          <el-table-column
            label="餐桌名称"
            align="center"
            prop="tableName"
          />
          <el-table-column
            label="餐桌容量"
            align="center"
            prop="tableCapacity"
          />
          <el-table-column
            label="菜品状态"
            align="center"
            prop="status"
            width="100px"
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
                @click="handleUpdate(scope.row)"
              >修改
              </el-button>
              <el-button
                size="small"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <el-dialog
      :title="title"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="120px"
      >
        <el-form-item
          label="餐桌名称"
          prop="tableName"
        >
          <el-input
            v-model="form.tableName"
            placeholder="请输入餐桌名称"
          />
        </el-form-item>
        <el-form-item
          label="餐桌状态"
          prop="status"
        >
          <el-select
            v-model="form.status"
            placeholder="请选择"
          >
            <el-option
              v-for="item in statusOptions"
              :key="item.status"
              :label="item.label"
              :value="item.status"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="餐桌容量"
          prop="tableCapacity"
        >
          <el-input
            v-model="form.tableCapacity"
            placeholder="请输入餐桌容量"
          />
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
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
    
    <script>
    /** 导入axios Api */
    import { listAllTable, listTableByStatus, listTableByTableName, listTableByTableId, updateTable, addTable, deleteTable } from '@/api/resourceM/table'
    export default {
      name: 'Table',
      data() {
        return {
          // 用于绑定el-table的loading属性
          loading: false,
          // 用于绑定el-dialog的title属性
          title: '',
          // 用于绑定el-dialog的open属性
          open: false,

          // 用于存储查询到的table
          tableList: [],

          // 数据查询参数
          queryParams: {
            tableName: null,
            status: null
          },


          // 数据编辑表单
          form: {},
          // 表单数据校验规则
          rules: {
            tableName: [
              { required: true, message: '餐桌名不能为空', trigger: 'blur' }
            ],
            status: [
              { required: true, message: '餐桌状态不能为空', trigger: 'blur' }
            ],
            tableCapacity: [
              { required: true, message: '餐桌容量不能为空', trigger: 'blur' }
            ]
          },
          statusOptions: [{
            status: '可使用',
            label: '可使用'
          },
          {
            status: '待清理',
            label: '待清理'
          },
          {
            status: '待结账',
            label: '待结账'
          }]
        }
      },
      created() {
        this.getList()
      },
      methods: {
        /** 无条件查询table，并将查到的table放入tableList */
        getList() {
          this.loading = true
          listAllTable().then((response) => {
            this.tableList = response.data
            this.loading = false
          })
        },
        /** 根据status条件查询table，并将查到的table放入tableList */
        listTableByStatus(status) {
          this.loading = true
          listTableByStatus(status).then((response) => {
            this.tableList = response.data
            this.loading = false
          })
        },
        /** 根据tableName条件查询table，并将查到的table放入tableList */
        listTableByTableName(tableName) {
          this.loading = true
          listTableByTableName(tableName).then((response) => {
            this.tableList = response.data
            this.loading = false
          })
        },
        /** 根据tableId条件查询table，并将查到的table放入tableList */
        listTableByTableId(tableId) {
          this.loading = true
          listTableByTableId(tableId).then((response) => {
            this.tableList = response.data
            this.loading = false
          })
        },
              /** 数据筛选搜索按钮 */
        handleQueryByStatus() {
          this.resetQueryParams()
          this.listTableByStatus(this.queryParams.status)
          this.$message.success('查询成功' + this.queryParams.status)
        },
        /** 数据筛选搜索按钮 */
        handleQueryByTableName() {
          this.resetQueryParams()
          this.listTableByTableName(this.queryParams.tableName)
          this.$message.success('查询成功' + this.queryParams.tableName)
        },
        /** 数据编辑对话框取消按钮 */
        cancel() {
          this.open = false
          this.resetForm()
        },
    
        /** 数据筛选参数重置按钮 */
        resetQueryParams() {
          this.queryParams.pageNum = 1
          this.queryParams.pageSize = 5
        },
    
        /** 数据编辑表单清空 */
        resetForm() {
          this.form = {
            tableId: null,
            tableName: null,
            tableCapacity: 0,
            status: null
          }
        },
        /** 数据筛选重置按钮 */
        resetQuery() {
          this.queryParams = {
            tableName: null,
            status: null
          }
          this.getList()
        },
    
        /** 新增按钮操作 */
        handleAdd() {
          this.resetForm()
          this.open = true
          this.title = '添加新餐桌'
        },
    
        /** 修改按钮操作 */
        handleUpdate(row) {
          this.resetForm()
          listTableByTableId(row.tableId).then((response) => {
            this.form = response.data
            this.open = true
            this.title = '修改Table'
          })
        },
        /** 数据提交按钮 */
        submitForm: function() {
          this.$refs['form'].validate((valid) => {
            if (valid) {
              if (this.form.tableId === null) {
                addTable(this.form).then((response) => {            
                  this.$message.success(response.msg)
                  this.open = false
                  this.title = ''
                  this.getList()
                })
              } else {
                updateTable(this.form).then((response) => {
                  this.$message.success(response.msg)
                  this.open = false
                  this.title = ''
                  this.getList()
                })
              }
            }
          })
        },
        /** 删除按钮操作 */
        handleDelete(row) {
          this.$confirm(
            '是否确认删除名称为"' + row.tableName + '"的数据项?', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          })
            .then(function() {
              return deleteTable(row.tableId)
            })
            .then(() => {
              this.getList()
              this.$message.success('删除成功')
            })
            .catch(() => { })
        }
      }
    }
    </script>
    