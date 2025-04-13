<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
            <el-form-item label="系统用户ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="系统用户ID"></el-input>
    </el-form-item>
    <el-form-item label="平台ID" prop="platformId">
      <el-input v-model="dataForm.platformId" placeholder="平台ID"></el-input>
    </el-form-item>
    <el-form-item label="用户为该账号设置的昵称 (方便区分)" prop="accountNickname">
      <el-input v-model="dataForm.accountNickname" placeholder="用户为该账号设置的昵称 (方便区分)"></el-input>
    </el-form-item>
    <el-form-item label="在第三方平台的用户名/ID (可选, 根据平台)" prop="platformUsername">
      <el-input v-model="dataForm.platformUsername" placeholder="在第三方平台的用户名/ID (可选, 根据平台)"></el-input>
    </el-form-item>
    <el-form-item label="认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理)" prop="credentials">
      <el-input v-model="dataForm.credentials" placeholder="认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理)"></el-input>
    </el-form-item>
    <el-form-item label="账号状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="账号状态"></el-input>
    </el-form-item>
    <el-form-item label="上次验证成功时间" prop="lastVerifiedAt">
      <el-input v-model="dataForm.lastVerifiedAt" placeholder="上次验证成功时间"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createdAt">
      <el-input v-model="dataForm.createdAt" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updatedAt">
      <el-input v-model="dataForm.updatedAt" placeholder="更新时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userId: '',
          platformId: '',
          accountNickname: '',
          platformUsername: '',
          credentials: '',
          status: '',
          lastVerifiedAt: '',
          createdAt: '',
          updatedAt: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '系统用户ID不能为空', trigger: 'blur' }
          ],
          platformId: [
            { required: true, message: '平台ID不能为空', trigger: 'blur' }
          ],
          accountNickname: [
            { required: true, message: '用户为该账号设置的昵称 (方便区分)不能为空', trigger: 'blur' }
          ],
          platformUsername: [
            { required: true, message: '在第三方平台的用户名/ID (可选, 根据平台)不能为空', trigger: 'blur' }
          ],
          credentials: [
            { required: true, message: '认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理)不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '账号状态不能为空', trigger: 'blur' }
          ],
          lastVerifiedAt: [
            { required: true, message: '上次验证成功时间不能为空', trigger: 'blur' }
          ],
          createdAt: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updatedAt: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/article//article/userPlatformAccounts/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.userPlatformAccounts.userId
                this.dataForm.platformId = data.userPlatformAccounts.platformId
                this.dataForm.accountNickname = data.userPlatformAccounts.accountNickname
                this.dataForm.platformUsername = data.userPlatformAccounts.platformUsername
                this.dataForm.credentials = data.userPlatformAccounts.credentials
                this.dataForm.status = data.userPlatformAccounts.status
                this.dataForm.lastVerifiedAt = data.userPlatformAccounts.lastVerifiedAt
                this.dataForm.createdAt = data.userPlatformAccounts.createdAt
                this.dataForm.updatedAt = data.userPlatformAccounts.updatedAt
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/article//article/userPlatformAccounts/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,

                'platformId': this.dataForm.platformId,

                'accountNickname': this.dataForm.accountNickname,

                'platformUsername': this.dataForm.platformUsername,

                'credentials': this.dataForm.credentials,

                'status': this.dataForm.status,

                'lastVerifiedAt': this.dataForm.lastVerifiedAt,

                'createdAt': this.dataForm.createdAt,

                'updatedAt': this.dataForm.updatedAt

              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
