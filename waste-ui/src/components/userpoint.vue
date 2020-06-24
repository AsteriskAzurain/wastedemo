<template>
  <div>
    <h1>查询积分明细</h1>
    <div style="width:100%">
      <el-table :data="detailtable" stripe style="width: 100%" max-height="1000">
        <el-table-column prop="id" label="明细id" width="100"></el-table-column>
        <el-table-column prop="num" label="积分数" width="180"></el-table-column>
        <el-table-column prop="source" label="积分来源" width="300"></el-table-column>
        <el-table-column prop="createtime" label="创建时间" sortable></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    const cuser = this.$cookies.get("currentuser");
    return {
      userid: cuser.id,
      detailtable: []
    };
  },
  methods: {
    async loaddetail() {
      const { data: res } = await this.$http.post(
        "http://localhost:8001/user/point/findbyuser",
        {
          id: this.userid
        }
      );
      if (res.code == 200) {
        let array = Array.from(res.data);
        array.forEach(element => {
          let date = new Date(element.createtime);
          let year = date.getFullYear(); //年
          let month = date.getMonth() + 1; //月
          let day = date.getDate(); //日
          let hours = date.getHours(); //时
          let min = date.getMinutes(); //分
          let second = date.getSeconds(); //秒
          let datestr =
            year +
            "年" +
            month +
            "月" +
            day +
            "日 " +
            hours +
            "时" +
            min +
            "分";
          element.createtime = datestr;
        });
        this.detailtable = array;
      }
    }
  },
  created() {
    this.loaddetail();
  }
};
</script>

<style>
div {
  text-align: left;
}
</style>