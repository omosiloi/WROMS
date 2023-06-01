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
            label="模块标题"
            prop="title"
          >
            <el-input
              v-model="queryParams.title"
              placehold
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="目标对象"
            prop="target"
          >
            <el-input
              v-model="queryParams.target"
              placehold
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="方法名称"
            prop="method"
          >
            <el-input
              v-model="queryParams.method"
              placehold
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="业务类型"
            prop="businessType"
          >
            <el-input
              v-model="queryParams.businessType"
              placehold
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="请求方式"
            prop="requestMethod"
          >
            <el-input
              v-model="queryParams.requestMethod"
              placehold
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="请求url"
            prop="requestUrl"
          >
            <el-input
              v-model="queryParams.requestUrl"
              placehold
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="请求结果编码"
            prop="resultCode"
          >
            <el-input
              v-model="queryParams.resultCode"
              placehold
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="请求者"
            prop="requestBy"
          >
            <el-input
              v-model="queryParams.requestBy"
              placehold
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item
            label="请求时间"
            prop="requestTime"
          >
            <el-input
              v-model="queryParams.requestTime"
              placehold
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
            >搜索</el-button>
            <el-button
              icon="el-icon-refresh"
              size="mini"
              @click="resetQuery"
            >重置</el-button>
          </el-form-item>
        </el-form>
        <el-row
          :gutter="10"
          class="mb8"
        >
          <el-col :span="3">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="small"
              @click="handleAdd"
            >新增
            </el-button>
          </el-col>
        </el-row>

        <!-- 数据表格 -->
        <el-table
          v-loading="loading"
          :data="logList"
        >
          <!-- 数据表格列 -->
          <el-table-column
            label="日志主键"
            align="center"
            prop="logId"
          />
          <el-table-column
            label="模块标题"
            align="center"
            prop="title"
          />
          <el-table-column
            label="目标对象"
            align="center"
            prop="target"
          />
          <el-table-column
            label="方法名称"
            align="center"
            prop="method"
          />
          <el-table-column
            label="业务类型"
            align="center"
            prop="businessType"
          />
          <el-table-column
            label="请求方式"
            align="center"
            prop="requestMethod"
          />
          <el-table-column
            label="请求url"
            align="center"
            prop="requestUrl"
          />
          <el-table-column
            label="请求结果编码"
            align="center"
            prop="resultCode"
          />
          <el-table-column
            label="请求者"
            align="center"
            prop="requestBy"
          />
          <el-table-column
            label="请求时间"
            align="center"
            prop="requestTime"
          />
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
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
              >修改</el-button>
              <el-button
                size="small"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除</el-button>
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
    <!-- 添加修改log对话框 -->
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
          label="模块标题"
          prop="title"
        >
          <el-input
            v-model="form.title"
            placeholder="请输入模块标题"
          />
        </el-form-item>
        <el-form-item
          label="目标对象"
          prop="target"
        >
          <el-input
            v-model="form.target"
            placeholder="请输入目标对象"
          />
        </el-form-item>
        <el-form-item
          label="方法名称"
          prop="method"
        >
          <el-input
            v-model="form.method"
            placeholder="请输入方法名称"
          />
        </el-form-item>
        <el-form-item
          label="业务类型"
          prop="businessType"
        >
          <el-input
            v-model="form.businessType"
            placeholder="请输入业务类型"
          />
        </el-form-item>
        <el-form-item
          label="请求方式"
          prop="requestMethod"
        >
          <el-input
            v-model="form.requestMethod"
            placeholder="请输入请求方式"
          />
        </el-form-item>
        <el-form-item
          label="请求url"
          prop="requestUrl"
        >
          <el-input
            v-model="form.requestUrl"
            placeholder="请输入请求url"
          />
        </el-form-item>
        <el-form-item
          label="请求结果编码"
          prop="resultCode"
        >
          <el-input
            v-model="form.resultCode"
            placeholder="请输入请求结果编码"
          />
        </el-form-item>
        <el-form-item
          label="请求者"
          prop="requestBy"
        >
          <el-input
            v-model="form.requestBy"
            placeholder="请输入请求者"
          />
        </el-form-item>
        <el-form-item
          label="请求时间"
          prop="requestTime"
        >
          <el-input
            v-model="form.requestTime"
            placeholder="请输入请求时间"
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
import { listLogByPage, getLog, addLog, updateLog, deleteLog } from '@/api/systemM/log'
export default {
  name: 'Log',
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
      // Log表格数据
      logList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        logId: null,
        title: null,
        target: null,
        method: null,
        businessType: null,
        requestMethod: null,
        requestUrl: null,
        resultCode: null,
        requestBy: null,
        requestTime: null
      },
      // 数据编辑表单
      form: {},
      // 表单数据校验规则
      rules: {
        logId: [
          { required: true, message: '日志主键不能为空', trigger: 'blur' }
        ],
        title: [
          { required: true, message: '模块标题不能为空', trigger: 'blur' }
        ],
        target: [
          { required: true, message: '目标对象不能为空', trigger: 'blur' }
        ],
        method: [
          { required: true, message: '方法名称不能为空', trigger: 'blur' }
        ],
        businessType: [
          { required: true, message: '业务类型不能为空', trigger: 'blur' }
        ],
        requestMethod: [
          { required: true, message: '请求方式不能为空', trigger: 'blur' }
        ],
        requestUrl: [
          { required: true, message: '请求url不能为空', trigger: 'blur' }
        ],
        resultCode: [
          { required: true, message: '请求结果编码不能为空', trigger: 'blur' }
        ],
        requestBy: [
          { required: true, message: '请求者不能为空', trigger: 'blur' }
        ],
        requestTime: [
          { required: true, message: '请求时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询Log数据列表 */
    getList() {
      this.loading = true
      listLogByPage(this.queryParams).then((response) => {
        this.logList = response.data.list
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
        logId: null,
        title: null,
        target: null,
        method: null,
        businessType: null,
        requestMethod: null,
        requestUrl: null,
        resultCode: null,
        requestBy: null,
        requestTime: null
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
      this.title = '添加Log'
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.resetForm()
      getLog(row.logId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改Log'
      })
    },

    /** 数据提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.logId !== null) {
            updateLog(this.form).then((response) => {
              this.open = false
              this.title = ''
              this.getList()
              this.$message.success(response.data)
            })
          } else {
            addLog(this.form).then((response) => {
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
      this.$confirm('是否确认删除ID为"' + row.logId + '"的数据项?', '警告', {
        confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'
      })
        .then(function() {
          return deleteLog(row.logId)
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
