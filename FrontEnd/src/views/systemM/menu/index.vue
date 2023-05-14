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
            label="菜单名"
            prop="menuName"
          >
            <el-input
              v-model="queryParams.menuName"
              placeholder="请输入菜单名"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="状态"
            prop="status"
          >
            <el-select
              v-model="queryParams.status"
              size="small"
              placeholder="请选择"
            >
              <el-option
                v-for="item in statusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
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
            >新增菜单
            </el-button>
          </el-col>
        </el-row>

        <!-- 数据表格 -->
        <el-table
          v-loading="loading"
          :data="menuList"
          style="width: 100%;margin-bottom: 20px;"
          row-key="menuId"
          default-expand-all
          :tree-props="{children: 'children'}"
        >
          <!-- 数据表格列 -->
          <el-table-column
            label="菜单名称"
            align="center"
            prop="menuName"
          />
          <el-table-column
            label="菜单ID"
            align="center"
            prop="menuId"
            width="100px"
          />
          <el-table-column
            label="路径"
            align="center"
            prop="path"
          />
          <el-table-column
            label="组件"
            align="center"
            prop="component"
          />
          <el-table-column
            label="类型"
            :formatter="menuTypeFormat"
            align="center"
            prop="menuType"
            width="100px"
          />
          <!-- <el-table-column label="可见" align="center" prop="visible" width="100px" /> -->
          <el-table-column
            label="状态"
            align="center"
            prop="status"
            width="100px"
          >
            <template slot-scope="scope">
              <el-tag
                v-if="scope.row.status === '0'"
                size="small"
                type="success"
              >正常</el-tag>
              <el-tag
                v-else-if="scope.row.status === '1'"
                size="small"
                type="danger"
              >禁用</el-tag>
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
        <el-form-item label="请选择">
          <el-tree
            ref="tree"
            :data="menuTree"
            :props="defaultProps"
            node-key="menuId"
            @node-click="handleNodeClick"
          />
        </el-form-item>
        <el-form-item
          label="父菜单ID"
          prop="parentId"
        >
          <el-input
            v-model="form.parentId"
            placeholder="请选择父级菜单"
            disabled
          />
        </el-form-item>
        <el-form-item
          label="菜单名称"
          prop="menuName"
        >
          <el-input
            v-model="form.menuName"
            placeholder="请输入菜单名"
          />
        </el-form-item>
        <el-form-item
          label="菜单类别"
          prop="menuType"
        >
          <el-select
            v-model="form.menuType"
            placeholder="请选择"
          >
            <el-option
              v-for="item in menuTypeOptions"
              :key="item.menuType"
              :label="item.label"
              :value="item.menuType"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="路由路径"
          prop="path"
        >
          <el-input
            v-model="form.path"
            placeholder="请输入路由路径"
          />
        </el-form-item>
        <el-form-item
          label="组件路径"
          prop="component"
        >
          <el-input
            v-model="form.component"
            placeholder="请输入组件路径"
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
import { listMenu, treeMenu, getMenu, addMenu, updateMenu, deleteMenu } from '@/api/systemM/menu'
export default {
  name: 'Menu',
  data () {
    return {
      // 状态类型
      statusOptions: [
        {
          value: '0',
          label: '正常'
        },
        {
          value: '1',
          label: '禁用'
        }
      ],
      // 遮罩层
      loading: false,
      // Menu表格数据
      menuList: [],
      // 对话框目录+菜单树
      menuTree: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 数据查询参数
      queryParams: {
        menuName: null,
        status: null
      },
      // 数据编辑表单
      form: {},
      // 表单数据校验规则
      rules: {
        menuName: [
          { required: true, message: '菜单名不能为空', trigger: 'blur' }
        ],
        menuType: [
          { required: true, message: '菜单类别不能为空', trigger: 'blur' }
        ]
      },
      menuTypeOptions: [{
        menuType: 'M',
        label: '目录'
      },
      {
        menuType: 'C',
        label: '菜单'
      },
      {
        menuType: 'F',
        label: '方法'
      }],
      defaultProps: {
        children: 'children',
        id: 'menuId',
        label: 'menuName'
      }
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询Menu数据列表 */
    getList () {
      this.loading = true
      listMenu(this.queryParams).then((response) => {
        this.menuList = response.data
        this.loading = false
      })
    },
    /** 得到对话框父级菜单树 */
    getTree () {
      this.loading = true
      treeMenu().then((response) => {
        this.menuTree = response.data
        this.loading = false
      })
    },
    /** table菜单类型格式化 */
    menuTypeFormat (row) {
      let type = row.menuType
      switch (type) {
        case 'M':
          type = '目录'
          break
        case 'C':
          type = '菜单'
          break
        case 'F':
          type = '按钮'
          break
      }
      return type
    },
    /** 数据编辑对话框取消按钮 */
    cancel () {
      this.open = false
      this.resetForm()
    },

    /** 数据筛选参数重置按钮 */
    resetQueryParams () {
      this.queryParams.pageNum = 1
      this.queryParams.pageSize = 5
    },

    /** 数据编辑表单清空 */
    resetForm () {
      this.form = {
        menuId: null,
        menuName: null,
        path: null,
        component: null,
        parentId: 0,
        menuType: null,
        status: '0'
      }
      // 得到父级菜单树
      this.getTree()
    },

    /** 数据筛选搜索按钮 */
    handleQuery () {
      this.resetQueryParams()
      this.getList()
      this.$message.success('查询成功')
    },

    /** 数据筛选重置按钮 */
    resetQuery () {
      this.queryParams = {
        menuName: null,
        status: null
      }
      this.getList()
    },

    /** 新增按钮操作 */
    handleAdd () {
      this.resetForm()
      this.open = true
      this.title = '添加Menu'
    },

    /** 修改按钮操作 */
    handleUpdate (row) {
      this.resetForm()
      getMenu(row.menuId).then((response) => {
        this.form = response.data
        console.log(this.form)
        this.open = true
        this.title = '修改Menu'
      })
    },

    /** 数据提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.menuId === null) {
            addMenu(this.form).then((response) => {
              this.$message.success(response.msg)
              this.open = false
              this.title = ''
              this.getList()
            })
          } else {
            updateMenu(this.form).then((response) => {
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
    handleDelete (row) {
      this.$confirm(
        '是否确认删除名称为"' + row.menuName + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(function () {
          return deleteMenu(row.menuId)
        })
        .then(() => {
          this.getList()
          this.$message.success('删除成功')
        })
        .catch(() => { })
    },
    /** 选中上级菜单 */
    handleNodeClick (data) {
      this.form.parentId = data.menuId
    }
  }
}
</script>
