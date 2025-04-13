<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
            <el-form-item label="作者的用户ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="作者的用户ID"></el-input>
    </el-form-item>
    <el-form-item label="文章标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="文章标题"></el-input>
    </el-form-item>
    <el-form-item label="文章的唯一标识符/别名 (用于URL，可选)" prop="slug">
      <el-input v-model="dataForm.slug" placeholder="文章的唯一标识符/别名 (用于URL，可选)"></el-input>
    </el-form-item>
    <el-form-item label="Markdown 格式的文章内容" prop="contentMd">
      <el-input v-model="dataForm.contentMd" placeholder="Markdown 格式的文章内容"></el-input>
    </el-form-item>
    <el-form-item label="封面图片URL" prop="coverImageUrl">
      <el-input v-model="dataForm.coverImageUrl" placeholder="封面图片URL"></el-input>
    </el-form-item>
    <el-form-item label="文章标签 (逗号分隔或其他格式)" prop="tags">
      <el-input v-model="dataForm.tags" placeholder="文章标签 (逗号分隔或其他格式)"></el-input>
    </el-form-item>
    <el-form-item label="文章在系统内的状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="文章在系统内的状态"></el-input>
    </el-form-item>
    <el-form-item label="是否原创" prop="isOriginal">
      <el-input v-model="dataForm.isOriginal" placeholder="是否原创"></el-input>
    </el-form-item>
    <el-form-item label="导入来源平台ID (如果文章是导入的)" prop="sourcePlatformId">
      <el-input v-model="dataForm.sourcePlatformId" placeholder="导入来源平台ID (如果文章是导入的)"></el-input>
    </el-form-item>
    <el-form-item label="导入来源的原始URL (如果文章是导入的)" prop="sourceUrl">
      <el-input v-model="dataForm.sourceUrl" placeholder="导入来源的原始URL (如果文章是导入的)"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createdAt">
      <el-input v-model="dataForm.createdAt" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="最后更新时间" prop="updatedAt">
      <el-input v-model="dataForm.updatedAt" placeholder="最后更新时间"></el-input>
    </el-form-item>
    <el-form-item label="首次发布时间 (任意平台)" prop="publishedAt">
      <el-input v-model="dataForm.publishedAt" placeholder="首次发布时间 (任意平台)"></el-input>
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
          title: '',
          slug: '',
          contentMd: '',
          coverImageUrl: '',
          tags: '',
          status: '',
          isOriginal: '',
          sourcePlatformId: '',
          sourceUrl: '',
          createdAt: '',
          updatedAt: '',
          publishedAt: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '作者的用户ID不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '文章标题不能为空', trigger: 'blur' }
          ],
          slug: [
            { required: true, message: '文章的唯一标识符/别名 (用于URL，可选)不能为空', trigger: 'blur' }
          ],
          contentMd: [
            { required: true, message: 'Markdown 格式的文章内容不能为空', trigger: 'blur' }
          ],
          coverImageUrl: [
            { required: true, message: '封面图片URL不能为空', trigger: 'blur' }
          ],
          tags: [
            { required: true, message: '文章标签 (逗号分隔或其他格式)不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '文章在系统内的状态不能为空', trigger: 'blur' }
          ],
          isOriginal: [
            { required: true, message: '是否原创不能为空', trigger: 'blur' }
          ],
          sourcePlatformId: [
            { required: true, message: '导入来源平台ID (如果文章是导入的)不能为空', trigger: 'blur' }
          ],
          sourceUrl: [
            { required: true, message: '导入来源的原始URL (如果文章是导入的)不能为空', trigger: 'blur' }
          ],
          createdAt: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updatedAt: [
            { required: true, message: '最后更新时间不能为空', trigger: 'blur' }
          ],
          publishedAt: [
            { required: true, message: '首次发布时间 (任意平台)不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/article//article/articles/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.articles.userId
                this.dataForm.title = data.articles.title
                this.dataForm.slug = data.articles.slug
                this.dataForm.contentMd = data.articles.contentMd
                this.dataForm.coverImageUrl = data.articles.coverImageUrl
                this.dataForm.tags = data.articles.tags
                this.dataForm.status = data.articles.status
                this.dataForm.isOriginal = data.articles.isOriginal
                this.dataForm.sourcePlatformId = data.articles.sourcePlatformId
                this.dataForm.sourceUrl = data.articles.sourceUrl
                this.dataForm.createdAt = data.articles.createdAt
                this.dataForm.updatedAt = data.articles.updatedAt
                this.dataForm.publishedAt = data.articles.publishedAt
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
              url: this.$http.adornUrl(`/article//article/articles/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,

                'title': this.dataForm.title,

                'slug': this.dataForm.slug,

                'contentMd': this.dataForm.contentMd,

                'coverImageUrl': this.dataForm.coverImageUrl,

                'tags': this.dataForm.tags,

                'status': this.dataForm.status,

                'isOriginal': this.dataForm.isOriginal,

                'sourcePlatformId': this.dataForm.sourcePlatformId,

                'sourceUrl': this.dataForm.sourceUrl,

                'createdAt': this.dataForm.createdAt,

                'updatedAt': this.dataForm.updatedAt,

                'publishedAt': this.dataForm.publishedAt

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
