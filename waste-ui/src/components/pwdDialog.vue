<template>
  <div>
    <el-dialog
      title="修改密码"
      :visible.sync="pwdVisible"
      @close="$emit('update:pwdShow', false)"
      :show="pwdShow"
      center
    >
      <el-form
        :model="pwdform"
        ref="pwdform"
        :rules="rules"
        label-position="right"
        label-width="100px"
      >
        <el-form-item label="旧密码" prop="oldpswd">
          <el-input placeholder="请确认旧密码" v-model="pwdform.oldpswd" show-password></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pswd1">
          <el-input placeholder="请输入密码" v-model="pwdform.pswd1" show-password></el-input>
        </el-form-item>
        <el-form-item label="重复密码" prop="pswd2">
          <el-input placeholder="请再次输入密码" v-model="pwdform.pswd2" show-password></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sendUpdReq">确认修改</el-button>
        <el-button @click="pwdVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "updatepwd",
  data() {
    var userid = this.$cookies.get("currentuser").id;
    var checkoldpass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入旧密码"));
      } else {
        let old = this.$cookies.get("currentuser").password;
        if (value != old) {
          callback(new Error("旧密码有误，请重新输入"));
        }
        callback();
      }
      callback();
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.pwdform.pswd2 !== "") {
          this.$refs.pwdform.validateField("pswd2");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.pwdform.pswd1) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      pwdVisible: false,
      pwdform: {
        id: userid,
        oldpswd: "",
        pswd1: "",
        pswd2: ""
      },
      rules: {
        oldpswd: [
          {
            required: true,
            validator: checkoldpass,
            trigger: "blur"
          }
        ],
        pswd1: [
          {
            required: true,
            validator: validatePass,
            trigger: "blur"
          }
        ],
        pswd2: [
          {
            required: true,
            validator: validatePass2,
            trigger: "blur"
          }
        ]
      }
    };
  },
  props: {
    pwdShow: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    pwdShow() {
      this.pwdVisible = this.pwdShow;
    }
  },
  methods: {
    sendUpdReq() {
      if (this.pwdform.id > 0) {
        let formName = "pwdform";
        this.$refs[formName].validate(async valid => {
          if (valid) {
            const { data: res } = await this.$http.post(
              "http://localhost:8001/user/password",
              this.pwdform
            );
            if (res.code == 200) {
              this.$message({
                message: "密码更新成功",
                type: "success"
              });
              this.$cookies.set("currentuser", res.data);
              this.pwdVisible = false;
            } else {
              this.$message({
                message: res.msg,
                type: "error"
              });
            }
          } else {
            this.$message({
              message: "修改失败",
              type: "error"
            });
            return false;
          }
        });
      } else {
        console.log(this.pwdform.id);
      }
    }
  },
  created() {}
};
</script>

<style scoped>
.el-form {
  /* text-align: center; */
  width: 100%;
}
</style>
