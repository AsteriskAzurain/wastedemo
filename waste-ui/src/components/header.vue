<template>
  <div>
    <el-row :gutter="20" type="flex" justify="space-between">
      <el-col :span="6">
        <div>
          <el-image
            style="width: auto; height: 140px; float:left;"
            :src="require('@/assets/logo.png')"
            fit="contain"
          ></el-image>
        </div>
      </el-col>
      <el-col :span="18">
        <el-row>
          <el-col :span="14" class="headerdiv">
            <div>
              <el-input placeholder="请输入内容" v-model="keyword" class="input-with-select">
                <el-button slot="append" icon="el-icon-search"></el-button>
              </el-input>
            </div>
          </el-col>
          <el-col :span="10" class="headerdiv">
            <div>
              <div v-if="isLogin" class="userinfo">
                <el-avatar
                  size="medium"
                  fit="scale-down"
                  shape="circle"
                  class="sub"
                  :src="squareUrl"
                  :alt="userInfo.username"
                ></el-avatar>
                <span class="sub">欢迎您，{{userInfo.name}}</span>
                <el-button type="primary" plain @click="logout" class="sub">注销</el-button>
              </div>
              <div v-else class="userinfo">
                <el-button type="primary" @click="loginShow = true">登录</el-button>
                <el-button type="primary" plain @click="toregister">注册</el-button>
              </div>
            </div>
          </el-col>
        </el-row>

        <el-row>
          <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            :router="true"
          >
            <el-menu-item index="1" route="/">主页</el-menu-item>
            <el-menu-item index="2" route="/noticeboard">公告栏</el-menu-item>
            <el-menu-item index="3" route="/knowledge">垃圾分类知识</el-menu-item>
            <el-menu-item index="4" route="/order">社区垃圾回收</el-menu-item>
            <el-menu-item index="5" route="/data">数据分析</el-menu-item>
            <el-menu-item index="6" route="/user">个人中心</el-menu-item>
          </el-menu>
        </el-row>
      </el-col>
    </el-row>

    <login-dialog :login-show.sync="loginShow" @say="loaduserinfo"></login-dialog>
    <logout-dialog :out-show.sync="outShow" @say="checklogout"></logout-dialog>
    <register-dialog :reg-show.sync="regShow"></register-dialog>
  </div>
</template>

<script>
import LoginDialog from "@/components/login";
import RegisterDialog from "@/components/register";
import LogoutDialog from "@/components/logout";

export default {
  name: "myHeader",
  data() {
    let routerdict = {
      "/": 1,
      "/noticeboard": 2,
      "/knowledge": 3,
      "/order": 4,
      "/data": 5,
      "/user": 6
    };
    return {
      squareUrl:
        "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
      isLogin: false,
      keyword: "",
      activeIndex: "1", //this.$route.path 默认激活为当前route path
      loginShow: false,
      outShow: false,
      regShow: false,
      userInfo: {},
      routerurl: {
        "/": "1",
        "/noticeboard": "2",
        "/knowledge": "3",
        "/order": "4",
        "/data": "5",
        "/user": "6"
      }
    };
  },
  methods: {
    tologin() {
      this.loginShow = true;
    },
    logout() {
      this.outShow = true;
      // this.isLogin = !this.isLogin;
    },
    toregister() {
      this.regShow = true;
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.activeIndex = key;
      this.$emit("on-callback", key);
    },
    loaduserinfo(userinfo) {
      // userinfo: 登陆后子控件传过来的对象
      this.userInfo = userinfo;
      console.log("after login", this.userInfo);
      this.isLogin = !this.isLogin;
    },
    checklogout(msg){
      console.log("is Logout?",msg)
      if(msg){
        this.isLogin=false
      }
    }
  },
  components: {
    LoginDialog,
    RegisterDialog,
    LogoutDialog
  },
  watch: {
    // outShow() {
    //   this.logoutVisible = this.outShow;
    // }
  },
  computed: {},
  created: function() {
    // ↓ 存在session storage里的 login状态
    let loginFlag = sessionStorage.getItem("isLogin");
    console.log("is Login?", loginFlag);
    this.isLogin = loginFlag;
    const cuser = this.$cookies.get("currentuser");
    if (cuser == null) {
      this.isLogin = false;
      this.$message({
        message: "您尚未登录，请登陆。",
        type: "error"
      });
    } else {
      this.isLogin = true;
      this.userInfo.name = cuser.name;
    }

    console.log(this.$route.path);
    // let path=this.$route.path
    let path = window.location.pathname.slice(6);
    this.activeIndex = this.routerurl[path];
  }
};
</script>
<style scoped>
/* mystyle */
.headerdiv {
  margin-top: 20px;
}

.userinfo {
  display: flex;
  align-items: center;
  justify-content: center;
}

.userinfo .sub {
  margin: 10px;
}
</style>