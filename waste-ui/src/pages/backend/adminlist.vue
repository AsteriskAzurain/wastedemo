<template>
  <div>
    <el-table :data="adminlist" style="width: 100%">
      <el-table-column prop="id" label="id"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="role" label="角色" :formatter="formatRole"></el-table-column>
      <el-table-column prop="tel" label="电话" :formatter="formatTel"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="delFlag" label="是否删除" :formatter="formatDel"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
    created(){
        this.$http.post("http://localhost:8001/admin/findAll").then(res=>{
            this.adminlist=res.data.data;
            console.log(res.data.data)
        })
    },
  data() {
    return {
      adminlist: [
        {}
      ]
    };
  },
  methods: {
    formatRole: function(row, column) {
      return row.role === 1? "管理员": row.role === 0? "超管":"志愿者";
    },
    formatDel: function(row,column){
        return row.delFlag===1?'未删除':'已删除';
    },
    formatTel: function(row,column){
        return row.tel==null?'空':row.tel;
    }
  }
};
</script>

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>