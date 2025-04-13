<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
            <el-form-item label="文章ID" prop="articleId">
      <el-input v-model="dataForm.articleId" placeholder="文章ID"></el-input>
    </el-form-item>
    <el-form-item label="目标发布平台ID" prop="platformId">
      <el-input v-model="dataForm.platformId" placeholder="目标发布平台ID"></el-input>
    </el-form-item>
    <el-form-item label="用于发布的平台账号ID" prop="userPlatformAccountId">
      <el-input v-model="dataForm.userPlatformAccountId" placeholder="用于发布的平台账号ID"></el-input>
    </el-form-item>
    <el-form-item label="发布状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="发布状态"></el-input>
    </el-form-item>
    <el-form-item label="文章在目标平台的唯一ID (如果发布成功)" prop="platformArticleId">
      <el-input v-model="dataForm.platformArticleId" placeholder="文章在目标平台的唯一ID (如果发布成功)"></el-input>
    </el-form-item>
    <el-form-item label="文章在目标平台的访问URL (如果发布成功)" prop="platformArticleUrl">
      <el-input v-model="dataForm.platformArticleUrl" placeholder="文章在目标平台的访问URL (如果发布成功)"></el-input>
    </el-form-item>
    <el-form-item label="发布/更新失败时的错误信息" prop="errorMessage">
      <el-input v-model="dataForm.errorMessage" placeholder="发布/更新失败时的错误信息"></el-input>
    </el-form-item>
    <el-form-item label="上次尝试发布/更新的时间" prop="lastAttemptAt">
      <el-input v-model="dataForm.lastAttemptAt" placeholder="上次尝试发布/更新的时间"></el-input>
    </el-form-item>
    <el-form-item label="成功发布到此平台的时间" prop="publishedAt">
      <el-input v-model="dataForm.publishedAt" placeholder="成功发布到此平台的时间"></el-input>
    </el-form-item>
    <el-form-item label="最后一次成功同步/发布/更新到此平台的时间" prop="lastSuccessSyncAt">
      <el-input v-model="dataForm.lastSuccessSyncAt" placeholder="最后一次成功同步/发布/更新到此平台的时间"></el-input>
    </el-form-item>
    <el-form-item label="记录创建时间" prop="createdAt">
      <el-input v-model="dataForm.createdAt" placeholder="记录创建时间"></el-input>
    </el-form-item>
    <el-form-item label="记录更新时间" prop="updatedAt">
      <el-input v-model="dataForm.updatedAt" placeholder="记录更新时间"></el-input>
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
          articleId: '',
          platformId: '',
          userPlatformAccountId: '',
          status: '',
          platformArticleId: '',
          platformArticleUrl: '',
          errorMessage: '',
          lastAttemptAt: '',
          publishedAt: '',
          lastSuccessSyncAt: '',
          createdAt: '',
          updatedAt: ''
        },
        dataRule: {
          articleId: [
            { required: true, message: '文章ID不能为空', trigger: 'blur' }
          ],
          platformId: [
            { required: true, message: '目标发布平台ID不能为空', trigger: 'blur' }
          ],
          userPlatformAccountId: [
            { required: true, message: '用于发布的平台账号ID不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '发布状态不能为空', trigger: 'blur' }
          ],
          platformArticleId: [
            { required: true, message: '文章在目标平台的唯一ID (如果发布成功)不能为空', trigger: 'blur' }
          ],
          platformArticleUrl: [
            { required: true, message: '文章在目标平台的访问URL (如果发布成功)不能为空', trigger: 'blur' }
          ],
          errorMessage: [
            { required: true, message: '发布/更新失败时的错误信息不能为空', trigger: 'blur' }
          ],
          lastAttemptAt: [
            { required: true, message: '上次尝试发布/更新的时间不能为空', trigger: 'blur' }
          ],
          publishedAt: [
            { required: true, message: '成功发布到此平台的时间不能为空', trigger: 'blur' }
          ],
          lastSuccessSyncAt: [
            { required: true, message: '最后一次成功同步/发布/更新到此平台的时间不能为空', trigger: 'blur' }
          ],
          createdAt: [
            { required: true, message: '记录创建时间不能为空', trigger: 'blur' }
          ],
          updatedAt: [
            { required: true, message: '记录更新时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/article//article/articlePublications/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.articleId = data.articlePublications.articleId
                this.dataForm.platformId = data.articlePublications.platformId
                this.dataForm.userPlatformAccountId = data.articlePublications.userPlatformAccountId
                this.dataForm.status = data.articlePublications.status
                this.dataForm.platformArticleId = data.articlePublications.platformArticleId
                this.dataForm.platformArticleUrl = data.articlePublications.platformArticleUrl
                this.dataForm.errorMessage = data.articlePublications.errorMessage
                this.dataForm.lastAttemptAt = data.articlePublications.lastAttemptAt
                this.dataForm.publishedAt = data.articlePublications.publishedAt
                this.dataForm.lastSuccessSyncAt = data.articlePublications.lastSuccessSyncAt
                this.dataForm.createdAt = data.articlePublications.createdAt
                this.dataForm.updatedAt = data.articlePublications.updatedAt
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
              url: this.$http.adornUrl(`/article//article/articlePublications/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'articleId': this.dataForm.articleId,

                'platformId': this.dataForm.platformId,

                'userPlatformAccountId': this.dataForm.userPlatformAccountId,

                'status': this.dataForm.status,

                'platformArticleId': this.dataForm.platformArticleId,

                'platformArticleUrl': this.dataForm.platformArticleUrl,

                'errorMessage': this.dataForm.errorMessage,

                'lastAttemptAt': this.dataForm.lastAttemptAt,

                'publishedAt': this.dataForm.publishedAt,

                'lastSuccessSyncAt': this.dataForm.lastSuccessSyncAt,

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
