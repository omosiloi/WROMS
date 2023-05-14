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
          <!-- 数据筛选表单组件 -->
          <el-form-item
            label="菜品名"
            prop="dishName"
          >
            <el-input
              v-model="queryParams.dishName"
              placeholder="请输入菜品名"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="菜品类型"
            prop="category"
          >
            <el-input
              v-model="queryParams.category"
              placeholder="请输入菜品类型"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
  
          <!-- 数据筛选操作按钮 -->
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleQuery"
            >搜索
            </el-button>
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
            >新增菜品
            </el-button>
          </el-col>
        </el-row>
  
        <!-- 数据表格 -->
        <el-table
          v-loading="loading"
          :data="dishList"
          style="width: 100%;margin-bottom: 20px;"
          row-key="dishId"
          default-expand-all
          :tree-props="{children: 'children'}"
        >
          <!-- 数据表格列 -->
          <el-table-column
            label="菜品名称"
            align="center"
            prop="dishName"
          />
          <el-table-column
            label="菜品ID"
            align="center"
            prop="dishId"
            width="100px"
          />
          <el-table-column
            label="菜品价格"
            align="center"
            prop="price"
          />
          <el-table-column
            label="菜品描述"
            align="center"
            prop="description"
          />
          <el-table-column
            label="菜品类型"
            :formatter="categoryFormat"
            align="center"
            prop="category"
            width="100px"
          />
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
        <!-- 数据分页加载 -->
        <!-- <el-pagination
          :current-page="pageNum"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          @pagination="getList"
        /> -->
      </el-col>
    </el-row>
    <!-- 添加修改menu对话框 -->
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
        <!-- 数据编辑表单组件 -->
        <el-form-item
          label="菜单名称"
          prop="dishName"
        >
          <el-input
            v-model="form.dishName"
            placeholder="请输入菜单名"
          />
        </el-form-item>
        <el-form-item
          label="菜单类别"
          prop="category"
        >
          <el-select
            v-model="form.category"
            placeholder="请选择"
          >
            <el-option
              v-for="item in categoryOptions"
              :key="item.category"
              :label="item.label"
              :value="item.category"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="菜品价格"
          prop="price"
        >
          <el-input
            v-model="form.price"
            placeholder="请输入菜品价格"
          />
        </el-form-item>
        <el-form-item
          label="菜品描述"
          prop="description"
        >
          <el-input
            v-model="form.description"
            placeholder="请输入菜品描述"
          />
        </el-form-item>
  
      </el-form>
      <!-- 数据提交操作按钮 -->
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
  import { listAllDish, listDishByCategory, listDishById, updateDish, addDish } from '@/api/resourceM/dish'
  export default {
    name: 'Dish',
    data() {
      return {
        // 遮罩层
        loading: false,
        // Dish表格数据
        dishList: [],
        // 对话框目录+菜单树
        categoryTree: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 数据查询参数
        queryParams: {
          dishName: null,
          category: null
        },
        // 数据编辑表单
        form: {},
        // 表单数据校验规则
        rules: {
          dishName: [
            { required: true, message: '菜单名不能为空', trigger: 'blur' }
          ],
          category: [
            { required: true, message: '菜单类别不能为空', trigger: 'blur' }
          ]
        },
        categoryOptions: [{
          category: 'appetizer',
          label: 'appetizer'
        },
        {
          category: 'beverage',
          label: 'beverage'
        },
        {
          category: 'dessert',
          label: 'dessert'
        }],
        defaultProps: {
          children: 'children',
          id: 'dishId',
          label: 'dishName'
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      /** 查询菜单数据列表 */
      getList() {
        this.loading = true
        listAllDish().then((response) => {
          this.dishList = response.data
          this.loading = false
        })
      },
      /** 根据category条件查询 */
      listDishByCategory(query) {
        this.loading = true
        listDishByCategory(query).then((response) => {
          this.dishList = response.data
          this.loading = false
        })
      },
      /** 根据dishId条件查询 */
      listDishById(query) {
        this.loading = true
        listDishById(query).then((response) => {
          this.dishList = response.data
          this.loading = false
        })
      },
      /** table菜单类型格式化 */
      categoryFormat(row) {
        let type = row.category
        switch (type) {
          case 'appetizer':
            type = 'appetizer'
            break
          case 'beverage':
            type = 'beverage'
            break
          case 'dessert':
            type = 'dessert'
            break
        }
        return type
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
          dishId: null,
          dishName: null,
          price: null,
          description: null,
          parentId: 0,
          category: null
        }
      },
  
      /** 数据筛选搜索按钮 */
      handleQuery() {
        this.resetQueryParams()
        this.listDishByCategory(this.queryParams.category)
        this.$message.success('查询成功' + this.queryParams.category)
      },
  
      /** 数据筛选重置按钮 */
      resetQuery() {
        this.queryParams = {
          dishName: null,
          category: null
        }
        this.getList()
      },
  
      /** 新增按钮操作 */
      handleAdd() {
        this.resetForm()
        this.open = true
        this.title = '添加新菜品'
      },
  
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.resetForm()
        listAllDish().then((response) => {
          this.form = response.data
          console.log(this.form)
          this.open = true
          this.title = '修改Menu'
        })
      },
  
    //   /** 数据提交按钮 */
      submitForm: function() {
        this.$refs['form'].validate((valid) => {
          
          if (valid) {
            if (this.form.dishId === null) {
              
              addDish(this.form).then((response) => {
                window.alert(this.form.category)
                this.$message.success(response.msg)
                this.open = false
                this.title = ''
                this.getList()
              })
            } else {
              updateDish(this.form).then((response) => {
                this.$message.success(response.msg)
                this.open = false
                this.title = ''
                this.getList()
              })
            }
          }
        })
      },
  
    //   /** 删除按钮操作 */
    //   handleDelete(row) {
    //     this.$confirm(
    //       '是否确认删除名称为"' + row.dishName + '"的数据项?', '警告', {
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     })
    //       .then(function() {
    //         return deleteMenu(row.dishId)
    //       })
    //       .then(() => {
    //         this.getList()
    //         this.$message.success('删除成功')
    //       })
    //       .catch(() => { })
    //   },
    //   /** 选中上级菜单 */
      handleNodeClick(data) {
        this.form.category = data.category
      }
    }
  }
  </script>
  