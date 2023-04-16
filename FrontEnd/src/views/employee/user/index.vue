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
            label="用户名"
            prop="userName"
          >
            <el-input
              v-model="queryParams.userName"
              placeholder="请输入用户名"
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
            >新增用户
            </el-button>
          </el-col>
        </el-row>

        <!-- 数据表格 -->
        <el-table
          v-loading="loading"
          :data="userList"
        >
          <!-- 数据表格列 -->
          <el-table-column
            label="用户ID"
            align="center"
            prop="userId"
          />
          <el-table-column
            label="用户名称"
            align="center"
            prop="userName"
          />
          <el-table-column
            label="用户昵称"
            align="center"
            prop="nickName"
          />
          <el-table-column
            label="状态"
            align="center"
            prop="status"
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
          >
            <template slot-scope="scope">
              <el-button
                size="small"
                type="text"
                icon="el-icon-key"
                @click="handleRole(scope.row)"
              >分配角色</el-button>
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
    <!-- 添加修改user对话框 -->
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
          label="用户名称"
          prop="userName"
        >
          <el-input
            v-model="form.userName"
            placeholder="请输入用户名"
          />
        </el-form-item>
        <el-form-item
          label="用户昵称"
          prop="nickName"
        >
          <el-input
            v-model="form.nickName"
            placeholder="请输入昵称"
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

    <!-- 分配权限对话框 -->
    <el-dialog
      :title="roleTitle"
      :visible.sync="roleDialogFormVisible"
      width="300px"
    >
      <el-form :model="roleForm">
        <el-tree
          ref="roleTree"
          :data="roleTreeData"
          show-checkbox
          :check-strictly="checkStrictly"
          node-key="roleId"
          :default-expand-all="true"
          :props="defaultProps"
        />
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="roleDialogFormVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="submitAuthRole"
        >确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
/** 导入axios Api */
import { listUserByPage, getUser, addUser, updateUser, deleteUser, authRole } from '@/api/employee/user'
import { listAllRole } from '@/api/employee/role'
export default {
  name: 'User',
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
      // User类型
      typeOptions: undefined,
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 当前页
      pageNum: 0,
      // 每页数据量
      pageSize: 0,
      // User表格数据
      userList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        userName: null,
        status: null
      },
      // 数据编辑表单
      form: {},
      // 表单数据校验规则
      rules: {
        userName: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: '昵称不能为空', trigger: 'blur' }
        ]
      },
      roleDialogFormVisible: false,
      defaultProps: {
        label: 'roleKey'
      },
      roleForm: {},
      roleTreeData: [],
      checkStrictly: true,
      roleTitle: ''
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询User数据列表 */
    getList () {
      this.loading = true
      listUserByPage(this.queryParams).then((response) => {
        this.userList = response.data.list
        this.total = response.data.total
        this.pageNum = this.queryParams.pageNum
        this.pageSize = this.queryParams.pageSize
        this.loading = false
      })
    },
    /** 处理分页 */
    handleSizeChange (val) {
      this.queryParams.pageSize = val
      this.getList()
    },
    handleCurrentChange (val) {
      this.queryParams.pageNum = val
      this.getList()
    },

    /** 数据编辑对话框取消按钮 */
    cancel () {
      this.open = false
      this.resetForm()
    },

    /** 数据编辑表单清空 */
    resetForm () {
      this.form = {
        userId: null,
        userName: null,
        nickName: null,
        status: '0'
      }
    },

    resetRoleForm () {
      this.roleForm = {
        userId: null,
        roleIds: []
      }
    },

    /** 数据筛选参数重置按钮 */
    resetQueryParams () {
      this.queryParams.pageNum = 1
      this.queryParams.pageSize = 5
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
        pageNum: 1,
        pageSize: 5,
        userName: null,
        status: null
      }
      this.getList()
    },

    /** 新增按钮操作 */
    handleAdd () {
      this.resetForm()
      this.open = true
      this.title = '添加User'
    },

    /** 修改按钮操作 */
    handleUpdate (row) {
      this.resetForm()
      getUser(row.userId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改User'
      })
    },

    /** 数据提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.userId === null) {
            addUser(this.form).then((response) => {
              this.$message.success(response.msg)
              this.open = false
              this.title = ''
              this.getList()
            })
          } else {
            updateUser(this.form).then((response) => {
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
        '是否确认删除名称为"' + row.userName + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(function () {
          return deleteUser(row.userId)
        })
        .then(() => {
          this.getList()
          this.$message.success('删除成功')
        })
        .catch(() => { })
    },

    /** 处理分配角色 */
    handleRole (row) {
      this.roleForm.userId = row.userId
      this.roleTitle = '为用户[' + row.nickName + ']分配角色'
      this.roleDialogFormVisible = true
      listAllRole().then((response) => {
        this.roleTreeData = response.data
      })
      getUser(this.roleForm.userId).then((response) => {
        var roles = response.data.roles
        const roleIds = roles.map((item) => item.roleId)
        this.$refs.roleTree.setCheckedKeys(roleIds)
      })
    },
    /** 提交分配角色表单 */
    submitAuthRole () {
      this.roleForm.roleIds = this.$refs.roleTree.getCheckedKeys()
      authRole(this.roleForm).then((response) => {
        this.$message.success(response.msg)
        this.roleDialogFormVisible = false
        this.resetRoleForm()
      })
    }
  }
}
</script>
