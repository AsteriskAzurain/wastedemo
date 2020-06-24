<template>
  <div style="text-align:left;">
    <el-dialog
      title="注册"
      :visible.sync="regVisible"
      @close="$emit('update:regShow', false)"
      :show="regShow"
    >
      <el-form
        :model="newUserForm"
        label-position="top"
        :inline="false"
        ref="newUserForm"
        :rules="rules"
        status-icon
        class="demo-ruleForm"
        :inline-message="true"
      >
        <el-form-item label="用户名" prop="username">
          <el-input placeholder="请输入用户名" v-model="newUserForm.username"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input placeholder="请输入密码" show-password v-model="newUserForm.password"></el-input>
        </el-form-item>

        <el-form-item label="重复密码" prop="pswd">
          <el-input placeholder="请再次输入密码" show-password v-model="newUserForm.pswd"></el-input>
        </el-form-item>

        <el-form-item label="角色权限" prop="role">
          <el-select placeholder="请选择注册角色" v-model="newUserForm.role">
            <el-option label="普通业主" value="1"></el-option>
            <el-option label="志愿者" value="2"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="真实姓名" prop="realname">
          <el-input placeholder="请输入真实姓名" v-model="newUserForm.realname"></el-input>
        </el-form-item>

        <el-form-item label="联系方式" prop="tel">
          <el-input type="tel" placeholder="请输入联系方法（电话/手机）" v-model.number="newUserForm.tel"></el-input>
        </el-form-item>

        <!-- <el-form-item label="电子邮件" prop="email">
          <el-input type="email" placeholder="请输入联系方式（电子邮件）" v-model="newUserForm.email"></el-input>
        </el-form-item>-->

        <el-form-item>
          <el-button type="primary" @click="submitForm('newUserForm')">注册</el-button>
          <el-button @click="resetForm('newUserForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "regdialog",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        console.log(value);
        callback(new Error("请输入密码"));
      } else {
        if (this.newUserForm.pswd !== "") {
          this.$refs.newUserForm.validateField("pswd");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.newUserForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    var checkTel = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("联系方式不能为空"));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          callback();
        }
      }, 1000);
    };
    return {
      regVisible: false,
      formLabelWidth: "120px",
      newUserForm: {
        username: "",
        realname: "",
        password: "",
        pswd: "",
        role: "",
        tel: null
        // email: ""
      },
      rules: {
        username: [
          { required: true, message: "请填写活动形式", trigger: "blur" }
        ],
        password: [
          {
            required: true,
            validator: validatePass,
            trigger: "blur"
          }
        ],
        pswd: [
          {
            required: true,
            validator: validatePass2,
            trigger: "blur"
          }
        ],
        role: [
          {
            required: true,
            trigger: "blur"
          }
        ],
        tel: [
          {
            required: true,
            validator: checkTel,
            trigger: "blur"
          }
        ]
        // email: [
        //   { required: true, message: "请输入邮箱地址", trigger: "blur" },
        //   {
        //     type: "email",
        //     message: "请输入正确的邮箱地址",
        //     trigger: ["blur", "change"]
        //   }
        // ]
      }
    };
  },
  props: {
    regShow: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    regShow() {
      this.regVisible = this.regShow;
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async valid => {
        if (valid) {
          console.log(this.newUserForm);
          // http://localhost:8001/user/insert
          // this.$http.post("http://localhost:8001/user/insert", this.newUserForm).then(res => {
          //     console.log(res)
          //   });
          const { data: res } = await this.$http.post(
            "http://localhost:8001/user/insert",
            this.newUserForm
          );
          if (res.code == 200) {
            this.$message({
              message: "注册成功",
              type: "success"
            });
            this.regVisible = false;
          } else {
            this.$message({
              message: res.data.msg,
              type: "error"
            });
          }
        } else {
          this.$message({
            message: "注册失败",
            type: "error"
          });
          return false;
        }
        
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {}
};
</script>

<style>
</style>