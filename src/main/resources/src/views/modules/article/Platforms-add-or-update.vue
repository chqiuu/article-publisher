<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
            <el-form-item label="平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github)" prop="platformKey">
      <el-input v-model="dataForm.platformKey" placeholder="平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github)"></el-input>
    </el-form-item>
    <el-form-item label="平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub)" prop="name">
      <el-input v-model="dataForm.name" placeholder="平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub)"></el-input>
    </el-form-item>
    <el-form-item label="平台类型: publish-仅发布, import-仅导入, both-发布和导入" prop="type">
      <el-input v-model="dataForm.type" placeholder="平台类型: publish-仅发布, import-仅导入, both-发布和导入"></el-input>
    </el-form-item>
    <el-form-item label="平台图标URL (可选)" prop="iconUrl">
      <el-input v-model="dataForm.iconUrl" placeholder="平台图标URL (可选)"></el-input>
    </el-form-item>
    <el-form-item label="平台描述 (可选)" prop="description">
      <el-input v-model="dataForm.description" placeholder="平台描述 (可选)"></el-input>
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
          platformKey: '',
          name: '',
          type: '',
          iconUrl: '',
          description: '',
          createdAt: '',
          updatedAt: ''
        },
        dataRule: {
          platformKey: [
            { required: true, message: '平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github)不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub)不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '平台类型: publish-仅发布, import-仅导入, both-发布和导入不能为空', trigger: 'blur' }
          ],
          iconUrl: [
            { required: true, message: '平台图标URL (可选)不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '平台描述 (可选)不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/article//article/platforms/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.platformKey = data.platforms.platformKey
                this.dataForm.name = data.platforms.name
                this.dataForm.type = data.platforms.type
                this.dataForm.iconUrl = data.platforms.iconUrl
                this.dataForm.description = data.platforms.description
                this.dataForm.createdAt = data.platforms.createdAt
                this.dataForm.updatedAt = data.platforms.updatedAt
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
              url: this.$http.adornUrl(`/article//article/platforms/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'platformKey': this.dataForm.platformKey,

                'name': this.dataForm.name,

                'type': this.dataForm.type,

                'iconUrl': this.dataForm.iconUrl,

                'description': this.dataForm.description,

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
