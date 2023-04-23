<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="24" :xs="24">
        <!-- 数据筛选表单 -->
        <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="100px">
          <!-- 数据筛选表单组件 -->
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="queryParams.menuName" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="父菜单ID" prop="parentId">
            <el-input v-model="queryParams.parentId" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="显示顺序" prop="menuSort">
            <el-input v-model="queryParams.menuSort" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="路由地址" prop="path">
            <el-input v-model="queryParams.path" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="组件路径" prop="component">
            <el-input v-model="queryParams.component" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="菜单类型（M目录 C菜单 F按钮）" prop="menuType">
            <el-input v-model="queryParams.menuType" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="菜单状态（0显示 1隐藏）" prop="visible">
            <el-input v-model="queryParams.visible" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="菜单状态（0正常 1停用）" prop="status">
            <el-input v-model="queryParams.status" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="权限标识" prop="perms">
            <el-input v-model="queryParams.perms" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="菜单图标" prop="icon">
            <el-input v-model="queryParams.icon" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="创建者" prop="createBy">
            <el-input v-model="queryParams.createBy" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
            <el-input v-model="queryParams.createTime" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="更新者" prop="updateBy">
            <el-input v-model="queryParams.updateBy" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="更新时间" prop="updateTime">
            <el-input v-model="queryParams.updateTime" placehold clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <!-- 数据筛选操作按钮 -->
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
        <el-row :gutter="10" class="mb8">
          <el-col :span="3">
            <el-button type="primary" icon="el-icon-plus" size="small" @click="handleAdd">新增
            </el-button>
          </el-col>
        </el-row>

        <!-- 数据表格 -->
        <el-table v-loading="loading" :data="menuList">
          <!-- 数据表格列 -->
          <el-table-column label="菜单ID" align="center" prop="menuId" />
          <el-table-column label="菜单名称" align="center" prop="menuName" />
          <el-table-column label="父菜单ID" align="center" prop="parentId" />
          <el-table-column label="显示顺序" align="center" prop="menuSort" />
          <el-table-column label="路由地址" align="center" prop="path" />
          <el-table-column label="组件路径" align="center" prop="component" />
          <el-table-column label="菜单类型（M目录 C菜单 F按钮）" align="center" prop="menuType" />
          <el-table-column label="菜单状态（0显示 1隐藏）" align="center" prop="visible" />
          <el-table-column label="菜单状态（0正常 1停用）" align="center" prop="status" />
          <el-table-column label="权限标识" align="center" prop="perms" />
          <el-table-column label="菜单图标" align="center" prop="icon" />
          <el-table-column label="创建者" align="center" prop="createBy" />
          <el-table-column label="创建时间" align="center" prop="createTime" />
          <el-table-column label="更新者" align="center" prop="updateBy" />
          <el-table-column label="更新时间" align="center" prop="updateTime" />
          <!-- 数据行操作按钮 -->
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="small" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
              <el-button size="small" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 数据分页加载 -->
        <el-pagination
          :current-page="pageNum"
          :page-sizes="[5, 10, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          @pagination="getList"
        />
      </el-col>
    </el-row>
    <!-- 添加修改menu对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <!-- 数据编辑表单组件 -->
        <el-form-item label="菜单名称" prop="menuName">
          <el-input v-model="form.menuName" placeholder="请输入菜单名称" />
        </el-form-item>
        <el-form-item label="父菜单ID" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父菜单ID" />
        </el-form-item>
        <el-form-item label="显示顺序" prop="menuSort">
          <el-input v-model="form.menuSort" placeholder="请输入显示顺序" />
        </el-form-item>
        <el-form-item label="路由地址" prop="path">
          <el-input v-model="form.path" placeholder="请输入路由地址" />
        </el-form-item>
        <el-form-item label="组件路径" prop="component">
          <el-input v-model="form.component" placeholder="请输入组件路径" />
        </el-form-item>
        <el-form-item label="菜单类型（M目录 C菜单 F按钮）" prop="menuType">
          <el-input v-model="form.menuType" placeholder="请输入菜单类型（M目录 C菜单 F按钮）" />
        </el-form-item>
        <el-form-item label="菜单状态（0显示 1隐藏）" prop="visible">
          <el-input v-model="form.visible" placeholder="请输入菜单状态（0显示 1隐藏）" />
        </el-form-item>
        <el-form-item label="菜单状态（0正常 1停用）" prop="status">
          <el-input v-model="form.status" placeholder="请输入菜单状态（0正常 1停用）" />
        </el-form-item>
        <el-form-item label="权限标识" prop="perms">
          <el-input v-model="form.perms" placeholder="请输入权限标识" />
        </el-form-item>
        <el-form-item label="菜单图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入菜单图标" />
        </el-form-item>
        <el-form-item label="创建者" prop="createBy">
          <el-input v-model="form.createBy" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="form.createTime" placeholder="请输入创建时间" />
        </el-form-item>
        <el-form-item label="更新者" prop="updateBy">
          <el-input v-model="form.updateBy" placeholder="请输入更新者" />
        </el-form-item>
        <el-form-item label="更新时间" prop="updateTime">
          <el-input v-model="form.updateTime" placeholder="请输入更新时间" />
        </el-form-item>
      </el-form>
      <!-- 数据提交操作按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
/** 导入axios Api */
import { listMenuByPage, getMenu, addMenu, updateMenu, deleteMenu } from '@/api/menu'
export default {
  name: 'Menu',
  data() {
    return {
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 当前页
      pageNum: 0,
      // 每页数据量
      pageSize: 0,
      // Menu表格数据
      menuList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        menuId: null,
        menuName: null,
        parentId: null,
        menuSort: null,
        path: null,
        component: null,
        menuType: null,
        visible: null,
        status: null,
        perms: null,
        icon: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
      },
      // 数据编辑表单
      form: {},
      // 表单数据校验规则
      rules: {
        menuId: [
          { required: true, message: '菜单ID不能为空', trigger: 'blur' }
        ],
        menuName: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' }
        ],
        parentId: [
          { required: true, message: '父菜单ID不能为空', trigger: 'blur' }
        ],
        menuSort: [
          { required: true, message: '显示顺序不能为空', trigger: 'blur' }
        ],
        path: [
          { required: true, message: '路由地址不能为空', trigger: 'blur' }
        ],
        component: [
          { required: true, message: '组件路径不能为空', trigger: 'blur' }
        ],
        menuType: [
          { required: true, message: '菜单类型（M目录 C菜单 F按钮）不能为空', trigger: 'blur' }
        ],
        visible: [
          { required: true, message: '菜单状态（0显示 1隐藏）不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '菜单状态（0正常 1停用）不能为空', trigger: 'blur' }
        ],
        perms: [
          { required: true, message: '权限标识不能为空', trigger: 'blur' }
        ],
        icon: [
          { required: true, message: '菜单图标不能为空', trigger: 'blur' }
        ],
        createBy: [
          { required: true, message: '创建者不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        updateBy: [
          { required: true, message: '更新者不能为空', trigger: 'blur' }
        ],
        updateTime: [
          { required: true, message: '更新时间不能为空', trigger: 'blur' }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询Menu数据列表 */
    getList() {
      this.loading = true
      listMenuByPage(this.queryParams).then((response) => {
        this.menuList = response.data.list
        this.total = response.data.total
        this.loading = false
      })
    },
    /** 处理分页 */
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      this.getList()
    },

    /** 数据编辑对话框取消按钮 */
    cancel() {
      this.open = false
      this.resetForm()
    },

    /** 数据编辑表单清空 */
    resetForm() {
      this.form = {
        menuId: null,
        menuName: null,
        parentId: null,
        menuSort: null,
        path: null,
        component: null,
        menuType: null,
        visible: null,
        status: null,
        perms: null,
        icon: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
      }
    },

    /** 重置分页 */
    resetQueryParams() {
      this.queryParams.pageNum = 1
      this.queryParams.pageSize = 5
    },

    /** 数据筛选搜索按钮 */
    handleQuery() {
      this.resetQueryParams()
      this.getList()
      this.$message.success('查询成功')
    },

    /** 数据筛选重置按钮 */
    resetQuery() {
      this.$refs.queryForm.resetFields()
      this.getList()
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.resetForm()
      this.open = true
      this.title = '添加Menu'
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.resetForm()
      getMenu(row.menuId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改Menu'
      })
    },

    /** 数据提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.menuId !== null) {
            updateMenu(this.form).then((response) => {
              this.open = false
              this.title = ''
              this.getList()
              this.$message.success(response.data)
            })
          } else {
            addMenu(this.form).then((response) => {
              this.open = false
              this.title = ''
              this.getList()
              this.$message.success(response.data)
            })
          }
        }
      })
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除ID为"' + row.menuId + '"的数据项?', '警告', {
          confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'
        })
        .then(function() {
          return deleteMenu(row.menuId)
        })
        .then(() => {
          this.getList()
          this.$message.success('删除成功')
        })
        .catch(() => {})
    }
  }
}
</script>
