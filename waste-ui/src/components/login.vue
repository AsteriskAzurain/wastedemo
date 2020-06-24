<template>
  <div>
    <el-dialog
      title="登录"
      :visible.sync="loginVisible"
      @close="$emit('update:loginShow', false)"
      :show="loginShow" center
    >
      <el-form :model="form">          
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input placeholder="请输入用户名" v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input placeholder="请输入密码" v-model="form.passwd" show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="loginAction">登 录</el-button>
        <el-button @click="loginVisible = false">取 消</el-button>        
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "logindialog",
  data() {
    return {
      loginVisible: false,
      form: {
        name: "",
        passwd: ""
      },
      formLabelWidth: "120px"
    };
  },
  props: {
    loginShow: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    loginShow() {
      this.loginVisible = this.loginShow;
    }
  },
  methods:{
    loginAction(){
      // console.log(this.form)
      this.$http.post("http://localhost:8001/user/login",this.form).then(res=>{
        // res code: res.status
        // res data: res.data{code:200, msg:null, data:{id, name, password, realname, tel, role, point, delFlag} }
        window.sessionStorage.setItem("isLogin",false)
        if(res.data.code==200){
          this.$message({
            message: "登录成功",
            type:'success'
          })
          const userinfo = res.data.data;
          window.sessionStorage.setItem("userid",userinfo.id)
          window.sessionStorage.setItem("username",userinfo.name)
          window.sessionStorage.setItem("isLogin",true)
          this.$emit('say',res.data.data)

          this.$cookies.set("currentuser", res.data.data) 
          // console.log("hey i got a cookie:",this.$cookies.get("currentuser").id)

        }else{
          this.$message({
            message: res.data.msg,
            type:'error'
          })
        }
      })
      this.loginVisible = false;
    }
  }
};
</script>

<style>
</style>