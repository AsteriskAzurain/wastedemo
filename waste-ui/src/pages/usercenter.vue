<template>
  <div class="father">
    <div>
      <div id="infodiv">
        <el-avatar shape="square" size="large" :src="squareUrl"></el-avatar>
        <div id="otherinfo">
          <h2>{{userinfo.name}} 的个人中心</h2>
          <h4>积分数：{{userinfo.point}}分</h4>
          <el-button type="primary" plain round size="mini">
            <!-- <el-link href="https://www.baidu.com" target="_blank">积分详情</el-link> -->
            <router-link to="/user/point">积分详情</router-link>
            </el-button>
          <el-button type="danger" plain round size="mini" @click="pwdShow=true">修改密码</el-button>
        </div>
      </div>

      <el-divider></el-divider>

      <el-form ref="userinfo" :model="userinfo" label-width="100px">
        <el-form-item label="用户id" prop="id">
          <el-input v-model="userinfo.id" :disabled="true"></el-input>
        </el-form-item>

        <el-form-item label="用户名" prop="name">
          <el-input v-model="userinfo.name" :disabled="true"></el-input>
        </el-form-item>

        <el-form-item label="角色权限" prop="role">
          <el-select placeholder="请选择注册角色" v-model="userinfo.role">
            <el-option label="普通业主" :value="1"></el-option>
            <el-option label="志愿者" :value="2"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="真实姓名" prop="realname">
          <el-input placeholder="请输入真实姓名" v-model="userinfo.realname"></el-input>
        </el-form-item>

        <el-form-item label="联系方式" prop="tel">
          <el-input type="tel" placeholder="请输入联系方法（电话/手机）" v-model.number="userinfo.tel"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('userinfo')">确认修改</el-button>
          <el-button @click="resetForm('userinfo')">重置</el-button>
        </el-form-item>
      </el-form>
      <updatepwd :pwd-show.sync="pwdShow"></updatepwd>
    </div>
  </div>
</template>

<script>
import updatepwd from "@/components/pwdDialog";

export default {
  data() {
    return {
      squareUrl:
        "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
      userinfo: {
        name:"null"
      },
      pwdShow: false
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async valid => {
        if (valid) {
          const { data: res } = await this.$http.post(
            "http://localhost:8001//user/update",
            this.userinfo
          );
          if (res.code == 200) {
            this.$message({
              message: "更新成功",
              type: "success"
            });
            this.userinfo = res.data;
            this.$cookies.set("currentuser", res.data);
          } else {
            this.$message({
              message: res.msg,
              type: "error"
            });
          }
        } else {
          this.$message({
            message: "更新失败",
            type: "error"
          });
          return false;
        }
      });
    },
    onSubmit() {
      console.log(this.userinfo);
    },
    resetForm(formName) {
      console.log("reset?");
      this.$refs[formName].resetFields();
    },
    loaduserdata() {
      this.userinfo = this.$cookies.get("currentuser");
      // console.log("hey i got a cookie:", this.userinfo);
      if (this.userinfo == null) {
      this.$message({
        message: "您尚未登录，请登陆后重试",
        type: "error"
      });
      return false;
    }
      let id = this.userinfo.id;
      this.$http
        .post("http://localhost:8001/user/loaduserinfo", { id: id })
        .then(res => {
          this.userinfo = res.data.data;
          this.$cookies.set("currentuser", res.data.data);
          console.log("current pswd:",res.data.data.password)
        });
    },
    opendialog(msg) {
      console.log("msg from child:", msg);
    }
  },
  created() {
    this.loaduserdata();
  },
  components: {
    updatepwd
  }
};
</script>
<style>
.father {
  margin: 0 10%;
  text-align: left;
  /* background-color: cornflowerblue; */
}

#infodiv {
  width: 100%;
  display: inline-block;
}

#infodiv .el-avatar {
  /* vertical-align: middle; */
  margin-top: 20px;
}

#otherinfo {
  margin-left: 20px;
  vertical-align: top;
  display: inline-block;
}

#otherinfo h4 {
  /* margin-left: 10%; */
  vertical-align: middle;
  display: inline-block;
}

#otherinfo .el-button {
  margin-left: 20px;
}

.el-form {
  width: 70%;
  text-align: left;
}

.el-select {
  text-align: left;
}
</style>